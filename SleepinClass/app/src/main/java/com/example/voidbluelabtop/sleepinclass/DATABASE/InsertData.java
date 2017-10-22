package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.os.AsyncTask;
import android.util.Log;

import com.estimote.mgmtsdk.feature.settings.api.Eddystone;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.id.input;

/**
 * Created by voidbluelabtop on 17. 10. 21.
 */

public class InsertData extends AsyncTask<String, Void, String>{
    int mode;

    public InsertData(String mode){
        if ( mode.equals("student")){
            this.mode = 1;
        }
        else if(mode.equals("class")){
            this.mode = 2;
        }
        else if(mode.equals("attendant")){
            this.mode = 3;
        }
        else{
        //TODO 에러 출력좀
        }
    }
    @Override
    protected String doInBackground(String... strings) {
        String postParameters = "";
        String extramode = "";
        if (this.mode == 1) {
            String name = strings[0];
            String major = strings[1];
            String code = strings[2];
            postParameters = "studentname=" + name + "&studentmajor=" + major + "&studentcode=" + code;
            extramode = "insertstudent.php";
        }
        else if(this.mode == 2){
            String name = strings[0];
            String time = strings[1];
            String room = strings[2];
            String beaconmajor = strings[3];
            String classcode = strings[4];
            postParameters = "classname=" + name + "&time=" + time + "&classroom=" + room +
                            "&beaconMajor=" + beaconmajor + "&classcode=" +  classcode;
            extramode = "insertclass.php";

        }
        else if(this.mode == 3){
            String classcode = strings[0];
            String date = strings[1];
            String studentcode = strings[2];
            String ischecked = strings[3];
            postParameters = "classcode=" + classcode + "&date=" + date + "&studentcode=" + studentcode +
                            "&ischecked=" + ischecked;
            extramode = "insertattendant.php";
        }

        String strurl = GlobalVariables.URL+ extramode;
        try{
            URL url = new URL(strurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(postParameters.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            int resposeStatusCode = httpURLConnection.getResponseCode();
            Log.d("server", "POST response code - " + resposeStatusCode);

            InputStream inputStream;
            if(resposeStatusCode == httpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
            }
            else{
                inputStream = httpURLConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder SB = new StringBuilder();
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                SB.append(line);
            }
            bufferedReader.close();
            return SB.toString();


        } catch (Exception e) {
            Log.d("connection error", "InsertData: Error ", e);

            return new String("Error: " + e.getMessage());
        }
    }

    public static void main(String args[]){
        InsertData id = new InsertData("student");
        id.execute("이름","전공","코드");


    }

}
