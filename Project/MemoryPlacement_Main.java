package Project;

public class MemoryPlacement_Main {
    public static void main(String[] args) {
        // Initial Memory Partition Setup
        Object[][] Current_MemoryPartition = { { 'A', 100 }, { 'X', 20 }, { 'A', 80 }, { 'R', 50 }, { 'A', 50 },
                { 'X', 120 }, { 'A', 100 } };

        // Incoming Pages to be allocated
        int[] upcomingPages = { 20, 40, 30, 100, 20 };

        // Print Current Memory State
        printFinalMemory(Current_MemoryPartition);

        // Apply Best Fit Placement Algorithm
        Object[][] Final_Memory = BestFit_Placement_Algorithm.BestFitAlgo(Current_MemoryPartition, upcomingPages);

        // Print Final Memory State after allocation
        printFinalMemory(Final_Memory);

    }

    public static void printFinalMemory(Object[][] memory) {
        // 1. Config: How wide should each block be?
        int cellWidth = 10;
        String border = "+";
        String rowStatus = "|";
        String rowSize = "|";

        // 2. Build the rows loop
        for (int i = 0; i < memory.length; i++) {
            // Create the horizontal line (e.g., +----------)
            for (int k = 0; k < cellWidth; k++)
                border += "-";
            border += "+";

            // Extract data
            char status = (char) memory[i][0];
            int size = (int) memory[i][1];

            // Format the Status Cell (Centered)
            rowStatus += centerString(String.valueOf(status), cellWidth) + "|";

            // Format the Size Cell (Centered)
            rowSize += centerString(String.valueOf(size), cellWidth) + "|";
        }

        // 3. Print the final box
        System.out.println("Current Memory Map:");
        System.out.println("(Status: A=Available, X=Used, R=Recent)");
        System.out.println(border);
        System.out.println(rowStatus);
        System.out.println(border);
        System.out.println(rowSize);
        System.out.println(border);
        System.out.println(); // Empty line for spacing
    }

    // Helper function to center text inside the box
    public static String centerString(String s, int width) {
        if (s.length() >= width)
            return s; // If too long, just return it
        int padding = (width - s.length()) / 2;
        String format = "%" + (padding + s.length()) + "s"; // Pad left
        String result = String.format(format, s);
        // Pad right to fill the rest
        while (result.length() < width) {
            result += " ";
        }
        return result;
    }
}
