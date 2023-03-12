package com.tencent.wxcloudrun.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tencent.wxcloudrun.bean.NamedItem;
import com.tencent.wxcloudrun.bean.NamedReq;
import com.tencent.wxcloudrun.bean.NamedRes;
import com.tencent.wxcloudrun.service.NamedService;
import com.named.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("namedService")
public class NamedServiceImpl implements NamedService {

    private final static String BASE_URL = "https://www.name321.com/";
    private final static String URL = "https://www.name321.com/qiming/";

    private final static String[] FIVE = new String[]{"金", "木", "水", "火", "土"};

    @Override
    public NamedRes getName(NamedReq namedReq) {
        Map<String, Object> params = initParamsMap(namedReq);
        String result = HttpUtil.doPost(URL, params);
        return getNameRes(result, namedReq);
    }

    private static Map<String, Object> initParamsMap(NamedReq namedReq) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("name", namedReq.getSurname());
        params.put("sex", namedReq.getGender() == 0 ? "男" : "女");
        params.put("zs", namedReq.getType());
        params.put("y", namedReq.getYear());
        params.put("m", namedReq.getMonth());
        params.put("d", namedReq.getDay());
        params.put("h", namedReq.getHour());
        params.put("i", namedReq.getMin());
        if (StringUtils.isNotEmpty(namedReq.getFixed())) {
            params.put("fixed", namedReq.getFixed());
        }
        if (namedReq.getFixedType() != 0) {
            params.put("fixedType", namedReq.getFixedType());
        }
        return params;
    }

    private NamedRes getNameRes(String result, NamedReq namedReq) {
        NamedRes res = new NamedRes();
        res.setSurname(namedReq.getSurname());
        String[] descArray = result.split("<div class=\"divjg2\"></div>");
        int birthStart = descArray[0].lastIndexOf("出生时间：");
        String birth = descArray[0].substring(birthStart);
        String[] birthArray = birth.split("<br />");
        res.setSolar(birthArray[0].replace("出生时间：", "").trim());
        res.setLunar(birthArray[1].replace("农历：", "").trim());
        res.setFolklore(birthArray[2].replace("生辰八字：", "").trim());
        result = result.replace(birth, "");

        int particularStart = descArray[1].indexOf("二、八字详细情况</strong><br />");
        String particular = descArray[1].substring(particularStart);
        particular = particular.replace("二、八字详细情况</strong><br />\r\n", "");
        res.setParticular(particular.trim().replace("<br />", "").replace("\r\n", ""));
        result = result.replace(particular, "");

        int analyseStart = descArray[2].indexOf("八字五行得分</strong><br />");
        String analyse = descArray[2].substring(analyseStart);
        String[] analyseArray = analyse.split("\\(");
        String[] metalAndWater = analyseArray[0].replace("八字五行得分</strong><br />\r\n", "").split("；");
        getFiveScore(metalAndWater[0], res);
        getFiveScore(metalAndWater[1], res);
        String[] other = analyseArray[1].split("；");
        getFiveScore(other[0].split("<br />\r\n")[1], res);
        getFiveScore(other[1], res);
        getFiveScore(other[2], res);
        result = result.replace(analyse, "");

        int contents1Start = result.indexOf("命主生辰八字弱：</b>");
        int contents1End = result.indexOf("<b>命主八字喜用神：</b>");
        String contents1 = result.substring(contents1Start, contents1End);
        res.setContents1(contents1.trim().replace("命主生辰八字弱：</b>", "").replace("<br />", ""));
        result = result.replace(contents1, "");

        int contents2Start = result.indexOf("命主八字喜用神：</b>");
        int contents2End = result.indexOf("<div class=\"divjg2\" id=\"miao\">");
        String contents2 = result.substring(contents2Start, contents2End);
        res.setContents2(contents1.trim().replace("命主八字喜用神：</b>", "").replace("<br />", "").replace("</b>", ""));
        result = result.replace(contents2, "");

        int start = result.indexOf("<a name=\"miao\"></a>");
        int end = result.indexOf("以下姓名补八字");
        String str = result.substring(start, end);
        res.setAnalyse(str.replace("<a name=\"miao\"></a>", "")
                .replace("<br />", "")
                .replace("</b>", "")
                .replace("\r\n", "")
                .replace("<b>", ""));

        List<NamedItem> namedList = Lists.newArrayList();
        int a = result.indexOf("<div class=\"xm\">");
        String substring = result.substring(a);
        int clear = substring.indexOf("</div>");
        substring = substring.substring(0, clear);
        substring = substring.replace("<div class=\"xm\">", "");
        substring = substring.replace("</div>", "");
        String[] split = substring.split("</a> ");
        for (int i = 0; i < split.length; i++) {
            if (!split[i].startsWith("<br />")) {
                String[] split1 = split[i].split("<br />");
                int i1 = split1[0].indexOf("=") + 3;
                int i2 = split1[0].indexOf(">") - 1;
                String detail = HttpUtil.doGet(BASE_URL + split1[0].substring(i1, i2));
                NamedItem item = new NamedItem();
                int detailStrStart = detail.indexOf("<table>");
                int detailStrEnd = detail.indexOf("</table>");
                String detailStr = detail.substring(detailStrStart, detailStrEnd);
                String[] detailArray = detailStr.split("<tr>");
                String[] nameArray = detailArray[1].split("</td>");
                StringBuilder name = new StringBuilder();
                for (int i3 = 1; i3 < nameArray.length - 1; i3++) {
                    name.append(nameArray[i3].substring(nameArray[i3].length() - 1));
                    name.append(" ");
                }
                item.setName(name.toString().trim());

                String[] spellArray = detailArray[3].split("</td>");
                StringBuilder spell = new StringBuilder();
                for (int i3 = 1; i3 < nameArray.length - 1; i3++) {
                    spell.append(spellArray[i3].substring(spellArray[i3].indexOf(">") + 1));
                    spell.append(" ");
                }
                item.setSpell(spell.toString().trim());

                int scoreIndex = detail.indexOf("</span> 分，你的名字起得");
                String score = detail.substring(scoreIndex - 2, scoreIndex);
                item.setScore(score);

                namedList.add(item);
            }
        }
        res.setNamedList(namedList);
        return res;
    }

    private void getFiveScore(String str, NamedRes res) {
        if (str.contains("金")) {
            res.setMetal(Integer.parseInt(str.replace("金", "").trim()));
        } else if (str.contains("木")) {
            res.setWood(Integer.parseInt(str.replace("木", "").trim()));
        } else if (str.contains("水")) {
            res.setWater(Integer.parseInt(str.replace("水", "").trim()));
        } else if (str.contains("火")) {
            res.setFire(Integer.parseInt(str.replace("火", "").trim()));
        } else if (str.contains("土")) {
            res.setEarth(Integer.parseInt(str.replace("土", "").trim()));
        }
    }
}
