import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static int[][] ReadFile(String filePath) throws IOException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("../test/"+filePath+".txt")));
          int rows = 4;
          int columns = 4;
          int [][] myArray = new int[rows][columns];
          while(sc.hasNextLine()) {
             for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                   myArray[i][j] = Integer.parseInt(line[j]);
                }
             }
          }
            return myArray;
        }
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int[][] x = new int[4][4];
        System.out.println("Read from file? (y/n)");
        String read = input.nextLine();
        if (read.equals("y")){
            System.out.println("Enter file name : ");
            String file = input.nextLine();
            x = ReadFile(file);
        } else if (read.equals("n")){
            System.out.println("Input puzzle : ");
            for (int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    x[i][j] = input.nextInt();
                }
            }
        }
        input.close();
        Puzzle p = new Puzzle(x);
        p.print(p.board);
        if (p.kurang()%2==0){
            long Time = System.currentTimeMillis();
            p.solve();
            long Time2 = System.currentTimeMillis();
            System.out.println("Waktu = "+(Time2-Time)+" ms");
        } else {
            System.out.println("Unsolvable");
        }
    }
}
