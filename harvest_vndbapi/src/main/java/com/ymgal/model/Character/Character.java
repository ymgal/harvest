package com.ymgal.model.Character;

import com.ymgal.model.Common.ImageRating;
import lombok.Data;

import java.util.List;

@Data
public class Character {
    /// <summary>
    /// Character's ID on Vndb
    /// </summary>
    private Integer id;
    /// <summary>
    /// Character's Name
    /// </summary>
    private String name;
    /// <summary>
    /// Character's Japanese/Original Name
    /// </summary>=
    private String original;
    /// <summary>
    /// Character's Gender
    /// </summary>
    //private Gender gender;
    private String gender;
    /// <summary>
    /// Actual Sex, if the gender is a spoiler
    /// </summary>
    private String spoil_gender;
    /// <summary>
    /// Character's Gender
    /// </summary>
    //private BloodType bloodt;
    private String bloodt;
    /// <summary>
    /// Character's Birthday
    /// </summary>
    private List<Integer> birthday;
    /// <summary>
    /// Character's Aliases/Nicknames
    /// </summary>
    private String aliases;
    /// <summary>
    /// Description of the character
    /// </summary>
    private String description;
    /// <summary>
    /// Character's age in years
    /// </summary>
    private Integer age;
    /// <summary>
    /// Url Image of the character
    /// </summary>
    private String image;
    /// <summary>
    /// Properties of the character's image. This determines how violent/sexual it is
    /// </summary>
    private ImageRating image_flagging;
    /// <summary>
    ///		Size in Centimeters
    /// </summary>
    private Integer bust;
    /// <summary>
    ///		Size in Centimeters
    /// </summary>
    private Integer waist;
    /// <summary>
    ///		Size in Centimeters
    /// </summary>
    private Integer hip;
    /// <summary>
    ///		Height in Centimeters
    /// </summary>
    private Integer height;
    /// <summary>
    ///		Weight in Kilograms
    /// </summary>
    private Integer weight;
    /// <summary>
    /// CupSize of the character
    /// </summary>
    private String cup_size;
    /// <summary>
    /// List of traits the character has
    /// </summary>
    //private List<TraitMetadata> traits;
    private List<Integer[]> traits;
    /// <summary>
    /// List of Visual Novels linked to this character
    /// </summary>
    //private List<VisualNovelMetadata> VisualNovels;
    private List<Object[]> vns;
    /// <summary>
    /// List of voice actresses (staff) that voiced this character, per VN
    /// </summary>
    private List<VoiceActorMetadata> voiced;
    /// <summary>
    /// List of instances of this character (excluding the character entry itself)
    /// </summary>
    private List<CharacterInstances> instances;
}
