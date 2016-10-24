package com.emergency.common.captcha;

/**
 * Created by yujl on 5/14/15.
 */
public class CaptchaType {

    public final static String CAPTCHA_TYPE_RESET_PASSWORD = "user:resetPassword";
    public final static String CAPTCHA_TYPE_UPDATE_PHONE_OLD_PHONE = "user:updatePhone:oldPhone";
    public final static String CAPTCHA_TYPE_UPDATE_PHONE_NEW_PHONE = "user:updatePhone:newPhone";
    public final static String CAPTCHA_TYPE_REGISTER = "user:register";
    public final static String CAPTCHA_TYPE_API_RESET_PASSWORD = "api:user:resetPassword";
    public final static String CAPTCHA_TYPE_API_REGISTER = "api:user:register";
    public final static String CAPTCHA_TYPE_ASSET_PROOF="user:assetProof";

}
