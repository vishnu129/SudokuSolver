//program to solve a 9*9 sudoku puzzle
public class Solver {
	
	/*
	 * int [][] puzzle = { {0,8,0,0,1,3,4,0,0}, {4,2,0,6,8,0,0,0,0},
	 * {0,0,1,0,5,4,0,8,3}, {1,9,0,0,0,8,7,0,0}, {0,4,7,0,0,2,5,0,8},
	 * {0,5,0,0,0,9,0,3,0}, {2,0,9,3,0,5,0,7,0}, {5,0,0,7,2,0,0,0,9},
	 * {7,3,0,0,0,0,2,0,6} };
	 */
	
	
	// taking the puzzle grid as a global matrix
	 int [][] puzzle = {
				{0,0,0,0,0,0,6,8,0},
				{0,0,0,0,7,3,0,0,9},
				{3,0,9,0,0,0,0,4,5},
			    {4,9,0,0,0,0,0,0,0},
				{8,0,3,0,5,0,9,0,2},
				{0,0,0,0,0,0,0,3,6},
				{9,6,0,0,0,0,3,0,8},
				{7,0,0,6,8,0,0,0,0},
				{0,2,8,0,0,0,0,0,0}
				};
	
	//method to check if a number can be inserted at a given position
	public boolean isPossible(int x, int y, int n)
	{
		//next two for loops check element in x and y axis
		for(int i=0; i<9; ++i)
		{
			if(puzzle[i][y] == n)
			{
				return false;
			}
		}
		for(int i=0; i<9; ++i)
		{
			if(puzzle[x][i] == n)
			{
				return false;
			}
		}
		//to identify which sub grid the element belongs
		int x0 = find(x);
		int y0 = find(y);
		//checking for element in its respective sub grid
		for(int i=x0; i<=x0+2; ++i)
		{
			for(int j=y0; j<=y0+2; ++j)
			{
				if(puzzle[i][j] == n) return false;
			}
		}
		
		return true;
	}
	
	
	public void Solve()
	{
		for(int i=0; i<9; ++i)
		{
			for(int j=0; j<9; ++j)
			{
				//if encountered a 0 then enters
				if(puzzle[i][j] == 0)
				{
					//iterate from 1 to 9 and check any of it is a fit 
					for(int k=1; k<10; ++k)
					{
						if(isPossible(i, j, k))
						{
							//if true then set that value at the location
							puzzle[i][j] = k;
							//recursively calling solve with updated puzzle to solve all empty spaces
							Solve();
							//this excecute when a dead end occurs.. that no value is a fit,return statement get excecuted
							//and previous value is set as 0 
							//refer backtracking
							puzzle[i][j] = 0;	
						}
					}
					//get excecuted when no 9 numbers is a fit, causes the back tracking
					return; 
				}
				
			}
		}
		//this will be only reached when all values are filled and no more 0s are available
		reader();
		//after the function end it will go back to the recursive call statement so an exit is needed
		System.exit(0);
	}
	
	public void reader()
	{
		 for(int i=0; i<9; ++i)
			{
				for(int j=0; j<9; ++j)
				{
					System.out.print(puzzle[i][j]+" ");
				}
				System.out.println();
			}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
    Solver obj = new Solver();
    obj.reader();
    System.out.println();
    obj.Solve();
   
	}
	
	public static int find(int n)
	{
		if(n <3) return 0;
		else if(n<6) return 3;
		else return 6;
	}

}
