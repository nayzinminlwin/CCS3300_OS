package Project;

import java.util.*;

public class FIFO {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter the number of demands: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.print("Enter the incoming pages (space-separated): ");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.println("Incoming Pages: " + Arrays.toString(pages));

        Queue<Integer> memory = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages) {

            if (memory.contains(page)) {
                System.out.println("Page " + page + " already in memory: " + memory);
                // sth here.
            } else {
                pageFaults++;

                if (memory.size() == frames) {
                    memory.poll(); // FIFO removal
                }

                memory.add(page);
                System.out.println(
                        "Page fault! After inserting page " + page + ": " + memory);
            }
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}