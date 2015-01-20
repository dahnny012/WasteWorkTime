class WorkRate{
    float perSecond;
    float perMinute;
    float perHour;
    float dollarRate;
    
    public WorkRate(float ratePerHour)
    {
        this.perHour = ratePerHour;
        this.perMinute = ratePerHour/60;
        this.perSecond = ratePerHour/60*60;
        this.dollarRate = 3600 / ratePerHour;
    }
    
    
    public void update(float ratePerHour)
    {
        this.perHour = ratePerHour;
        this.perMinute = ratePerHour/60;
        this.perSecond = ratePerHour/60*60;
        this.dollarRate = 3600 / ratePerHour;
        
    }

}

public class Model{
    WorkRate workrate;
    int totalMade = 0;
    float timeElapsed = 0;
	String bangBang = "!";
    public Model(int rate){
        workrate = new WorkRate(rate);
    }
    public Model(float rate){
        workrate = new WorkRate(rate);
    }
    public Model(WorkRate rate)
    {
        this.workrate = rate;
    }
    public void updateWorkRate(int rate)
    {
        if(workrate == null){
            workrate = new WorkRate(rate);
        }
        else{
            workrate.update(rate);
        }
    }
    public void updateTotal(int add){
        // totalmade += add
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
}