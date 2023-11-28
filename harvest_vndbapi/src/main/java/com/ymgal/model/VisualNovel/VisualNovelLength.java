package com.ymgal.model.VisualNovel;

/// <summary>
/// Visual Novel Length
/// </summary>
public enum VisualNovelLength {
	/// <summary>
	/// Unknown Length
	/// </summary>
	Unknown(0, "unknown"),
	/// <summary>
	/// Very Short (Less than 2 hours)
	/// </summary>
	VeryShort(1, "< 2 hours"),
	/// <summary>
	/// Short (2 - 10 hours)
	/// </summary>
	Short(2, "2 - 10 hours"),
	/// <summary>
	/// Medium (10 - 30 hours)
	/// </summary>
	Medium(3, "10 - 20 hours"),

	/// <summary>
	/// Long (30 - 50 hours)
	/// </summary>
	Long(4, "30 - 50 hours"),
	/// <summary>
	/// Very Long (50 hours or more)
	/// </summary>
	VeryLong(5, "> 50 hours");

	private final Integer length;

	private final String desc;

	VisualNovelLength(Integer length, String desc) {
		this.length = length;
		this.desc = desc;
	}

	public Integer getLength() {
		return length;
	}

	public String getDesc() {
		return desc;
	}
}
