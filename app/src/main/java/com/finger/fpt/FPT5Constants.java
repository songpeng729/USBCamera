package com.finger.fpt;

/**
 * FPT5常量
 */
public class FPT5Constants {

    public static String VERSION_FPT50 = "FPT0500";
    //常用证件类型默认值
    public static String DEFAULT_CERTIFICATE_TYPE = "111"; //身份证
    //东方金指系统代码
    public static String SYS_CODE_EGFS = "1900";
    //空身份证号
    public static String EMPTY_IDCARDNO = "000000000000000000";
    //空手机号
    public static String EMPTY_TELNUMBER = "0000000000000";

    /**
     * 刑专系统：来源系统为固定内容“XZ”；
     * 警综平台：来源系统为固定内容“JZ”；
     * 指掌纹自动识别系统：来源系统为固定内容“AFIS”；
     * 采集系统：来源系统为固定内容“CJ”；
     * 现勘系统：来源系统为固定内容“XK”；
     * 其他系统：来源系统内容为”QT”。
     */
    public static String SYS_XINGZHUAN = "XZ";
    public static String SYS_JINGZONG = "JZ";
    public static String SYS_AFIS = "AFIS";
    public static String SYS_CAPTURE = "CJ";
    public static String SYS_SURVEY = "XK";
    public static String SYS_OTHER = "QT";

    //指纹数据代码第8部分：指纹特征提取方式缩略规则
    public static String EXTRACT_METHOD_A = "A"; //自动提取
    public static String EXTRACT_METHOD_U = "U"; //自动提取且需要人工编辑
    public static String EXTRACT_METHOD_E = "E"; //自动提取且已经人工编辑
    public static String EXTRACT_METHOD_M = "M"; //人工抽取
    public static String EXTRACT_METHOD_O = "O"; //其他

    //公安信息代码第XXX部分：指掌纹缺失情况代码
    public static String FINGER_LOST_NORMAL = "0";  //正常
    public static String FINGER_LOST_INCOMPLETE = "1";  //残缺
    public static String FINGER_LOST_SYS_NO_GATHER = "2";  //系统设置不采集
    public static String FINGER_LOST_INJURED = "3";  //受伤未采集
    public static String FINGER_LOST_OTHER = "9";  //其他缺失情况



    //图像压缩方式
    // 不压缩
    public static String GAIMG_CPRMETHOD_NOCPR_CODE = "0000";
    // 公安部10压缩
    public static String GAIMG_CPRMETHOD_GA10_CODE = "0010";
    // 东方金指
    public static String GAIMG_CPRMETHOD_EGFS_CODE = "1900";
    //东方金指WSQ3.1压缩
    public static String GAIMG_CPRMETHOD_EGFS_WSQ_CODE = "0131";
    // 北大高科
    public static String GAIMG_CPRMETHOD_PKU_CODE = "1300";
    //WSQ压缩方法
    public static String GAIMG_CPRMETHOD_WSQ_CODE = "1400";
    //WSQ压缩方法
    public static String GAIMG_CPRMETHOD_WSQ_BY_GFS_CODE = "1419";
    // 北京海鑫
    public static String GAIMG_CPRMETHOD_COGENT_CODE = "1700";
    // 小日本NEC
    public static String GAIMG_CPRMETHOD_NEC_CODE = "1800";
    // 北京邮电大学
    public static String GAIMG_CPRMETHOD_BUPT_CODE = "1200";
    // 北京刑科所
    public static String GAIMG_CPRMETHOD_TSINGHUA_CODE = "1100";
    //魔佛
    public static String GAIMG_CPRMETHOD_MORPHO_CODE = "2000"; // 广东测试提供的数据，SAGEM MORPHO
    //汉林信通
    public static String GAIMG_CPRMETHOD_HLXT_CODE = "2900"; // 广东测试提供的数据，SAGEM MORPHO



    /**
     * 导出类型
     */
    public static int EXPORT_TYPE_TT = 0; //查重
    public static int EXPORT_TYPE_TL = 1; //倒查
    public static int EXPORT_TYPE_LT = 2; //正常
    public static int EXPORT_TYPE_LL = 3; //串查
    public static int EXPORT_TYPE_LP = 4; //案件
    public static int EXPORT_TYPE_LP_FINGER = 5; //现场指纹
    public static int EXPORT_TYPE_TP = 6; //捺印
    public static int EXPORT_TYPE_LP_PALM = 7; //现场掌纹
    public static int EXPORT_TYPE_TP_LP = 8; //同时导出捺印和案件

    /**
     * FPT导出类型转换
     * @param type
     * @return
     */
    public static int exportTypeInvoke(String type){
        int exportType = 0;
        switch (type){
            case "tt_ck": exportType = EXPORT_TYPE_TT;break; //查重
            case "lt_tl_ck": exportType = EXPORT_TYPE_LT;break; //正查
            case "ll_ck": exportType = EXPORT_TYPE_LL;break; //串查
            case "tp" : exportType = EXPORT_TYPE_TP;break; //捺印
            case "lp" : exportType = EXPORT_TYPE_LP;break; //案件
            case "tplp": exportType = EXPORT_TYPE_TP_LP;break; //捺印案件
            case "lpFinger": exportType = EXPORT_TYPE_LP_FINGER;break; //现场指纹
            case "lpPalm": exportType = EXPORT_TYPE_LP_PALM;break; //现场掌纹
        }
        return exportType;
    }
    
    
    /**
     * 现场物证分类-指纹
     */
    public static String PHYSICAL_EVIDENCE_TYPE_ZW = "1101";
    /**
     * 现场物证分类-指节纹
     */
    public static String PHYSICAL_EVIDENCE_TYPE_ZJW = "1102";
    /**
     * 现场物证分类-掌纹
     */
    public static String PHYSICAL_EVIDENCE_TYPE_ZHW = "1103";
    /**
     * 现场物证分类-手套印
     */
    public static String PHYSICAL_EVIDENCE_TYPE_SHTY = "1104";
    /**
     * 现场物证分类-其他手印痕迹
     */
    public static String PHYSICAL_EVIDENCE_TYPE_QT = "1199";


    //============================查重比中类型==============================
    /**
     * 正常比中 十指正常比中，所有指位都是确认同一
     */
    public static String TT_HITTYPE_NORMAL = "A";
    /**
     * 同卡比中 这两个次的数据是通过复制或者简单裁剪、旋转或者重新扫描等方式录入的，是同一张卡,是上一种情况的特例
     */
    public static String TT_HITTYPE_REPEAT = "B";
    /**
     * 部分比中
     A某些指位是明确比中了，但是其他指位因为质量问题等，不确定是否比中，各个指位比中情况有确认同一或者不确定是否同一两种
     B不同人捺印，某些指位出现确定不同一，并且不是这个人的其他指位指纹的重复，但是其他指位是确认同一或者不确定是否同一
     */
    public static String TT_HITTYPE_PART = "C";
    /**
     * 异位比中
     左右手弄反了，不同指位的指纹确认同一
     */
    public static String TT_HTITYPE_REVERSE = "D";
    /**
     * 镜像比中
     */
    public static String TT_HTITYPE_MIRROR = "E";

    /**
     * FPT5比对任务最大候选个数
     */
    public static int MAXCANDSIZE = 1000;

    //=================================掌纹三角位置类型代码=========================
    /**
     * 未知
     */
    public static String PALM_TRIANGLE_UNKNOWN = "00";
    /**
     * 右手腕部三角
     */
    public static String PALM_TRIANGLE_RIGHT_WRIST = "01";
    /**
     * 右手食指指根三角
     */
    public static String PALM_TRIANGLE_RIGHT_INDEXFINGER = "02";
    /**
     * 右手中指指根三角
     */
    public static String PALM_TRIANGLE_RIGHT_MIDDLEFINGER = "03";
    /**
     * 右手环指指根三角
     */
    public static String PALM_TRIANGLE_RIGHT_RINGFINGER = "04";
    /**
     * 右手小指指根三角
     */
    public static String PALM_TRIANGLE_RIGHT_LITTLEFINGER = "05";
    /**
     * 左手腕部三角
     */
    public static String PALM_TRIANGLE_LEFT_WRIST = "06";
    /**
     * 左手食指指根三角
     */
    public static String PALM_TRIANGLE_LEFT_INDEXFINGER = "07";
    /**
     * 左手中指指根三角
     */
    public static String PALM_TRIANGLE_LEFT_MIDDLEFINGER = "08";
    /**
     * 左手环指指根三角
     */
    public static String PALM_TRIANGLE_LEFT_RINGFINGER = "09";
    /**
     * 左手小指指根三角
     */
    public static String PALM_TRIANGLE_LEFT_LITTLEFINGER = "10";

    //===================FPT5文件名前缀===========================
    public static String FILENAME_PREFIX_NYZZW = "NYZZW"; 	//交换的全部为捺印指掌纹信息
    public static String FILENAME_PREFIX_XCZZW = "XCZZW"; 	//交换的全部为现场指掌纹信息
    public static String FILENAME_PREFIX_LTBZ = "LTBZ";	 //交换的全部为正查及倒查比中信息
    public static String FILENAME_PREFIX_TTBZ = "TTBZ";	 //交换的全部为查重比中信息
    public static String FILENAME_PREFIX_LLBZ = "LLBZ";	 //交换的全部为串查比中信息
    public static String FILENAME_PREFIX_XCCX = "XCCX";	 //交换的全部为现场指掌纹查询请求信息
    public static String FILENAME_PREFIX_NYCX = "NYCX";	 //交换的全部为捺印指掌纹查询请求信息
    public static String FILENAME_PREFIX_LTBD = "LTBD";	 //交换的全部为正查比对结果信息
    public static String FILENAME_PREFIX_TLBD = "TLBD";	 //交换的全部为倒查比对结果信息
    public static String FILENAME_PREFIX_TTBD = "TTBD";	 //交换的全部为查重比对结果信息
    public static String FILENAME_PREFIX_LLBD = "LLBD";	 //交换的全部为串查比对结果信息
    public static String FILENAME_PREFIX_CXXC = "CXXC";	 //交换的全部为撤销现场指掌纹信息

}
