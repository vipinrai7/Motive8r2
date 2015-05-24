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

class Compare extends AsyncTask<Void, Void, String[]> {

    String res;
    String response = null;
    String text;

    Compare(String t, String r) {
        text = t;res = r;
    }


    @Override
    protected String[] doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String key ="c420d8cd-84ad-450c-afc7-76c465cb4c51";


        try {

            final String FORECAST_BASE_URL = "http://ragven14.5gbfree.com/r2.php?";
            final String EMO = "emo";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(EMO, text)
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





        String[] r;
        r=response.split(":");
        return r;

    }


    @Override
    protected void onPostExecute(String[] result) {
        //res =result[0];

        //if (result != null) {
        // SenderActivity.msgAdapter.clear();
        res =   result[0];
        SenderActivity.msgAdapter.add(res);

        // SenderActivity.msgAdapter2.add(text);
        //}
    }
}
