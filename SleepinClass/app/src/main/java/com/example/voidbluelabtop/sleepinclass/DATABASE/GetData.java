package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.app.ProgressDialog;
import android.opengl.GLDebugHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;

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
import java.util.HashMap;

/**
 * Created by voidbluelabtop on 17. 10. 21.
 */

public class GetData extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    String errorString = null;
    String mJsonString;
    HashMap<String,String> hashedJson;
    int mode;
    String serverURL;
    String jasonTag;
    @Override
    protected void onPreExecute() {

    }


    @Override
    protected void onPostExecute(String result) {
        process();
    }
    public void setMode(String mode){
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


            return sb.toString().trim();


        } catch (Exception e) {

            Log.d("error", "InsertData: Error ", e);
            errorString = e.toString();

            return null;
        }
    }


    private HashMap<String, String> process(){
        try {
            JSONObject jsonObject = new JSONObject();
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
                    String time = item.getString("time");
                    String classroom = item.getString("classroom");
                    String beaconmajor = item.getString("beaconmajor");
                    String classcode = item.getString("classcode");

                    hashMap.put("classname" + i, classname);
                    hashMap.put("time" + i, time);
                    hashMap.put("classroom" + i, classroom);
                    hashMap.put("beaconmajor" + i , beaconmajor);
                    hashMap.put("classcode" + i , classcode);
                }
            } else if (mode == 3){
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject item = jsonArray.getJSONObject(i);

                    String classcode = item.getString("classcode");
                    String studentcode = item.getString("studentcode");


                    hashMap.put("classcode" + i, classcode);
                    hashMap.put("studentcode" + i, studentcode);
                }
            } else if (mode == 4){
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject item = jsonArray.getJSONObject(i);

                    String classcode = item.getString("classcode");
                    String studentcode = item.getString("studentcode");


                    hashMap.put("classcode" + i, classcode);
                    hashMap.put("studentcode" + i, studentcode);
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