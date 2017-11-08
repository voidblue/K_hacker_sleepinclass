package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.app.ProgressDialog;
import android.opengl.GLDebugHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;

import com.estimote.coresdk.repackaged.gson_v2_3_1.com.google.gson.JsonParser;
import com.example.voidbluelabtop.sleepinclass.Utils.Get_Current_Class;
import com.example.voidbluelabtop.sleepinclass.Utils.GlobalVariables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by voidbluelabtop on 17. 10. 21.
 */

public class GetData extends AsyncTask<String, Void, String> {
    String errorString = null;
    public String mJsonString;
    HashMap<String,String> hashedJson;
    int mode;
    String serverURL;
    String jasonTag;
    public void setMode(String mode){
        mJsonString = null;
        if (mode.equals("signedclass")){
            this.mode = 1;
            serverURL = GlobalVariables.URL + "getsignedclass.php";
            jasonTag = "signedclass";
        } else if(mode.equals("class")){
            this.mode = 2;
            serverURL = GlobalVariables.URL + "getclass.php";
            jasonTag = "class";
        } else if(mode.equals("student")){
            this.mode = 3;
            serverURL = GlobalVariables.URL + "getstudent.php";
            jasonTag = "student";
        } else if(mode.equals("attendant")){
            this.mode = 4;
            serverURL = GlobalVariables.URL + "getattendant.php";
            jasonTag = "attendant";
        }

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();


            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d("error", "response code - " + responseStatusCode);

            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }


            bufferedReader.close();
            mJsonString = sb.toString().trim();
            this.cancel(true);
            return sb.toString().trim();


        } catch (Exception e) {

            Log.d("error", "InsertData: Error ", e);
            errorString = e.toString();

            return null;
        }
    }


    public HashMap<String, String> process(){
        Log.d("", "process: " + mJsonString);
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(jasonTag);
            HashMap<String, String> hashMap = new HashMap<>();
            if (mode == 1) {
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject item = jsonArray.getJSONObject(i);

                    String classcode = item.getString("classcode");
                    String studentcode = item.getString("studentcode");

                    hashMap.put("classcode" + i, classcode);
                    hashMap.put("studentcode" + i, studentcode);
                }
            } else if (mode == 2){
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    String classname = item.getString("classname");
                    String date = item.getString("date");
                    String duration = item.getString("duration");
                    String classroom = item.getString("classroom");
                    String beaconmajor = item.getString("beaconmajor");
                    String classcode = item.getString("classcode");
                    String professorcode = item.getString("professorcode");

                    hashMap.put("classname" + i, classname);
                    hashMap.put("date" + i, date);
                    hashMap.put("duration" + i, duration);
                    hashMap.put("classroom" + i, classroom);
                    hashMap.put("beaconmajor" + i , beaconmajor);
                    hashMap.put("classcode" + i , classcode);
                    hashMap.put("professorcode" + i, professorcode);
                }
            } else if (mode == 3){
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject item = jsonArray.getJSONObject(i);

                    String studentname = item.getString("studentname");
                    String studentmajor = item.getString("studentmajor");
                    String studentcode = item.getString("studentcode");
                    String classcode = item.getString("classcode");

                    hashMap.put("studentname" + i, studentname);
                    hashMap.put("studentcode" + i, studentcode);
                    hashMap.put("studentmajor" + i, studentmajor);
                    hashMap.put("classcode" + i, classcode);
                }
            } else if (mode == 4){
                for (int i = 0; i < jsonArray.length(); i++) {
                    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    JSONObject item = jsonArray.getJSONObject(i);

                    String classcode = item.getString("classcode");
                    String strdate = item.getString("date");
                    String studentcode = item.getString("studentcode");
                    String ischecked = item.getString("ischecked");


                    hashMap.put("classcode" + i, classcode);
                    hashMap.put("date" + i, strdate);
                    hashMap.put("studentcode" + i, studentcode);
                    hashMap.put("ischecked" + i , ischecked);
                }
            }
            hashedJson = hashMap;
            return hashMap;
        } catch (JSONException e) {

            Log.d("tag", "showResult : ", e);
        }
        return null;
    }

    public HashMap<String, String> getHashedJson(){
        return hashedJson;
    }
}