package com.ymgal.harvest;

import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.model.archive.OrgArchive;
import com.ymgal.harvest.model.archive.PersonArchive;
import com.ymgal.harvest.utils.VndbConverter;
import com.ymgal.harvest.vndb.VndbGetMethod;
import com.ymgal.harvest.vndb.filter.VndbFilters;
import com.ymgal.harvest.vndb.helper.TcpHelper;
import com.ymgal.harvest.vndb.model.Producer.Producer;
import com.ymgal.harvest.vndb.model.Staff.Staff;
import com.ymgal.harvest.vndb.model.VndbResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;

/**
 * Vndb档案分类收集器
 */
public class VndbClassifiedHarvest {

    private final TcpHelper tcpHelper = new TcpHelper();

    private final VndbGetMethod vndbGetMethod = new VndbGetMethod(tcpHelper);

    private final VndbConverter converter = new VndbConverter();

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Character extends BaseHarvestResult implements HarvestMetadata {
        private CharacterArchive characterArchive;
    }

    public class CharacterHarvest extends Harvest<Character> {

        private static final String PREFIX = "https://vndb.org/c";

        public CharacterHarvest(String gameUrl) {
            super(gameUrl);
        }

        @Override
        protected void validateUrl(String gameUrl) {
            if (gameUrl.startsWith(PREFIX)) return;
            throw new IllegalArgumentException("VNDB address parsing error: " + gameUrl);
        }

        @Override
        protected Character exec(String gameUrl, InetSocketAddress proxy) throws Exception {
            Integer vnid = Integer.parseInt(gameUrl.split(PREFIX)[1]);

            tcpHelper.login();

            VndbResponse<com.ymgal.harvest.vndb.model.Character.Character> response = vndbGetMethod.GetCharacter(VndbFilters.Id.Equals(vnid).toString());
            if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
                return null;
            }

            com.ymgal.harvest.vndb.model.Character.Character character = response.getItems().get(0);
            CharacterArchive characterArchive = converter.toCharacterArchive(character);
            tcpHelper.logout();

            Character result = new Character();
            result.setCharacterArchive(characterArchive);
            return result;
        }

        @Override
        protected Character createErrorResult(LocalDateTime start, Exception e) {
            Character character = new Character();
            character.setError(e);
            character.setTaskStartTime(start);
            return character;
        }
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Org extends BaseHarvestResult implements HarvestMetadata {
        private OrgArchive orgArchive;
    }

    public class OrgHarvest extends Harvest<Org> {

        private static final String PREFIX = "https://vndb.org/p";

        public OrgHarvest(String gameUrl) {
            super(gameUrl);
        }

        @Override
        protected void validateUrl(String gameUrl) {
            if (gameUrl.startsWith(PREFIX)) return;
            throw new IllegalArgumentException("VNDB address parsing error: " + gameUrl);
        }

        @Override
        protected Org exec(String gameUrl, InetSocketAddress proxy) throws Exception {
            Integer vnid = Integer.parseInt(gameUrl.split(PREFIX)[1]);

            tcpHelper.login();
            VndbResponse<Producer> response = vndbGetMethod.GetProducer(VndbFilters.Id.Equals(vnid).toString());
            if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
                return null;
            }
            Producer producer = response.getItems().get(0);
            OrgArchive orgArchive = converter.toOrgArchive(producer);
            tcpHelper.logout();

            Org result = new Org();
            result.setOrgArchive(orgArchive);
            return result;
        }

        @Override
        protected Org createErrorResult(LocalDateTime start, Exception e) {
            Org entity = new Org();
            entity.setError(e);
            entity.setTaskStartTime(start);
            return entity;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Person extends BaseHarvestResult implements HarvestMetadata {
        private PersonArchive personArchive;
    }

    public class PersonHarvest extends Harvest<Person> {

        private static final String PREFIX = "https://vndb.org/s";

        public PersonHarvest(String gameUrl) {
            super(gameUrl);
        }

        @Override
        protected void validateUrl(String gameUrl) {
            if (gameUrl.startsWith(PREFIX)) return;
            throw new IllegalArgumentException("VNDB address parsing error: " + gameUrl);
        }

        @Override
        protected Person exec(String gameUrl, InetSocketAddress proxy) throws Exception {
            Integer vnid = Integer.parseInt(gameUrl.split(PREFIX)[1]);

            tcpHelper.login();
            VndbResponse<Staff> response = vndbGetMethod.GetStaff(VndbFilters.Id.Equals(vnid).toString());
            if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
                return null;
            }
            Staff staff = response.getItems().get(0);
            PersonArchive personArchive = converter.toPersonArchive(staff);
            tcpHelper.logout();

            Person result = new Person();
            result.setPersonArchive(personArchive);
            return result;
        }

        @Override
        protected Person createErrorResult(LocalDateTime start, Exception e) {
            Person entity = new Person();
            entity.setError(e);
            entity.setTaskStartTime(start);
            return entity;
        }
    }


}
