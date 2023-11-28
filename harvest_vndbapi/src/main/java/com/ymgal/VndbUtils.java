package com.ymgal;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ymgal.helper.JsonHelper;
import com.ymgal.helper.TcpHelper;
import com.ymgal.model.VndbFlagsConstant;

/**
 * @Auther: lyl
 * @Date: 2023/10/23 20:21
 * @Description:
 */
public class VndbUtils {

    public static Integer getVndbFlag(String method) {
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

    public static <T> T SendGetRequestInternalAsync(String cmd, TypeReference<T> typeReference) {
        TcpHelper.sendData(cmd);
        String response = TcpHelper.getResponse();

        String[] results = response.split(" ", 2);

        if (results.length == 2 &&
                (results[0].equals(Constants.Results) || results[0].equals(Constants.DbStats)))
            return JsonHelper.parse(results[1], typeReference);
        return null;
    }

}
