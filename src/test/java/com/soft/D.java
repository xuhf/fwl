package com.soft;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xuhf on 2018/3/29.
 */
public class D {

    @Test
    public void test() {
        String address = "广东省湛江市霞山区新兴街道湛江霞山区椹川大道南62号方兴苑1门204房(524000)....";
        System.out.println(replaceLastPoint(address));
    }

    @Test
    public void post() {
        String address = "广东省湛江市霞山区新兴街道湛江霞山区椹川大道南62号方兴苑1门204房(524000)....";
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
        Matcher matcher = pattern.matcher(address);
        if (matcher.find()) {
            System.out.println("----");
            System.out.println(matcher.group());
        }
    }

    @Test
    public void r() {
        String address = "河南省郑州市新密市青屏街街道祥云街三小对面奥迪玩具(菜鸟驿站:13523489349)(asdas).";
        System.out.println(address.replaceAll("\\((.*)\\)", ""));
    }

    @Test
    public void r2() {
        String address = "湖南省常德市澧县澧阳镇地址:\\t湖南常德市澧县县城内华侨新村二栋二单元四楼\\r\\\\n固定电话:(000000).";
        System.out.println(address.replaceAll("\\\\t", "").replaceAll("\\\\r","").replace("\\\\n",""));
    }


    private String replaceLastPoint(String address) {
        if (StringUtils.isBlank(address)) {
            return address;
        }
        if (address.endsWith(".")) {
            return replaceLastPoint(address.substring(0, address.length() - 1));
        } else {
            return address;
        }
    }
}
