package com.ymgal.harvest.vndb.helper;

import com.ymgal.harvest.vndb.modelhttp.PathPostfix;
import com.ymgal.harvest.vndb.modelhttp.vo.Producer;
import com.ymgal.harvest.vndb.modelhttp.vo.Release;
import com.ymgal.harvest.vndb.modelhttp.vo.Vn;
import com.ymgal.harvest.vndb.model.VndbResponse;
import com.ymgal.harvest.vndb.modelhttp.enums.Field;

/**
 * @Auther: lyl
 * @Date: 2023/10/16 14:34
 * @Description:
 */
public class PathPostfixHelper {

    public static Class getVoClass(PathPostfix pathPostfix) {
        Class<?> cls = null;

        switch (pathPostfix) {
            case VN:
                cls = (new VndbResponse<Vn>()).getClass();
                break;
            case RELEASE:
                cls = (new VndbResponse<Release>()).getClass();
                break;
            case CHARACTER:
                cls = (new VndbResponse<Character>()).getClass();
                break;
            case PRODUCER:
                cls = (new VndbResponse<Producer>()).getClass();
                break;
            default:
                break;
        }
        return cls;
    }

    public static String getFields(PathPostfix pathPostfix) {
        String fields = "";

        switch (pathPostfix) {
            case VN:
                fields = Field.vn;
                break;
            case RELEASE:
                fields = Field.release;
                break;
            case CHARACTER:
                fields = Field.character;
                break;
            case PRODUCER:
                fields = Field.producer;
                break;
            default:
                fields = "id";
                break;
        }
        return fields;
    }

}
