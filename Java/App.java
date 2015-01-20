public class App{
    public static void main(String arg[])
    {
        Controller control = new Controller();
        System.out.println("Please enter how much you make an hour.\nIf you are salaried type salary");
        while(control.invalid){
            control.getInput();
        }
        System.out.println("Thank you I will update everytime you make a dollar!");
        System.out.println("In the mean time here are the console commands");
        System.out.println("> display");
        System.out.println("> edit %f");
        System.out.println(">x(exit)");
        control.startTerminal();
        while(true){
            Thread.yield();
        }
        
    }
}
