import java.util.Scanner;
import java.util.HashMap;

class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		HashMap<String, Integer> map1 = new HashMap<>();
		String arar1[] = sc.nextLine().split(" ");
		for(int i = 0; i < n; i++) {
			map1.put(arar1[i], i);
		}
		EdgeWeightedGraph graph1 = new EdgeWeightedGraph(n);
		for ( int j = 0; j < m; j++) {
		String[] arra2 = sc.nextLine().split(" ");
		graph1.addEdge(new Edge(map1.get(arra2[0]), map1.get(arra2[1]), Double.parseDouble(arra2[2])));			
		}
		int a = sc.nextInt();
		sc.nextLine();
		for (int k = 0; k < a; k++) {
			String arra3[] = sc.nextLine().split(" ");
			DijkstraUndirectedSP dj = new DijkstraUndirectedSP(graph1,
                    map1.get(arra3[0]));
			            System.out.println(Math.round(dj.distTo(map1.get(arra3[1]))));

		}
	}

}