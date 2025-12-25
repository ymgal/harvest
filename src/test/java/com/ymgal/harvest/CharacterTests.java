package com.ymgal.harvest;

import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.utils.VndbConverter;
import com.ymgal.harvest.vndb.VndbGetMethod;
import com.ymgal.harvest.vndb.filter.VndbFilters;
import com.ymgal.harvest.vndb.helper.TcpHelper;
import com.ymgal.harvest.vndb.model.Character.Character;
import com.ymgal.harvest.vndb.model.VndbResponse;

public class CharacterTests {

    private final TcpHelper tcpHelper = new TcpHelper();

    private final VndbGetMethod vndbGetMethod = new VndbGetMethod(tcpHelper);

    public static void main(String[] args) {
        CharacterTests characterTests = new CharacterTests();

        int id = 100001;

        characterTests.tcpHelper.login();

        try {
            VndbResponse<Character> response = characterTests.vndbGetMethod.GetCharacter(VndbFilters.Id.Equals(id).toString());
            if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
                System.out.println("response is null or empty");
                return;
            }

            Character character = response.getItems().get(0);
            CharacterArchive characterArchive = new VndbConverter().toCharacterArchive(character);
            System.out.println(characterArchive);
        } finally {
            characterTests.tcpHelper.logout();
        }
    }
}
