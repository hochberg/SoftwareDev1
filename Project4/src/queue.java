/**
 * Created by Alex on 5/5/14.
 */
public class queue {


        public queue() {
            init();
        }

        //
        // Public
        //
        public void enqueue(int item){
            // Check for overflow.
            if (backPtr < qCAPACITY-1) {
                backPtr = backPtr + 1;
                arr[backPtr] = item;
            } else {
                Exception overflow = new Exception("Queue Overflow");

            }
        }

        public int dequeue()  {
            int retVal = 0;
            // Check for underflow.

            if (! this.isEmpty()) {
                retVal = arr[frontPtr];
                // Shift every element towards the front.
                for(int i = 0; i < backPtr; i++) {
                    arr[i] = arr[i+1];
                }
                // Reinitialize the last element
                arr[backPtr] = 0;
                // shift the back pointer towards the front.
                backPtr--;
            } else {
                // In case of underflow, throw an underflow exception.
                Exception underflow = new Exception("Queue Underflow");

            }
            return retVal;
        }

       public boolean isEmpty() {
            boolean retVal = false;
            if (backPtr == -1) {
                retVal = true;
            }
            return retVal;
        }

        public int getQueueCapacity() {
            return qCAPACITY;
        }

        //
        // Private
        //
        private void init() {
            for (int i = 0; i < qCAPACITY; i++) {
                arr[i] = 0;
            }
        }

        private final int qCAPACITY = 100;
        private int[] arr = new int[qCAPACITY];
        private int frontPtr = 0;
        private int backPtr  = -1;
    }

























