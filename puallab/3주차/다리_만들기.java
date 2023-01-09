import java.util.*;
import java.io.*;

public class Main{
	static class Pair{
		int y, x, cnt;
		public Pair(int yy , int xx){
			y = yy;
			x = xx;
		}
	}

	static int n, idx ,ans = Integer.MAX_VALUE;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int[][] board;
	static int[][] visIsland;
	static boolean[][] vis;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		visIsland = new int[n][n];
		for(int i =0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<n; j++){
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		marking();
		int target = 1;
		for(int i =0; i<n; i++){
			for(int j=0; j<n; j++){
				if(board[i][j] == target){
					ans = Math.min(ans, getBridge(i, j, target++));
				}
			}
		}

		System.out.println(ans);
		
	}

	static void marking(){
		vis = new boolean[n][n];
		for(int i =0; i<n;i++){
			for(int j =0; j<n; j++){
				if(vis[i][j]) continue;
				if(board[i][j] == 0) continue;
				mark(i, j, ++idx);
			}
		}
	}

	static void mark(int y, int x, int num){
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y, x));
		vis[y][x] = true;
		board[y][x] = num;
		while(!q.isEmpty()){
			Pair now = q.poll();
			for(int i =0; i<4; i++){
				y = now.y +dy[i];
				x = now.x +dx[i];
				if(!isValid(y,x) || vis[y][x] || board[y][x] != 1) continue;
				vis[y][x] = true;
				board[y][x] = num;
				q.add(new Pair(y, x));
			}
		}
	}
	
	static boolean isValid(int y, int x){
		return (y>=0 && x>=0 && y<n && x<n);
	}

	static int getBridge(int y, int x, int num){
		int val = 0;
		PriorityQueue<Pair> q = new PriorityQueue<>((o1,o2)->{
			return o1.cnt-o2.cnt;
		});
		q.add(new Pair(y, x));
		boolean[][] vis = new boolean[n][n];
		vis[y][x] = true;

		while(!q.isEmpty()){
			Pair now = q.poll();
			for(int i =0; i<4; i++){
				y = now.y + dy[i];
				x = now.x + dx[i];
				if(!isValid(y, x) || vis[y][x]) continue;
				Pair next = new Pair(y, x);
				if(board[y][x] == 0){
					next.cnt = now.cnt + 1;
				}else if(board[y][x] != num){
					return now.cnt;
				}
				vis[y][x] =true;
				q.add(next);
			}

		}
		return val;
	}
}