
public class Loc {

    //
    // Public
    //

    // Constructor
    public Loc(int id) {
        this.id = id;
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.name + "\n" + this.descrip + "\n" + this.directions;
    }
    public String getTextNoD() {
        return this.name + "\n" + this.directions;
    }


    public String getName() {
        return this.name;
    }
    public void setName(String value) {
        this.name = value;
    }

    public String getDescrip() {return this.descrip;}
    public void setDescrip(String value) {
        this.descrip = value;
    }

    public Boolean getHasVisited(){ return this.hasVisited;}
    public void setHasVisited(Boolean value){this.hasVisited=value;}

    public Boolean getPeaVisited(){ return this.peaVisited;}
    public void setPeaVisited(Boolean value){this.peaVisited=value;}

    public String getDirections() {return this.directions;}
    public void setDirections(String value) {
        this.directions= value;
    }




    // Other methods
    @Override
    public String toString(){
        return "[Loc id="
                + this.id
                + " name="
                + this.name
                + " descrip=" + this.descrip
                + " directions=" + this.directions
                + " hasVisited=" + this.hasVisited + "]";
    }

    //
    //  Private
    //
    private int     id;
    private String  name;
    private String  descrip;
    private Boolean hasVisited = false;
    private Boolean peaVisited = false;
    private String item;
    private String directions;


}



