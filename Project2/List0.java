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

    public Item getHead() {
        return head;
    }
    public void setHead(Item head) {
        this.head = head;
    }

    // Other methods
    @Override
    public String toString() {
        String retVal = new String();
        retVal =  this.name +"\n"+  this.desc + "\n";
        Item currentItem = this.head;
        while (currentItem != null) {
            retVal = retVal + "   " + currentItem.toString() + "\n";
            currentItem = currentItem.getNext();
        }
        return retVal;
    }


    //
    // Private
    //
    private String name;
    private String desc;
    private Item head;

}

