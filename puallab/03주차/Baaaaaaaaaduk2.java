import java.util.*;
import java.io.*;

public class Baaaaaaaaaduk2{
	static class Pair{
		int y, x;
		public Pair(int yy, int xx){
			y = yy;
			x = xx;
		}
	}
	static int n, m, ans;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int[][] board;
	static boolean[][] vis;
	static List<Pair> zeros= new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		for(int i =0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<m; j++){
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0){
					zeros.add(new Pair(i, j));
				}
			}
		}

		baduk2(0, 0);
		System.out.println(ans);

	}

	static void baduk2(int picked, int idx){

		if(picked == 2){
			ans = Math.max(ans, getAns());
			return;
		}

		for(int i =idx; i<zeros.size(); i++){
			Pair pos = zeros.get(i);
			board[pos.y][pos.x] = 1;
			baduk2(picked+1, i+1);
			board[pos.y][pos.x] = 0;
		}

	}

	static int getAns(){
		int val = 0;
		vis = new boolean[n][m];
		for(int i=0; i<n; i++){
			for(int j =0; j<m; j++){
				if(vis[i][j] || board[i][j] != 2) continue;
				val += bfs(i,j);
			}
		}

		return val;
	}

	static int bfs(int y, int x){
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y, x));
		vis[y][x] = true;
		int cnt = 1;
		boolean flag = false;
		while(!q.isEmpty()){
			Pair now = q.poll();
			for(int i =0; i<4; i++){
				y = now.y + dy[i];
				x = now.x + dx[i];
				if(!isValid(y, x) || vis[y][x]) continue;
				if(board[y][x] == 0 ) {
					flag= true;
					continue;
				}
				if(board[y][x] == 2){
					cnt += 1;
					q.add(new Pair(y, x));
				}	
				vis[y][x] = true;
				
			}

		}
		if(flag ) return 0;
		return cnt;
	}

	static boolean isValid(int y, int x){
		return (y >= 0 && x >= 0 && y<n && x <m);
	}

	static void showBoard(){
		System.out.println();
		for(int i =0; i<n; i++){
			for(int j =0; j<m; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}