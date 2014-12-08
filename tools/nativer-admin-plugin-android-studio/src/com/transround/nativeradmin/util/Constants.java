package com.transround.nativeradmin.util;

/**
 * Created by szeibert on 2014.11.24..
 */
public class Constants {    
    public final static String baseUrl = "http://nativer01.nativer.com/prod/";
    public final static String accountServiceUrl = baseUrl + "admin/index.php?route=account/accountservice/";
    public final static String eulaUrl = baseUrl + "index.php?route=common/infoservice/getEULA&language_code=en";
    public final static String uploadUrl = baseUrl + "index.php?route=product/uploadservice/uploadResources";
    public final static String helpUrl = baseUrl + "index.php?route=common/infoservice/getContextSensitiveHelp&language_code=en&page_name=nativer_admin_plugin";

    public final static String[] requiredPermissions = new String[]{"android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE"};
    public final static String[] buildDirectories = new String[]{"build", "bin"};
    public final static String[] stringElementNames = new String[]{"string", "string-array", "plurals"};

    public final static String aspectDroidRepository = "http://www.transround.com/repositories/public/";
    public final static String pluginRepositoryURL = "http://www.transround.com/repositories/public/";
    
    public final static String pluginName = "com.transround:nativer-sdk:1.0.+";
    
}
