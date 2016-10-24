package com.emergency.common.constants;

import com.emergency.common.util.DateUtil;

public class CommonConstants {
//    public static final String PAYAPP_FUND_ACCT = "200063432499";//move to properties
//    public static final String PAYAPP_CUST_NO = "00337652";//move to properties
    public static final String PAYAPP_BANK_NAME = "中国民生银行广州分行";
    public static final String PAYAPP_CARD_NO = "696650351";
    public static final String PAYAPP_COMPANY = "广东金融高新区股权交易中心";
    public static final String PAYAPP_COMPANY_CODE = "0002";

    public static final String PAYAPP_RITP_COMPANY_CODE = "0003";
    public static final String PAYAPP_RITP_BANK_NAME = "中国民生银行广州分行";
    public static final String PAYAPP_RITP_CARD_NO = "696650351";
    public static final String PAYAPP_RITP_COMPANY = "广东金融高新区股权交易中心";

    public static final int REAL_TIME_EXCHANGE = 1;

    public static final int NORMAL_OWNER_CHANGE = 2;

    public static final int PRODCUT_TYPE_IPO = 1;

    public static final int PRODUCT_TYPE_FIP = 2;

    public static final int PRODUCT_TYPE_RITP = 3;

    public static final int FIP_USEROWN_STATE_BEFORESTART = 1;//产品仍在募集期内

    public static final int FIP_USEROWN_STATE_RUNNING = 2;//产品封闭期内

    public static final int FIP_USEROWN_STATE_FINISH = 3;//封闭期结束，等待回款

    public static final int FIP_USEROWN_STATE_MONEYBACK = 4;//本息已到账

    public static final int FIP_USEROWN_STATE_BUY = 5;//已经发起购买

    public static final int FIP_STATE_WEEK_BEFORESTART = 1;//开卖前30天以上

    public static final int FIP_STATE_START_IN_WEEK = 2;//开卖前12小时以上

    public static final int FIP_STATE_START_IN_HALF_DAY = 3;//开卖前12小时以内，5分钟以上

    public static final int FIP_STATE_OPEN = 4;//抢购中

    public static final int FIP_STATE_WAITFORNEXT = 5;//本期售罄

    public static final int FIP_STATE_FINISH = 6;//认购期结束

    public static final int FIP_STATE_SOLDOUT = 7;//最后一期已售罄，时间还未到认购期结束

    public static final int FIP_STATE_READY_TO_OPEN = 8;//开卖前5分钟以内，可以进入准备抢页面

    public static final int[] FIP_STATE_ORDER = {
            FIP_STATE_OPEN,
            FIP_STATE_READY_TO_OPEN,
            FIP_STATE_WAITFORNEXT,
            FIP_STATE_START_IN_HALF_DAY,
            FIP_STATE_START_IN_WEEK,
            FIP_STATE_SOLDOUT,
            FIP_STATE_FINISH
    };

    public static final int[] FIP_USEROWN_STATE_ORDER = {
            FIP_USEROWN_STATE_FINISH,
            FIP_USEROWN_STATE_BEFORESTART,
            FIP_USEROWN_STATE_BUY,
            FIP_USEROWN_STATE_RUNNING,
            FIP_USEROWN_STATE_MONEYBACK
    };

    public static final long FIP_TIME_MILLIS_WEEK = 30 * DateUtil.D;
    public static final long FIP_TIME_MILLIS_HALF_DAY = 12 * DateUtil.H;
    public static final long FIP_TIME_MILLIS_READ_TO_ROB = 5 * DateUtil.M;

    public static final int SHIPPING_MODULE_TYPE_COMPANY = 1;//运费模版类型，公司

    public static final int SHIPPING_MODULE_TYPE_PROVINCE = 2;//运费模版类型，省

    public static final int USER_ADDRESS_TYPE_COMPANY = 1; //用户地址类型，公司

    public static final int USER_ADDRESS_TYPE_HOME = 2; //用户地址类型，家

    public static final int USER_ADDRESS_TYPE_PARENTS = 3; //用户地址类型，父母

    public static final int USER_ADDRESS_TYPE_FRIENDS = 4; //用户地址类型，朋友

    public static final int USER_ADDRESS_IS_DEFAULT = 1;

    public static final int USER_ADDRESS_IS_NOT_DEFAULT = 0;

    public static final String API_NATIVE_URL_DOMAIN = "hnczb://native?url=";

    public static final KeyValueObject API_NATIVE_MY_MAIN = new KeyValueObject("m", "我的首页", API_NATIVE_URL_DOMAIN + "m");

    public static final KeyValueObject API_NATIVE_FEEDBACK_LIST = new KeyValueObject("fb", "反馈列表页", API_NATIVE_URL_DOMAIN + "fb");

    public static final KeyValueObject API_NATIVE_FEEDBACK_REPORT = new KeyValueObject("fb_s", "提交反馈", API_NATIVE_URL_DOMAIN + "fb_s");

    public static final KeyValueObject API_NATIVE_NEWS_LIST = new KeyValueObject("msg", "消息列表页", API_NATIVE_URL_DOMAIN + "msg");

    public static final KeyValueObject API_NATIVE_FINANCING_MAIN = new KeyValueObject("f", "理财宝首页", API_NATIVE_URL_DOMAIN + "f");

    public static final KeyValueObject API_NATIVE_MY_FINANCING = new KeyValueObject("f_m", "我的理财宝", API_NATIVE_URL_DOMAIN + "f_m");

    public static final KeyValueObject API_NATIVE_COFFER_MAIN = new KeyValueObject("c", "工资宝首页", API_NATIVE_URL_DOMAIN + "c");

    public static final KeyValueObject API_NATIVE_COFFER_BUY = new KeyValueObject("c_b", "工资宝购买页", API_NATIVE_URL_DOMAIN + "c_b");

    public static final KeyValueObject API_NATIVE_COFFER_REDEEM = new KeyValueObject("c_r", "工资宝赎回页", API_NATIVE_URL_DOMAIN + "c_r");

    public static final KeyValueObject API_NATIVE_REGISTER = new KeyValueObject("l_r", "登录前注册页", API_NATIVE_URL_DOMAIN + "l_r");

    public static final KeyValueObject[] API_NATIVE_PAGES = {
            API_NATIVE_MY_MAIN,
            API_NATIVE_FEEDBACK_LIST,
            API_NATIVE_FEEDBACK_REPORT,
            API_NATIVE_NEWS_LIST,
            API_NATIVE_FINANCING_MAIN,
            API_NATIVE_MY_FINANCING,
            API_NATIVE_COFFER_MAIN,
            API_NATIVE_COFFER_BUY,
            API_NATIVE_COFFER_REDEEM,
            API_NATIVE_REGISTER
    };

    public static final KeyValueObject API_BANNER_ACTION_URL = new KeyValueObject("url", "打开网页", "请输入url");

    public static final KeyValueObject API_BANNER_ACTION_ALERT = new KeyValueObject("alert", "提示框", "请输入文本");

    public static final KeyValueObject API_BANNER_ACTION_NATIVE = new KeyValueObject("embed", "打开本地页面", "请选择跳转页面");

    public static final KeyValueObject[] API_BANNER_ACTIONS = {
            API_BANNER_ACTION_URL,
            API_BANNER_ACTION_ALERT,
            API_BANNER_ACTION_NATIVE
    };

    //手机星号代替起始
    public static final Integer MOBILE_STAR_START=3;

    public static final Integer MOBILE_STAR_LENGTH=4;

}
