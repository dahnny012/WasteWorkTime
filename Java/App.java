public class App{
    public static void main(String arg[])
    {
        Controller control = new Controller();
        System.out.println("Please enter how much you make an hour.");
        while(control.invalid){
            control.getInput();
        }
        System.out.println("Thank you I will update everytime you make a dollar!");
        System.out.println("In the mean time here are the console commands");
        System.out.println("> display");
        System.out.println("> edit %f");
        control.startTerminal();
        while(true){
            long timeToDollar =(long) control.calculate("d") * 1000;
            try {
                Thread.sleep(timeToDollar);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Made one dollar");
        }
        
    }
}
