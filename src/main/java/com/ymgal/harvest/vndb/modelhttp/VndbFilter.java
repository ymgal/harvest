package com.ymgal.harvest.vndb.modelhttp;

import com.ymgal.harvest.vndb.modelhttp.enums.FilterName;
import com.ymgal.harvest.vndb.modelhttp.enums.FilterOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lyl
 * @Date: 2023/10/13 11:11
 * @Description:
 */
public class VndbFilter {

    public FilterName filterFiled;

    public FilterOperator filterOperator;

    public String filterValue;

    public VndbFilter(String filterFiled, String operator, String filterValue) {
        this.filterFiled = FilterName.fromString(filterFiled.toLowerCase());
        this.filterOperator = FilterOperator.fromString(operator);
        this.filterValue = filterValue;
    }

    public List<String> toFormatString() {
        List<String> list = new ArrayList<>();
        list.add(this.filterFiled.getFilterName());
        list.add(this.filterOperator.getOperator());
        list.add(this.filterValue);
        return list;
    }
}
