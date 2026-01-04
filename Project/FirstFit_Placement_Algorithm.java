package first_fit;

import java.util.Scanner;


public class FirstFit_Placement_Algorithm {

	public static void main(String[]Args) {
		
		//Original Memory Partition (A=available, X=Occupied, R = Most Recent)
		Object[][] OgMemory = { { 'A', 100 }, { 'X', 20 }, { 'A', 80 }, { 'R', 50 }, { 'A', 50 },
                { 'X', 120 }, { 'A', 100 } };
		
		Scanner sc = new Scanner(System.in);
		//User enter amount of pages added
		System.out.print("Enter number of incoming pages: ");
		int pages = sc.nextInt();
		
		//Enter page sizes
		int[] upcomingPages = new int[pages];
		System.out.print("Enter sizes of upcoming pages: ");
		for (int i = 0 ; i<pages ; i ++) {
			upcomingPages[i] = sc.nextInt();
		}
		
		
		//FirstFit Algorithm
		Integer [] placements = new Integer [OgMemory.length];	//Integer is needed to be able to store NULL
		
		for(int p : upcomingPages) {
			boolean placed = false;
		
		
		//Scan from beginning after every insertion
		for (int i = 0; i<OgMemory.length; i++) {
			
			char status = (char)OgMemory[i][0];
			int size = (int)OgMemory[i][1];
			
			//Check status
			if (status == 'A' && size >= p) {
				placements[i] = p;
				OgMemory[i][0] = 'R';
				placed = true;
				break;	}
			
		}
		
		//Error detection
		if (!placed) {
			System.out.print("Error: Insufficient Memory");
			sc.close();
			return;	}
		}
		
		sc.close();
		
		//Result Display
		System.out.println("Result: ");
		for (int i =0; i<OgMemory.length; i++) {
			if (placements[i] != null) 
				System.out.print(placements[i] + ",");
			else 
				System.out.print(OgMemory[i][1] + ",");	
				}
			}
		}
		
		
		
		
	

