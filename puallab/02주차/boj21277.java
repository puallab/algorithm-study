import java.util.*;
import java.io.*;
public class boj21277{
    static int ty, tx, by, bx, ans = Integer.MAX_VALUE; //작인거 ty, tx, 큰거 by, bx
    static int[][] mover, fixed, board; // 더 작은 사각형을 돌리면서 비교
                                        // mover : 움직이는 사각형
                                        // fixed : 고정된 사각형
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n1 = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        int[][] arr1 = new int[n1][m1];
        for(int i =0; i<n1; i++){
            String s = br.readLine();
            for(int j =0; j<m1; j++){
                arr1[i][j] = s.charAt(j)-'0';
            }
        }

        st = new StringTokenizer(br.readLine());
        int n2 = Integer.parseInt(st.nextToken());
        int m2 = Integer.parseInt(st.nextToken());
        int[][] arr2 = new int[n2][m2];
        for(int i =0; i<n2; i++){
            String s = br.readLine();
            for(int j=0; j<m2; j++){
                arr2[i][j] = s.charAt(j) - '0';
            }
        }

        if(n1*m1 < n2*m2){
            mover = arr1;
            fixed = arr2;
        }else{
            mover = arr2;
            fixed = arr1;
        }
        ans = (n1+n2)*(m1+m2);
        getMinSize();
        System.out.println(ans);
    }

    
    static void getMinSize(){
        

        for(int i =0; i<4; i++){
            init();
            for(int dy = 0; dy<= board.length-ty; dy++){
                for(int dx = 0; dx<= board[0].length -tx; dx++){
                    if(check(dy, dx)){
                        ans = Math.min(ans, getSize(dy, dx));
                    }
                }
            }
            
            rotate();
        }
    }

    static void init(){
        ty = mover.length; tx = mover[0].length;
        by = fixed.length; bx = fixed[0].length;

        board = new int[by+2*ty+1][bx+2*tx+1];
        for(int i =0; i<by; i++){
            for(int j =0; j<bx; j++){
                board[i+ty][j+tx] = fixed[i][j];
            }
        }
    }

    static boolean check(int dy, int dx){
        for(int y= 0; y< ty; y++){
            for(int x=0; x< tx; x++){
                if(mover[y][x] == 1 && board[y+dy][x+dx] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    //mover와 fixed의 좌표를 비교해서 사각형의 넓이를 구한다.
    static int getSize(int dy, int dx){
        int h = Math.max(ty+by, ty+dy) - Math.min(ty, dy);
        int w = Math.max(tx+bx, tx+dx) - Math.min(tx, dx);

        return w*h;
    }

    //90씩 회전
    static void rotate(){

        int[][] temp = new int[tx][ty];
        for(int i =0; i<ty; i++){
            for(int j=0; j<tx; j++){
                temp[j][ty-i-1] = mover[i][j];
            }
        }

        mover = temp;
        ty = mover.length;
        tx = mover[0].length;
    }
}