package Project;

import java.util.ArrayList;
import java.util.Arrays;

public class Optimal_Replacement_Algorithm {

    public static void Optimal_Replacement_Algo(int[] pages, int frames) {
        ArrayList<Integer> memorySpace = new ArrayList<>(); // main memory space
        ArrayList<Integer> lastUsed = new ArrayList<>(); // Track last used times
        int pageFaults = 0;

        for (int i = 0; i < pages.length; i++) {
            // Check if page already exists in memory
            if (!memorySpace.contains(pages[i])) {
                pageFaults++;

                // Memory has space, just add the page
                if (memorySpace.size() < frames) {
                    memorySpace.add(pages[i]);
                    lastUsed.add(i);
                } else {
                    int pageToReplacePos = -1;

                    // Find pages that won't be used in future
                    ArrayList<Integer> notUsedInFuture = new ArrayList<>();
                    int farthestIndex = -1;
                    int farthestPos = -1;

                    for (int j = 0; j < memorySpace.size(); j++) {
                        int nextUse = findNextOccurrence(i + 1, pages, memorySpace.get(j));

                        if (nextUse == -1) {
                            // This page won't be used in remaining references
                            notUsedInFuture.add(j);
                        } else {
                            // Track the page with farthest next use
                            if (nextUse > farthestIndex) {
                                farthestIndex = nextUse;
                                farthestPos = j;
                            }
                        }
                    }

                    // If there are pages not used in future, use LRU among them
                    if (!notUsedInFuture.isEmpty()) {
                        System.out.println("Pages not used in future: " + notUsedInFuture.size() + " pages");
                        int lruTime = Integer.MAX_VALUE;

                        for (int pos : notUsedInFuture) {
                            if (lastUsed.get(pos) < lruTime) {
                                lruTime = lastUsed.get(pos);
                                pageToReplacePos = pos;
                            }
                        }
                    } else {
                        // All pages will be used in future, replace the one used farthest
                        pageToReplacePos = farthestPos;
                    }

                    // Replace the page
                    memorySpace.set(pageToReplacePos, pages[i]);
                    // Update last used time
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