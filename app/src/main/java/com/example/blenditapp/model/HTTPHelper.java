package com.example.blenditapp.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HTTPHelper extends AsyncTask<String, Void, String> {

    static String url = "";
    TextView tvAddress;

    public HTTPHelper(TextView tvAddress, String value){
        this.tvAddress = tvAddress;
        url = "https://viacep.com.br/ws/"+value+"/json/";
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params){
        String json = null;
        try {
            URL objUrl = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) objUrl.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            json = convertStreamToString(in);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(json != null){
                JSONObject oAdress = new JSONObject(json);
                return (oAdress.getString("logradouro") + ", " + oAdress.getString("bairro") + ", " + oAdress.getString("localidade"));
            }else{
                return "CEP não encontrado";
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String str){
        tvAddress.setText(str);
    }


    private static String convertStreamToString(InputStream is){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        return sb.toString();
    }

}