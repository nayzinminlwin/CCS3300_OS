package Project;

import java.util.Scanner;

public class MemoryReplacement_Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter the number of demands: ");
        int demand = sc.nextInt();

        System.out.print("Enter the incoming pages (space-separated): ");
        int[] pages = new int[demand];
        // for (int i = 0; i < demand; i++) {
        // pages[i] = sc.nextInt();
        // }

        sc.close();
        int[] dummyPages = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                1, 2, 1, 1, 2, 6, 7, 8, 9, 1, 5, 6, 7, 7, 11, 15, 16, 11, 20, 2 };
        pages = dummyPages;
        demand = pages.length;

        System.out.println("Total number of frames: " + frames);
        System.out.println("Incoming Pages: " + java.util.Arrays.toString(pages));
        System.out.println("length of incoming pages: " + pages.length);

        Optimal_Replacement_Algorithm.Optimal_Replacement_Algo(pages, frames);
    }

}
