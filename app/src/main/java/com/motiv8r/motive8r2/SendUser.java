package com.motiv8r.motive8r2;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class SendUser extends AsyncTask<Void, Void, String[]> {

    String username;
    String password;
    String mobile;
    String response;

    SendUser(String u,String p,String m) {
        username = u;password=p;mobile=m;

    }

    @Override
    protected String[] doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;



        try {
            final String FORECAST_BASE_URL = "http://ragven14.5gbfree.com/insert.php?";
            final String USER = "username";
            final String PASWD = "password";
            final String MOB = "mobile";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(USER,username)
                    .appendQueryParameter(PASWD, password)
                    .appendQueryParameter(MOB, mobile)
                    .build();

            URL url = new URL(builtUri.toString());
            Log.v("URLLLLLLLLLLL",url.toString());

            // Create the request
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            response=buffer.toString();

           // Log.v("TTTTTTTTTTTTTTTTTTTTTT", messages);

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
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

        String[] res = response.split(":");

        return res;

    }




}
