package com.example.todolist;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    public static void writeToFiles(Context context, String fileName, String content) throws IOException {
        File path = context.getFilesDir();
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path, fileName));
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }
    public static void writeToJson(Context context, Object object, String fileName) throws IOException{
        Gson gson = new Gson();
        String j = gson.toJson(object);
        FileWriter.writeToFiles(context, "list_data.json", j);
    }
}
