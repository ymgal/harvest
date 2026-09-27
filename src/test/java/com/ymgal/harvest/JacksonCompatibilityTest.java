package com.ymgal.harvest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ymgal.harvest.vndb.helper.JsonHelper;
import com.ymgal.harvest.vndb.model.DatabaseStats;
import com.ymgal.harvest.vndb.model.VndbResponse;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/** 使用离线数据检查两代 Jackson 的注解兼容及原 JSON 工具协议。 */
class JacksonCompatibilityTest {

    @Test
    void jackson2AndJackson3ReadSharedModelAnnotations() {
        String json = """
                {"more":false,"num":1,"results":[{"vn":42,"chars":7}],"extra":"ignored"}
                """;
        VndbResponse<DatabaseStats> internal = JsonHelper.parse(json, new TypeReference<>() {});
        JsonMapper hostMapper = JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();
        VndbResponse<DatabaseStats> host = hostMapper.readValue(json,
                new tools.jackson.core.type.TypeReference<>() {});

        // results 别名和 vn/chars 字段名称必须被两代映射器同样识别。
        assertResponse(internal);
        assertResponse(host);
        assertEquals(42, hostMapper.readTree(hostMapper.writeValueAsString(internal))
                .get("items").get(0).get("vn").intValue());
    }

    @Test
    void internalHelperKeepsNullStringDateAndCollectionBehavior() throws Exception {
        assertNull(JsonHelper.serialize(null));
        assertEquals("原始文本", JsonHelper.serialize("原始文本"));
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("missing", null);
        values.put("date", LocalDate.of(2026, 9, 27));
        values.put("time", LocalDateTime.of(2026, 9, 27, 10, 11, 12));
        var tree = JsonHelper.mapper.readTree(JsonHelper.serialize(values));
        assertFalse(tree.has("missing"));
        // 保留库内原本的时间数组格式，不借升级改成日期字符串。
        assertEquals(JsonHelper.mapper.readTree("[2026,9,27]"), tree.get("date"));
        assertEquals(JsonHelper.mapper.readTree("[2026,9,27,10,11,12]"), tree.get("time"));
        assertEquals(List.of(1, 2), JsonHelper.parseList("[1,2]", Integer.class));
        assertEquals(Map.of("count", 2), JsonHelper.parseMap("{\"count\":2}", String.class, Integer.class));
    }

    private void assertResponse(VndbResponse<DatabaseStats> response) {
        assertNotNull(response);
        assertEquals(Boolean.FALSE, response.getMore());
        assertEquals(1, response.getNum().intValue());
        assertEquals(1, response.getItems().size());
        assertEquals(42, response.getItems().get(0).getVisualNovels().intValue());
        assertEquals(7, response.getItems().get(0).getCharacters().intValue());
    }
}
