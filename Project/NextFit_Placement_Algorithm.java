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
        // Initial state from your provided scenario
        List<Partition> memory = new ArrayList<>(Arrays.asList(
                new Partition(100, "A"), new Partition(20, "X"),
                new Partition(80, "A"), new Partition(50, "R"),
                new Partition(50, "A"), new Partition(120, "X"),
                new Partition(100, "A")));

        int[] inputs = { 20, 40, 30, 100, 20, 60 }; // Pages to place
        System.out.println("Total number of pages : " + inputs.length);
        System.out.println("List of pages to place (in Kb): " + Arrays.toString(inputs));

        System.out.println("Current Memory Map:");
        printMemoryMap(memory);

        // Find the index of the initial 'R' to know where to start searching
        int currentPointer = 0;
        for (int i = 0; i < memory.size(); i++) {
            if (memory.get(i).status.equals("R")) {
                currentPointer = i;
                break;
            }
        }

        for (int requestSize : inputs) {
            boolean found = false;
            int n = memory.size();

            // NEXT FIT SEARCH: Start from (currentPointer + 1) to skip the 'R' block
            for (int i = 1; i <= n; i++) {
                int searchIndex = (currentPointer + i) % n;
                Partition p = memory.get(searchIndex);

                // FIX: Only 'A' (Available) can be occupied or split
                if (p.status.equals("A") && p.size >= requestSize) {

                    // Convert old 'R' to 'X' because it's no longer the most recent
                    for (Partition part : memory) {
                        if (part.status.equals("R"))
                            part.status = "X";
                    }

                    System.out.printf("Next Fit for %dKb Block Found at Index: %d with Size: %d\n",
                            requestSize, searchIndex, p.size);

                    if (p.size == requestSize) {
                        p.status = "R";
                        currentPointer = searchIndex;
                    } else {
                        int remaining = p.size - requestSize;
                        p.size = requestSize;
                        p.status = "R";
                        // Split the 'A' block and insert the remainder
                        memory.add(searchIndex + 1, new Partition(remaining, "A"));
                        currentPointer = searchIndex;
                    }

                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("No suitable partition found for " + requestSize + "Kb");
            }
            printMemoryMap(memory);
        }

        System.out.println("\nCurrent Memory Map:");
        printMemoryMap(memory);
    }

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