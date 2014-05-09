
public class Freezer extends Loc {       // Freezer IS-A Locale.

    //
    // Public
    //

    // Constructor
    public Freezer(int id){
        super(id);
    }

    // Getters and Setters
    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }


    @Override
    public String toString() {
        return "Freezer..." + super.toString() + " temp=" + this.temp;
    }

    //
    // Private
    //
    private String temp= "-1 Degrees F";
}
