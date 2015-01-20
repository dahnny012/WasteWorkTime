
// One thread for now. No gui
//class AppThread implements Runnable{
    
//}
import java.util.*;
import java.util.Scanner;


class Update extends TimerTask{
    View view = View.getInstance();
    public void run() {
        if(view.stop)
            cancel();
        float timeToDollar = view.data.getRate("d");
        if(view.data.timeElapsed >= timeToDollar){
            view.data.timeElapsed -= timeToDollar;
            view.addDollar();
			view.addBang();
            System.out.println("Made " + view.data.totalMade + " dollars " + view.data.bangBang);
        }
            view.data.timeElapsed += 1;
    }
    
    public static void main(String args[]){
        TimerTask timerTask = new Update();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000); // 0 to 10s
    }
}
    



class Terminal implements Runnable{
    int COMMAND = 0;
    int RATE  = 1;
    Scanner scan = new Scanner(System.in);
    // Singleton View
    View view = View.getInstance();
    public void run() {
        while(true){
            String req= scan.nextLine();
            String[] buff = req.split(" ");
            switch (buff[COMMAND]){
                case "display":
                    view.display();
                    break;
                case "edit":
                    System.out.println("Recieved command to edit");
                    view.edit(buff[RATE]);
                    break;
                case "x":
                    view.stop();
                    break;
                default:
                    System.out.println("No valid command");
            }
        }
    }
}

public class Controller{
    
    // Singleton View
    View view;
    Scanner scan;
    boolean invalid = true;
    public Controller(){
        view = View.getInstance();
        scan = new Scanner(System.in);
    }
    public int getInput(){
        String num = scan.nextLine();
        String[]args = num.split(" ");
        int isSalary = 0;
        try{
            if(args[0].equals("salary")){
                System.out.println("Ok whats your salary hotshot?");
                num=scan.nextLine();
                isSalary = 1;
            }
            
            Model data = new Model(Float.parseFloat(num),isSalary);
            view.addData(data);
            Update update = new Update();
            update.main(args);
            invalid = false;
            return 1;
        }
        catch(NumberFormatException e){
          invalid = true;
            return -1;
          }
    }
    
    public float calculate(String s){
        return view.data.getRate(s);
    }
    public void startTerminal(){
        Terminal thread = new Terminal();
        thread.run();
    }

}