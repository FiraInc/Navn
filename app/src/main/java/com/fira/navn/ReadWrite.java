package com.fira.navn;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class ReadWrite {

    public static String read(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            Log.e("IJSAOJDSOS", sb.toString() + "222");
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "3";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "3";
        } catch (IOException e) {
            e.printStackTrace();
            return "3";
        }
    }

    public static void write (Context context, String filename, String textToWrite) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename , Context.MODE_PRIVATE);
            outputStream.write(textToWrite.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
