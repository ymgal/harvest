package com.ymgal.model.Common;

public enum Gender {
    /// <summary>
    /// Male gender
    /// </summary>
    Male("m"),
    /// <summary>
    /// Female gender
    /// </summary>
    Female("f"),
    /// <summary>
    /// Mixed/Both Gender
    /// </summary>
    Both("b"),
    /// <summary>
    /// Unknown gender for use with SpoilGender
    /// </summary>
    Unknown("unknown");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
