import java.util.Scanner;

public class MyTicTacToe {
    public static char[][] XOarray ;
    public static boolean validMove = true;

    public static void main(String[] args) {
        startGame();
    }
    public static void startGame(){
        try {
            clearScreen();
        } catch (Exception e) {
        }
        XOarray= new char[][]{{'.', '.', '.'}, {'.', '.', '.'}, {'.', '.', '.'}};
        int PlayerToMove = 1;
        initBoard(XOarray);
        int[] coordinates = new int[2];
        do {
            coordinates = getHumanMove(PlayerToMove);
            mark(coordinates[0], coordinates[1], PlayerToMove);
            try {
                clearScreen();
            } catch (Exception e) {
            }
            initBoard(XOarray);
            if(validMove)PlayerToMove = setPlayer(PlayerToMove);
        }
        while (!hasWon());
        startGame();
    }

    static void initBoard(char[][] XOarray) {
        String[][] boardToStart = {
                {"  1   2   3"},
                {"A", "|", "|"},
                {"----+---+---"},
                {"B", "|", "|"},
                {"----+---+---"},
                {"C", "|", "|"}};
        int XOrow = 0;
        for (int row = 0; row < boardToStart.length; row++) {
            for (int i = 0; i < boardToStart[row].length; i++) {
                if (row % 2 == 1) {
                    System.out.printf(" %s %s", boardToStart[row][i], XOarray[XOrow][i]);
                } else {
                    System.out.printf(" %s", boardToStart[row][i]);
                }
            }
            if (row % 2 == 1) XOrow++;
            System.out.print("\n");
        }
    }

    static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static int[] getHumanMove(int currentPlayer) {
        System.out.printf("Next move Player %S.\n",currentPlayer);
        Scanner scanner = new Scanner(System.in);
        String playerMove = scanner.nextLine();
        int[] nextMove = new int[2];
        nextMove[0] = switch (playerMove.substring(0, 1).toUpperCase()) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            default -> 4;
        };
        nextMove[1] = Integer.parseInt(playerMove.substring(1, 2)) - 1;
        if(nextMove[0]<3 && nextMove[0]>-1 && nextMove[1]<3 && nextMove[1]>-1 && playerMove.length()<3){
            return nextMove;
        }else{
            initBoard(XOarray);
            System.out.println("Invalid move!");
            return getHumanMove(currentPlayer);}
    }

    static void mark(int x, int y, int currentPlayer) {
        if (XOarray[x][y] != "X".charAt(0) && XOarray[x][y] != "O".charAt(0)) {
            if (currentPlayer == 1) {
                XOarray[x][y] = "X".charAt(0);
            } else {
                XOarray[x][y] = "O".charAt(0);
            }
            validMove = true;
        } else {
            validMove = false;
            System.out.println("It's already taken!");
        }
    }


    static int setPlayer(int currentPlayer) {
        if (currentPlayer == 1) {
            return 2;
        } else {
            return 1;
        }
    }
    static boolean hasWon(){
        char winner=" ".charAt(0);
        for(int i=0;i<XOarray.length;i++){
            //Winner row
            if(XOarray[i][0]!=".".charAt(0) && XOarray[i][0]==XOarray[i][1] && XOarray[i][1]==XOarray[i][2]){
                winner=XOarray[i][2];
            }
            //Winner col
            if(XOarray[0][i]!=".".charAt(0) && XOarray[0][i]==XOarray[1][i] && XOarray[1][i]==XOarray[2][i]) {
                winner=XOarray[0][i];
            }
        }
        //Winner cross
        if(XOarray[0][0]!=".".charAt(0) && XOarray[0][0]==XOarray[1][1] && XOarray[1][1]==XOarray[2][2]){
            winner=XOarray[0][0];
        }
        if(XOarray[2][0]!=".".charAt(0) && XOarray[2][0]==XOarray[1][1] && XOarray[1][1]==XOarray[0][2]){
            winner=XOarray[0][2];
        }
        if(winner!=" ".charAt(0)){
            if(winner=="X".charAt(0)) {
                winner = "1".charAt(0);
            } else {winner = "2".charAt(0);}
            System.out.printf("Player %s. has won the party!\n",winner);
                return true;

        }
        return false;
    }

}