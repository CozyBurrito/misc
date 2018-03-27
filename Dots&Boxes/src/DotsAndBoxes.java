import java.util.Scanner;
import java.util.Random;

public class DotsAndBoxes
{
	//Variables
	private Scanner sc = new Scanner(System.in);
	private boolean turn = true, isEnd = false, winTurn = false;
	private int numPlayers, boardSize, arrSize, numMovesLeft, row, col, 
		        p1Points = 0, p2Points = 0;
	private char[][] board = null;
	private String userInput;
	private Random rnd = new Random();
	
	public static void main(String[] args)
	{	
		new DotsAndBoxes().run();
	}
	
	private void run()
	{
		//Get number of players (0 - 2) and board size (1 - 12)
		numPlayers = getNumPlayers(); 
		boardSize = getBoardSize(); 
		
		//Generate initial board 
		generateBoard();
		
		if (numPlayers == 2)
		{
			//Print the board, get the players turn, check to see if that 
			//player completed a box, if so then give the current player
			//another turn, if not then give the turn to the other player
			while (!isEnd)
			{
				printBoard();
				isEnd = playerTurn();
						
				if (!winTurn)
					turn = !turn;
				else
					winTurn = !winTurn;
			}
		}
		else if (numPlayers == 1)
		{
			//Print the board, if it's player 1's turn then get it from the
			//user, if not then get it from the computer
			while (!isEnd)
			{
				printBoard();
				
				if (turn)
					isEnd = playerTurn();	
				else 
					isEnd = computerTurn();
				
				if (!winTurn)
					turn = !turn;
				else
					winTurn = !winTurn;
			}
		}
		else 
		{
			//Print the board and get the computers turn each time 
			while (!isEnd)
			{
				printBoard();
				
				isEnd = computerTurn();
				
				if (!winTurn)
					turn = !turn;
				else
					winTurn = !winTurn;
			}
			
		}

		//Print final score
		System.out.println("\nFinal Score: \tPlayer 1: " + p1Points + 
						   "\tPlayer 2: " + p2Points);
				
		if (p1Points > p2Points)
			System.out.println("\nPlayer 1 Wins! \nThank you for playing!");
		else if (p1Points < p2Points)
			System.out.println("\nPlayer 2 Wins! \nThank you for playing!");
		else
			System.out.println("\nIt's a Tie! \nThank you for playing!");
				
		//Close scanner
		sc.close();
	}
	
	private boolean computerTurn()
	{
		boolean isOver = false;
	
		//Check to see if the board is filled
		if (numMovesLeft > 0)
		{
			//For the user to follow which computer's turn it is
			if (turn)
				System.out.print("\nPlayer 1's");
			else
				System.out.print("\nPlayer 2's");
			System.out.print(" move \n");
			
			//Get a move from the computer, until it is valid
			do 
			{
				getComputerMove();
			} while (!canPlay());
			
			//Play the move and check if a box has been completed
			play();
			completeBox();	
			
		}
		else
			isOver = true;
		
		//Game is over when no more moves left to play
		return isOver;
		
	}
	
	private void getComputerMove()
	{
		//Generate random  numbers between 0 and length of the array
		row = rnd.nextInt(board.length);
		col = rnd.nextInt(board.length);
	}
	
	private void play()
	{
		//Check whether a '|' or a '-' should be played 
		if ((col % 2) == 0 && (row % 2) != 0)
			board[row][col] = '|';	
		else if ((row % 2) == 0 && (col % 2) != 0)
			board[row][col] = '-';	
		
		numMovesLeft--;
	}
	
	private boolean playerTurn()
	{
		boolean isOver = false;
		
		//Check to see if possible to continue playing
		if (numMovesLeft > 0)
		{
			//get the player's move, if invalid then end the game
			getPlayerMove();
		
			if (canPlay())
			{
				//if valid move, play the move and check to see if a box has 
				//been completed
				play();
				completeBox();
			}
			else
				isOver = true;
		}
		else
			isOver = true;
		
		//True when no mor moves left or invalid move played by user
		return isOver;
	}
	
	private void completeBox()
	{
		//check last move to see if '-' or '|' move played
		if (board[row][col] == '-')
		{
			//if played on first row
			if (row == 0)
			{
				//if it's a piece that created a box
				if (board[row+1][col] == ' '   &&
					board[row+1][col-1] == '|' &&
					board[row+1][col+1] == '|' &&
					board[row+2][col] == '-')
				{
					//add the points to player and give him another turn
					addPoints(row+1, col);
					winTurn = !winTurn;
				}
			}
			//if played on last row 
			else if (row+1 == board.length)
			{
				//if it's a piece that created a box
				if (board[row-1][col] == ' '   &&
					board[row-1][col-1] == '|' &&
					board[row-1][col+1] == '|' &&
					board[row-2][col] == '-')
				{
					//add the points to player and give him another turn
					addPoints(row-1, col);
					winTurn = !winTurn;
				}	
			}
			//if played on neither first nor last row
			else
			{
				//check to see if box is possible above it, and below it
				if (board[row+1][col] == ' '   &&
					board[row+1][col-1] == '|' &&
					board[row+1][col+1] == '|' &&
					board[row+2][col] == '-'   &&
					board[row-1][col] == ' '   &&
					board[row-1][col-1] == '|' &&
					board[row-1][col+1] == '|' &&
					board[row-2][col] == '-')
				{
					//add 2 points to player and give him another turn
					addPoints(row-1, col);
					addPoints(row+1, col);
					winTurn = !winTurn;
				}
				//if box it possible above it
				else if (board[row-1][col] == ' '   &&
						 board[row-1][col-1] == '|' &&
						 board[row-1][col+1] == '|' &&
						 board[row-2][col] == '-')
				{
					//add the points to player and give him another turn
					addPoints(row-1, col);
					winTurn = !winTurn;
				}
				//if box is possible below it
				else if (board[row+1][col] == ' '   &&
						board[row+1][col-1] == '|' &&
						board[row+1][col+1] == '|' &&
						board[row+2][col] == '-')
				{
					//add the points to player and give him another turn
					addPoints(row+1, col);
					winTurn = !winTurn;
				}
			}
		}
		//if the last play was a '|'
		else if (board[row][col] == '|')
		{
			//if in first col
			if (col == 0)
			{
				//check right to see if box is possible
				if (board[row][col+1] == ' '   &&
					board[row-1][col+1] == '-' &&
					board[row+1][col+1] == '-' &&
					board[row][col+2] == '|')
				{
					//add the points to player and give him another turn
					addPoints(row, col+1);
					winTurn = !winTurn;
				}
			}
			//if in last col
			else if (col+1 == board.length)
			{
				//check left to see if box is possible
				if (board[row][col-1] == ' '   &&
					board[row-1][col-1] == '-' &&
					board[row+1][col-1] == '-' &&
					board[row][col-2] == '|')
				{
					//add the points to player and give him another turn
					addPoints(row, col-1);
					winTurn = !winTurn;
				}
			}
			//if not played on first col or last col
			else
			{
				//check left and right to see if box is possible
				if (board[row][col+1] == ' '   &&
					board[row-1][col+1] == '-' &&
					board[row+1][col+1] == '-' &&
					board[row][col+2] == '|'   &&
					board[row][col-1] == ' '   &&
					board[row-1][col-1] == '-' &&
					board[row+1][col-1] == '-' &&
					board[row][col-2] == '|')
				{
					//add 2 points to player and give him another turn
					addPoints(row, col-1);
					addPoints(row, col+1);
					winTurn = !winTurn;
				}
				//if only possible on right
				else if (board[row][col-1] == ' '   &&
						 board[row-1][col-1] == '-' &&
						 board[row+1][col-1] == '-' &&
						 board[row][col-2] == '|')
				{
					//add the points to player and give him another turn
					addPoints(row, col-1);
					winTurn = !winTurn;
				}
				//if box is possible on left
				else if (board[row][col+1] == ' '   &&
						 board[row-1][col+1] == '-' &&
						 board[row+1][col+1] == '-' &&
						 board[row][col+2] == '|')
				{
					//add the points to player and give him another turn
					addPoints(row, col+1);
					winTurn = !winTurn;
				}
			}

		}
		
	}
	
	private void addPoints(int row2, int col2)
	{
		//checks player's turn and gives them points and write's their 
		//number in the space
		if (turn)
		{
			board[row2][col2] = '1';
			p1Points++;
		}
		else
		{
			board[row2][col2] = '2';
			p2Points++;
		}
		
	}
	
	private boolean canPlay()
	{
		boolean isValid = false;
		//check to see if the player is playing on certain spots on the board
		if (row != col          &&
			row <= board.length &&
			col <= board.length &&
			!(row % 2 == 0 && col % 2 == 0) && 
			!(row % 2 != 0 && col % 2 != 0))
		{
			//check to see if the space they are playing at is empty
			if (board[row][col] == ' ')
			{
				isValid = true;
			}
		}
		
		return isValid;
	}
	
	private void getPlayerMove()
	{
		boolean isValid = false;
		//Get a position from the user
		//Make sure total length is only 2
		do{
			if (turn)
				System.out.print("\nPlayer 1's");
			else
				System.out.print("\nPlayer 2's");
			System.out.print(" move (row, col): ");
			userInput = sc.nextLine();
			//check to see if first is upper case and second is lower case
			if (userInput.length() == 2)
			{
				isValid = Character.isUpperCase(userInput.charAt(0)) &&
						  Character.isLowerCase(userInput.charAt(1));
			}
			
		} while (!isValid);
		
		//sets's a = 0, b = 1, ... 
		row = userInput.charAt(0) - 65; 
		col = userInput.charAt(1) - 97;
		
	}
	
	private int getNumPlayers()
	{
		//Get the number of players from user
		//Check each input's range, valid = 0 - 2
		do{
			System.out.print("Number of players (0 - 2): ");
			userInput = sc.nextLine();
		} while (!userInput.matches("[0-2]"));
		return Integer.parseInt(userInput);
	}
	
	private int getBoardSize()
	{
		//Get the board size from the user
		//Check each input's range, valid = 1 - 12
		do{
			System.out.print("Board size (1 - 12): ");
			userInput = sc.nextLine();
		} while (!userInput.matches("[1-9]|[1-1][0-2]"));
		return Integer.parseInt(userInput);
	}
	
	private void generateBoard()
	{
		//calculate number of moves left
		numMovesLeft = (boardSize * (boardSize + 1)) * 2;
		
		//calculate and create the array
		arrSize = (boardSize * 2) + 1;
		board = new char[arrSize][arrSize];
		
		//Set each value at [x][y] index as a ' '
		//For each [x][y] index that are both even, set index value as a 'o'  
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board.length; j++) 
			{
				board[i][j] = ' ';
				if ((i % 2) == 0 && (j % 2) == 0)
				{
					board[i][j] = 'o';
				}
			}
		}
	}
	
	private void printBoard()
	{
		System.out.println("");
		
		//Lowercase alphabet
		System.out.print("  ");
		for (int i = 0; i < board.length; i++) 
			System.out.print( " " + (char)('a' + i));
		System.out.println("");
		
		//Uppercase alphabet + board
		for (int i = 0; i < board.length; i++) 
		{
			System.out.print(" " + (char)('A' + i));
			for (int j = 0; j < board.length; j++) 
				System.out.print(" " + board[i][j]);
			System.out.println("");
		}
	}

}
