import java.util.*;
import java.io.*;

public class boj18808{
	static int n, m, k, r, c;
	static int[][] board;
	static int[][] subBoard;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		board = new int[n][m];
		for(int i =0; i<k; i++){
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			subBoard = new int[r][c];
			for(int y=0; y<r; y++){
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x<c; x++){
					subBoard[y][x] = Integer.parseInt(st.nextToken());
				}
				
			}

			postSticker( i+1);
			//showBoard();
		}

		System.out.println(getAns());

    }

	static void postSticker(int idx){
		for(int i =0; i<4; i++){
			for(int y =0; y<= n-r; y++){
				for(int x =0; x<= m-c; x++){
					if(isPossible(y, x)){
						post(y, x, idx);
						return;
					}
				}
			}
			rotate();
		}
	}

	static boolean isPossible(int y, int x){

		for(int i =y; i< y+r; i++){
			for(int j = x; j < x+c; j++){
				if(subBoard[i-y][j-x] == 1 && board[i][j] != 0 ) return false;
			}
		}
		return true;
	}

	static void post(int y, int x, int idx){
		for(int i =y; i< y+r; i++){
			for(int j = x; j < x+c; j++){
				if(subBoard[i-y][j-x] == 1){
					board[i][j] = idx;
				}
			}
		}
	}

	static void rotate(){
		// 90도 회전 
		//Y 랑 X 바꾸미
		int[][] tempBoard = new int[c][r];
		
		// 90도 회전한 값 넣어주기 
		for(int x = 0; x<c; x++){
			for(int y = 0; y< r; y++){
				tempBoard[x][r-y-1] = subBoard[y][x];
			}
		}

		subBoard = tempBoard;
		// c랑 t 교환
		r= subBoard.length;
		c= subBoard[0].length;
	}

	static int getAns(){
		int val =0;
		for(int i =0; i<n; i++){
			for(int j =0; j<m; j++){
				if(board[i][j] != 0){
					val += 1;
				}
			}
		}
		return val;
	}

	static void showBoard(){
		System.out.println();
		System.out.println();
		for(int i =0; i<n ;i++){
			
			for(int j =0; j<m; j++){
				System.out.print(board[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
}