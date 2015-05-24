package com.motiv8r.motive8r2;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class FetchUser extends AsyncTask<Void, Void, String[]> {
    //MsgDetail ms = new MsgDetail();
    String username;
    String password;
    String response;



    FetchUser(String u,String p) {
        username=u;password=p;
    }


/*
    Context context;
    private FetchUser(Context context) {
        this.context = context.getApplicationContext();
    }
*/
    @Override
    protected String[] doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String key ="c420d8cd-84ad-450c-afc7-76c465cb4c51";
        String text = "I feel really sad";

        try {

            final String FORECAST_BASE_URL = "http://ragven14.5gbfree.com/r.php?";
            final String USER = "username";
            final String PASWD = "password";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(USER,username)
                    .appendQueryParameter(PASWD, password)
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

        //super.onPostExecute(result);
       // MainActivity.progressDialog.dismiss();

        if (result[1] != "1") {


           // context.startActivity(new Intent(context, SenderActivity.class));
        }
    }

}
