import java.io.File;
import java.util.Scanner;

public class List0 {

    //
    // Public
    //

    // Constructor
    public List0() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public List0 getNext() {return next;}
    public void setNext(List0 next) { this.next = next; }

    public double getCost() {return cost;}
    public void setCost(double cost) { this.cost = cost; }


         @Override
         public String toString() {
             return "[ListItem name=" + this.name
                     + " desc=" + this.desc
                     + " cost=" + this.cost + " peas]";
         }

    // Private
    //
    private String name;
    private String desc;
    private List0 next=null;
    private double cost;

}






















