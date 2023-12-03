package com.ymgal.harvest.vndb.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @Auther: lyl
 * @Date: 2023/10/13 10:28
 * @Description:
 */
public class JsonHelper {
    public static ObjectMapper mapper = new ObjectMapper();

    static {
        //序列化的时候序列对象的所有非空属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //反序列化的时候如果多了其他属性,不抛出异常
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //如果是空对象的时候,不抛异常
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //不使用默认的dateTime进行序列化,
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);

        SimpleModule module = new SimpleModule();

        //PS：这里只是单纯占位。 要序列化 LocalDate/LocalDateTime 成 2014-01-01 这种标准格式则需要自定义
        module.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE);
        module.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);

        //使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
        mapper.registerModules(new JavaTimeModule(), module);

        //语言环境
        mapper.setLocale(Locale.CHINA);
    }

    /**
     * @param obj
     * @return java.lang.String
     * @Name serialize
     * @Description 序列化对象（转json）
     * @Author wen
     * @Date 2019/3/30
     */
    public static String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param json
     * @param tClass
     * @return T
     * @Name parse
     * @Description 反序列化（json转为Bean）
     * @Author wen
     * @Date 2019/3/30
     */
    public static <T> T parse(String json, Class<T> tClass) {
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parse(String json, TypeReference<T> valueTypeRef) {
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param json
     * @param eClass
     * @return java.util.List<E>
     * @Name parseList
     * @Description 反序列化（json转List）
     * @Author wen
     * @Date 2019/3/30
     */
    public static <E> List<E> parseList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param json
     * @param kClass
     * @param vClass
     * @return java.util.Map<K, V>
     * @Name parseMap
     * @Description 反序列化（json转Map）
     * @Author wen
     * @Date 2019/3/30
     */
    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param json
     * @param type
     * @return T
     * @Name nativeRead
     * @Description json转复杂对象
     * @Author wen
     * @Date 2019/3/30
     */
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
