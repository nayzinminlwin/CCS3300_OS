package Project;

public class BestFit_Placement_Algorithm {

    public static Object[][] BestFitAlgo(Object[][] currentMemory, int[] incomingPages) {
        Object[][] newMemory = currentMemory;
        int lastRecentIndex = findLastRecentIndex(newMemory); // Find the last 'R' block index

        // loop through each incoming page
        for (int i = 0; i < incomingPages.length; i++) {
            int minIndex = -1; // To track the index of the best fit block
            int min = Integer.MAX_VALUE; // To track the size of the best fit block

            // loop through memory blocks to find the best fit
            for (int j = 0; j < newMemory.length; j++) {
                int currentVal = (int) newMemory[j][1]; // get the value of the current memory block
                // System.out.println(
                // "Incoming Page Size: " + incomingPages[i] + ", Checking Memory Block Size: "
                // + currentVal
                // + " at Index: " + j);

                // Check if block is available
                if (newMemory[j][0].equals('A')) {
                    // Perfect fit
                    if (currentVal == incomingPages[i]) {
                        newMemory[j][0] = 'R';
                        System.out
                                .println("Perfect Fit for " + incomingPages[i] + "Kb Block Found at Index: " + j
                                        + " with Size: " + currentVal + "\n");

                        // Update last recent index
                        if (lastRecentIndex != -1) {
                            newMemory[lastRecentIndex][0] = 'X';
                        }
                        lastRecentIndex = j;

                        minIndex = -2; // Indicate perfect fit found
                        break;
                    }
                    // Best fit so far
                    else if (currentVal > incomingPages[i] && currentVal < min) {
                        min = currentVal;
                        minIndex = j;
                    }
                }
            }

            // found the perfect fit
            if (minIndex == -2) {
                // do nothing, already handled
            }

            // not found any suitable block
            else if (minIndex == -1) {
                System.out.println("No suitable block found for page size: " + incomingPages[i] + "\n");
            }

            // found the best fit
            else {
                System.out.println("Best Fit for " + incomingPages[i] + "Kb Block Found at Index: " + minIndex
                        + " with Size: " + min + "\n");
                if (lastRecentIndex != -1) {
                    newMemory[lastRecentIndex][0] = 'X';
                }
                lastRecentIndex = minIndex;
                newMemory = resizeMemory(newMemory, minIndex, incomingPages[i]);
            }

            // uncomment the following line to print memory state after each allocation
            // MemoryPlacement_Main.printFinalMemory(newMemory);
        }

        return newMemory;
    }

    // Method to resize memory after allocation
    public static Object[][] resizeMemory(Object[][] memory, int index, int pageSize) {
        Object[][] updatedMemory = new Object[memory.length + 1][];

        // Copy elements before the index
        for (int i = 0; i < index; i++) {
            updatedMemory[i] = memory[i];
        }

        // Insert the allocated block
        updatedMemory[index] = new Object[] { 'R', pageSize };

        // Insert the remaining available block
        int remainingSize = (int) memory[index][1] - pageSize;
        updatedMemory[index + 1] = new Object[] { 'A', remainingSize };

        // Copy elements after the index
        for (int i = index + 1; i < memory.length; i++) {
            updatedMemory[i + 1] = memory[i];
        }

        return updatedMemory;
    }

    // Method to find the most recent 'R' block index
    public static int findLastRecentIndex(Object[][] memory) {
        for (int i = memory.length - 1; i >= 0; i--) {
            if (memory[i][0].equals('R')) {
                return i;
            }
        }
        return -1; // No recent block found
    }

}