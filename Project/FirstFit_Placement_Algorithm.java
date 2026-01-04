package first_fit;

import java.util.Scanner;


public class FirstFit_Placement_Algorithm {

	
		//FirstFit Algorithm
		Integer [] placements = new Integer [Current_MemoryPartition.length];	//Integer is needed to be able to store NULL
		
		for(int p : upcomingPages) {
			boolean placed = false;
		
		
		//Scan from beginning after every insertion
		for (int i = 0; i<Current_MemoryPartition.length; i++) {
			
			char status = (char)Current_MemoryPartition[i][0];
			int size = (int)Current_MemoryPartition[i][1];
			
			//Check status
			if (status == 'A' && size >= p) {
				placements[i] = p;
				OgMemory[i][0] = 'R';
				placed = true;
				break;	}
			
		}
}
		
		//Error detection
		if (!placed) {
			System.out.print("Error: Insufficient Memory");
			return;	}
		
		
		//Result Display
		System.out.println("Result: ");
		for (int i =0; i<Current_MemoryPartition.length; i++) {
			if (placements[i] != null) //Check whether the placements are empty
				System.out.print(placements[i] + ",");
			else 
				System.out.print(Current_MemoryPartition[i][1] + ",");	
				}
			}
		
		
		
		
		
	




