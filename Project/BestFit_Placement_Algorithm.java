package Project;

import java.util.Arrays;

public class BestFit_Placement_Algorithm {

    public static Object[][] BestFitAlgo(Object[][] currentMemory, int[] incomingPages) {
        Object[][] newMemory = currentMemory;

        for (int i = 0; i < incomingPages.length; i++) {
            int minIndex = -1;
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < newMemory.length; j++) {
                int currentVal = (int) newMemory[j][1];
                System.out.println(
                        "Incoming Page Size: " + incomingPages[i] + ", Checking Memory Block Size: " + currentVal
                                + " at Index: " + j);
                if (newMemory[j][0].equals('A')) {
                    if (currentVal >= incomingPages[i] && currentVal < min) {
                        min = currentVal;
                        minIndex = j;
                    }
                }
            }

            if (minIndex != -1) {
                System.out.println("Best Fit Block Found at Index: " + minIndex + " with Size: " + min + "\n");
                newMemory = resizeMemory(newMemory, minIndex, incomingPages[i]);
            } else {
                System.out.println("No suitable block found for page size: " + incomingPages[i] + "\n");
            }
        }

        return newMemory;
    }

    public static Object[][] resizeMemory(Object[][] memory, int index, int pageSize) {
        Object[][] updatedMemory = new Object[memory.length + 1][];

        // Copy elements before the index
        for (int i = 0; i < index; i++) {
            updatedMemory[i] = memory[i];
        }

        // Insert the allocated block
        updatedMemory[index] = new Object[] { 'X', pageSize };

        // Insert the remaining available block
        int remainingSize = (int) memory[index][1] - pageSize;
        updatedMemory[index + 1] = new Object[] { 'A', remainingSize };

        // Copy elements after the index
        for (int i = index + 1; i < memory.length; i++) {
            updatedMemory[i + 1] = memory[i];
        }

        return updatedMemory;
    }

}