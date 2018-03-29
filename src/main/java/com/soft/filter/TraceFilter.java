package com.soft.filter;

import com.google.common.collect.Lists;
import com.soft.model.Trace;

import java.util.List;

/**
 * Created by xuhf on 2018/3/29.
 */
public class TraceFilter {

    public static List<Filter> filters = Lists.newArrayList();

    public static void filter(Trace trace) {
        for (Filter filter : filters) {
            filter.filter(trace);
        }
    }

    public static void registerFilter(Filter filter) {
        filters.add(filter);
    }

}
