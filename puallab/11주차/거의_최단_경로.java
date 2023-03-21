import java.util.*;
import java.io.*;

public class 거의_최단_경로{

	static class Node{
		int to, val;
		public Node(int to, int val){
			this.to = to;
			this.val =val;
		}
	}

	static int n, m, s, d;
	static boolean[] vis;
	static int[] dist;
	static ArrayList<Node>[] list, reverse;
	static PriorityQueue<Node> pq;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true){
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			list = new ArrayList[n];
			reverse = new ArrayList[n];
			for(int i =0; i<n; i++){
				list[i] = new ArrayList<>();
				reverse[i] = new ArrayList<>();
			}
			if(n ==0 && m == 0) break;
			
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());

			for(int i =0; i<m; i++){
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				list[from].add(new Node(to, val));
				reverse[to].add(new Node(from, list[from].size()-1));
			}

			dijkstart();

			//간선 제거 
			remove();

			//다시 최단 거리.
			dijkstart();
			sb.append(dist[d]==Integer.MAX_VALUE ? -1 : dist[d]).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void dijkstart(){
		pq = new PriorityQueue<>((o1, o2)->{
			return o1.val-o2.val;
		});
		vis = new boolean[n];
		dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		pq.add(new Node(s, 0));
		

		while(!pq.isEmpty()){
			Node now = pq.poll();
			if(vis[now.to]) continue;
			vis[now.to] = true;
			for(Node next : list[now.to]){
				if(next.val == Integer.MAX_VALUE) continue;
				if(dist[next.to] > dist[now.to] + next.val){
					dist[next.to] = dist[now.to] + next.val;
					pq.add(new Node(next.to, dist[next.to]));
				}
			}
		}
	}
	
	static void remove(){
		//d부터 s까지 탐색하면서 제거
		Queue<Integer> q = new LinkedList<>();
		q.add(d);
		while(!q.isEmpty()){
			int now= q.poll();
			for(Node next : reverse[now]){
				if(dist[now] == dist[next.to] + list[next.to].get(next.val).val){
					list[next.to].get(next.val).val = Integer.MAX_VALUE;
					q.add(next.to);
				}
			}
		}
	}
}