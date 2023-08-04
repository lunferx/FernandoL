package com.example.tvintento2;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "HttpRequestTask";
    private static final String BEARER_PREFIX = "Bearer";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private String requestMethod = "GET";
    private String token;
    private String jsonBody;
    private OnHttpRequestCompleteListener listener;

    public HttpRequestTask(String requestMethod, String jsonBody, OnHttpRequestCompleteListener listener) {
        this.requestMethod = requestMethod;
        this.jsonBody = jsonBody;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        if (urls.length == 0)
            return null;

        String urlString = urls[0];
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            /*if (token != null && !token.isEmpty()) {
                String authorizationHeader = BEARER_PREFIX + " " + token;
                connection.setRequestProperty(AUTHORIZATION_HEADER, authorizationHeader);
            }*/
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } catch (IOException e) {
            listener.onHttpRequestError(e.getMessage());
            Log.e(TAG, "Error occurred during HTTP request: " + e.getMessage());
        } /*catch (JSONException e) {
            listener.onHttpRequestError(e.getMessage());
            throw new RuntimeException(e);
        }*/

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener != null) {
            listener.onHttpRequestComplete(result);
        } else {
            listener.onHttpRequestError("Request failed");
        }
    }

    public interface OnHttpRequestCompleteListener {
        void onHttpRequestComplete(String response);
        void onHttpRequestError(String error);
    }
}