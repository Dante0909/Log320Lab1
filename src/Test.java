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
		
		Board board = new Board();
		CPUPlayer ai = new CPUPlayer(Mark.X);
		//var test = ai.getNextMoveMinMax(board);
		//9 moves with equal value(tie)

		/*
		Move m = new Move(0,0);
		board.play(m, Mark.X);
		Move m2 = new Move(0,2);
		board.play(m2, Mark.O);
		ArrayList<Move> moves = ai.getNextMoveMinMax(board);*/

		//3 moves with equal value (win)
				
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
				mark = Mark.X;
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
			Move move = new Move(row,column);
			board.play(move,mark);

			// Permettre à l'AI de jouer son coup
			if(!board.IsFull()){
				ArrayList<Move> aimoves = ai.getNextMoveMinMax(board);
				board.play(aimoves.get(0),Mark.X);
				System.out.println("AI has found " + aimoves.size() + " best moves. Picking the first (for now).");
			}

			board.PrintBoard();
		}
		if (board.evaluate(Mark.X) == 100)
		{
			System.out.println("X wins");
		}
		else if (board.evaluate(Mark.X) == -100)
		{
			System.out.println("O wins");
		}
		else
		{
			System.out.println("It's a draw");
		}


	}
}
