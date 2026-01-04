package first_fit;

public class FirstFit_Placement_Algorithm {

	// First Fit Algo
	public static Object[][] FirstFitAlgo(Object[][] currentMemory, int[] incomingPages) {

		Object[][] newMemory = currentMemory;
		int lastRecentIndex = findLastRecentIndex(newMemory);

		// Looping for each page
		for (int i = 0; i < incomingPages.length; i++) {

			boolean allocated = false;

			// Scan the available memory
			for (int j = 0; j < newMemory.length; j++) {

				int currentVal = (int) newMemory[j][1];

				// Check block status
				if (newMemory[j][0].equals('A') && currentVal >= incomingPages[i]) {

					System.out.println("First Fit for " + incomingPages[i] + "Kb Block found at index " + j
							+ " with Size: " + currentVal + "\n");

					// Mark the previous most recent as occupied
					if (lastRecentIndex != -1) {
						newMemory[lastRecentIndex][0] = 'X';
					}

					// Memory Allocation
					lastRecentIndex = j;
					newMemory = resizeMemory(newMemory, j, incomingPages[i]);
					allocated = true;
					break;

				}
			}

			// no suitable block found
			if (!allocated) {
				System.out.println("No suitable block found for page size: "
						+ incomingPages[i] + "\n");
			}

			// Print Current Memory State after each allocation
			MemoryPlacement_Main.printFinalMemory(newMemory);
		}

		return newMemory;
	}

	// Resize Memory Method
	public static Object[][] resizeMemory(Object[][] memory, int index, int pageSize) {
		Object[][] updatedMemory = new Object[memory.length + 1][];

		for (int i = 0; i < index; i++) {
			updatedMemory[i] = memory[i];
		}

		// Insert allocated block
		updatedMemory[index] = new Object[] { 'R', pageSize };

		// Insert remaining free block
		int remainingSize = (int) memory[index][1] - pageSize;
		updatedMemory[index + 1] = new Object[] { 'A', remainingSize };

		// Copy elements after index
		for (int i = index + 1; i < memory.length; i++) {
			updatedMemory[i + 1] = memory[i];
		}
		return updatedMemory;
	}

	// Find Latest Index Method

	public static int findLastRecentIndex(Object[][] memory) {

		for (int i = memory.length - 1; i >= 0; i--) {
			if (memory[i][0].equals('R')) {
				return i;
			}
		}
		return -1;
	}

}
