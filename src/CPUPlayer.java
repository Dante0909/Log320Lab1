package src;
import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{
	private Mark cpu;
	private Mark player;
    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
    	this.cpu = cpu;
    	player = cpu == Mark.O ? Mark.X : Mark.O;
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
    	ArrayList<Move> moves = new ArrayList<Move>();
    	int maxVal = Integer.MIN_VALUE;
    	
    	Mark[][] state = board.getMarks();
    	
    	for(int i = 0; i < state.length; ++i) {
    		for(int j = 0; j < state[i].length; ++j) {
    			
    			if(state[i][j] == Mark.EMPTY) {
    				Move m = new Move(i,j);
    				board.play(m, cpu);
    				
    				
    				int eval = minimax(board, numExploredNodes, false);
    				
    				board.UndoMove(m);
    				
    				if(eval > maxVal) {
    					moves.clear();
    					maxVal = eval;
    					moves.add(m);
    				}
    				else if(eval == maxVal) {
    					moves.add(m);
    				}
    			}
    			
    		}
    	}
    	
    	
        numExploredNodes = 0;
        return moves;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){

        numExploredNodes = 0;

        /**
         * MinimaxAlphaBeta(posActuelle,joueur,alpha,beta)
             * si posActuelle est finale
             *      return f(p)
             *  si joueur == Max
             *      alphat = -infini
             *      foreach(successeurs pi de posActuelle)
             *          score = MiniMaxAlphaBet(pi,min,MAX(alpha,alphat),beta)
             *          alphat=MAX(alphat,score)
             *          if(alphat>=beta)
             *              return alphat
             *      return alphat
             *  si joueur == Min
         */

        return null;
    }
    
    private int minimax(Board board, int depth, boolean isMax) {
    	numExploredNodes++;
    	
    	int score = board.evaluate(cpu);
    	
    	if(board.IsFull() || score != 0) return score;
    	
    	Mark[][] state = board.getMarks();
    	//int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    	//Mark m = isMax ? player : cpu;
    	
    	/*for(int i = 0; i < state.length; ++i) {
    		for(int j = 0; j < state[i].length; ++j) {
    			
    			if(state[i][j] == Mark.EMPTY) {
    				Move move = new Move(i , j);
    				
    				board.play(move, m);
    				
    				if(isMax) best = Math.max(best, minimax(board, numExploredNodes, !isMax));
    				else best = Math.min(best, minimax(board,numExploredNodes,!isMax));
    				
    				board.UndoMove(move);
    				
    			}
    			
    		}
    	}*/
    	
    	//J'ai s�par� en deux for loop pour clarit� mais celle au dessus fait la m�me chose
    	
    	if(isMax) {
    		int best = Integer.MIN_VALUE;
    		Mark m = cpu;
    		for(int i = 0; i < state.length; ++i) {
        		for(int j = 0; j < state[i].length; ++j) {
        			if(state[i][j] == Mark.EMPTY) {
        				Move move = new Move(i , j);
            			board.play(move, m);
            			best = Math.max(best, minimax(board, numExploredNodes, !isMax));
            			board.UndoMove(move);            			
        			}
        			
        		}
        	}
    		return best;
    	}
    	else {
			int best = Integer.MAX_VALUE;
			Mark m = player;
			for (int i = 0; i < state.length; ++i) {
				for (int j = 0; j < state[i].length; ++j) {
					if (state[i][j] == Mark.EMPTY) {

						Move move = new Move(i, j);
						board.play(move, m);
						best = Math.min(best, minimax(board, numExploredNodes, !isMax));
						board.UndoMove(move);
					}
				}
			}
		}
    		return best;
	}

    private int miniMaxAB(Board board,Mark mark,int alpha, int beta, boolean isMax){
        Mark max;
        Mark min;
        if(isMax){
            max = cpu;
            min = player;
        } else{
            max = player;
            min = cpu;
        }

        if(board.evaluate(mark) != 0 || board.IsFull()){
            return board.evaluate(mark);
        }
        if(isMax){
            int alphat = Integer.MIN_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(board[i][j] == Mark.EMPTY){
                        Move m = new Move(i,j);
                        board.play(m,mark);
                        int score = miniMaxAB(board,min,Math.max(alpha,alphat),beta,!isMax);
                        alphat = Math.max(alphat,score);
                        if(alphat >= beta){
                            return alphat;
                        }
                        board.UndoMove(m);
                    }
                }
            }
            return alphat;
        }
        else{
            //todo: joueur==Min
        }
    }
}
