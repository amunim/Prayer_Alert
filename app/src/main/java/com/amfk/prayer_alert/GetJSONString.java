package com.amfk.prayer_alert;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GetJSONString extends AsyncTask<String, Void, String> {
    public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(String... requestUrl) {

        try{
            String out = new Scanner(new URL(requestUrl[0]).openStream(), "UTF-8").useDelimiter("//A").next();
            android.util.Log.w("output", out);
            return out;
        }
        catch (IOException ioe){
            throw new RuntimeException(ioe);
        }

    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            // Handle the result from your request here...
            delegate.processFinish(result);
        }
    }
}
