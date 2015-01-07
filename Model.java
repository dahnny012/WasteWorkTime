class WorkRate{
    float perSecond;
    float perMinute;
    float perHour;
    public WorkRate(double ratePerHour)
    {
        this.perHour = ratePerHour;
        this.perMinute = ratePerHour/60;
        this.perSecond = ratePerHour/60*60;
    }

}

public class Model{
    
}