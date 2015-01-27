package com.example.danh.app;



import android.widget.TextView;

/**
 * Created by danh on 1/21/2015.
 */



public class Controller{

    // Singleton AppView
    AppView view;
    boolean invalid = true;
    boolean salary = false;
    boolean running = false;
    public Controller(TextView textbox,TextView time){
        view = AppView.getInstance();
        view.addTextBox(textbox);
        view.addTimeBox(time);
    }
    public void getInput(String num,Runnable updater){
        String[]args = num.split(" ");
        if(running)
            view.resetTime();
        try{
            Model data = new Model(Float.parseFloat(num),salary);
            initApp(data,updater);
        }
        catch(NumberFormatException e){
            view.stop();
        }
    }

    public void loadTemplate(int position,Runnable updater){
        if(position == 0) {
            view.stop();
            return;
        }
        if(running)
            view.resetTime();
        Model model = new Model(view.templates[position],false);
        initApp(model,updater);
    }
    public void initApp(Model model,Runnable updater){
        view.addData(model);
        view.start();
        updater.run();
        running = true;
    }



    public float calculate(String s){
        return view.data.getRate(s);
    }
}