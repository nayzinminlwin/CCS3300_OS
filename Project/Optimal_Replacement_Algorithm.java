package Project;

import java.util.ArrayList;
import java.util.Arrays;

public class Optimal_Replacement_Algorithm {

    public static void Optimal_Replacement_Algo(int[] pages, int frames) {
        ArrayList<Integer> memorySpace = new ArrayList<>();
        ArrayList<Integer> lastUsed = new ArrayList<>(); // Track last used times
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            // Check if page already exists in memory
            if (!memorySpace.contains(pages[i])) {
                pageFaults++;

                if (memorySpace.size() < frames) {
                    // Memory has space, just add the page
                    memorySpace.add(pages[i]);
                    lastUsed.add(i);
                } else {
                    int remainingPages = pages.length - (i + 1);
                    int pageToReplacePos = -1;

                    // If we have enough future pages to check all frames
                    if (remainingPages >= frames) {
                        // Use Optimal algorithm
                        int farthestIndex = -1;

                        for (int j = 0; j < memorySpace.size(); j++) {
                            int nextUse = findNextOccurrence(i + 1, pages, memorySpace.get(j));

                            if (nextUse == -1) {
                                pageToReplacePos = j;
                                break;
                            }

                            if (nextUse > farthestIndex) {
                                farthestIndex = nextUse;
                                pageToReplacePos = j;
                            }
                        }
                    } else {
                        // Not enough future references, use LRU
                        System.out.println("Using LRU fallback (not enough future references)");
                        int lruTime = Integer.MAX_VALUE;

                        for (int j = 0; j < memorySpace.size(); j++) {
                            if (lastUsed.get(j) < lruTime) {
                                lruTime = lastUsed.get(j);
                                pageToReplacePos = j;
                            }
                        }
                    }

                    memorySpace.set(pageToReplacePos, pages[i]);
                    lastUsed.set(pageToReplacePos, i);
                }

                System.out.println(
                        "Page fault! After inserting page " + pages[i] + ": " + Arrays.toString(memorySpace.toArray()));
            } else {
                // Update last used time for existing page
                int index = memorySpace.indexOf(pages[i]);
                lastUsed.set(index, i);

                System.out
                        .println("Page " + pages[i] + " already in memory: " + Arrays.toString(memorySpace.toArray()));
            }
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
    }

    public static int findNextOccurrence(int startIndex, int[] pages, int searchVal) {
        for (int i = startIndex; i < pages.length; i++) {
            if (pages[i] == searchVal) {
                return i;
            }
        }
        return -1; // Not found in future
    }
}