import java.util.*;
import java.io.*;

public class 퍼즐{
	static class Board{
		int[][] board = new int[3][3];
		int y, x, cnt;
		
		public Board(int[][] board){
			for(int i =0; i<3; i++){
				for(int j =0; j<3; j++){
					if(board[i][j] == 0){
						y = i;
						x = j;
					}
					this.board[i][j] = board[i][j];
				}
			}
		}

		@Override
		public int hashCode() {
			int id = 0;
			int val = 100000000;
			for(int i =0; i<3; i++){
				for(int j =0; j<3; j++){
					id += (board[i][j]*val);
					val /= 10;
				}
			}
			return id;
		}

		@Override
		public boolean equals(Object obj) {
			return (this.hashCode() == obj.hashCode());
		}
	}
	static int[][] map = new int[3][3];
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int targetId = 123456780;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=0; i<3; i++ ){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs());
	}

	static int bfs(){
		Board start = new Board(map);
		Queue<Board> q = new LinkedList<>();
		q.add(start);
		HashSet<Board> hash = new HashSet<>();
		hash.add(start);

		if(start.hashCode() == targetId){
			return 0;
		}

		while(!q.isEmpty()){
			Board now = q.poll();
			for(int i =0; i<4; i++){
				int y = now.y + dy[i];
				int x = now.x + dx[i];

				if(!isValid(y, x)) continue;

				Board temp = new Board(now.board);
				temp.board[y][x] = now.board[now.y][now.x];
				temp.board[now.y][now.x] = now.board[y][x];
				temp.y = y;
				temp.x = x;
			
				if(hash.add(temp)){
					if(temp.hashCode() == targetId){
						return now.cnt+1;
					}

					temp.cnt = now.cnt+1;
					q.add(temp);
					
				}
			}
		}
		return -1;

	}

	static boolean isValid(int y, int x){
		return (y>=0 && x >=0 && y<3 && x<3);
	}
}