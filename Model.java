package com.example.danh.app;

/**
 * Created by danh on 1/21/2015.
 */
class WorkRate{
    float perSecond;
    float perMinute;
    float perHour;
    float dollarRate;
    int hour = 3600;
    public WorkRate(float ratePerHour,boolean salary)
    {
        float salaryRate = ratePerHour;
        if(salary == true){
            ratePerHour =  (salaryRate/ 52)/40;
        }
        this.perHour = ratePerHour;
        this.perMinute = ratePerHour/60;
        this.perSecond = ratePerHour/60*60;
        this.dollarRate = hour / ratePerHour;
    }

    public WorkRate(float ratePerHour)
    {
        this.perHour = ratePerHour;
        this.perMinute = ratePerHour/60;
        this.perSecond = ratePerHour/60*60;
        this.dollarRate = hour / ratePerHour;
    }

    public void update(float ratePerHour,boolean salary)
    {
        float salaryRate = ratePerHour;
        if(salary == true){
            ratePerHour =  (salaryRate/ 52)/40;
        }
        this.perHour = ratePerHour;
        this.perMinute = ratePerHour/60;
        this.perSecond = ratePerHour/60*60;
        this.dollarRate = hour / ratePerHour;
    }

}

public class Model{
    WorkRate workrate;
    int totalMade = 0;
    float timeElapsed = 0;
    String bangBang = "";
    boolean type = false;
    int incrementer = 1;/// 1 dollar
    int interval = 1000; // 1 second

    public Model(int rate){
        workrate = new WorkRate(rate);
    }
    public Model(float rate,boolean type){
        this.type = type;
        workrate = new WorkRate(rate,type);
        while(workrate.dollarRate < 1) {
            incrementer *= 10;
            workrate.hour *= incrementer;
            workrate.update(rate,type);
        }
    }
    public Model(WorkRate rate)
    {
        this.workrate = rate;
    }
    public float getRate(String str){
        switch(str){
            case "h":
                return workrate.perHour;
            case "m":
                return workrate.perMinute;
            case "s":
                return workrate.perSecond;
            default:
                return workrate.dollarRate;
        }
    }

    public String timeLeft(){

        return (Math.round(workrate.dollarRate - timeElapsed)) + " s";
        // elapsed vs workrate.dollarRate + " s";
    }
}

