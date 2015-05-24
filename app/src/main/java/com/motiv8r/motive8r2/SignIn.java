package com.motiv8r.motive8r2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

//package com.raghavendra.mychat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignIn extends ActionBarActivity {

    //public SignIn() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);


        setContentView(R.layout.signin);
        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SettingsFragment())
                    .commit();
        }*/
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

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_refresh) {
            FetchData myData = new FetchData(MsgDetail.frm_num);
            myData.execute();
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }
/*
    public void myNumber(View view) {
        final EditText e;
        e = (EditText) findViewById(R.id.editText2);

        MsgDetail.frm_num = e.getText().toString();
    }

    public void addNumber(View view) {
        final EditText e2;
        e2 = (EditText) findViewById(R.id.editText3);
        String temp = e2.getText().toString();
        //MsgDetail.msgAdapter.clear();
        MessageFragment.contactAdapter.add(temp);
    }

*/
    /*
private class SettingsFragment extends Fragment {

        public SettingsFragment() {

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);


        }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it is present.
            //inflater.inflate(R.menu.my_menu, menu);

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_refresh) {
                FetchData myData = new FetchData(MsgDetail.frm_num);
                myData.execute();
                return true;
            }

            //return super.onOptionsItemSelected(item);
            return true;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.signin, container, false);


            return rootView;


        }public void addNumber(View view) {
        final EditText e2;
        e2 = (EditText) findViewById(R.id.editText3);
        String temp = e2.getText().toString();
        //MsgDetail.msgAdapter.clear();
        MessageFragment.contactAdapter.add(temp);
    }

    }*/
public void logIn(View view) {


    final EditText e1, e2;
    e1 = (EditText) findViewById(R.id.edittext4);
    e2 = (EditText) findViewById(R.id.edittext5);

    FetchUser data = new FetchUser(e1.getText().toString(), e2.getText().toString());
    data.execute();

    Intent signIn = new Intent(SignIn.this, SenderActivity.class);
    startActivity(signIn);
}

    /*public void logInc(View view){
    Intent signIn = new Intent(SignIn.this, SenderActivity.class);
    startActivity(signIn);
}*/

    public void signUp(View view) {


        setContentView(R.layout.signup);
    }
    public void logInb(View view) {


        setContentView(R.layout.signin);
    }
    public void signUpb(View view) {

        final EditText e1,e2,e3;
        e1 = (EditText) findViewById(R.id.edittext1);
        e2 = (EditText) findViewById(R.id.edittext2);
        e3 = (EditText) findViewById(R.id.edittext3);

        SendUser myData = new SendUser(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
        myData.execute();
        setContentView(R.layout.signin);

    }


}