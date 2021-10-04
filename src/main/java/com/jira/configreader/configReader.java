package com.jira.configreader;

import com.jira.utils.apiModule;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class configReader {


    Properties prop = new Properties();

    public void readConfig() {
        try {
            InputStream ip = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
            prop.load(ip);

            apiModule.baseUrl = prop.getProperty("baseURL");
            apiModule.username = prop.getProperty("Username");
            apiModule.password = prop.getProperty("Password");
            apiModule.Jquery = prop.getProperty("JQuery");

            ip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
