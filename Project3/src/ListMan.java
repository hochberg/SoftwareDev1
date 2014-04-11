/**
 * Created by Alex on 4/11/14.
 */
public class ListMan {


        //
        // Public
        //
        public ListMan() {
        }

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

        public List0 getHead() {
            return head;
        }
        public void setHead(List0 head) {
            this.head = head;
        }

        public List0 getLast() {
            return last;
        }
        public void setLast(List0 last) {
            this.last = last;
        }



    public void add(List0 item) {

        if (this.head == null) {

            this.head = item;
            this.last = item;
        } else {

            this.last.setNext(item);
            this.last = item;
        }

    }




        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ListMan: name=" + this.name + " desc=" + this.desc + "] List Items:\n");
            List0 currentItem = this.head;
            while (currentItem != null) {
                sb.append(currentItem.toString());
                sb.append("\n");
                currentItem = currentItem.getNext();
            }
            return sb.toString();
        }

        //
        // Private
        //
        private String name;
        private String desc;
        private List0 head = null;
        private List0 last = null;
    }


