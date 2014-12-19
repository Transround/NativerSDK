package com.transround.nativeradmin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by szeibert on 2014.11.24..
 */
public class Constants {
    private static Properties properties;
    //public final static String baseUrl = "http://192.168.0.46/dev/";
    public static String baseUrl = "http://nativer01.nativer.com/prod/";
    //public final static String baseUrl = "http://nativer02.nativer.com/prod/";
    public static String accountServiceUrl = baseUrl + "admin/index.php?route=account/accountservice/";
    public static String eulaUrl = baseUrl + "index.php?route=common/infoservice/getEULA&language_code=en";
    public static String uploadUrl = baseUrl + "index.php?route=product/uploadservice/uploadResources";
    public static String helpUrl = baseUrl + "index.php?route=common/infoservice/getContextSensitiveHelp&language_code=en&page_name=nativer_admin_plugin";

    public static String[] requiredPermissions = new String[]{"android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE"};
    public static String[] buildDirectories = new String[]{"build", "bin"};
    public static String[] stringElementNames = new String[]{"string", "string-array", "plurals"};

    public static String aspectDroidRepository = "http://www.transround.com/repositories/public/";
    public static String pluginRepositoryURL = "http://www.transround.com/repositories/public/";
    //public final static String pluginRepositoryURL = "http://www.transround.com/repositories/test/"; // test repository
    public static String pluginName = "com.transround:nativer-sdk:1.0.+";
    //public final static String pluginName = "com.transround:nativer-sdk:1.0.1-nativer02"; // test plugin

    static {
        properties = new Properties();
        try {
            properties.load(Constants.class.getResourceAsStream("/nativer.properties"));
            baseUrl = properties.getProperty("base.url");
            accountServiceUrl = baseUrl + properties.getProperty("accountservice.url");
            eulaUrl = baseUrl + properties.getProperty("eula.url");
            uploadUrl = baseUrl + properties.getProperty("uploadservice.url");
            helpUrl = baseUrl + properties.getProperty("help.url");
            aspectDroidRepository = properties.getProperty("repository.aspectdroid");
            pluginRepositoryURL = properties.getProperty("repository.plugin");
            pluginName = properties.getProperty("pluginversion");
        } catch (IOException e) {
        }
    }
}
