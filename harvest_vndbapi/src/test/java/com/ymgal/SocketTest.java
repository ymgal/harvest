package com.ymgal;

import com.ymgal.filter.VndbFilters;
import com.ymgal.helper.TcpHelper;
import com.ymgal.model.Character.Character;
import com.ymgal.model.Producer.Producer;
import com.ymgal.model.Release.Release;
import com.ymgal.model.Staff.Staff;
import com.ymgal.model.User.User;
import com.ymgal.model.VisualNovel.VisualNovel;
import com.ymgal.model.VndbResponse;
import org.junit.jupiter.api.Test;

/**
 * @Auther: lyl
 * @Date: 2023/10/23 20:39
 * @Description:
 */
public class SocketTest {


    @Test
    public void tcpTest() {
        TcpHelper.Login();
        VndbResponse<VisualNovel> visualNovelVndbResponse = VndbGetMethod.GetVisualNovel(VndbFilters.Id.Equals(17, 8).toString());
        VndbResponse<Release> ReleaseVndbResponse = VndbGetMethod.GetRelease(VndbFilters.VisualNovel.Equals(17).toString());
        VndbResponse<Producer> ProducerVndbResponse = VndbGetMethod.GetProducer(VndbFilters.Id.Equals(9).toString());
        VndbResponse<Character> CharacterVndbResponse = VndbGetMethod.GetCharacter(VndbFilters.VisualNovel.Equals(17).toString());
        VndbResponse<Staff> StaffVndbResponse = VndbGetMethod.GetStaff(VndbFilters.Id.Equals(2).toString());
        VndbResponse<User> UserVndbResponse = VndbGetMethod.GetUser(VndbFilters.Id.Equals(2).toString());
        TcpHelper.Loginout();
    }


    @Test
    public void FilterTest() {
        String s = VndbFilters.Id.Equals(17, 18).toString();
        System.out.println(s);
    }
}
