package Project;

import java.util.*;

public class NextFit_Placement_Algorithm {
    static class Partition {
        int size;
        String status; // A=Available, X=Used, R=Recent

        Partition(int size, String status) {
            this.size = size;
            this.status = status;
        }
    }

    public static void main(String[] args) {
        // 1. Initialize Memory based on the scenario in Screenshot 1
        List<Partition> memory = new ArrayList<>(Arrays.asList(
                new Partition(100, "A"), new Partition(20, "X"),
                new Partition(80, "A"), new Partition(50, "R"),
                new Partition(50, "A"), new Partition(120, "X"),
                new Partition(100, "A")));

        // 2. Define inputs from Screenshot 2
        int[] inputs = { 20, 40, 30, 100, 20 };
        System.out.println("Total number of pages : " + inputs.length);
        System.out.println("List of pages to place (in Kb): " + Arrays.toString(inputs));

        System.out.println("Current Memory Map:");
        printMemoryMap(memory);

        // 3. Next Fit Logic: Pointer starts at the initial 'R' (Index 3)
        int pointer = 3;

        for (int requestSize : inputs) {
            boolean found = false;
            int n = memory.size();

            // Search starts from 'pointer' and wraps around
            for (int i = 0; i < n; i++) {
                int currentIndex = (pointer + i) % n;
                Partition p = memory.get(currentIndex);

                // Check if partition is Available ('A') or the current Recent ('R')
                if ((p.status.equals("A") || p.status.equals("R")) && p.size >= requestSize) {

                    // Mark previous 'R' as 'X' (Used) before setting the new 'R'
                    for (Partition part : memory) {
                        if (part.status.equals("R"))
                            part.status = "X";
                    }

                    System.out.printf("Next Fit for %dKb Block Found at Index: %d with Size: %d\n",
                            requestSize, currentIndex, p.size);

                    if (p.size == requestSize) {
                        p.status = "R";
                        pointer = currentIndex;
                    } else {
                        int remaining = p.size - requestSize;
                        p.size = requestSize;
                        p.status = "R";
                        // Split partition: insert remaining as Available ('A')
                        memory.add(currentIndex + 1, new Partition(remaining, "A"));
                        pointer = currentIndex; // Next search starts here
                    }

                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No suitable partition found for " + requestSize + "Kb");
            }
        }

        // 4. Print Final Memory Map
        System.out.println("\nCurrent Memory Map:");
        printMemoryMap(memory);
    }

    // Helper method to print the ASCII Table matching Screenshot 3
    public static void printMemoryMap(List<Partition> memory) {
        StringBuilder divider = new StringBuilder("+");
        StringBuilder statusRow = new StringBuilder("|");
        StringBuilder sizeRow = new StringBuilder("|");

        for (Partition p : memory) {
            divider.append("-------+");
            statusRow.append(String.format("   %s   |", p.status));
            sizeRow.append(String.format("  %3d  |", p.size));
        }

        System.out.println("(Status: A=Available, X=Used, R=Recent)");
        System.out.println(divider);
        System.out.println(statusRow);
        System.out.println(divider);
        System.out.println(sizeRow);
        System.out.println(divider + "\n");
    }
}