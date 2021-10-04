package com.jira.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jira.configreader.configReader;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class apiModule {
    public static String baseUrl = null;
    public static String username = null;
    public static String password = null;
    public static String Jquery = null;
    HttpResponse<JsonNode> response=null;

    public apiModule(){
        configReader config=new configReader();
        config.readConfig();
        setObjectMapper();
    }
    public HttpResponse<JsonNode> getJsonResponse(String ticket){
        Unirest.setHttpClient(setIgnoreCookies());
        try {
            response = Unirest.get(baseUrl + "issue/"+ ticket)
                    .basicAuth(username, password)
                    .header("Accept", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return response;
    }
    public HttpResponse<JsonNode> getJsonResponse(ObjectNode payload, String APIMethod){
        Unirest.setHttpClient(setIgnoreCookies());
        try {
            response = Unirest.post(baseUrl + APIMethod).basicAuth(username, password)
                    .header("Accept", "application/json").header("Content-Type", "application/json")
                    .body(payload)
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response;
    }

    public HttpClient setIgnoreCookies(){
        RequestConfig globalConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        HttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
        return httpclient;
    }

    public void setObjectMapper(){
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
