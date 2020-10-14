public class ConnectFour extends TicTacToe{
    public ConnectFour(){
        super(6);
    }
    public boolean makeMove(int x){
        if(board[0][x] != 0){
            return false;
        }
        else{
            for(int i = 5; i >= 0; i--){
                if(board[i][x] == 0){
                    board[i][x] = currentPlayer;
                    break;
                }
            }
            if(currentPlayer == 1){
                currentPlayer = 2;
            }
            else if(currentPlayer == 2){
                currentPlayer = 1;
            }
            return true;
        }
    }
    public boolean legalMove(int x){
        if(board[0][x] != 0){
            return false;
        }
        return true;
    }
    public int gameStatus(){
        //handles horizontal wins and vertical wins
        for(int i = 5; i >= 0; i--){
            int horizontal1 = 0;
            int horizontal2 = 0;
            int vertical1 = 0;
            int vertical2 = 0;
            for(int j = 0; j < 6; j++){
                if(board[i][j] == 1){
                    horizontal1++;
                    horizontal2 = 0;
                }
                else if(board[i][j] == 2){
                    horizontal2++;
                    horizontal1 = 0;
                }
                else if(board[i][j] == 0){
                    horizontal1 = 0;
                    horizontal2 = 0;
                }
                if(board[j][i] == 1){
                    vertical1++;
                    vertical2 = 0;
                }
                else if(board[j][i] == 2){
                    vertical2++;
                    vertical1 = 0;
                }
                else if(board[j][i] == 0){
                    vertical1 = 0;
                    vertical2 = 0;
                }
                if(horizontal1 == 4 || vertical1 == 4){
                    return 1;
                }
                else if(horizontal2 == 4 || vertical2 == 4){
                    return 2;
                }
            }
        }
        //handles left to right diagonal wins
        for(int i = 5; i >= 0; i--){
            for(int j = 0; j < 6; j++){
                if(board[i][j] == 1){
                    int count = 1;
                    try{
                        for(int k = 1; k < 4; k++){
                            if(board[i-k][j+k] == 1){
                                count++;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                    if(count == 4){
                        return 1;
                    }
                }
                else if(board[i][j] == 2){
                    int count = 1;
                    try{
                        for(int k = 1; k < 4; k++){
                            if(board[i-k][j+k] == 2){
                                count++;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                    if(count == 4){
                        return 2;
                    }
                }
            }
        }
        //handles right to left diagonal wins
        for(int i = 5; i >= 0; i--){
            for(int j = 0; j < 6; j++){
                if(board[i][j] == 1){
                    int count = 1;
                    try{
                        for(int k = 1; k < 4; k++){
                            if(board[i-k][j-k] == 1){
                                count++;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                    if(count == 4){
                        return 1;
                    }
                }
                else if(board[i][j] == 2){
                    int count = 1;
                    try{
                        for(int k = 1; k < 4; k++){
                            if(board[i-k][j-k] == 2){
                                count++;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    catch(IndexOutOfBoundsException e){}
                    if(count == 4){
                        return 2;
                    }
                }
            }
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
}
