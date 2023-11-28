package com.ymgal.filter.conditionals;

import com.ymgal.Interfaces.IFilter;
import lombok.SneakyThrows;

/**
 * @Auther: lyl
 * @Date: 2023/11/13 15:16
 * @Description:
 */
public class FilterOr implements IFilter {

    /// <summary>
    ///		The Filter on the Left, or First Condition
    /// </summary>
    public IFilter LeftFilter;
    /// <summary>
    ///		The Filter on the Right, or Last Condition
    /// </summary>
    public IFilter RightFilter;

//    public static FilterAnd And(IFilter leftFilter, IFilter rightFilter) {
//        return leftFilter & rightFilter;
//    }

//    public static FilterAnd operator &(FilterAnd leftFilter, IFilter rightFilter)
//            => leftFilter.And(rightFilter);
//
//    /// <summary>
//    ///		Equivlant to IFilter.Or(IFilter)
//    /// </summary>
//    public static FilterOr operator |(FilterAnd leftFilter, IFilter rightFilter)
//            => leftFilter.Or(rightFilter);

    @Override
    public Boolean IsFilterValid() {
        return this.LeftFilter.IsFilterValid() && this.RightFilter.IsFilterValid();
    }

    @SneakyThrows
    @Override
    public String toString() {
        if (!this.IsFilterValid())
            throw new Exception("One of the provided filters are not valid.");
        return "(" + this.LeftFilter + " or " + this.RightFilter + ")";
    }
}
