import java.util.*;
import java.io.*;

public class boj16571{
	static int[][] board = new int[3][3];
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i =0; i<3; i++){
			st = new StringTokenizer(br.readLine());
			for(int j =0; j<3; j++){
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0) cnt++;
			}
		}
	
		//0의 갯수가 홀수 -> X 차례 (1)
		//0의 갯수가 짝수 -> O 차례 (2)
		int result = simul(2-cnt%2);
		if(result == 1){
			System.out.println("W");
		}else if (result == -1){
			System.out.println("L");
		}else{
			System.out.println("D");
		}
	}

	// ret : {0 : draw, -1 : loss, 1 : win}
	static int simul(int turn){
	
		//내 턴에서 종료 -> 이전판에서 상대방이 이김 --> 나의 패배
		int ret = isOver();
		if(ret != -1){
			return -ret;
		}

		int result = -1;
		for(int i =0; i<3; i++){
			for(int j =0; j<3; j++){
				if(board[i][j] != 0 ) continue;
				board[i][j] = turn;
				// 내가 받은건 상대방의 결과,
				// 즉, 내 행동의 결과는 반대값임.
				result = Math.max(result, -simul(3-turn));
				//showBoard();
				board[i][j] = 0;
			}
		}
		// 내가 낼 수 있는 최고의 결과 반환.
		return result;
	}

	// 게임이 끝났는지 ret = {1: 이김, 0 :무승부, -1: 안끝남) 
	static int isOver(){

		//가로 검사
		for(int i =0; i<3; i++){
			if(board[i][0] == 0) continue;
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2]) return 1;
		}
		
		//세로 검사
		for(int j =0; j<3; j++){
			if(board[0][j] == 0) continue; 
			if(board[0][j] == board[1][j] && board[1][j] == board[2][j]) return 1;
		}
		//대각 검사 (좌상)
		if(board[1][1] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return 1;
		//대각 검사 (우상)
		if(board[1][1] != 0 && board[2][0] == board[1][1] && board[1][1] == board[0][2]) return 1;

		//draw 검사
		for(int i =0; i<3; i++){
			for(int j =0; j<3; j++){
				if(board[i][j] == 0) return -1;
			}
		}

		//무승부
		return 0;
	}	
}