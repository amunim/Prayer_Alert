package com.amfk.prayer_alert;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class GetJSONString extends AsyncTask<String, Void, String> {
    public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(String... requestUrl) {
        String result = null;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(requestUrl[0]);

        try {
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            result = httpClient.execute(request, responseHandler);
        } catch (IOException e) {
            Log.e("reqStringFromWebServer", "Whoops!", e);
        }

        httpClient.getConnectionManager().shutdown();

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            // Handle the result from your request here...
            delegate.processFinish(result);
        }
    }
}
