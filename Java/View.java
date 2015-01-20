
class EditButton{
    // console command edit 1
    // console write: To edit type >edit %i
}

class MoneyPanel{
    // console command display
    // console write: To display type >display 
    
    public void update(Model data){
        // Write data
    }
}

public class View{
    Model data;
    EditButton EB;
    MoneyPanel MP;
    int foo = 0;
    private static View view;
    private View(){
        EB = new EditButton();
        MP = new MoneyPanel();
        System.out.println("Welcome to WasteWorkTime");
    }
    
    public static View getInstance(){
        if(view == null)
            view = new View();
        return view;
    }
    public void updateWorkRate(float rate){
        //data.updateWorkRate(rate);
        //MP.update(data);
    }
    
    public void addData(Model data){
        this.data = data;
    }
	
	public void addBang(){
        this.data.bangBang += "!";
    }
	
    public void addDollar(){
        data.totalMade += 1;
    }
    public void display(){
        System.out.println(data.totalMade);
    }
    
}