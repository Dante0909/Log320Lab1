package src;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to AI tictactoe\n---");
		Board board = new Board();
		CPUPlayer ai = new CPUPlayer(Mark.X);
				
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String useAlphaBetaText;
		do{
			System.out.print("Do you want the AI to use alpha beta? (y/n) > ");
			useAlphaBetaText = reader.readLine();
		}while(!useAlphaBetaText.equals("y") && !useAlphaBetaText.equals("n") && !useAlphaBetaText.equals("Y") && !useAlphaBetaText.equals("N"));
		boolean useAlphaBeta = true;
		if(useAlphaBetaText.equals("n") || useAlphaBetaText.equals("N")){
			useAlphaBeta = false;
		}
		System.out.println("You are O, AI is X.");
		while (board.evaluate(Mark.X) == 0 && !board.IsFull())
		{
			System.out.print("Play your piece (ex O 0 0) > ");
			String input = reader.readLine();
			String[] inputParts = input.split(" ");
			int column;
			int row;
			Mark mark = Mark.EMPTY;
			if (inputParts.length != 3)
			{
				System.out.println("Input must include 3 parameters");
				continue;
			}
			if (inputParts[0].equals("X") || inputParts[0].equals("x"))
			{
				System.out.println("\nX are taken by AI. Try with O.\n");
				continue;
			}
			else if (inputParts[0].equals("O") || inputParts[0].equals("o"))
			{
				mark = Mark.O;
			}
			else
			{
				System.out.println(inputParts[0]);
				continue;
			}
			try
			{
				row = Integer.valueOf(inputParts[1]);
				column = Integer.valueOf(inputParts[2]);
			} catch (NumberFormatException e) {
				System.out.println("Row and columns must be integers");
				continue;
			}

			if(board.getMarks()[row][column] != Mark.EMPTY){
				System.out.println("Trying to put a mark on an already taken case. Try again.");
				continue;
			}

			Move move = new Move(row,column);
			board.play(move,mark);

			// Permettre à l'AI de jouer son coup
			if(!board.IsFull()){
				ArrayList<Move> aimoves = useAlphaBeta ? ai.getNextMoveAB(board) : ai.getNextMoveMinMax(board);
				board.play(aimoves.get(0),Mark.X);
				System.out.println("AI has found " + aimoves.size() + " best moves. Picking the first (for now).");
			}

			board.PrintBoard();
		}
		if (board.evaluate(Mark.X) == 100)
		{
			System.out.println("CPU wins");
		}
		else if (board.evaluate(Mark.X) == -100)
		{
			System.out.println("Player wins");
		}
		else
		{
			System.out.println("It's a draw");
		}
	}
}
