import java.util.*;
import java.io.*;

public class Boggle{
    static int w, b, score, cnt;
    static String longest;
    static String[] dict;
    static char[][] board = new char[4][4];
    static boolean[][] vis;
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        w = Integer.parseInt(br.readLine());
        dict = new String[w];
        for(int i =0; i<w; i++){
            dict[i] = br.readLine();
        }

        b = Integer.parseInt(br.readLine());
        while(b-- > 0){
            score = 0;
            longest = "";
            cnt = 0;

            for(int i =0; i<4; i++){
                String s = br.readLine();
                board[i] = s.toCharArray();
            }

            playGame();
            sb.append(score + " " + longest + " " + cnt + "\n");
        }
    }

    static void playGame(){

        for(String s : dict){
            vis = new boolean[4][4];
            boolean check = false;
            for(int i =0; i<4; i++){
                for(int j =0; j<4; j++){
                    if(s.charAt(0) == board[i][j]){
                        check = dfs(s, 1, i, j);
                    }
                    if(check){
                        score += getScore(s);
                        cnt += 1;
                        longest = getLongest(s);
                        return;
                    }
                }
            }

        }
    }

    static boolean dfs(String target, int idx, int y, int x){
        if(idx == target.length()){
            return true;
        }
        boolean ret = false;
        for(int i =0; i<8; i++){
            int ny = y+ dy[i];
            int nx = x+ dx[i];
            if(ret) break;
            if(!isValid(ny, nx) || vis[ny][nx] || board[ny][nx] != target.charAt(idx)) continue;
            vis[ny][nx] =true;
            ret = dfs(target, idx+1, ny, nx);
            vis[ny][nx] = false;
        }
        return ret;
    }

    static int getScore(String s){
        switch(s.length()){
            case 8 : return 11;
            case 7 : return 5;
            case 6 : return 3;
            case 5 : return 2;
            case 4 : return 1;
            case 3 : return 1;
            default: return 0;
        }
    }

    static String getLongest(String s){
        if(longest.length() == s.length()){
            if(longest.compareTo(s) > 0){
                return s;
            }
        }else if(longest.length() < s.length()){
            return s;
        }
        return longest;
    }

    static boolean isValid(int y, int x){
        return (y>=0 && x>=0 && y<4 && x <4);
    }
}