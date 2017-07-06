package com.fira.navn;

import android.content.Context;
import java.util.Calendar;
import android.util.Log;

import java.io.BufferedReader;
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
            return "0";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
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

    public static int calculateHours(int lastDate, int lastHour) {
        int hoursGone = 0;

        Calendar calendar = Calendar.getInstance();
        int nowDate = calendar.get(Calendar.DAY_OF_YEAR);
        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);


        if (lastDate == 0 && lastHour == 0) {
            hoursGone = 0;
        }else {
            if (nowDate == lastDate) {
                if (nowHour == lastHour) {
                    hoursGone = 0;
                }else {
                    hoursGone = nowHour - lastHour;
                }
            }else if (nowDate > lastDate) {
                int daysGone = nowDate-lastDate;
                int hoursGone2 = 0;
                if (daysGone > 1) {
                    hoursGone2 = (daysGone * 24) - 24;
                }
                hoursGone = 24-lastHour + nowHour;
                hoursGone = hoursGone + hoursGone2;
            }
        }
        return hoursGone;
    }

    public static int calculateMinutes(int lastDate, int lastHour, int lastMinute) {
        int minutesGone;
        int hoursGone;

        Calendar calendar = Calendar.getInstance();
        int nowMinute = calendar.get(Calendar.MINUTE);

        hoursGone = calculateHours(lastDate, lastHour);

        if (lastMinute > nowMinute) {
            minutesGone = hoursGone*60 + ((60-lastMinute) + nowMinute)-60;
        }else if (nowMinute > lastMinute) {
            minutesGone = hoursGone*60 + (nowMinute-lastMinute);
        }else {
            minutesGone = 0;
        }

        return minutesGone;
    }
}
