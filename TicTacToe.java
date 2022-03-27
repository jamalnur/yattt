
import java.io.Console;
import java.util.Scanner;
public class TicTacToe{

   /**
     * <b>main</b> of the application. Creates the instance of  TicTacToeGame 
     * and starts the game. If two parameters lines  and columns
     * are passed, they are used. If the paramters lines, columns
     * and win are passed, they are used.
     * Otherwise, a default value is used. Defaults values (3) are also
     * used if the paramters are too small (less than 2).
     * Here, we assume that the command lines arguments are indeed integers
     *
     * @param args
     *            command lines parameters
     */
     public static void main(String[] args) {

        StudentInfo.display();

        Console console = System.console();
        TicTacToeGame game;
        int lines, columns, win;
        lines = 3;
        columns = 3;
        win = 3;

        if (args.length >= 2) {
            lines = Integer.parseInt(args[0]);
            if(lines<2){
                System.out.println("Invalid argument, using default...");
                lines = 3;
            }
            columns = Integer.parseInt(args[1]);
            if(columns<2){
                System.out.println("Invalid argument, using default...");
                columns = 3;
            }
        }
        if (args.length >= 3){
            win = Integer.parseInt(args[2]);
            if(win<2){
                System.out.println("Invalid argument, using default...");
                win = 3;
            }
        } 
        if (args.length > 3){
            System.out.println("Too many arguments. Only the first 3 are used.");
        } 
        
        int input=1;
        game = new TicTacToeGame(lines,columns,win);
        Scanner scanInput= new Scanner(System.in);
        while(game.getGameState()== GameState.PLAYING)
        {
            System.out.println(game.toString());

            if (game.nextCellValue()==CellValue.X)
            {
                System.out.println("X to Play: ");
                input=scanInput.nextInt();
                while((input<1||input>9)||(game.valueAt(input-1)!=CellValue.EMPTY))
                {
                    if (game.valueAt(input-1)!=CellValue.EMPTY)
                    {
                        System.out.println("That index already has a value");
                    }
                    else
                    {
                    System.out.println("Enter a value between 1 and 9");
                    }
                    input=scanInput.nextInt();
                }
                game.play((input-1));
            }
            else if (game.nextCellValue()==CellValue.O)
            {
                System.out.println("O to Play: ");
                input=scanInput.nextInt();
                while(input<1||input>9||(game.valueAt(input-1)!=CellValue.EMPTY))
                {
                    if (game.valueAt(input-1)!=CellValue.EMPTY)
                    {
                        System.out.println("That index already has a value");
                    }
                    else
                    {
                    System.out.println("Enter a value between 1 and 9");
                    }
                    input=scanInput.nextInt();
                }
                game.play((input-1));
            }

        }
        System.out.println(game.toString());
         if (game.getGameState()==GameState.XWIN)
            {
                System.out.println("X has Won");
            }
            else if (game.getGameState()==GameState.OWIN)
            {
                System.out.println("O has Won");
            }
            else
            {
                System.out.println("The game is a draw");
            }



    
    }


}