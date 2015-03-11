/**
 * Tic Tac Toe .java
 * 
 * This class handles the "behind the scenes" parts of the tic tac toe game
 * 
 * + TicTacToe - constructor: creates the board
 * createBoard (void) void - creates board array, populates with -1
 * printBoard (void) void - prints out the board array, in a 3x3 matrix form
 * isFull (void) boolean - checks to see if the array is full
 * checkForWinners (void) boolean - checks to see if there are winners, 
 * 		calls the following 3 methods: checkRows, checkCols, checkDiags
 * checkRows (void) boolean - loops through all the rows, checks for winner
 * checkCols (void) boolean - loops through all the cols, checks for winner
 * checkDiags (void) boolean - loops through all the diags, checks for winner
 * check (int, int, int) boolean - checks to see if the 3 ints are equal
 * getTurn (void) int -  returns the value of turn
 * updateTurn (void) void - increments turn
 * mark (int) boolean - at the index that is passed in, 
 * 		update the value of board and update turn
 * 
 * 
 * @author Dustin
 *
 */
public class TicTacToe {
	int board[];
	int turn; // 0 for p1, 1 for p2
	final int ROW = 3, COL = 3;
	
	/** 
	 * Constructor, creates board.
	 * no need for params, will always be a 3x3 board
	 */
	public TicTacToe()
	{
		createBoard();
	}
	
	/**
	 * createBoard
	 * creates a new board array of size row X column
	 * this was a double array but converted to a single to help
	 * with the GUI
	 * 
	 * the equation to get the proper row value is
	 * row * totalColumnCount + column
	 * 
	 * @param r  - represents row
	 * @param c  - represents col
	 */
	public void createBoard()
	{
		board = new int[ROW*COL];
		
		for(int r = 0; r < ROW; r++)
			for(int c = 0; c < COL; c++)
				board[r * COL + c] = -1;
	}
	
	/**
	 * Test method, prints out the board in a tic tac toe layout
	 */
	public void printBoard()
	{
		for(int i = 0; i < ROW; i++){
			for(int j = 0; j < COL; j++){
				System.out.printf("%d ", board[i * COL + j]);
			}
			System.out.printf("\n");
		}
	}
	
	/**
	 * checks each slot for a -1
	 * @return true iff every slot is either 0 or 1, false if there is at least one -1
	 */
	public boolean isFull()
	{
		for(int i = 0; i < ROW; i++)
			for(int j = 0; j < COL; j++)
				if(board[i * COL + j] < 0)
					return false;
		return true;
	}
	
	/**
	 * calls the helper methods for checking rows, columns and diags
	 * 
	 * @return true iff one of the three helper methods returns true
	 */
	public boolean checkForWinners()
	{
		return (checkRows() || checkCols() || checkDiags());
	}
	
	/**
	 * runs the check method based on 3 params, these
	 * params are each of the rows with specific columns.
	 * loops though each row, looks for True
	 * 
	 * @return true iff all 3 slots are the same
	 */
	private boolean checkRows()
	{
		// loops through all 3 rows
		for(int i = 0; i < ROW; i++)
		{
			// runs a check on each row's 3 columns.
			if(check(board[i * COL + 0], board[i * COL + 1], board[i * COL + 2]))
				return true;
		}
		return false;
	}
	
	/**
	 * runs the check method based on 3 params, these
	 * params are each of the columns with specific rows.
	 * loops though each column, looks for True
	 * 
	 * @return true iff all 3 slots are the same
	 */
	private boolean checkCols()
	{
		// loops through all 3 columns
		for(int j = 0; j < COL; j++)
		{
			// runs a check on each column's 3 rows.
			if(check(board[0 * COL + j], board[1 * COL + j], board[2 * COL + j]))
				return true;
		}
		return false;
	}
	
	/**
	 * calls the check method with 3 params
	 * done with an or gate, if either the first check or the second check
	 * is true, then it is true
	 * 
	 * @return true iff a diag is a winner
	 */
	private boolean checkDiags()
	{
		// 00, 11, 22 and 0,2, 11, 2,0
		if(check(board[0 * COL + 0], board[1 * COL + 1], board[2 * COL + 2]) || check(board[0 * COL + 2], board[1 * COL + 1], board[2 * COL + 0]))
			return true;
		return false;
	}
	
	/**
	 * checks if a, b and c are equal, this is done by the transitive propertie
	 * if a = b and b = c then a = c.
	 * 
	 * if this is the case then a b and c are all x or o which is a winner.
	 * 
	 * the a >= 0 is an optional check, however, if the first item in the line
	 * is not marked, then it speeds up the check.
	 * 
	 * @param a first spot to check
	 * @param b second
	 * @param c third
	 * @return true if all 3 are equal, false if at least one is false
	 */
	private boolean check(int a, int b, int c)
	{
		// based on logical tables and transitive properties
		return ((a >= 0) && (a == b) && (b == c));
	}
	
	/**
	 * setter method, increments turn
	 */
	private void updateTurn() { turn++; }
	
	/**
	 * getter method
	 * @return turn value
	 */
	public int getTurn() { return turn; }
	
	/**
	 *  determines the turn, 0 for p1, 1 for p2
	 *  checks to make sure the index is within the array bounds
	 *  updates the value at that position in the array based on the player
	 *  
	 *  updates the turn value
	 *  
	 * @param index - represents the position in which you are adding the mark
	 * @return true for correctly marking the square, false for already taken
	 */
	public boolean mark(int index)
	{
		// this determines whether or not it is an x or o
		int indicator = -1;
		if(turn % 2 == 0)
			indicator = 0; // 0 = x ( red )
		else
			indicator = 1; // 1 = o ( blue )
		
		// checks for array bounds
		if(index >= 0 && index < ROW * COL){
				if(board[index] < 0){
					// updates with 1 or 0
					board[index] = indicator;
					// updates turn variable
					updateTurn();
					// returns true if it successfully updates the board
					return true;
				}
		}
		// returns false if fails to update the board
		return false;
	}
}
