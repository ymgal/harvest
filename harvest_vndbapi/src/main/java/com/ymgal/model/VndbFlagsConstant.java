package com.ymgal.model;

/**
 * @Auther: lyl
 * @Date: 2023/11/13 10:59
 * @Description:
 */
public class VndbFlagsConstant {

    /// <summary>
    ///		Equivlant to <see cref="Basic"/> | <see cref="Details"/> | <see cref="Anime"/> | <see cref="Relations"/> | <see cref="Tags"/> | <see cref="Stats"/> | <see cref="Screenshots"/> | <see cref="Staff"/>
    /// </summary>
    public final static Integer FullVisualNovel = VndbFlags.Basic.getValue() | VndbFlags.Details.getValue()
            | VndbFlags.Anime.getValue() | VndbFlags.Relations.getValue() | VndbFlags.Tags.getValue() | VndbFlags.Stats.getValue()
            | VndbFlags.Screenshots.getValue() | VndbFlags.Staff.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/> | <see cref="Details"/> | <see cref="VisualNovel"/> | <see cref="Producers"/>
    /// </summary>
    public final static Integer FullRelease = VndbFlags.Basic.getValue() | VndbFlags.Details.getValue() | VndbFlags.VisualNovels.getValue()
            | VndbFlags.Producers.getValue();

    //<summary>
//	Equivlant to <see cref="Basic"/> | <see cref="Details"/> | <see cref="Relations"/>
// </summary>
    public final static Integer FullProducer = VndbFlags.Basic.getValue() | VndbFlags.Details.getValue() | VndbFlags.Relations.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/> | <see cref="Details"/> | <see cref="Measurements"/> | <see cref="Traits"/> | <see cref="VisualNovels" /> | <see cref="Voiced"/> | <see cref="Instances"/>
    /// </summary>
    public final static Integer FullCharacter = VndbFlags.Basic.getValue() | VndbFlags.Details.getValue() | VndbFlags.Measurements.getValue() | VndbFlags.Traits.getValue()
            | VndbFlags.VisualNovels.getValue() | VndbFlags.Voiced.getValue() | VndbFlags.Instances.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/> | <see cref="Details"/> | <see cref="VisualNovels"/> | <see cref="Voiced" />
    /// </summary>
    public final static Integer FullStaff = VndbFlags.Basic.getValue() | VndbFlags.Details.getValue() | VndbFlags.VisualNovels.getValue()
            | VndbFlags.Voiced.getValue() | VndbFlags.Aliases.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/>
    /// </summary>

    public final static Integer FullUser = VndbFlags.Basic.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/>
    /// </summary>
    public final static Integer FullVotelist = VndbFlags.Basic.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/>
    /// </summary>
    public final static Integer FullVisualNovelList = VndbFlags.Basic.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/>
    /// </summary>
    public final static Integer FullWishlist = VndbFlags.Basic.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/> | <see cref="Labels" />
    /// </summary>
    public final static Integer FullUserList = VndbFlags.Basic.getValue() | VndbFlags.Labels.getValue();

    /// <summary>
    ///		Equivlant to <see cref="Basic"/> | <see cref="Labels" />
    /// </summary>
    public final static Integer FullUserListLabels = VndbFlags.Basic.getValue();

}
