
public class Item {

    //
    // Public
    //

    // Constructor
    public Item(int id){
        this.id = id;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return descrip;
    }
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Item getNext() {
        return next;
    }
    public void setNext(Item next) {
        this.next = next;
    }

    public Boolean getTaken() {
        return taken;
    }
    public void setTaken(Boolean taken) {this.taken = taken;}

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }


    // Other methods
    @Override
    public String toString() {
        return this.id+ " Name= " + this.name + "| Descrip= " + this.descrip + "| Cost= " + this.cost + " peas";
    }



    //
    // Private
    //
    private int id;
    public  Boolean taken;
    private String name;
    private String descrip;
    private Item next = null;
    private int cost = 0;


}



