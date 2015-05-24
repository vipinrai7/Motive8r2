package com.motiv8r.motive8r2;

        import android.support.v7.app.ActionBarActivity;
        import android.view.LayoutInflater;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.TextView;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;


/**
 * Created by raghavendra on 5/16/2015.
 */
public class SenderActivity extends ActionBarActivity {
    public SenderActivity() {
    }
   /* public static  String frm_num="9633104690";
    public static String to_num = "8861515071";

*/
    public static String[] msg_lst = {"welcome","Hi how are you"};
    public  static ArrayAdapter<String> msgAdapter;
    public  static ArrayAdapter<String> msgAdapter2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);


        setContentView(R.layout.activity_listmain);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.listmain, new DetailFragment())
                    .commit();
        }
    }


    /*@Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.my_menu, menu);

    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
/*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            FetchData myData = new FetchData(frm_num);
            myData.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
  */return true;
    }


    public void sendMessage(View view) {
        final EditText editText;
        //  ImageButton imageButton;
        editText = (EditText) findViewById(R.id.edittext6);
        //imageButton = (ImageButton) findViewById(R.id.imageButton);


       FetchData data = new FetchData(editText.getText().toString());
        data.execute();


        editText.setText("");

    }



    public class DetailFragment extends Fragment {

        public DetailFragment() {

        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);


        }





        @Override
        public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it is present.
           // inflater.inflate(R.menu.my_menu, menu);

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
/*
            //noinspection SimplifiableIfStatement
            if (id == R.id.action_refresh) {
                FetchData myData =new FetchData(frm_num);
                myData.execute();
                return true;
            }

            return super.onOptionsItemSelected(item);
 */ return true;
        }




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {




            View rootView = inflater.inflate(R.layout.sender, container, false);


            List<String> my_msg = new ArrayList<String>(
                    Arrays.asList(msg_lst)
            );

            msgAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.list_item,
                    R.id.textview2,
                    my_msg
            );

           /* msgAdapter2 = new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.listitem2,
                    R.id.textview8,
                    my_msg
            );
*/

            ListView listView = (ListView) rootView.findViewById(R.id.my_detail);
            listView.setAdapter(msgAdapter);
            //listView.setAdapter(msgAdapter2);

//            listView.setAdapter(msgAdapter2);


            return rootView;



        }
    }
}