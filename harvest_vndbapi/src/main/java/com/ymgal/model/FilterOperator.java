package com.ymgal.model;

import java.util.Objects;

/// <summary>
/// Available filter operators
/// </summary>
public class FilterOperator {
    /// <summary>
    ///		= Operator
    /// </summary>
    public static FilterOperator Equal = new FilterOperator("=", "Equals");
    /// <summary>
    ///		!= Operator
    /// </summary>
    public static FilterOperator NotEqual = new FilterOperator("!=", "NotEquals");
    /// <summary>
    ///		> Operator
    /// </summary>
    public static FilterOperator GreaterThan = new FilterOperator(">", "GreaterThan");
    /// <summary>
    ///		&lt; Operator
    /// </summary>
    public static FilterOperator LessThan = new FilterOperator("<", "LessThan");
    /// <summary>
    ///		>= Operator
    /// </summary>
    public static FilterOperator GreaterOrEqual = new FilterOperator(">=", "GreaterThanOrEqual");
    /// <summary>
    ///		&lt;= Operator
    /// </summary>
    public static FilterOperator LessOrEqual = new FilterOperator("<=", "LessThanOrEqual");
    /// <summary>
    ///		~ Operator
    /// </summary>
    public static FilterOperator Fuzzy = new FilterOperator("~", "Fuzzy");
    protected String Operator;
    protected String Name;


    protected FilterOperator(String value, String name) {
        this.Operator = value;
        this.Name = name;
    }

    /// <summary>
    /// Checks if the first filter equals the second filter
    /// </summary>
    /// <param name="filter1">The left IFilter</param>
    /// <param name="filter2">The right IFilter</param>
    /// <returns></returns>
    public static Boolean EqualFilter(FilterOperator filter1, FilterOperator filter2) {
        return Objects.equals(filter1.Operator, filter2.Operator);
    }

    /// <summary>
    /// Checks if the first filter is NOT equal to the second filter
    /// </summary>
    /// <param name="filter1">The left IFilter</param>
    /// <param name="filter2">The right IFilter</param>
    /// <returns></returns>
    public static Boolean NotEqualFilter(FilterOperator filter1, FilterOperator filter2) {
        return !(Objects.equals(filter1.Operator, filter2.Operator));
    }

    /// <summary>
    /// Checks if the 2 objects are equal
    /// </summary>
    /// <param name="obj"></param>
    /// <returns></returns>
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // 同一对象
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // 类型不同或obj为null
        }
        FilterOperator otherPerson = (FilterOperator) obj; // 类型匹配，进行具体的比较
        return Objects.equals(this.Operator, otherPerson.Operator)
                && Objects.equals(this.Name, otherPerson.Name);
    }

    /// <summary>
    /// Converts filter to string
    /// </summary>
    /// <returns></returns>
    @Override
    public String toString() {
        return this.Operator;
    }

    /// <summary>
    /// Gets hash code of Filter
    /// </summary>
    /// <returns></returns>
    @Override
    public int hashCode() {
        return this.Operator.hashCode();
    }
}

