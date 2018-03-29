package com.soft;

import com.soft.filter.CommonFilter;
import com.soft.filter.KeywordFilter;
import com.soft.filter.NameFilter;
import com.soft.filter.PhoneStateFilter;
import com.soft.filter.PostCodeFilter;
import com.soft.filter.TraceFilter;
import com.soft.filter.TraditionalFilter;
import com.soft.model.Member;
import com.soft.model.Trace;

import java.util.List;

/**
 * Created by xuhf on 2018/3/28.
 */
public class App {

    public static void main(String[] args) throws Exception {
        List<Member> members = Merger.getMembers();
        TraceFilter.registerFilter(new CommonFilter());
        TraceFilter.registerFilter(new PhoneStateFilter());
        TraceFilter.registerFilter(new TraditionalFilter());
        TraceFilter.registerFilter(new PostCodeFilter());
        TraceFilter.registerFilter(new KeywordFilter());
        TraceFilter.registerFilter(new NameFilter());

        for (Member m : members) {
            for (Trace trace : m.getTraces()) {
                TraceFilter.filter(trace);
            }
        }

        Writer.write(members);
    }
}
