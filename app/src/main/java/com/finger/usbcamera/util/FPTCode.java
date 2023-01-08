package com.finger.usbcamera.util;

import java.util.HashMap;

public class FPTCode {
    private static HashMap<String,String> genderMap = new HashMap<>();
    private static HashMap<String,String> ethnicMap = new HashMap<>();
    static {
        //初始化数据
        //TODO 读取配置
        genderMap.put("男","1");
        genderMap.put("女","2");
        genderMap.put("未知","0");
        //民族
        ethnicMap.put("汉族","01");
        ethnicMap.put("蒙古族","02");
        ethnicMap.put("回族","03");
        ethnicMap.put("藏族","04");
        ethnicMap.put("维吾尔族","05");
        ethnicMap.put("苗族","06");
        ethnicMap.put("彝族","07");
        ethnicMap.put("壮族","08");
        ethnicMap.put("布依族","09");
        ethnicMap.put("朝鲜族","10");
        ethnicMap.put("满族","11");
        ethnicMap.put("侗族","12");
        ethnicMap.put("瑶族","13");
        ethnicMap.put("白族","14");
        ethnicMap.put("土家族","15");
        ethnicMap.put("哈尼族","16");
        ethnicMap.put("哈萨克族","17");
        ethnicMap.put("傣族","18");
        ethnicMap.put("黎族","19");
        ethnicMap.put("僳僳族","20");
        ethnicMap.put("佤族","21");
        ethnicMap.put("畲族","22");
        ethnicMap.put("高山族","23");
        ethnicMap.put("拉祜族","24");
        ethnicMap.put("水族","25");
        ethnicMap.put("东乡族","26");
        ethnicMap.put("纳西族","27");
        ethnicMap.put("景颇族","28");
        ethnicMap.put("柯尔克孜族","29");
        ethnicMap.put("土族","30");
        ethnicMap.put("达斡尔族","31");
        ethnicMap.put("仫佬族","32");
        ethnicMap.put("羌族","33");
        ethnicMap.put("布朗族","34");
        ethnicMap.put("撒拉族","35");
        ethnicMap.put("毛难族","36");
        ethnicMap.put("仡佬族","37");
        ethnicMap.put("锡伯族","38");
        ethnicMap.put("阿昌族","39");
        ethnicMap.put("普米族","40");
        ethnicMap.put("塔吉克族","41");
        ethnicMap.put("怒族","42");
        ethnicMap.put("乌孜别克族","43");
        ethnicMap.put("俄罗斯族","44");
        ethnicMap.put("鄂温克族","45");
        ethnicMap.put("崩龙族","46");
        ethnicMap.put("保安族","47");
        ethnicMap.put("裕固族","48");
        ethnicMap.put("京族","49");
        ethnicMap.put("塔塔尔族","50");
        ethnicMap.put("独龙族","51");
        ethnicMap.put("鄂伦春族","52");
        ethnicMap.put("赫哲族","53");
        ethnicMap.put("门巴族","54");
        ethnicMap.put("珞巴族","55");
        ethnicMap.put("基诺族","56");
        ethnicMap.put("外国血统(中国籍人士)","98");
        ethnicMap.put("未确认","99");
    }

    public static String getGenderCode(String gender){
        return genderMap.get(gender);
    }
    public static String getEthnicCode(String ethnic){
        return ethnicMap.get(ethnic);
    }
}
