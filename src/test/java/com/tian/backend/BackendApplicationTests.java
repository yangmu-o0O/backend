package com.tian.backend;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        String[] p = new String[]{"IV","奥凯","百年","百威","博优","常春藤","畅捷通","超旺",
                "超赢","管家婆","海典","浩普","皓客","华创","汇云朗翰","经营圣手","凯宝","科脉"
                ,"科脉蛙笑V11","上海海典","时空智友","思讯-eshop4","思讯-eshop5","思讯-eshop6"
                ,"思讯-爱贝","思讯-爱客","思讯-孕婴童3","思讯-专卖10","思讯-专卖7","思讯-专卖8",
                "思讯-专卖9","泰格","童梦国","旺盛","西联","赢通","用友","智百威","紫日"};
        List<String> list = new ArrayList<>();
        for (String a: p) {
            list.add(PinyinUtil.getPinyin(a,""));
        }
        list.forEach(System.out::println);
    }

}
