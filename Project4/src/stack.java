/**
 * Created by Alex on 5/5/14.
 */
public class stack {
        //
        // Public
        //
        public stack() {
            init();
        }

        public void push(int item) {
            // Check for stack overflow.
            if (topPtr > 0) {
                topPtr = topPtr - 1;
                arr[topPtr] = item;
            } else {
                Exception overflow = new Exception("Queue Overflow");

            }
        }

        public int pop(){
            int retVal = 0;
            // Check for stack underflow.
            if (topPtr < stackCAPACITY) {
                retVal = arr[topPtr];
                topPtr = topPtr + 1;
            } else {
                // In case of underflow, return -1.
                retVal = -1;
                Exception underflow = new Exception("Queue Underflow");

            }
            return retVal;
        }

        public boolean isEmpty() {
            boolean retVal = false;
            if (topPtr == stackCAPACITY) {
                retVal = true;
            }
            return retVal;
        }

        public int getstackCAPACITY(){
            return stackCAPACITY;
        }


        //
        // Private
        //
        private final int stackCAPACITY = 100;
        private int[] arr = new int[stackCAPACITY];
        private int topPtr = 0;

        private void init() {
            for (int i = 0; i < stackCAPACITY; i++) {
                arr[i] = 0;
            }
            topPtr = stackCAPACITY;
        }


    }











