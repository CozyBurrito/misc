
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class test {

public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(new File("input.txt"));
    boolean[][] grid = new boolean[100][100];
    boolean[][] tempgrid = new boolean[100][100];
    int index = 0;
    while(scan.hasNext()) {
        String temp = scan.nextLine();
        for(int x=0;x<temp.length();x++) {
            if(temp.charAt(x) == '#')
                grid[index][x] = true;
            else
                grid[index][x] = false;
        }
        index++;
    }
    index = 0;
    /*part 2 stuff*/
    grid[0][0] = true;
    grid[0][99] = true;
    grid[99][0] = true;
    grid[99][99] = true;
    /*end part 2 stuff*/
    for(int i=0;i<100;i++) {
        for(int q=0; q<grid.length; q++)
              for(int j=0; j<grid.length; j++)
                tempgrid[q][j]=grid[q][j];
        for(int x=0;x<grid.length;x++) {
            for(int y=0;y<grid.length;y++) {
                for(int a=x-1;a<=x+1;a++) {
                    for(int b=y-1;b<=y+1;b++) {
                        if(a >= 0 && b >= 0 && a < grid.length && b < grid.length && (a != x || b != y))
                            if(tempgrid[a][b] == true)
                                index++;
                    }
                }
                if(tempgrid[x][y] == true) {
                    if(index != 2 && index != 3)
                        grid[x][y] = false;
                }
                else {
                    if(index == 3)
                        grid[x][y] = true;
                }
                index = 0;
                /*Part 2 stuff*/
                grid[0][0] = true;
                grid[0][99] = true;
                grid[99][0] = true;
                grid[99][99] = true;
                /*end part 2 stuff*/
            }
        }
    }
    index = 0;
    for(int x=0;x<grid.length;x++)
        for(int y=0;y<grid.length;y++)
            if(grid[x][y] == true)
                index++;
    System.out.println(index);

}
}