import java.util.*;
import java.io.*;

public class 문명{

	static class Pair{
		int y, x, t =0;
		public Pair(int yy, int xx, int tt){
			y = yy;
			x = xx;
			t =tt;
		}
	}

	static int n, k;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int[] parents;
	static int[] cnts;
	static int[][][] board;
	static Queue<Pair> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		parents = new int[k+1];
		cnts = new int[k+1];
		for(int i =1; i<=k; i++){
			parents[i] = i;
			cnts[i] = 1;
		}
		board = new int[n][n][2];
		int idx = 1;
		for(int i =0; i<k; i++){
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int find = isJoint(y, x);
			board[y][x][0] = idx++;
			if(find != 0){
				union(find, board[y][x][0]);
			}
			q.add(new Pair(y, x, 0));
		}

		//최초에 연결되어 있는거 확인
		int ans = 0;
		if(cnts[1] != k) ans = bfs();
		
		System.out.println(ans);
	}

	static int isJoint(int y, int x){
		for(int i =0; i<4; i++){
			int yy = y + dy[i];
			int xx = x + dx[i];
			if(!isValid(yy, xx)) continue;
			if(board[yy][xx][0] != 0) return board[yy][xx][0];
		}
		return 0;
	}

	static int bfs(){
		while(!q.isEmpty()){
			Pair now = q.poll();
			int pn = find(board[now.y][now.x][0]);
			for(int i =0; i<4; i++){
				int y = dy[i] + now.y;
				int x = dx[i] + now.x;
				if(!isValid(y, x) || find(board[y][x][0]) == pn) continue;

				if(board[y][x][0] == 0){  
					board[y][x][0] = pn;
					board[y][x][1] = now.t+1;
					q.add(new Pair(y, x, now.t+1));
					continue;
				}
				
				union(pn, board[y][x][0]);

				if(cnts[pn] == k){
					if(board[y][x][1] == now.t+1) return now.t+1;
					else return now.t;
				} 
			}
		}
		return -1;
	}

	static boolean isValid(int y, int x){
		return (y>=0 && x>=0 && y<n && x<n);
	}

	static int find(int a){
		if(a == parents[a]){
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b){
		int pa = find(a);
		int pb = find(b);
		if(pa == pb ) return;
		cnts[pa] += cnts[pb];
		parents[pb] = pa;
	}
}