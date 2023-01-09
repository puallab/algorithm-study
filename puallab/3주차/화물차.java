import java.util.*;
import java.io.*;

public class 화물차{

	static class Tuple{
		int y, x, t;
		public Tuple(int yy, int xx ,int tt){
			y =yy;
			x =xx;
			t= tt;
		}

	}

	static class Signal{
		int first, second;
		boolean start;
		public Signal(int frs, int sec, boolean t){
			first = frs;
			second = sec;
			start = t;
		}
	}

	static int n, m, sCnt, ans = Integer.MAX_VALUE;
	static String[] board;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static Signal[] signals;
	static int[][] vis;
	static int startY, startX, destY, destX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true){
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if( n == 0) break;

			board = new String[n];
			vis = new int[n][m];
			sCnt = 0;
			ans = Integer.MAX_VALUE;
			boolean flag =false;
			for(int i =0; i<n; i++){
				board[i] = br.readLine();
				for(int j =0; j<m; j++){
					if(board[i].charAt(j) == 'A'){
						startY = i;
						startX = j;
					}else if(board[i].charAt(j) == 'B'){
						destY = i;
						destX = j;
					}else if(board[i].charAt(j) >= '0' && board[i].charAt(j) <='9'){
						sCnt = Math.max(sCnt, board[i].charAt(j)-'0');
						flag = true;
					}
				}
			}

			if (flag) {
				signals = new Signal[sCnt + 1];
				for (int i = 0; i <= sCnt; i++) {
					st = new StringTokenizer(br.readLine());
					int idx = Integer.parseInt(st.nextToken());
					char c = st.nextToken().charAt(0);
					int t1 = Integer.parseInt(st.nextToken());
					int t2 = Integer.parseInt(st.nextToken());
					if(c == '-'){
						signals[idx] = new Signal(t1, t2, true);
					}else{
						signals[idx] = new Signal(t2, t1, false);
					}
					
				}
			}
			
			bfs();
			if(ans == Integer.MAX_VALUE ) sb.append("impossible\n");
			else sb.append(ans).append("\n");
			br.readLine();
		}
		System.out.println(sb.toString());
	}

	static void bfs(){
		PriorityQueue<Tuple> pq = new PriorityQueue<>(((o1, o2) ->{
			return o1.t-o2.t;
		} ));
		pq.add(new Tuple(startY, startX, 0));

		while(!pq.isEmpty()){

			Tuple now = pq.poll();
            vis[now.y][now.x] = now.t;
			for(int i=0; i<4; i++){
				int y = now.y + dy[i];
				int x = now.x + dx[i];
				if(!isValid(y, x) || vis[y][x] !=0 || board[y].charAt(x) =='.' || board[y].charAt(x) == 'A') continue;
				if(board[y].charAt(x) == 'B'){
					ans = Math.min(ans , now.t+1);
				}else if(board[y].charAt(x) =='#'){
					pq.add(new Tuple(y, x, now.t+1));
				}else{
					//교차로인 경우
					int sIdx = board[y].charAt(x)-'0';
					int waitTime = getWaitTime(sIdx, i , now);
					pq.add(new Tuple(y, x, vis[y][x] + now.t + waitTime + 1));
				}
			}
		}
	}

	static int getWaitTime(int idx, int dir, Tuple now){
        //현재시간에 해당 신호등 방향 
        //현재시간에 해당 신호등 방향 == dir? akwdma
		Signal signal = signals[idx];
		int period = signal.first + signal.second;

		int checkTime = (now.t)%period+1;
		
		//동서부터 시작
		if(signal.start){
			if(dir < 2 ){
				if(checkTime <= signal.first) return 0;
				else return period-checkTime+1;
			}
			else {
				if(checkTime <= signal.first ) return signal.first-checkTime+1;
				else return 0;
			}
			
		}else{

			if(dir >= 2 ){
				if(checkTime <= signal.first) return 0;
				else return period-checkTime+1;
			}
			else {
				if(checkTime <= signal.first ) return signal.first-checkTime+1;
				else return 0;
			}
		}
        
	}

	static boolean isValid(int y, int x){
		return (y >=0 && x >=0 && y<n && x<m);
	}
}