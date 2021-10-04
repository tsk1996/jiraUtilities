package com.jira.searchModule;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jira.utils.apiModule;
import com.jira.utils.writers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class searchSchema {
    static HttpResponse<JsonNode> response=null;
    public static String jsonPath=System.getProperty("user.dir") + "/results/jsonSchema.json";

    public static void loadJsonSchema(){
        apiModule apiObj=new apiModule();
        response=apiObj.getJsonResponse(schemaPayload(),"search");

        String jsonSchema=response.getBody().getObject().get("schema").toString();

        writers.writeStringToFile(jsonPath,jsonSchema);

        System.out.println(jsonSchema);
    }

    public static ObjectNode schemaPayload(){
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();

        {
			ArrayNode expand = payload.putArray("expand");
			expand.add("schema");
            payload.put("jql", "");
            payload.put("maxResults", 1);
            ArrayNode fields = payload.putArray("fields");
            fields.add("*all");
            payload.put("startAt", 0);
        }
        return payload;
    }
}
