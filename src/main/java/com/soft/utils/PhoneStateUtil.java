package com.soft.utils;

import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by xuhf on 2018/3/29.
 */
public class PhoneStateUtil {

    private static Map<String, String> states = Maps.newHashMap();

    static {
        try {
            File file = new File("C:\\Users\\xuhf\\Desktop\\fwl\\data\\result\\phones_state_thread.txt");
            List<String> lines = FileUtils.readLines(file, "utf-8");
            for (String line : lines) {
                String[] data = line.split(",");
                states.put(data[0], data[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getState(String phone) {
        String s = states.get(phone);
        if (s == null) {
            return "0";
        }
        return s;
    }

}
