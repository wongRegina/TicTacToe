import java.util.Random;
import java.util.Scanner;


public class TicTacToe {
	/** Main Method - Executes the Game.
	 * 
	 */
	public static String currentPlayer = "O";
	public static void main(String[] args) {
		Scanner move = new Scanner (System.in);
		System.out.println("Let's play TicTacToe! O starts");
		System.out.print("If you want to play with the computer please input yes. If you don't want to play with the computer enter anything else");
		boolean computer = false;
		String reply = move.next();
		if(reply.equalsIgnoreCase("yes"))
		{
		    computer = true;
		}
		if (computer == true)
		{
		    double first = Math.random();
		    boolean computerTurn = false, computerFirst = false;
		    if (first < 0.5)
		    {
		        System.out.println("Computer goes first");
		        computerTurn = true;
		        computerFirst= true;
		    }
		    else
		    {
		        System.out.println("You go first");
		    }
		    String board [][] =  { {"0","1","2"}, {"3","4","5"} , {"6","7","8"} };
		    printBoard(board);
		    boolean gameOver = false;
            int countMove = 0;
		    while (gameOver == false && countMove < 9)
		    {
		        if (computerTurn == false)
		        {
		            getMove(currentPlayer , board , move);
		            computerTurn = true; 
		        }
		        else
		        {
		            System.out.println("The Computer made a move");
		            computerMove(board);
		            computerTurn = false;
		        }
		        printBoard(board);
		        gameOver = checkWin(board);
		        changePlayer(currentPlayer);
		        countMove++;
		    }
		    if (checkWin(board) == true)
		    {
		        if ((currentPlayer.equals("X") && computerFirst == true) || (currentPlayer.equals("O") && computerFirst == false))
		        {
		            System.out.print("Computer won the game. Better luck next time");
		        }
		        else
		        {
		            System.out.print("Congratulations! You beat the computer \\^o^/");
		        }
		    }
		    else
		    {
		        System.out.print("It is a tie ");
		    }
		}
		
		// initialize board, first player and scanner
        if (computer == false)
        {
            String board [][] =  { {"0","1","2"}, {"3","4","5"} , {"6","7","8"} };
            printBoard(board);
            boolean gameOver = false;
            int countMove = 0;
		    while (gameOver == false && countMove < 9)
		    {
		        getMove(currentPlayer , board , move);
		        printBoard(board);
		        gameOver = checkWin(board);
		        changePlayer(currentPlayer);
		        countMove++;
		    }
		    if (checkWin(board) == true)
		    {
    		    System.out.print("Congratulations! " + changePlayer(currentPlayer) + " won the game \\^o^/");
		    }
		    else
		    {
		        System.out.print("It is a tie ");
		    }
        }
		
		//loop through the game: player chooses position to play, board prints, 
		//check for win with break out condition, then change player if game continues
	
		
		//Prints congratulations message to winner or tie message if no win exists
	
	}
	
	/** Prints TicTacToe board in 3 by 3 grid
	 * 
	 * @param board	
	 */
	public static void printBoard(String[][] board) {
		for (int row = 0 ; row < 3 ; row ++)
		{
		    for (int col = 0 ; col < 3 ; col++)
		    {
		        if (col != 2 )
		        {
		            System.out.print(board [row] [col] + " | ");
		        }
		        else
		        {
		            System.out.print(board [row] [col]);
		        }
		    }
		    System.out.println();
		    if ( row != 2)
		    {
		        System.out.println("~~+~~~+~~");
		    }
		}
		//Print board in 3 by 3 grid. First 3 positions go into the first row, etc.
	}
	
	/** Changes player for new turn
	 * 
	 * @param currPlayer	Current player, X or O
	 * @return				Changed player, X or O
	 */
	public static String changePlayer (String currPlayer) {
		if (currPlayer.equals("X"))
		{
		    currentPlayer = "O";
		}
		else
		{
		    currentPlayer = "X";
		}
		return currentPlayer;
		//Check current player: if x, then switch to o. If o, then switch to x. Return new player.
		
	}
	
	/** Current Player selects position for next move
	 * 
	 * @param currPlayer	Current player, X or O
	 * @param board			TicTacToe board represented by a 3x3 array
	 * @param s				Scanner for input
	 */
	public static void getMove(String currPlayer, String[][] board, Scanner s) {
		System.out.print("Choose a position on the board numbers 0-8 Player " + currPlayer + ": ");
        int choice = 0;
        if (s.hasNextInt())
        {
            choice = s.nextInt();
        }
        else 
        {
            while (!s.hasNextInt())
            {
                System.out.print("Enter a number between 0-8 : ");
                String input = s.next();
                if (s.hasNextInt())
                {
                    choice = s.nextInt();
                    break;
                }
            }
        }
		choice = checkMove(choice , board , s);
		board [choice/3] [choice%3] = currPlayer;
	}
	
	public static void computerMove(String [] [] board)
	{
	    for (int row = 0 ; row < 3 ;row ++)
		{
		    if (board [row] [0].equals(board [row] [1]) && !(board [row] [2] .equals("X") || board [row] [2] .equals("O")))
		    {
		        board [row] [2] = currentPlayer;
		        return;
		    }
		    else if (board [row] [0].equals(board [row] [2]) && !(board [row] [1] .equals("X") || board [row] [1] .equals("O")))
		    {
		        board [row] [1] = currentPlayer;
		        return;
		    }
		    else if (board [row] [1].equals(board [row] [2]) && !(board [row] [0] .equals("X") || board [row] [0] .equals("O")))
		    {
		        board [row] [0] = currentPlayer;
		        return;
		    }
		}
		for (int col = 0 ; col < 3 ;col ++)
		{
		    if (board [1] [col].equals(board [0] [col]) && !(board [2] [col] .equals("X") || board [2] [col] .equals("O")))
		    {
		        board [2] [col] = currentPlayer;
		        return;
		    }
		    else if (board [2] [col].equals(board [0] [col]) && !(board [1] [col] .equals("X") || board [1] [col] .equals("O")))
		    {
		        board [1] [col] = currentPlayer;
		        return;
		    }
		    else if (board [1] [col].equals(board [2] [col]) && !(board [0] [col] .equals("X") || board [0] [col] .equals("O")))
		    {
		        board [0] [col] = currentPlayer;
		        return;
		    }
		}
		if(board [0][0] == board [1] [1] && !(board [2] [2] .equals("X") || board [2] [2] .equals("O")))
		{
		    board [2] [2] = currentPlayer;
		    return;
		}
		else if (board [2] [2] == board [0][0] && !(board [1] [1] .equals("X") || board [1] [1] .equals("O")))
		{
		    board [1] [1] = currentPlayer;
		    return;
		}
		else if (board [2] [2] == board [1][1] && !(board [0] [0] .equals("X") || board [0] [0] .equals("O")))
		{
		    board [0] [0] = currentPlayer;
		    return;
		}
		
		if(board [2][0] == board [1] [1] && !(board [0] [2] .equals("X") || board [0] [2] .equals("O")))
		{
		    board [0] [2] = currentPlayer;
		    return;
		}
		else if (board [0] [2] == board [2][0] && !(board [1] [1] .equals("X") || board [1] [1] .equals("O")))
		{
		    board [1] [1] = currentPlayer;
		    return;
		}
		else if (board [0] [2] == board [1][1] && !(board [2] [0] .equals("X") || board [2] [0] .equals("O")))
		{
		    board [2] [0] = currentPlayer;
		    return;
		}
		
		Random rnd = new Random ();
		int psn;
		boolean empty = false;
		if (board [1] [1].equals("4"))
		{
		    board [1][1] = currentPlayer;
		    return;
		}
		else if(board [0] [0].equals("0") || board [0] [2].equals("2") || board [2] [0].equals("6") ||board [2] [2].equals("8"))
		{
		    psn =rnd.nextInt(5) * 2;
		    empty = ! filled(psn , board);
		    while( empty == false)
		    {
		        psn =rnd.nextInt(5) * 2;
		        empty = ! filled(psn , board);
		    } 
		    board [psn/3] [psn%3] = currentPlayer;
		}
		else
		{
		    psn =rnd.nextInt(9);
		    empty = ! filled(psn , board);
		    while( empty == false)
		    {
		        psn =rnd.nextInt(9);
		        empty = ! filled(psn , board);
		    }
		    board [psn/3] [psn%3] = currentPlayer;
		}
        
	}
	
	
	/** Checks position of next move to determine if input is valid
	 * or position is already filled. Allows player to choose another
	 * position if position is invalid
	 * 
	 * @param position		Chosen position of current player
	 * @param board			TicTacToe board represented by a 3x3 array
	 * @param s				Scanner for input
	 * @return				Chosen position of current player
	 */
	public static boolean filled (int psn , String[][] board)
	{
	    	boolean filled = (board [psn/3] [psn%3] .equals("X") || board [psn/3] [psn%3] .equals("O"));
	    	return filled;
	}

	public static int checkMove(int psn , String[][] board, Scanner s) {
		boolean filled = true;
		boolean valid = (psn >= 0 && psn < 9);
        if (valid == true)
        {
            filled = (board [psn/3] [psn%3] .equals("X") || board [psn/3] [psn%3] .equals("O"));
        }
		while( valid == false || filled == true)
		{
		    if (valid == false)
		    {
		        System.out.print("Out of bound. Choose a vaild position : ");
		        if (s.hasNextInt())
                {
                    psn = s.nextInt();
                }
                else 
                {
                    while (!s.hasNextInt())
                    {
                        System.out.print("Enter a number between 0-8 : ");
                        String input = s.next();
                        if (s.hasNextInt())
                        {
                            psn = s.nextInt();
                            break;
                        }
                    }
                }
		    }
		//Determine if position input is an integer 0-8. Player is directed to choose again if choice is invalid.
		    if (filled == true)
		    {
		        System.out.println("That place is already taken. ");
		        System.out.print("Choose another postion : ");
		        if (s.hasNextInt())
                {
                    psn = s.nextInt();
                }
                else 
                {
                    while (!s.hasNextInt())
                    {
                        System.out.print("Enter a number between 0-8 : ");
                        String input = s.next();
                        if (s.hasNextInt())
                        {
                            psn = s.nextInt();
                            break;
                        }
                    }
                }
		    }
		    valid = (psn >= 0 && psn < 9);
		    if (valid == true)
            {
                filled = (board [psn/3] [psn%3] .equals("X") || board [psn/3] [psn%3] .equals("O"));
            }
            else
            {
                filled = false;
            }
		}
		return psn;
		//Determine if position is already filled. Player is directed to choose again if choice is invalid.
	}
	
	/** Checks rows, columns, and diagonals for all X's or all O's - win condition
	 * 
	 * @param board			TicTacToe board represented by a 3x3 array
	 * @return				true if win exists, false if no win exists
	 */
	public static boolean checkWin(String[][] board) {
		for (int row = 0 ; row < 3 ;row ++)
		{
		    if (board [row] [0].equals(board [row] [1]) && board [row] [0].equals(board [row] [2]))
		    {
		        return true;
		    }
		}
		
		//Check rows for win condition - all X's or all O's.
		for (int col = 0 ; col < 3 ; col ++)
		{
		    if (board [0] [col].equals(board [1] [col]) && board [0] [col].equals(board [2] [col]))
		    {
		        return true;
		    }
		}
		//Check columns for win condition - all X's or all O's.
		if(board [0][0] == board [1] [1] && board [2] [2] == board [0][0])
		{
		    return true;
		}
		if (board [2][0] == board [1] [1] && board [0] [2] == board [2][0])
		{
		    return true;
		}
		
		//Check diagonals for win condition - all X's or all O's.
		return false;
	}
	
}
