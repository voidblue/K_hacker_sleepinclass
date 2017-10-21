package com.example.voidbluelabtop.sleepinclass.DATABASE;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;

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

    @Override
    protected void onPreExecute() {
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressDialog.dismiss();
        Log.d("resp", "response  - " + result);

        if (result == null){
            Log.d("null error", "onposetexcute error");
        }
        else {
            mJsonString = result;
            showResult();
        }

    }


    @Override
    protected String doInBackground(String... params) {

        String serverURL = params[0];


        try {

            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();


            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d("error", "response code - " + responseStatusCode);

            InputStream inputStream;
            if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            }
            else{
                inputStream = httpURLConnection.getErrorStream();
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
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


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String name = item.getString("classcode");
                String address = item.getString("studentcode");

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_NAME, name);
                hashMap.put(TAG_ADDRESS, address);
\
            }
        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
