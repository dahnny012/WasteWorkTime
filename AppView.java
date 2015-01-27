package com.example.danh.app;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;

public class AppView{
    Model data;
    TextView text;
    TextView time;
    private static AppView view;
    boolean stop = false;
    float[] templates = {0,3600,7.5f,1800.f,15.f};
    private AppView(){
    }

    public static AppView getInstance(){
        if(view == null)
            view = new AppView();
        return view;
    }

    public void addTextBox(TextView text){
        this.text = text;
    }
    public void addTimeBox(TextView time) {
        this.time = time;
    }
    public void addData(Model data){
        if(this.data != null)
            data.totalMade = this.data.totalMade;
        this.data = data;
    }

    public void addBang(){
        this.data.bangBang += "!";
    }

    public void addMoney(){
        data.totalMade += data.incrementer;
    }
    public void display(){
        System.out.println("Total made: " + data.totalMade);
    }
    public void edit(String rate){
        try{
            float num = Float.parseFloat(rate);
            Model newData = new Model(num,data.type);
            addData(newData);
        }
        catch(NumberFormatException e){
            System.out.println("Enter a valid rate");
        }
    }
    public void update(String rate){
        this.text.setText(rate);
    }
    public void stop(){
        stop = true;
    }
    public void start(){
        stop = false;
    }
    public void resetTime(){
        data.timeElapsed = 0;
    }
    public void timeUpdate(){
        this.time.setText(view.data.timeLeft());
    }
    public int getInterval() {
        return data.interval;
    }


}