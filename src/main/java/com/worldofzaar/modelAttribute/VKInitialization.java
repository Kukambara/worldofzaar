package com.worldofzaar.modelAttribute;

import com.worldofzaar.util.HashConverter;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 25.12.13
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
public class VKInitialization implements Validation {

    private final String API_SECRET = "Y3dzbz9fc98H4bUDWbkW";
    private String api_url;
    private int api_id;
    private int api_settings;
    private int viewer_id;
    private int viewer_type;
    private String sid;
    private String secret;
    private String access_token;
    private int user_id;
    private int group_id;
    private int is_app_user;
    private String auth_key;
    private int language;
    private int parent_language;
    private String ad_info;
    private int is_secure;
    private String ads_app_id;
    private String referrer;
    private String lc_name;
    private String hash;

    public VKInitialization() {
        api_url = "-";
        api_id = -1;
        api_settings = -1;
        viewer_id = -1;
        viewer_type = -1;
        sid = "-";
        secret = "-";
        access_token = "-";
        user_id = -1;
        group_id = -1;
        is_app_user = -1;
        auth_key = "-";
        language = -1;
        parent_language = -1;
        ad_info = "-";
        is_secure = -1;
        ads_app_id = "-";
        referrer = "-";
        lc_name = "-";
        hash = "-";
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_settings() {
        return api_settings;
    }

    public void setApi_settings(int api_settings) {
        this.api_settings = api_settings;
    }

    public int getViewer_id() {
        return viewer_id;
    }

    public void setViewer_id(int viewer_id) {
        this.viewer_id = viewer_id;
    }

    public int getViewer_type() {
        return viewer_type;
    }

    public void setViewer_type(int viewer_type) {
        this.viewer_type = viewer_type;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getIs_app_user() {
        return is_app_user;
    }

    public void setIs_app_user(int is_app_user) {
        this.is_app_user = is_app_user;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getParent_language() {
        return parent_language;
    }

    public void setParent_language(int parent_language) {
        this.parent_language = parent_language;
    }

    public String getAd_info() {
        return ad_info;
    }

    public void setAd_info(String ad_info) {
        this.ad_info = ad_info;
    }

    public int getIs_secure() {
        return is_secure;
    }

    public void setIs_secure(int is_secure) {
        this.is_secure = is_secure;
    }

    public String getAds_app_id() {
        return ads_app_id;
    }

    public void setAds_app_id(String ads_app_id) {
        this.ads_app_id = ads_app_id;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getLc_name() {
        return lc_name;
    }

    public void setLc_name(String lc_name) {
        this.lc_name = lc_name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean valid() {
        if (HashConverter.md5(api_id + "_"+viewer_id + "_"+API_SECRET).equals(auth_key))
            return true;
        return false;
    }
}
