package src;
import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark cpuMax = Mark.EMPTY;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        cpuMax = cpu;
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
        numExploredNodes = 0;
        return null;
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

    public int miniMaxAB(Board board,Mark mark,int alpha, int beta, boolean isMax){
        Mark max;
        Mark min;
        if(isMax){
            max = cpuMax;
            min = max == Mark.X ? Mark.O : Mark.X;
        } else{
            max = cpuMax == Mark.X ? Mark.O : Mark.X;
            min = cpuMax;
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
