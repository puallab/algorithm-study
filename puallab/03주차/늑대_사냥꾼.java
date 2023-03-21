import java.util.*;
import java.io.*;
public class 늑대_사냥꾼{
	static class Pair{
		int y, x, cnt;
		public Pair(int yy, int xx, int c){
			y = yy;
			x = xx;
			cnt = c;
		}
	}
	static int n, m;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static String[] board;
	static int[][] lenMap;
	static boolean[][] check;
	static Pair start;
	static Queue<Pair> trees = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new String[n];
		lenMap = new int[n][m];
		check = new boolean[n][m];
		for(int i =0;i <n; i++){
			board[i] = br.readLine();
			for(int j =0; j<m; j++){
				if(board[i].charAt(j) == 'V'){
					start = new Pair(i, j, 0);
				}else if(board[i].charAt(j) =='+'){
					trees.add(new Pair(i, j, 0));
					check[i][j] = true;
				}
			}
		}
		makeLenMap();
		System.out.println(bfs());
	}

	static int bfs(){
		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2)->{
			return o2.cnt - o1.cnt;
		});
		
		start.cnt = lenMap[start.y][start.x];
		pq.add(start);
		boolean[][] vis = new boolean[n][m];
		vis[start.y][start.x] = true;
		while(!pq.isEmpty()){
			Pair now = pq.poll();
			for(int i =0; i<4; i++){
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				if(!isValid(y, x) || vis[y][x]) continue;
				//거리 계산.
				pq.add(new Pair(y, x, Math.min(lenMap[y][x], now.cnt)));
				vis[y][x] = true;

				if(board[y].charAt(x) =='J'){
					return  Math.min(lenMap[y][x], now.cnt);
				}
			}
		}

		return 0;

	}
	static boolean isValid(int y, int x){
		return (y>=0 && x>=0 & y<n && x<m);
	}

	static void makeLenMap(){
		while(!trees.isEmpty()){
			Pair now = trees.poll();
			for(int i =0; i<4; i++){
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				if(!isValid(y, x) || check[y][x]) continue;
				Pair next = new Pair(y, x, now.cnt+1);
				check[y][x] = true;
				trees.add(next);
				lenMap[y][x] = next.cnt;
			}

		}
	}
}