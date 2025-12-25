package com.ymgal.harvest;

import com.ymgal.harvest.model.archive.OrgArchive;
import com.ymgal.harvest.model.archive.PersonArchive;
import com.ymgal.harvest.utils.VndbConverter;
import com.ymgal.harvest.vndb.VndbGetMethodByHttp;
import com.ymgal.harvest.vndb.helper.TcpHelper;
import com.ymgal.harvest.vndb.model.Release.Release;
import com.ymgal.harvest.vndb.model.Staff.Staff;
import com.ymgal.harvest.vndb.modelhttp.VndbFilter;
import com.ymgal.harvest.vndb.modelhttp.vo.Vn;
import com.ymgal.harvest.vndb.modelhttp.vo.common.Exlink;
import com.ymgal.harvest.vndb.VndbGetMethod;
import com.ymgal.harvest.vndb.filter.VndbFilters;
import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.model.archive.GameArchive;
import com.ymgal.harvest.vndb.model.Character.Character;
import com.ymgal.harvest.vndb.model.Producer.Producer;
import com.ymgal.harvest.vndb.model.VisualNovel.VisualNovel;
import com.ymgal.harvest.vndb.model.VndbResponse;
import com.ymgal.harvest.vndb.modelhttp.enums.FilterName;
import com.ymgal.harvest.vndb.modelhttp.enums.FilterOperator;

import java.net.InetSocketAddress;
import java.util.*;

public class VndbHarvest extends Harvest {

    private final String PREFIX = "https://vndb.org/v";

    private final TcpHelper tcpHelper = new TcpHelper();

    private final VndbGetMethod vndbGetMethod = new VndbGetMethod(tcpHelper);

    private final VndbConverter converter = new VndbConverter();

    public VndbHarvest(String gameUrl) {
        super(gameUrl);
    }

    @Override
    protected void validateUrl(String gameUrl) {
        if (gameUrl.startsWith(PREFIX)) return;
        throw new IllegalArgumentException("VNDB address parsing error: " + gameUrl);
    }


    @Override
    protected HarvestResult exec(String gameUrl, InetSocketAddress proxy) {

        Integer vnid = Integer.parseInt(gameUrl.split(PREFIX)[1]);
        tcpHelper.login();
        GameArchive gameAcrhive = getGameAcrhive(vnid);
        OrgArchive orgArchive = getOrgArchive(gameAcrhive.getDeveloper());
        List<PersonArchive> personArchiveList = getPersonArchiveList(gameAcrhive.getStaff().stream().map(x -> x.getSid()).toArray(x -> new Integer[x]));
        List<CharacterArchive> characterArchiveList = getCharacterArchiveList(vnid);

        HarvestResult harvestResult = HarvestResult.ok(
                gameAcrhive, orgArchive, personArchiveList, characterArchiveList
        );
        tcpHelper.logout();

        return harvestResult;
    }

    public GameArchive getGameAcrhive(Integer vnid) {

        // HTTP查询数据
        VndbFilter vndbFilter = new VndbFilter(FilterName.ID.getFilterName(), FilterOperator.EQ.getOperator(), vnid + "");
        VndbResponse<Vn> responseBody = VndbGetMethodByHttp.GetVisualNovel(vndbFilter);
        if (responseBody == null || responseBody.getItems() == null || responseBody.getItems().size() == 0) {
            return null;
        }

        Vn vn = responseBody.getItems().get(0);

        // 获取Links
        List<Exlink> linksByHtml = VndbGetMethodByHttp.getLinksByHtml(PREFIX + vnid);

        // 角色
        VndbResponse<Character> character_tcp = vndbGetMethod.GetCharacter(VndbFilters.VisualNovel.Equals(vnid).toString());
        List<Character> characters = (character_tcp != null && character_tcp.getItems() != null)
                ? character_tcp.getItems()
                : null;

        // 发售信息
        VndbResponse<Release> release_tcp = vndbGetMethod.GetRelease(VndbFilters.VisualNovel.Equals(vnid).toString());
        List<Release> releases = (release_tcp != null && release_tcp.getItems() != null)
                ? release_tcp.getItems()
                : null;

        // Staff
        VndbResponse<VisualNovel> visualNovelVndbResponse = vndbGetMethod.GetVisualNovel(VndbFilters.Id.Equals(vnid).toString());
        VisualNovel visualNovel = (visualNovelVndbResponse != null && visualNovelVndbResponse.getItems() != null)
                ? visualNovelVndbResponse.getItems().get(0)
                : null;

        return converter.toGameArchive(vn, characters, releases, visualNovel, linksByHtml);
    }

    public OrgArchive getOrgArchive(Integer orgid) {

        VndbResponse<Producer> ProducerVndbResponse = vndbGetMethod.GetProducer(VndbFilters.Id.Equals(orgid).toString());
        if (ProducerVndbResponse == null || ProducerVndbResponse.getItems() == null || ProducerVndbResponse.getItems().size() == 0) {
            return null;
        }

        Producer producer = ProducerVndbResponse.getItems().get(0);

        return converter.toOrgArchive(producer);
    }

    public List<PersonArchive> getPersonArchiveList(Integer[] staffIds) {
        VndbResponse<Staff> StaffVndbResponse = vndbGetMethod.GetStaff(VndbFilters.Id.Equals(staffIds).toString());
        if (StaffVndbResponse == null || StaffVndbResponse.getItems() == null || StaffVndbResponse.getItems().size() == 0) {
            return Collections.emptyList();
        }

        List<Staff> staffList = StaffVndbResponse.getItems();

        return converter.toPersonArchiveList(staffList);
    }

    public List<CharacterArchive> getCharacterArchiveList(Integer vnid) {
        VndbResponse<Character> CharacterVndbResponse = vndbGetMethod.GetCharacter(VndbFilters.VisualNovel.Equals(vnid).toString());
        if (CharacterVndbResponse == null || CharacterVndbResponse.getItems() == null || CharacterVndbResponse.getItems().size() == 0) {
            return Collections.emptyList();
        }

        List<Character> characterList = CharacterVndbResponse.getItems();

        return converter.toCharacterArchiveList(characterList);
    }

}