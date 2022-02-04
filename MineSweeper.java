import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class MineSweeper {
	
	int row, col;
	String[][] map = new String[row][col];
	String[][] game = new String[row][col];
	
	Scanner input = new Scanner(System.in);
	
	MineSweeper(int row, int col){
		this.row = row;
		this.col = col;
		this.map = new String[row][col];
		this.game = new String[row][col];
	}
	
	public void run(){
		Random rand = new Random();
		
		int numMine = (int) (row * col) / 4;
		
		// Mine MAP
		
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				if (map[i][j] == null){
					map[i][j] = " - ";
				}
			}
		}
		
		while (numMine > 0){
			int randRow = rand.nextInt(this.row);
			int randCol = rand.nextInt(this.col);
			if (map [randRow] [randCol].equals(" - ")){
				map[randRow][randCol] = " * ";
			}
			numMine--;
		}
		
		
		// GAME MAP
		
		for (int i = 0; i < game.length; i++){
			for (int j = 0; j < game[0].length; j++){
				if (game[i][j] == null){
					game[i][j] = " - ";
				}
			}
		}
		
		printMap();
		input();
	}
	
	public void printMap(){
		System.out.println("======= MINE MAP =========");
		
		for (String i[] : map){
			for (String j : i){
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println("=========== GAME MAP ============");
		
		for (String i[] : game){
			for (String j : i){
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	
	public void input(){
		
		int countMine = 0;
		
		while (isWin(map)){
			System.out.print("ROW: ");
			int r = input.nextInt();
			System.out.print("COLUMN: ");
			int c = input.nextInt();
			
			if(r < 0 || r > map.length - 1 || c < 0 || c > map[0].length - 1){
				System.out.println("Please enter a number within the game limits!");
				continue;
			}
			
			if (map[r][c].equals(" * ")){
				System.out.print("GAME OVER!!!!!!!");
				break;
			}
			
			// EDGES
			
			if (r == 0 && c == 0 && map[r][c].equals(" - ")){
				if (map[r + 1][c].equals(" * ")){
					countMine++;
				}
				if (map[r + 1][c + 1].equals(" * ")){
					countMine++;
				}
				if (map[r][c + 1].equals(" * ")){
					countMine++;
				}
				
				game[r][c] = " " + Integer.toString(countMine);
				map[r][c] = " " + Integer.toString(countMine);
				countMine = 0;
			}
			
			if (r == 0 && c == map[0].length - 1 && map[r][c].equals(" - ")){
				if (map[r + 1][c].equals(" * ")){
					countMine;
				}
				if (map[r + 1][c - 1].equals(" * ")){
					countMine;
				}
				if (map[r][c - 1].equals(" * ")){
					countMine;
				}
				game[r][c] = " " + Integer.toString(countMine);
				map[r][c] = " " + Integer.toString(countMine);
				countMine = 0;
				
			}
			if (r == map.length - 1 && c == 0 && map[r][c].equals(" - ")){
				if (map[r - 1][c].equals(" * ")){
					countMine++;
				}
				if (map[r - 1][c + 1].equals(" * ")){
					countMine++;
				}
				if (map[r][c + 1].equals(" * ")){
					countMine++;
				}
				
				game[r][c] = " " + Integer.toString(countMine);
				map[r][c] = " " + Integer.toString(countMine);
				countMine = 0;
			}
			
			if (r == map.length - 1 && c == map[0].length - 1 && map[r][c].equals(" - ")){
				if (map[r][c - 1].equals(" * ")){
					countMine++;
				}
				if (map[r - 1][c - 1].equals(" * ")){
					countMine++;
				}
				if (map[r - 1][c].equals(" * ")){
					countMine++;
				}
				
				game[r][c] = " " + Integer.toString(countMine);
				map[r][c] = " " + Integer.toString(countMine);
				countMine = 0;
			}
			
			// CIRCUMFERENCE WITHOUT EDGES
		
			if (r == 0 && c > 0 && c < map[0].length - 1){
				if (map[r][c - 1].equals(" * ")){
					countMine++;
				}
				if (map[r][c + 1].equals(" * ")){
					countMine++;
				}
				if (map[r + 1][c -1].equals(" * ")){
					countMine++;
				}
				if (map[r + 1][c].equals(" * ")){
					countMine++;
				}
				if (map[r + 1][c + 1].equals(" * ")){
					countMine++;
				}
				
				game[r][c] = " " + Integer.toString(countMine);
				map[r][c] = " " + Integer.toString(countMine);
				countMine = 0;
			}
			
			if ( r > 0 && r < map.length-1 && c == 0 ) {
                if ( map[r-1][c].equals(" * ") ) {
                    countMine++;
                }
                if ( map[r-1][c+1].equals(" * ")) {
                    countMine++;
                }
                if ( map[r][c+1].equals(" * ")) {
                    countMine++;
                }
                if ( map[r+1][c].equals(" * ")) {
                    countMine++;
                }
                if ( map[r+1][c+1].equals(" * ")) {
                    countMine++;
                }
                game[r][c] = "  " + Integer.toString(countMine);
                map[r][c] = "  " + Integer.toString(countMine);
                countMine = 0;
            }

            if ( r == map.length-1 && c > 0 && c < map[0].length-1 ) {
                if ( map[r][c-1].equals(" * ") ) {
                    countMine++;
                }
                if ( map[r][c+1].equals(" * ")) {
                    countMine++;
                }
                if ( map[r-1][c-1].equals(" * ")) {
                    countMine++;
                }
                if ( map[r-1][c].equals(" * ")) {
                    countMine++;
                }
                if ( map[r-1][c+1].equals(" * ")) {
                    countMine++;
                }
                game[r][c] = "  " + Integer.toString(countMine);
                map[r][c] = "  " + Integer.toString(countMine);
                countMine = 0;
            }

            if ( r > 0 && r < map.length-1 && c == map[0].length-1 ) {
                if ( map[r-1][c].equals(" * ") ) {
                    countMine++;
                }
                if ( map[r-1][c-1].equals(" * ")) {
                    countMine++;
                }
                if ( map[r][c-1].equals(" * ")) {
                    countMine++;
                }
                if ( map[r+1][c-1].equals(" * ")) {
                    countMine++;
                }
                if ( map[r+1][c].equals(" * ")) {
                    countMine++;
                }
                game[r][c] = "  " + Integer.toString(countMine);
                map[r][c] = "  " + Integer.toString(countMine);
                countMine = 0;
            }
                                // iÇ KISIMLAR
            if ( r > 0 && r < map.length-1 && c > 0 && c < map[0].length-1 && map[r][c].equals(" - ") ) {
                if (  map[r-1][c].equals(" * ") )   {
                    countMine ++ ;    
                }
                if (map[r+1][c].equals(" * ")) {
                    countMine ++ ;
                }
                if (map[r][c-1].equals(" * ")) {
                    countMine ++ ;
                }
                if (map[r][c+1].equals(" * ")) {
                    countMine ++ ;
                }
                if (map[r-1][c+1].equals(" * ")) {
                    countMine ++ ;
                }
                if (map[r-1][c-1].equals(" * ")) {
                    countMine ++ ;
                }
                if (map[r+1][c-1].equals(" * ")) {
                    countMine ++ ;
                }
                if (map[r+1][c+1].equals(" * ")) {
                    countMine ++ ;
                }
           
                game[r][c] = "  " + Integer.toString(countMine);
                map[r][c] = "  " + Integer.toString(countMine);
                countMine = 0;
            } 
            printMap();  
        }
        if(!isWin(map)) {
        System.out.println("TEBRÝKLER KAZANDINIZ !!!!!!");
        }
    }

    public boolean isWin ( String[][] finish ) {
        for (String[] a : finish ) {
            for ( String b : a ) {
                if ( b.equals(" - ") ) {
                    return true;
                } 
            } 
        }
        return false;
			
			
			
			
			
			
		}
	}
	         
	         
	         
}

