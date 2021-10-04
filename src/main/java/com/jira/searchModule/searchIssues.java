package com.jira.searchModule;

import com.jira.utils.apiModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class searchIssues {
    static HttpResponse<JsonNode> response=null;

    public static void searchTicket(String ticket){
        apiModule apiObj=new apiModule();
        response=apiObj.getJsonResponse(ticket);

        System.out.printf(response.getBody().toString());
    }
}
