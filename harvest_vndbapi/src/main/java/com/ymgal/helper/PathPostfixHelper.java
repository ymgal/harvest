package com.ymgal.helper;

import com.ymgal.model.VndbResponse;
import com.ymgal.modelhttp.PathPostfix;
import com.ymgal.modelhttp.enums.Field;
import com.ymgal.modelhttp.vo.Producer;
import com.ymgal.modelhttp.vo.Release;
import com.ymgal.modelhttp.vo.Vn;

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
