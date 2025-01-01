package com.ymgal.harvest.vndb;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ymgal.harvest.vndb.helper.JsonHelper;
import com.ymgal.harvest.vndb.helper.TcpHelper;
import com.ymgal.harvest.vndb.model.Release.Release;
import com.ymgal.harvest.vndb.model.Staff.Staff;
import com.ymgal.harvest.vndb.model.User.User;
import com.ymgal.harvest.vndb.model.VisualNovel.VisualNovel;
import com.ymgal.harvest.vndb.model.VndbFlags;
import com.ymgal.harvest.vndb.model.VndbFlagsConstant;
import com.ymgal.harvest.vndb.model.VndbResponse;
import com.ymgal.harvest.vndb.model.Character.Character;
import com.ymgal.harvest.vndb.model.Producer.Producer;

import java.util.stream.Collectors;

public class VndbGetMethod {

    private final TcpHelper tcpHelper;

    public VndbGetMethod(TcpHelper tcpHelper) {
        this.tcpHelper = tcpHelper;
    }


    public Integer getVndbFlag(String method) {
        Integer fullFlags = 0;
        switch (method) {
            case Constants.GetVisualNovelCommand:
                fullFlags = VndbFlagsConstant.FullVisualNovel;
                break;
            case Constants.GetReleaseCommand:
                fullFlags = VndbFlagsConstant.FullRelease;
                break;
            case Constants.GetProducerCommand:
                fullFlags = VndbFlagsConstant.FullProducer;
                break;
            case Constants.GetCharacterCommand:
                fullFlags = VndbFlagsConstant.FullCharacter;
                break;
            case Constants.GetUserCommand:
                fullFlags = VndbFlagsConstant.FullUser;
                break;
            case Constants.GetVotelistCommand:
                fullFlags = VndbFlagsConstant.FullVotelist;
                break;
            case Constants.GetVisualNovelListCommand:
                fullFlags = VndbFlagsConstant.FullVisualNovelList;
                break;
            case Constants.GetWishlistCommand:
                fullFlags = VndbFlagsConstant.FullWishlist;
                break;
            case Constants.GetStaffCommand:
                fullFlags = VndbFlagsConstant.FullStaff;
                break;
            case Constants.GetUserListCommand:
                fullFlags = VndbFlagsConstant.FullUserList;
                break;
            case Constants.GetUserListLabelsCommand:
                fullFlags = VndbFlagsConstant.FullUserListLabels;
                break;
            default:
                break;
        }
        return fullFlags;
    }

    public <T> T sendGetRequestInternalAsync(String cmd, TypeReference<T> typeReference) {
        tcpHelper.sendData(cmd);
        String response = tcpHelper.getResponse();

        String[] results = response.split(" ", 2);

        if (results.length == 2 &&
                (results[0].equals(Constants.Results) || results[0].equals(Constants.DbStats)))
            return JsonHelper.parse(results[1], typeReference);
        return null;
    }


    private String GetFullMethod(String method, String filters) {
        Integer vndbFlag = this.getVndbFlag(method);
        String fields = VndbFlags.getDescs(vndbFlag).stream().collect(Collectors.joining(",", " ", " "));
        if ((method.equals(Constants.GetStaffCommand) || method.equals(Constants.GetCharacterCommand)) &&
                fields.contains(VndbFlags.VisualNovels.getDesc())) {
            fields = fields.replace(VndbFlags.VisualNovels.getDesc(), VndbFlags.VisualNovels.getDesc() + "s");
        }

        String cmd = method + fields + "(" + filters + ")";
        System.out.println("CMD:" + cmd);
        return cmd;
    }

    public VndbResponse<VisualNovel> GetVisualNovel(String filters) {
        String cmd = GetFullMethod(Constants.GetVisualNovelCommand, filters);
        return sendGetRequestInternalAsync(cmd, new TypeReference<VndbResponse<VisualNovel>>() {
        });
    }

    public VndbResponse<Release> GetRelease(String filters) {
        String cmd = GetFullMethod(Constants.GetReleaseCommand, filters);
        return sendGetRequestInternalAsync(cmd, new TypeReference<VndbResponse<Release>>() {
        });
    }

    public VndbResponse<Producer> GetProducer(String filters) {
        String cmd = GetFullMethod(Constants.GetProducerCommand, filters);
        return sendGetRequestInternalAsync(cmd, new TypeReference<VndbResponse<Producer>>() {
        });
    }

    public VndbResponse<Character> GetCharacter(String filters) {
        String cmd = GetFullMethod(Constants.GetCharacterCommand, filters);
        return sendGetRequestInternalAsync(cmd, new TypeReference<VndbResponse<Character>>() {
        });
    }

    public VndbResponse<Staff> GetStaff(String filters) {
        String cmd = GetFullMethod(Constants.GetStaffCommand, filters);
        return sendGetRequestInternalAsync(cmd, new TypeReference<VndbResponse<Staff>>() {
        });
    }

    public VndbResponse<User> GetUser(String filters) {
        String cmd = GetFullMethod(Constants.GetUserCommand, filters);
        return sendGetRequestInternalAsync(cmd, new TypeReference<VndbResponse<User>>() {
        });
    }
}
