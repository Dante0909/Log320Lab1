package src;
import java.util.ArrayList;
import java.util.Arrays;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
    	board = new Mark[3][3];
    	for(Mark[] row: board) {
    		Arrays.fill(row, Mark.EMPTY);
    	}
    	System.out.println(board[2][2]);
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
		if(board[m.getRow()][m.getCol()] == Mark.Empty){
			board[m.getRow()][m.getCol()] = mark;
		}
    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
    	if (won(mark, board))
		{
			return 100;
		}
		else if ((mark == Mark.X && won(Mark.O, board)) || (mark = Mark.O && won(Mark.X, board)))
		{
			return -100;
		}
		return 0;
    }
    
    public boolean won(Mark player, Mark[][] b) {
    	
    	if(
    			//horizontal
    			(b[0][0] == player && b[0][1] == player && b[0][2] == player) ||
    			(b[1][0] == player && b[1][1] == player && b[1][2] == player) ||
    			(b[2][0] == player && b[2][1] == player && b[2][2] == player) ||
    			
    			//vertical
    			(b[0][0] == player && b[1][0] == player && b[2][0] == player) ||
    			(b[0][1] == player && b[1][1] == player && b[2][1] == player) ||
    			(b[0][2] == player && b[1][2] == player && b[2][2] == player) ||
    			
    			//cross
    			(b[0][0] == player && b[1][1] == player && b[2][2] == player) ||
    			(b[0][2] == player && b[1][1] == player && b[2][0] == player)
    			) 
    	{
    		return true;
    	}
    		
    	return false;
    }
}
