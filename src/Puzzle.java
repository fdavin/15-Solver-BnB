public class Puzzle {
    int[][] board = new int[4][4];
    int[][] solution = new int[][]{
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}
    };
    public Puzzle(){
    }
    public Puzzle(int[][] board){
        this.board = board;
    }
    public int get0Row(int[][] currentboard){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(currentboard[i][j] == 0){
                    return i;
                }
            }
        }
        return -1;
    }
    public int get0Col(int[][] currentboard){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(currentboard[i][j] == 0){
                    return j;
                }
            }
        }
        return -1;
    }
    public int g(){
        int g = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] != solution[i][j] & board[i][j] != 0){
                    g++;
                }
            }
        }
        return g;
    }
    public int distance(int FirstRow, int FirstCol, int SecondRow, int SecondCol){
        int distanceRow = Math.abs(FirstRow - SecondRow);
        int distanceCol = Math.abs(FirstCol - SecondCol);
        return distanceRow+distanceCol;
    }
    public void change(int[][] result){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                board[i][j] = result[i][j];
            }
        }
    }
    public int[][] copy(){
        int[][] result = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                result[i][j] = board[i][j];
            }
        }
        return result;
    }
    public void up(){
        int row = get0Row(board);
        int col = get0Col(board);
        if(row != 0){
            int temp = board[row-1][col];
            board[row-1][col] = 0;
            board[row][col] = temp;
        }
    }
    public void down(){
        int row = get0Row(board);
        int col = get0Col(board);
        if(row != 3){
            int temp = board[row+1][col];
            board[row+1][col] = 0;
            board[row][col] = temp;
        }
    }
    public void right(){
        int row = get0Row(board);
        int col = get0Col(board);
        if(col != 3){
            int temp = board[row][col+1];
            board[row][col+1] = 0;
            board[row][col] = temp;
        }
    }
    public void left(){
        int row = get0Row(board);
        int col = get0Col(board);
        if(col != 0){
            int temp = board[row][col-1];
            board[row][col-1] = 0;
            board[row][col] = temp;
        }
    }
    public int kurang(){
        int kurang = 0;
        boolean loop = true;
        System.out.println("Kurang(1) = 0");
        for (int dicari=2;dicari<16;dicari++){
            while(loop){
                int temp = dicari-1;
                for (int i=0;i<4;i++){
                    for (int j=0;j<4;j++){
                        if (temp==0){
                            loop = false;
                            break;
                        }
                        if (board[i][j] < dicari & board[i][j] != 0){
                            temp-=1;
                        }
                        if (board[i][j]==dicari){
                            kurang += temp;
                            System.out.println("Kurang ("+dicari+") = "+temp);
                            loop = false;
                            loop = false;
                            break;
                        }
                    }
                    if (!loop){
                        break;
                    }
                }
            }
            loop = true;
        }
        int temp = 15;
        int count = 0;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                count = board[i][j];
                if (count < 16 & count != 0){
                    temp--;
                }
                if (count==0){
                    kurang += temp;
                    break;
                }
            }
            if (count==0){
                break;
            }
        }
        System.out.println("Kurang(16) = "+temp);
        if ((get0Col(board)+get0Row(board))%2!=0){
            kurang +=1;
        }
        System.out.println("Kurang(i) total = "+kurang);
        return kurang;
    }

    public void print(int[][] board){
        for(int i = 0; i < 4; i++){
            System.out.print("|");
            for(int j = 0; j < 4; j++){
                if (board[i][j]<10){
                    System.out.print(board[i][j] + " |");
                }
                else {
                    System.out.print(board[i][j] + "|");
                }
            }
            System.out.println();
        }
    }


    public void solve(){
        int simpul = 0;
        String last = "none";
        int iterasi = 0;
        int right = 999;
        int left = 999;
        int down = 999;
        int up = 999;
        boolean found = false;
        int[][] tempup = new int[4][4];
        int[][] tempdown = new int[4][4];
        int[][] tempright = new int[4][4];
        int[][] templeft = new int[4][4];
        PrioQueue pq = new PrioQueue();
        Node[] Solution = new Node[9999];
        Node n = new Node(copy(), g(),"none",0);
        Solution[0] = n;
        pq.enqueue(n);
        if (g() == 0){
            System.out.println("Solusi ditemukan");
            print(board);
            return;
        } else {
            Node previous = pq.dequeue();
            while (!found){
                if (get0Row(board)!=0 & last!="down"){
                    up();
                    up = g()+iterasi+1;
                    tempup = this.copy();
                    Node upMove = new Node(tempup, up,"up",iterasi+1);
                    pq.enqueue(upMove);
                    down();
                    simpul += 1;
                }
                if (get0Col(board)!=0 & last!="right"){
                    left();
                    left = g()+iterasi+1;
                    templeft = this.copy();
                    Node leftMove = new Node(templeft, left,"left",iterasi+1);
                    pq.enqueue(leftMove);
                    right();
                    simpul += 1;
                }
                if (get0Row(board)!=3 & last!="up"){
                    down();
                    down = g()+iterasi+1;
                    tempdown = this.copy();
                    Node downMove = new Node(tempdown, down,"down",iterasi+1);
                    pq.enqueue(downMove);
                    up();
                    simpul += 1;
                }
                if (get0Col(board)!=3 & last!="left"){
                    right();
                    right = g()+iterasi+1;
                    tempright = this.copy();
                    Node rightMove = new Node(tempright, right,"right",iterasi+1);
                    pq.enqueue(rightMove);
                    left();
                    simpul += 1;
                }
                previous = pq.dequeue();
                iterasi = previous.iterasi;
                Solution[iterasi] = previous;
                change(previous.node);
                if (g()==0){
                    found = true;
                    last = previous.last;
                    System.out.println("Solusi ditemukan");
                    int j=0;
                    while(Solution[j].node!=previous.node){
                        System.out.println("Matriks iterasi ke-"+Solution[j].iterasi+". Move "+Solution[j].last);
                        print(Solution[j].node);
                        j++;
                    }
                    System.out.println("Matriks iterasi ke-"+j);
                    print(previous.node);
                } else {
                    last = previous.last;
                }
            }
        }if (iterasi==1000){
            System.out.println("Program tidak dapat sampai");
        } else {
               System.out.println("SOLVED");
        }
        System.out.println("Jumlah simpul terbuat : " + simpul);
    }

}
