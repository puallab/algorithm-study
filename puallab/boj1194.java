import java.util.*;
import java.io.*;

public class boj1194{

	static class Pair{
		int y, x, key;
		public Pair(int yy, int xx, int k){
			y = yy;
			x = xx;
			key = k;
		}
	}

	static int n, m, ans;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static String[] board;
	static Pair start;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new String[n];

		for(int i =0; i<n; i++){
			board[i] = br.readLine();
			for(int j=0; j<m; j++){
				if(board[i].charAt(j) == '0'){
					start = new Pair(i, j,0);
				}
			}
		}

		bfs();
        if(ans == 0) ans = -1;
        System.out.println(ans);
	}

	static boolean isValid(int y, int x){
		return(y>=0 && x >=0 && y<n && x<m);
	}

	static void bfs(){
		Queue<Pair> q = new LinkedList<>();
		q.add(start);

		int[][][] vis = new int[n][m][128];

		vis[start.y][start.x][start.key] = 1;

		while(!q.isEmpty()){

			Pair now = q.poll();

			for(int i =0; i<4; i++){
				int y = dy[i] + now.y;
				int x = dx[i] + now.x;
				int k = now.key;
				if(!isValid(y, x) || vis[y][x][k] > 0 ||  board[y].charAt(x)=='#') continue;

                char ch = board[y].charAt(x);

                if(ch == '1'){
                    ans = vis[now.y][now.x][k];
                    return; 
                }

                if(ch >= 'a' && ch <='f'){
                    k |= 1 << ch-'a';
                    vis[y][x][k] = vis[now.y][now.x][now.key]+1;
                    q.add(new Pair(y, x, k));
                }else if(ch >= 'A' && ch <= 'F'){
                    if( (k & (1<<ch-'A')) != 0){
                        vis[y][x][k] = vis[now.y][now.x][now.key]+1;
                        q.add(new Pair(y, x, k));
                    }
                }else{
                    vis[y][x][k] = vis[now.y][now.x][now.key] +1;
                    q.add(new Pair(y, x, k));
                }
			}
		}
	}
}