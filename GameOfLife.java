package GameOfLife;

public class GameOfLife {

	public static void main(String[] args) {
		System.out.println("Welcome to the game of life!");

		// The size of the matrix
		int SizeOfMatt = MyConsole.readInt("Please enter the size of the matrix: ");
		while (SizeOfMatt < 0 || SizeOfMatt > 99) { // Check input
			System.out.println("Wrong value for the size of the matrix. Try again.");
			SizeOfMatt = MyConsole.readInt("Please enter the size of the matrix: "); 
			// generation population
		} // while


		// The maximum number of generations
		int MaxNumGen = MyConsole.readInt("Please enter the maximum number of generations: ");
		while (MaxNumGen < 0) { // Check input
			System.out.println("Wrong value for the maximum number of generations. Try again.");
			MaxNumGen = MyConsole.readInt("Please enter the maximum number of generations: "); 
			// generation population
		} // while

		boolean matt[][] = new boolean[SizeOfMatt][SizeOfMatt]; // New Matrix - The size of the Matrix is 'SizeOfMatt'

		// The first generation
		int NumOfCreatures = 0; // The number of creatures in this generation
		firstGen(matt); // Call to function firstGen
		System.out.println('\n' + "The game begins!");
		System.out.println("Generation number 1");
		printBoard(matt); // Call to function printBoard
		System.out.println(); // Descending line

		// The number of creatures in first generation 
		for(int i = 0; i < matt.length; i++){
			for(int j = 0; j < matt.length; j++) {
				if(matt[i][j]) 
					NumOfCreatures++;
			} // loop j
		} // loop i

		System.out.println("The number of creatures in this generation is: " + NumOfCreatures); 
		System.out.println("The number of creatures born when moving to this generation is:  " + NumOfCreatures); 
		System.out.println("The number of creatures died when moving to this generation is: 0 "); 
		System.out.println('\n'); // Descending line


		// The next generation
		int GenerationNum = 2; // Generation Number
		while(!No_living_Creatures(matt) && !Generation_Equals(matt) && NumOfCreatures < MaxNumGen) {
			NumOfCreatures = 0;
			System.out.println("Generation number " + GenerationNum++);
			int Num_Of_Creatures_Born_Died[] = nextGen(matt);
			printBoard(matt); // Call to function printBoard
			System.out.println(); // Descending line

			for(int i = 0; i < matt.length; i++){
				for(int j = 0; j < matt.length; j++) {
					if(matt[i][j]) 
						NumOfCreatures++;
				} // loop j
			} // loop i

			System.out.println("The number of creatures in this generation is: " + NumOfCreatures); 
			System.out.println("The number of creatures born when moving to this generation is: " + Num_Of_Creatures_Born_Died[0]); 
			System.out.println("The number of creatures died when moving to this generation is: " + Num_Of_Creatures_Born_Died[1]); 
			System.out.println(); // Descending line

		} // while


		// Game over
		if(Generation_Equals(matt) && !No_living_Creatures(matt)) {
			System.out.println("Generation number " + GenerationNum++);
			printBoard(matt); // Call to function printBoard
			System.out.println(); // Descending line
			System.out.println("The number of creatures in this generation is: " + NumOfCreatures); 
			System.out.println("The number of creatures born when moving to this generation is: 0"); 
			System.out.println("The number of creatures died when moving to this generation is: 0"); 
			System.out.println(); // Descending line
			System.out.println("Game over: this generation equals to the previous one.");
		} // if

		if(No_living_Creatures(matt))
			System.out.println("Game over: The whole generation is dead.");

		if(NumOfCreatures >= MaxNumGen) 
			System.out.println("Game over: We developed the number of generations we wanted");

	} // main


	// How many creatures would start
	public static int firstGen(boolean[][] matt) {
		System.out.println("How many creatures would you like to start with?");
		int K = MyConsole.readInt(""); // Number of first generation
		int X, Y; // population

		// Check input
		while (K < 0 || K > matt.length * matt.length) {
			System.out.println("Wrong value for the number of first generations. Try again.");
			System.out.println("How many creatures would you like to start with?");
			K = MyConsole.readInt(""); // Number of first generation
		} // while

		for (int i = 1; i <= K; i++) {
			System.out.println("Creature number " + i);
			X = MyConsole.readInt("Enter X value: ");

			// Check input
			while (X < 1 || X > matt.length) {
				System.out.println("Wrong X value for creature number " + i + " Try again.");
				System.out.println("Creature number " + i);
				X = MyConsole.readInt("Enter X value: ");
			} // while

			Y = MyConsole.readInt("Enter Y value: ");

			// Check input
			while  (Y < 1 || Y > matt.length) {
				System.out.println("Wrong Y value for creature number " + i + " Try again.");
				System.out.println("Creature number " + i);
				X = MyConsole.readInt("Enter X value: ");
				Y = MyConsole.readInt("Enter Y value: ");

			} // while

			if(!matt[Y-1][X-1])
				matt[Y-1][X-1] = true;
			else {
				System.out.println("This creature has been entered choose a new value.");
				i--;
			} // else
			
		} // for
		return K; // return the number of the first generations
	} // firstGen


	// The Function Returns the number of neighbors of a certain cell.
	public static int numOfNeighbors(boolean[][] matt, int x, int y) {
		int numberOfNeighbors = 0; // Initial value
		x += 1;
		y += 1;

		if(x < matt.length)
			if (matt[y - 1][x])
				numberOfNeighbors++;

		if(x > 1)
			if (matt[y - 1][x - 2])
				numberOfNeighbors++;

		if(x < matt.length && y < matt.length)
			if (matt[y][x])
				numberOfNeighbors++;

		if(y < matt.length)
			if (matt[y][x - 1])
				numberOfNeighbors++;

		if(x > 1 && y < matt.length)
			if (matt[y][x - 2])
				numberOfNeighbors++;

		if(y > 1 && x < matt.length)
			if (matt[y - 2][x])
				numberOfNeighbors++;

		if(y > 1)
			if (matt[y - 2][x - 1])
				numberOfNeighbors++;

		if(y > 1 && x > 1)
			if (matt[y - 2][x - 2])
				numberOfNeighbors++;

		return numberOfNeighbors; // return the number Of Neighbors
	} // numOfNeighbors


	// function to print the Board
	public static void printBoard(boolean[][] matt) {
		String matrix[][] = new String[matt.length][matt.length]; // new matrix for the '*'

		System.out.print("  "); // Print a space
		for (int i = 1; i <= matrix.length; i++) // Prints the numbers from 1 to matt.length 
			if(i < 10)
				System.out.print(" " + i + " ");
			else
				System.out.print(" " + i);

		System.out.println(); // Descending line

		System.out.print("   "); // Print a space

		// print "---" depending on the matrix
		for (int i = 1; i < matrix.length; i++) 
			System.out.print("---"); 
		System.out.print("--");
		System.out.println(); // Descending line

		// print the Board 
		for(int i = 0; i < matrix.length; i++){
			if(i < 9)
				System.out.print(" " + (i+1) + "|");
			else
				System.out.print((i+1) + "|");

			// if there is a living creature print "*", else print space
			for(int j = 0; j < matrix.length; j++) {
				if(matt[i][j]) 
					matrix[i][j] = "*";
				else
					matrix[i][j] = " ";

				System.out.print(matrix[i][j]);

				if(j == matrix.length - 1)
					System.out.print(" |");
				else
					System.out.print("  "); // Print a space
			} // for j
			System.out.println();
		} // for i

		System.out.print("   "); // Print a space

		// print "---" depending on the matrix
		for (int i = 1; i < matrix.length; i++) 
			System.out.print("---");
		System.out.print("--");

	} // printBoard


	// The Function Returns How many creatures born or died in next generation 
	public static int[] nextGen(boolean[][] matt) {
		String mattrix[][] = new String[matt.length][matt.length]; // new matrix for the '*'
		int arr[] = new int[2]; // new array - size 2
		int CountDied = 0; // counter for How many creatures died
		int CountBorn = 0; // counter for How many creatures born

		// We need to work on another matrix so we need a new matrix
		for(int i = 0; i < mattrix.length; i++){
			for(int j = 0; j < mattrix.length; j++) {
				if(matt[i][j])
					mattrix[i][j] = "*";
				else
					mattrix[i][j] = " ";
			} // loop j
		} // loop i

		// We check how many creatures were born or died in the next generation
		for(int i = 0; i < mattrix.length; i++){
			for(int j = 0; j < mattrix.length; j++) {
				if(mattrix[i][j] == "*") {

					if((i == 0) || (i == mattrix.length-1) || (j == mattrix.length-1) || (j == 0)) {
						mattrix[i][j] = " ";
						CountDied++;
					} // if

					else if((numOfNeighbors(matt ,j ,i) < 2) || (numOfNeighbors(matt ,j ,i) > 3)) {
						mattrix[i][j] = " ";
						CountDied++;
					} // if
				} // if 

				else {
					if((numOfNeighbors(matt ,j ,i) == 3) && !((i == 0) || (i == mattrix.length-1) || (j == mattrix.length-1) || (j == 0))){
						mattrix[i][j] = "*";
						CountBorn++;
					} // if
				} // else
			} // loop j
		} // loop i

		// After a change we will update to the original matrix
		for(int i = 0; i < mattrix.length; i++){
			for(int j = 0; j < mattrix.length; j++) {
				if(mattrix[i][j] == "*")
					matt[i][j] = true;
				else
					matt[i][j] = false;
			} // loop j
		} // loop i

		arr[0] = CountBorn; // The number of creatures born to index 0
		arr[1] = CountDied; // The number of creatures died to index 1

		return arr; // return the array
	} // nextGen


	// The function returns if the whole generation is dead
	public static boolean No_living_Creatures(boolean[][] matt) {

		for(int i = 0; i < matt.length; i++){
			for(int j = 0; j < matt.length; j++) {
				if(matt[i][j])
					return false;
			} // loop j
		} // loop i
		return true;
	} // No_living_creatures


	// The function returns if the generation is equal to the previous one
	public static boolean  Generation_Equals(boolean[][] matt) {
		boolean matrix[][] = new boolean[matt.length][matt.length];

		for(int i = 0; i < matt.length; i++){
			for(int j = 0; j < matt.length; j++) {
				matrix[i][j] = matt[i][j];
			} // loop j
		} // loop i

		nextGen(matrix);

		for(int i = 0; i < matt.length; i++){
			for(int j = 0; j < matt.length; j++) {
				if(matt[i][j] != matrix[i][j])
					return false;
			} // loop j
		} // loop i

		return true;
	} // Generation_Equals

} // GameOfLife
