package com.example.danh.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends ActionBarActivity {

    Controller control;
    EditText userWage;
    AppView view = AppView.getInstance();
    private NotificationCompat.Builder mBuilder;
    private Handler handler = new Handler();
    private Runnable updater = new Runnable(){
        @Override
        public void run() {
            AppView view = AppView.getInstance();
            int interval = view.getInterval();
            if(view.stop)
                return;
            handler.postDelayed(this, interval);
            float timeToDollar = view.data.getRate("d");
            if(view.data.timeElapsed >= timeToDollar){
                view.data.timeElapsed -= timeToDollar;
                view.addMoney();
                view.addBang();
                view.update("You made "+view.data.totalMade+" dollars");
                //System.out.println("Made " + view.data.totalMade + " dollars " + view.data.bangBang);
            }
            view.timeUpdate();
            view.data.timeElapsed += interval/1000;

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Save alert box
        TextView text = (TextView)findViewById(R.id.dollarsView);
        TextView time = (TextView)findViewById(R.id.timeLeft);
        // Set up spinner template
        Spinner spinner = (Spinner) findViewById(R.id.profiles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.profiles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                handler.removeCallbacksAndMessages(null);
                control.loadTemplate(position, updater);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        // Start up the controller and save the userWage box
        control = new Controller(text,time);
        userWage = (EditText)findViewById(R.id.userWage);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startTimer(View v){
        String arg = userWage.getText().toString();
        handler.removeCallbacksAndMessages(null);
        control.getInput(arg,updater);
        userWage.setText("");
    }

    public void stopTimer(View v){
        view.stop();
        handler.removeCallbacksAndMessages(null);
    }

    public void checkSalary(View v){
        if(control.salary) {
            ((CheckBox)v).setChecked(false);
        }
        boolean checked = ((CheckBox) v).isChecked();
        control.salary = checked;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
