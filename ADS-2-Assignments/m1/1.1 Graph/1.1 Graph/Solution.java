import java.util.Scanner;
import java.util.Arrays;

interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
class Solution {
	Solution() {

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		if (str1.equals("List")) {
			// int noOfVertices = Integer.parseInt(sc.nextLine());
			// Int noOfEdges = Integer.parseInt(sc.nextLine());
			// String[] arra1 = sc.nextLine().split(",");


		}
		if ((str1.equals("Matrix"))) {
			int noOfVertices = Integer.parseInt(sc.nextLine());
			int noOfEdges = Integer.parseInt(sc.nextLine());
			String[] arra1 = sc.nextLine().split(",");
			int[][] graph = new int[noOfVertices][noOfVertices];
			while (noOfEdges > 0) {
				String str2 = sc.nextLine();
				String arra2[] = str2.split(" ");
				graph[Integer.parseInt(arra2[0])][Integer.parseInt(arra2[0])] = 1;
				noOfEdges--;
			}
			for (int[] each : graph) {
				for (int each1: each ) {
					
				System.out.print(each + " ");
				}
				System.out.println();
			}
		}
	}
}

