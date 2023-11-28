package com.ymgal.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/// <summary>
///		The Flag Values for Commands
/// </summary>
public enum VndbFlags {
    /// <summary>
    ///		<para>The most basic of information for the command</para>
    ///		<para>Valid on: Vn, Release, Producer, Character, User, Votelist, Vnlist, Wishlist</para>
    /// </summary>
    Basic(1 << 0, "basic"),

    /// <summary>
    ///		<para>Provides more detailed information</para>
    ///		<para>Valid on: Vn, Release, Producer, Character</para>
    /// </summary>
    Details(1 << 1, "details"),

    /// <summary>
    ///		<para>Provides metadata of anime adaptions for the visual novel</para>
    ///		<para>Valid on: Vn</para>
    /// </summary>
    Anime(1 << 2, "anime"),

    /// <summary>
    ///		<para>Provides metadata on related visual novel(s)</para>
    ///		<para>Valid on: Vn, Producer</para>
    /// </summary>
    Relations(1 << 3, "relations"),

    /// <summary>
    ///		<para>Provides a list of tag metadata linked to the visual novel</para>
    ///		<para>Valid on: Vn</para>
    /// </summary>
    Tags(1 << 4, "tags"),

    /// <summary>
    ///		<para>Provides user voting stats</para>
    ///		<para>Valid on: Vn</para>
    /// </summary>
    Stats(1 << 5, "stats"),

    /// <summary>
    ///		<para>Provides screenshot metadata</para>
    ///		<para>Valid on: Vn</para>
    /// </summary>
    Screenshots(1 << 6, "screens"),

    /// <summary>
    ///		<para>Provides visual novel metadata</para>
    ///		<para>Valid on: Release, Character</para>
    /// </summary>
    VisualNovels(1 << 7, "vn"),

    /// <summary>
    ///		<para>Provides producers metadata</para>
    ///		<para>Valid on: Release</para>
    /// </summary>
    Producers(1 << 8, "producers"),

    /// <summary>
    ///		<para>Provides the characters 3 sizes, height and weight</para>
    ///		<para>Valid on: Release, Producer, Character</para>
    /// </summary>
    Measurements(1 << 9, "meas"),

    /// <summary>
    ///		<para>Provides the metadata for the traits linked to the character</para>
    ///		<para>Valid on: Character</para>
    /// </summary>
    Traits(1 << 10, "traits"),
    /// <summary>
    ///		<para>Provides the staff who worked on the VN</para>
    ///		<para>Valid on: Vn</para>
    /// </summary>
    Staff(1 << 11, "staff"),

    /// <summary>
    ///		<para>Provides the list of voice actresses who worked on the VN</para>
    ///		<para>Valid on: Character, Staff</para>
    /// </summary>
    Voiced(1 << 12, "voiced"),

    /// <summary>
    ///		<para>Provides a list of instances, of the character (excluding the current instance)</para>
    ///		<para>Valid on: Character</para>
    /// </summary>
    Instances(1 << 13, "instances"),


    /// <summary>
    ///		<para>Provides the aliases of the staff who worked on the VN</para>
    ///		<para>Valid on: Staff</para>
    /// </summary>
    Aliases(1 << 14, "aliases"),

    /// <summary>
    ///		<para>Provides the labels assigned to a user or VN entry</para>
    ///		<para>Valid on: UserList, UserListLabels</para>
    /// </summary>
    Labels(1 << 15, "labels");


    private static final Map<Integer, VndbFlags> vndbFlagsMap = Arrays.stream(VndbFlags.values()).collect(Collectors.toMap(VndbFlags::getValue, v -> v));
    private final Integer value;
    private final String desc;

    VndbFlags(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据提供的枚举值，获取该值中包含的所有枚举对象（拆分枚举合计值为枚举列表）
     *
     * @param value 提供查询的枚举值（供拆分的枚举合计值，由一个或多个枚举index相加的值）
     * @return 返回符合条件的枚举列表
     */
    public static List<String> getDescs(int value) {
        List<String> result = new ArrayList<>();
        if (vndbFlagsMap.containsKey(value)) {
            result.add(vndbFlagsMap.get(value).desc);
            return result;
        }
        int currentValue = value;
        do {
            final int tempValue = currentValue;
            int maxValue = vndbFlagsMap.keySet().stream().filter(f -> f <= tempValue).max(Integer::compare).get();
            result.add(vndbFlagsMap.get(maxValue).desc);
            currentValue -= maxValue;
        } while (currentValue > 0);
        return result;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
