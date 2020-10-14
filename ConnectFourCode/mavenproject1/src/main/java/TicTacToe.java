import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class TicTacToe {
    private int n;
    public int currentPlayer;
    public int[][] board;

    public TicTacToe(int n){
        this.n = n;
        this.currentPlayer = 1;
        board = new int[n][n];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                this.board[i][j] = 0;
            }
        }
    }
    public boolean makeMove(int x, int y){
        if(this.board[x][y] != 0){
            return false;
        }
        else{
            this.board[x][y] = this.currentPlayer;
            if(currentPlayer == 1){
                this.currentPlayer = 2;
            }
            else if(currentPlayer == 2){
                this.currentPlayer = 1;
            }
            return true;
        }
    }
    public int turn(){
        return this.currentPlayer;
    }
    public int gameStatus(){
        //handles horizontal wins
        int[] horizontal = new int[this.n];
        for(int i = 0; i < board.length; i++){
            horizontal = board[i].clone(); //need to call clone otherwise we are setting horizontal as a reference to board. https://www.geeksforgeeks.org/array-copy-in-java/
            Arrays.sort(horizontal);
            if(horizontal[0] == horizontal[this.n - 1]){
                if(horizontal[0] == 1){
                    return 1;
                }
                else if(horizontal[0] == 2){
                    return 2;
                }
            }
        }
        //handles vertical wins
        boolean flag1;
        boolean flag2;
        for(int i = 0; i < board.length; i++){
            flag1 = true;
            flag2 = true;
            for(int j = 0; j < board[i].length; j++){
                if(board[j][i] != 1){
                    flag1 = false;
                }
                if(board[j][i] != 2){
                    flag2 = false;
                }
            }
            if(flag1 == true){
                return 1;
            }
            else if(flag2 == true){
                return 2;
            }
        }
        //handles left to right diagonal wins
        flag1 = true;
        flag2 = true;
        for(int i = 0; i < board.length; i++){
            if(board[i][i] != 1){
                flag1 = false;
            }
            if(board[i][i] != 2){
                flag2 = false;
            }
        }
        if(flag1 == true){
            return 1;
        }
        else if(flag2 == true){
            return 2;
        }
        //handles right to left diagonal wins
        flag1 = true;
        flag2 = true;
        int j = 0;
        for(int i = board.length - 1; i >= 0; i--){
            if(board[i][j] != 1){
                flag1 = false;
            }
            if(board[i][j] != 2){
                flag2 = false;
            }
            j++;
        }
        if(flag1 == true){
            return 1;
        }
        else if(flag2 == true){
            return 2;
        }
        //handles tie or if there are currently no winners
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                if(board[x][y] == 0){
                    return 0;
                }
            }
        }
        return -1; //tie
    }
    public boolean gameOver(){
        return gameStatus() != 0;
    }
    public String toString(){
        String x = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                x += Integer.toString(board[i][j]);
            }
            if(i < 5){
                x += "\n";
            }
        }
        return x;
    }
    void loadBoard(String fileName){
        try{
            FileReader reader = new FileReader(fileName);
            Scanner in = new Scanner(reader);
            int row = 0;
            while(in.hasNextLine()){
                String input = in.next();
                for(int i = 0; i < input.length(); i++){
                    this.board[row][i] = Character.getNumericValue(input.charAt(i));
                }
                row++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("LoadBoard Error. Invalid file.\n");
        }
    }
    void loadBoard2(File fileName){
        try{
            FileReader reader = new FileReader(fileName);
            Scanner in = new Scanner(reader);
            int row = 0;
            while(in.hasNextLine()){
                String input = in.next();
                for(int i = 0; i < input.length(); i++){
                    this.board[row][i] = Character.getNumericValue(input.charAt(i));
                }
                row++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("LoadBoard Error. Invalid file.\n");
        }
    }
    void saveBoard(String fileName){
        try{
            PrintWriter out = new PrintWriter(fileName);
            out.print(toString());
            out.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("SaveBoard Error.");
        }
    }
    public int checkPosition(int x, int y){
        try{
            return this.board[x][y];
        }
        catch(ArrayIndexOutOfBoundsException exception){
            return -1;
        }
    }
}
