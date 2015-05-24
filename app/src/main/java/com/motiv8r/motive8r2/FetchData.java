package com.motiv8r.motive8r2;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.String;
import org.json.*;

class FetchData extends AsyncTask<Void, Void, String[]> {

    String res;
    String response = null;
    String text;

    FetchData(String t) {
        text = t;
    }


    @Override
    protected String[] doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String key ="c420d8cd-84ad-450c-afc7-76c465cb4c51";


        try {

            final String FORECAST_BASE_URL = "https://api.idolondemand.com/1/api/sync/analyzesentiment/v1?";
            final String TEXT = "text";
            final String LANGUAGE = "language";
            final String API_KEY = "apikey";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(TEXT, text)
                    .appendQueryParameter(LANGUAGE, "eng")
                    .appendQueryParameter(API_KEY, key)
                    .build();

            URL url = new URL(builtUri.toString());

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                response = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                response = null;
            }
            response = buffer.toString();
            Log.v("TTTTTTTTTTTTTTTTTTTTTT", response);

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            response = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }





        try {
            JSONObject obj =new JSONObject(response);
            String result =obj.getString("positive");
           /* if(result ==  "[]"){
                result = obj.getString("negative");
            }*/
            Log.v("SSSSSSSSSS",result);


            JSONArray jArray = new JSONArray(result);
            if(jArray.length() == 0){result = obj.getString("negative");
                jArray = new JSONArray(result);
            }
            if(jArray.length() == 0){
                result = obj.getString("aggregate");
                JSONObject obj2 =new JSONObject(result);
                res=obj2.getString("sentiment");
                return null;
            }

            for(int i=0;i<jArray.length();i++){


                JSONObject json_data = jArray.getJSONObject(i);
                res = json_data.getString("sentiment");
                Log.v("SSSSSSSSSS",res);


            }

            } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
            // Do something to recover ... or kill the app.
        }

        return null;

    }


    @Override
    protected void onPostExecute(String[] result) {
        //if (result != null) {
           // SenderActivity.msgAdapter.clear();
        //res = text + "\n" + res;
        Compare c = new Compare(res, text + "\n" + res);
        c.execute();

        SenderActivity.msgAdapter.add(text + "\n" + res);

           // SenderActivity.msgAdapter2.add(text);
        //}
    }
}
