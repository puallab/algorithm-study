import java.util.*;
import java.io.*;

public class 도로포장{
	static class Tuple{
		int to, k;
		long val;
		public Tuple(int t, long v, int kk){
			to = t;
			val =v;
			k = kk;
		}
		
	}
	static int n, m, k;
	static long[][] dist;
	static boolean[][] vis;
	static ArrayList<Tuple>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dist = new long[n+1][k+1];
		vis = new boolean[n+1][k+1];
		list = new ArrayList[n+1];
		for(int i =1; i<=n ;i++){
			list[i] = new ArrayList<>();
			if(i == 1) {
				Arrays.fill(dist[i], 0);
			}
			else Arrays.fill(dist[i], Long.MAX_VALUE);
		}

		for(int i =0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			list[from].add(new Tuple(to, val, 0));
			list[to].add(new Tuple(from, val, 0));
		}

		dijkstar();

	}

	static void dijkstar(){
		PriorityQueue<Tuple> pq = new PriorityQueue<>(((o1, o2) ->{
			if(o1.val > o2.val) return 1;
			else if(o1.val < o2.val) return -1;
			else return 0;
		} ));
		pq.add(new Tuple(1, 0, k));

		while(!pq.isEmpty()){
			Tuple now = pq.poll();
			if(vis[now.to][now.k]) continue;
			vis[now.to][now.k] = true;
			if(now.to == n){
				System.out.println(now.val);
				return;
			}
			for(Tuple next : list[now.to]){

				if(now.k > 0){
					if(dist[next.to][now.k-1] > dist[now.to][now.k]){
						dist[next.to][now.k-1] = dist[now.to][now.k];
						pq.add(new Tuple(next.to, dist[now.to][now.k], now.k-1));
					}
				}
				if(dist[next.to][now.k] > dist[now.to][now.k] + next.val){
					dist[next.to][now.k] = dist[now.to][now.k] + next.val;
					pq.add(new Tuple(next.to, dist[next.to][now.k], now.k));
				}
			}

		}
	}
}