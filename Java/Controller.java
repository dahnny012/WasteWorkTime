
// One thread for now. No gui
//class AppThread implements Runnable{
    
//}
import java.util.*;
import java.util.Scanner;


class Update extends TimerTask{
    View view = View.getInstance();
    public void run() {
        float timeToDollar = view.data.getRate("d");
        System.out.println("Elapsed " + view.data.timeElapsed);
        System.out.println("Needed " + timeToDollar);
        if(view.data.timeElapsed >= timeToDollar){
            view.data.timeElapsed -= timeToDollar;
            view.addDollar();
            System.out.println("Made a dollar");
        }
            view.data.timeElapsed += 1;
    }
    
    public static void main(String args[]){
        TimerTask timerTask = new Update();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000); // 0 to 10s
        System.out.println("TimerTask started");
    }
}
    



class Terminal implements Runnable{
    int COMMAND = 0;
    Scanner scan = new Scanner(System.in);
    // Singleton View
    View view = View.getInstance();
    public void run() {
        while(true){
            String req= scan.nextLine();
            String[] buff = req.split(" ");
            switch (buff[COMMAND]){
                case "display":
                    System.out.println("Recieved command to display");
                    view.display();
                    break;
                case "edit":
                    System.out.println("Recieved command to edit");
                    break;
                default:
                    System.out.println("Foo" + view.foo);
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
        int i;
        String num = scan.nextLine();
        try{
            invalid = false;
            Model data = new Model(Float.parseFloat(num));
            view.addData(data);
            Update update = new Update();
            String[] a = new String[1];
            update.main(a);
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