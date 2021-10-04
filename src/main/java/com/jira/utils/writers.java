package com.jira.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class writers {
    public static void writeStringToFile(String filepath,String fileContent){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filepath));
            out.write(fileContent);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
