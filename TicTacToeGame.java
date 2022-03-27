/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class TicTacToeGame {

// FINISH THE VARIABLE DECLARATION
   /**
	* The board of the game, stored as a one dimension array.
	*/
	private CellValue board[];


   /**
	* level records the number of rounds that have been
	* played so far. 
	*/
	private int level;

   /**
	* gameState records the current state of the game.
	*/
	private GameState gameState;


   /**
	* lines is the number of lines in the grid
	*/
	private int lines;

   /**
	* columns is the number of columns in the grid
	*/
	private int columns;


   /**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game
	*/
	private int sizeWin;


   /**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	public TicTacToeGame(){

		// YOUR CODE HERE 
		this.lines =3;
		this.columns=3;
		sizeWin = 3;
		level = 0;
		board = new CellValue[lines*columns];
		gameState=GameState.PLAYING;
		for (int i=0; i<board.length;i++){
			board[i]=CellValue.EMPTY;
		}


	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
	public TicTacToeGame(int lines, int columns){

		// YOUR CODE HERE 
		this.lines =lines;
		this.columns=columns;
		sizeWin = 3;
		board = new CellValue[lines*columns];
		level = 0;
		gameState=GameState.PLAYING;
		for (int i=0; i<board.length;i++){
			board[i]=CellValue.EMPTY;
		}

	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as 
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){

		// YOUR CODE HERE 
		this.lines =lines;
		this.columns=columns;
		this.sizeWin = sizeWin;
		board = new CellValue[lines*columns];
		level = 0;
		gameState=GameState.PLAYING;
		for (int i=0; i<board.length;i++){
			board[i]=CellValue.EMPTY;
		}

	}



   /**
	* getter for the variable lines
	* @return
	* 	the value of lines
	*/
	public int getLines(){

		// YOUR CODE HERE 
		return lines;
	}

   /**
	* getter for the variable columns
	* @return
	* 	the value of columns
	*/
	public int getColumns(){

		// YOUR CODE HERE 
		return columns;
	}

   /**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){

		// YOUR CODE HERE 
		return level;
	}

  	/**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){

		// YOUR CODE HERE 
		return sizeWin;
	}

   /**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){

		// YOUR CODE HERE 
		return gameState;
	}

   /**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should 
	* play next.
	* This method does not modify the state of the
	* game.
	* @return 
    *  the value of the enum CellValue corresponding
    * to the next expected value.
  	*/
	public CellValue nextCellValue(){

		// YOUR CODE HERE
		if (level%2==0){
			return CellValue.X;
		}
		else{
			return CellValue.O;
		}
	}

   /**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    *  the index of the cell in the array board
    * @return 
    *  the value at index i in the variable board.
  	*/
	public CellValue valueAt(int i) {

		// YOUR CODE HERE 
		if (i<0 || i>board.length){
			return CellValue.EMPTY;
		}
		else{
			return (board[i]);
		}
		
	}

   /**
	* This method is called when the next move has been
	* decided by the next player. It receives the index
	* of the cell to play as parameter.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, is is acceptable to keep playing
	* after a game is already won. If that is the case, the
	* a message should be printed out and the move recorded. 
	* the  winner of the game is the player who won first
   	* @param i
    *  the index of the cell in the array board that has been 
    * selected by the next player
  	*/
	public void play(int i) {


		// YOUR CODE HERE 
		if (i <board.length && i >=0 && board[i]==CellValue.EMPTY){
			board[i]=nextCellValue();
			setGameState(i);
			level+=1;
			
		}
		else{
			System.out.println("That index does not EXIST!");
		}
		/////////////////////////////////////////////////////
	
	}

 
   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set in
	* the method play(int i)
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (i.e. the game was playing). Therefore, it only needs to 
	* check if playing at index i has concluded the game, and if
	* set the oucome correctly
	* 
   	* @param i
    *  the index of the cell in the array board that has just 
    * been set
  	*/


	private void setGameState(int i){

		// YOUR CODE HERE 
		// initializing variables
		int count=(i/columns)*columns;
		int runTotal=0;
		int rows=0;
		boolean flag=true;
		CellValue vertical[] = new CellValue[lines];
		CellValue horizontal[] = new CellValue[columns];
		CellValue diag[];
		CellValue diag2[];
		// setting diagonal arrays to be the max possible amount of values it can be, then filling it with EMPTY if the diagonal is smaller
		if (lines>columns){
			diag = new CellValue[columns];
			diag2 = new CellValue[columns];
			for (int j=0; j<columns;j++){
				diag[j]=CellValue.EMPTY;
				diag2[j]=CellValue.EMPTY;
			}
		}
		else {
			diag = new CellValue[lines];
			diag2 = new CellValue[lines];
			for (int j=0; j<lines;j++){
				diag[j]=CellValue.EMPTY;
				diag2[j]=CellValue.EMPTY;
			}
		}

		// filling the horizontal and vertical array with the line of the new placement and testing if the game is over
		for (int j=0; j<columns; j++){
			horizontal[j]=board[count+j];
		}
		if (gameWon(horizontal,i)){
			if (nextCellValue()==CellValue.X){
				gameState=GameState.XWIN;
			}
			else{
				gameState=GameState.OWIN;
			}
		}
		count=(i%columns);
		for (int j=0;j<board.length;j+=columns){
			vertical[rows]=board[count+j];
			rows++;
		}
		if (gameWon(vertical,i)){
			if (nextCellValue()==CellValue.X){
				gameState=GameState.XWIN;
			}
			else{
				gameState=GameState.OWIN;
			}
		}
		// finding what index are the furthest diagonal away from the placement
		count=i;
		while ((count-columns+1)>0 && flag){
			if ((count-columns+1)%columns==0 ){
				flag=false;
			}
			else{
				count=count-columns+1;
			}
		}
		
		// putting diagonal in the array and testing
		diag[0]=board[count];
		rows=1;
		for (int j=columns;(count+j-rows)<board.length &&  (count+j-rows+1)%columns!=0 ;j+=columns){
			diag[rows]=board[count+j-rows];
			rows++;

		}
		if (gameWon(diag,i)){
			if (nextCellValue()==CellValue.X){
				gameState=GameState.XWIN;
			}
			else{
				gameState=GameState.OWIN;
			}
		}

		// repeat for other diagonal
		flag = true;
		count=i;
		while ((count-columns-1)>=0 && flag){
			if ((count-columns)%columns==0 || (count-columns)<0 ){
				flag=false;
			}
			else{
				count=count-columns-1;
			}
		}

		rows=1;
		diag2[0]=board[count];
		for (int j=columns;(count+j+rows)<board.length &&  (count+j+rows)%columns!=0 ;j+=columns){
			diag2[rows]=board[count+j+rows];
			rows++;
		}
		if (gameWon(diag2,i)){
			if (nextCellValue()==CellValue.X){
				gameState=GameState.XWIN;
			}
			else{
				gameState=GameState.OWIN;
			}
		}
		flag = true;
		if (gameState==GameState.PLAYING){
			for (int j=0; j<board.length && flag;j++){
				if (board[j]==CellValue.EMPTY){
					flag=false;
				}
			}
			if (flag){
				gameState=GameState.DRAW;
			}
		}

		
	}
	// helper function to test if there is a streak long enough to win the game, returns true if so
	private boolean gameWon(CellValue row[],int i){
		int runTotal=0;
		for (int j=0;j<row.length;j++){
			if (valueAt(i)==row[j]){
				runTotal+=1;
			}
			else{
				runTotal=0;
			}
			if (runTotal==sizeWin){
				return true;
			}
		}
		return false;
	}


   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	* 
   	* @return
    *  String representation of the game
  	*/

	public String toString(){

		// YOUR CODE HERE 
		String stringBoard=" ";
		String line2="---";
		for (int i=0; i<board.length;i++){
			if (valueAt(i)==CellValue.EMPTY){
				stringBoard+=" ";
			}
			else if (valueAt(i)==CellValue.X){
				stringBoard+="X";
			}
			else{
				stringBoard+="O";
			}
			if ((i+1)%columns==0){
				if (i+1!=board.length){
					stringBoard+="\n"+line2+"--\n ";
				}
				else{
					stringBoard+="\n";
				}
				line2="---";
			}
			else{
				stringBoard+=" | ";
				line2+="---";
				
			}
			

		}
		return stringBoard;
	}

}