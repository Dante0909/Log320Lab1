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
		numExploredNodes = 0;
    	
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
        return moves;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
		ArrayList<Move> moves = new ArrayList<Move>();
		int maxVal = Integer.MIN_VALUE;
		numExploredNodes = 0;

		Mark[][] state = board.getMarks();

		for(int i = 0; i < state.length; ++i) {
			for(int j = 0; j < state[i].length; ++j) {

				if(state[i][j] == Mark.EMPTY) {
					Move m = new Move(i,j);
					board.play(m, cpu);

					int eval = miniMaxAB(board,numExploredNodes,Integer.MIN_VALUE,Integer.MAX_VALUE,false);
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
		return moves;
    }
    
    private int minimax(Board board, int depth, boolean isMax) {
    	numExploredNodes++;
    	
    	int score = board.evaluate(cpu);
    	
    	if(board.IsFull() || score != 0) return score;
    	
    	Mark[][] state = board.getMarks();
    	
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
			return best;
		}
	}

    private int miniMaxAB(Board board,int depth,int alpha, int beta, boolean isMax){
		numExploredNodes++;

		int score = board.evaluate(cpu);

		if (board.IsFull() || score != 0) return score;

		Mark[][] state = board.getMarks();

		if (isMax) {
			int best = Integer.MIN_VALUE;
			Mark m = cpu;
			for (int i = 0; i < state.length; ++i) {
				for (int j = 0; j < state[i].length; ++j) {
					if (state[i][j] == Mark.EMPTY) {
						Move move = new Move(i, j);
						board.play(move, m);
						best = Math.max(best, miniMaxAB(board, depth - 1, Math.max(alpha,best), beta, !isMax));
						board.UndoMove(move);
						if (best >= beta) {
							return best;
						}
					}
				}
			}
			return best;
		} else {
			int best = Integer.MAX_VALUE;
			Mark m = player;
			for (int i = 0; i < state.length; ++i) {
				for (int j = 0; j < state[i].length; ++j) {
					if (state[i][j] == Mark.EMPTY) {
						Move move = new Move(i, j);
						board.play(move, m);
						best = Math.min(best, miniMaxAB(board, depth - 1, alpha, Math.min(beta, best), !isMax));
						board.UndoMove(move);
						if (best <= alpha) {
							return best;
						}
					}
				}
			}
			return best;
		}
    }
}
