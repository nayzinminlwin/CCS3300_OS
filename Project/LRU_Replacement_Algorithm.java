package memoryreplacement;

import java.util.ArrayList;
import java.util.Arrays;

public class LRU_Replacement_Algorithm {

	public static void LRU_Algo(int[] pages, int frames) {
	
		//Store pages in memory
		ArrayList<Integer> memory = new ArrayList<>();
		ArrayList<Integer> lastUsed = new ArrayList<>();
		
		// Page Fault
		int pFaults = 0;
		
		for (int i = 0; i<pages.length; i++) {
			
			//Detection for page fault
			if (!memory.contains(pages[i])) {
				pFaults++;
				
				//Add pages to memory if space is available
				if (memory.size() < frames) {
					memory.add(pages[i]);
					lastUsed.add(i);
				}
				
				else {	//Find a page to replace
					int lru = Integer.MAX_VALUE;
					int pageToReplace =-1 ;
					
					for (int j =0; j<memory.size(); j ++) { 
					if (lastUsed.get(j) < lru) {
					lru = lastUsed.get(j);
					pageToReplace = j;
					}
				}
				
				//Replace the page
				memory.set(pageToReplace, pages[i]);
				lastUsed.set(pageToReplace, i);
			}
			
			System.out.println("Page fault. After inserting pages: " + pages[i] + ":" + Arrays.toString(memory.toArray()));
		}
			//Page Hit
			else {
				int index = memory.indexOf(pages[i]);
				lastUsed.set(index,i);
				
				System.out.println("Page " + pages[i] + "already in memory: " + Arrays.toString(memory.toArray()));
			}
		}
		System.out.println("\nTotal page Faults(LRU): " + pFaults);
	}
}	
