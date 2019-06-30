 
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Timestamp1 {
	private int[] id;  
	private static int components; // number of components

	// Initialize each sites to unique id .
	public  Timestamp1(int N) {
		components = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

// Return the number of components in the network
	public int components() {
		return components;
	}

	
	 //checks if the members are friends or not
	 
	public boolean Friends(int p, int q) {
		return identity(p) == identity(q);
	}

// Return the id of member
	public int identity(int member) {
		return id[member];
	}

	/**
	 * checks if the members are friends or not.if not Form friendship between member p and member q by changing the value of id[p] to id[q].
	 *  Decrement the number of components
	  */
	public void FriendShip(int p, int q) {
		int pID = identity(p);
		int qID = identity(q);
		if (pID == qID)
			return; 
		for (int i = 0; i < id.length; i++)
			if (id[i] == pID)
				id[i] = qID; 
		components--; 
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		String filename=new String(args[0]);
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		int N = Integer.parseInt(scan.nextLine());
		Timestamp1 connectivity = new Timestamp1 (N); // Initialize N members.
		String line, earliestConnectTime = null;
		while (scan.hasNextLine()) {
			line = scan.nextLine();
			if (line != null && !line.trim().equals("")) {
				String[] lineArray = line.split(" ");
				if (lineArray.length == 4) {
					String timestampDate = lineArray[0];
					String timestamptime=lineArray[1];
					int p = Integer.parseInt(lineArray[2]);
					int q = Integer.parseInt(lineArray[3]);
					
					if (connectivity.Friends(p, q))
						continue; // Ignore if they are friends.
					connectivity.FriendShip(p, q); // form friendship
					if (components == 1) { // If all members form friendship
						earliestConnectTime=timestampDate + " " +  timestamptime;
						break;
					}
				}
			}
		}
		System.out.println(" All members are connected at a time : " + earliestConnectTime); // prints the time at which all are connected.
	}
}
