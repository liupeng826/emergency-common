package com.emergency.common.constants;

public class CacheKeyConstants extends CommonConstants {

    public final static String PREFIX_USER = "CZB:USER:";
    public final static String PREFIX_GZB = "CZB:GZB:";

    public final static String USER_COMPANY_ALL_MAP = PREFIX_USER + "COMPANY_ALL_MAP";
    public final static Long USER_COMPANY_ALL_MAP_EXPIRE = (long) (24 * 60 * 60);

    public final static String USER_DTO_PREFIX = PREFIX_USER + "USER_DTO:";
    public final static Long USER_DTO_EXPIRE = (long) 30 * 60;

    public static final String USER_PASSWORD_ERROR_LOG_KEY_PREFIX = PREFIX_USER + "PASSWORD_ERROR_LOG:";
    public static final Long USER_PASSWORD_ERROR_LOG_KEY_EXPIRE = (long) (3 * 60 * 60);

    public static final String USER_SESSION_MAP_KEY_PREFIX = PREFIX_USER + ":USER_SESSION_MAP:";

    public static final String GZB_TRANSACTION_STATUS_KEY = "GZB:TRANSACTIONSTATUS";
    public static final Long GZB_TRANSACTION_STATUS_EXPIRE = (long) (24 * 60 * 60);

    public static final String USER_ANNOUNCEMENT_KEY = PREFIX_USER + "ANNOUNCEMENT";
    public static final Long USER_ANNOUNCEMENT_EXPIRE = (long) (24 * 60 * 60);

    public static final String USER_CHANNEL_PREFIX = PREFIX_GZB + "CHANNEL";
    public static final Long USER_CHANNEL_EXPIRE = 24 * 60 * 60l;

    public static final String USER_ASSET_PREFIX = PREFIX_GZB + "ASSET";
    public static final Long USER_ASSET_EXPIRE =  60 * 60l;


}
