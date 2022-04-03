public class Node {
    int[][] node = new int[4][4];
    int[][] solution = new int[][]{
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}};
    int c;
    String last;
    int iterasi;
    Node(int[][] board, int c, String last, int iterasi){
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                node[i][j] = board[i][j];
            }
        }
        this.c = c;
        this.last = last;
        this.iterasi = iterasi;
    }
}
