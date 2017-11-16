package r201.vista.lake.listapp.Utils;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by saii on 11/11/17.
 */

public class Log {

    private static Log instance;
    private static boolean loggable = false;
    private static BufferedWriter br;
    private static Date d;

    private Log(){}

    public static Log getInstance(){
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void initializeLogging() throws IOException {
        br = new BufferedWriter(new FileWriter("log.txt", true));
        loggable = true;
    }

    private static void logMsg(Class cl, String msg){

        if(loggable) {
            d = new Date();
            StringBuilder s = new StringBuilder(String.valueOf(d.getTime()));
            s.append('\t');
            s.append(cl.getSimpleName());
            s.append('\t');
            s.append('m');
            s.append('\t');
            s.append(msg);

            try {
                br.write(s.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void logErr(Class cl, String err) {

        if (loggable) {
            d = new Date();
            StringBuilder s = new StringBuilder(String.valueOf(d.getTime()));
            s.append('\t');
            s.append(cl.getSimpleName());
            s.append('\t');
            s.append('e');
            s.append('\t');
            s.append(err);

            try {
                br.write(s.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
