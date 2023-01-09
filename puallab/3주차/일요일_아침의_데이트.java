import java.io.*;
import java.util.*;

public class 일요일_아침의_데이트{
	static class Tuple{
		int y, x, cross, side;
		public Tuple(int yy, int xx){
			y =yy;
			x =xx;
		}
	}
	static int n, m;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static String[] board;
	static Tuple start, end;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new String[n];
		for(int i =0; i<n; i++){
			board[i] = br.readLine();
			for(int j =0; j<m; j++){
				if(board[i].charAt(j) =='S'){
					start = new Tuple(i, j);
				}else if(board[i].charAt(j) == 'F'){
					end = new Tuple(i, j);
				}
			}
		}

		bfs();
	}

	static void bfs(){
		PriorityQueue<Tuple> pq =new PriorityQueue<>((o1,o2)->{
			if(o1.cross == o2.cross){
				return o1.side-o2.side;
			}else{
				return o1.cross-o2.cross;
			}
		});

		pq.add(start);
		boolean[][] vis = new boolean[n][m];
		vis[start.y][start.x] = true;
		while(!pq.isEmpty()){
			Tuple now = pq.poll();
			
			for(int i =0; i<4; i++){
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				if(!isValid(y, x) || board[y].charAt(x) == 'S') continue;
				if(vis[y][x]) continue;

				if(board[y].charAt(x) == 'F'){
					System.out.println(now.cross + " " + now.side);
					return;
				}

				Tuple next = new Tuple(y, x);
				if(board[y].charAt(x)== 'g'){
					next.cross = now.cross + 1;
					next.side = now.side;
				}
				else if(isBeside(y, x)){
					next.cross = now.cross;
					next.side += now.side + 1;
					
				}else{
					next.cross = now.cross;
					next.side = now.side;
				}
				vis[y][x] = true;
				pq.add(next);
			}
		}

	}

	static boolean isValid(int y, int x){
		return (y >=0 && x>=0 && y<n && x<m);
	}

	static boolean isBeside(int y, int x){
		for(int i =0; i<4; i++){
			int yy = y + dy[i];
			int xx = x + dx[i];
			if(!isValid(yy, xx)) continue;
			if(board[yy].charAt(xx) == 'g') return true;
		}
		return false;
	}
}