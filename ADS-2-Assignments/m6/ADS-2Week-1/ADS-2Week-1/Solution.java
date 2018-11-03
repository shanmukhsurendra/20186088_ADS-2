import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;


class PageRank {
	HashMap<Integer, ArrayList<Integer>> incomingVertices;
	HashMap<Integer, Double> values;
	Digraph dg;
	int outDegree;
	int inDegree;
	PageRank(Digraph digraph, Integer vertice) {
		this.outDegree = digraph.outdegree(vertice);
		this.inDegree = digraph.indegree(vertice);
		this.dg = digraph;
		incomingVertices = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < digraph.V(); i++) {
			for (Integer k : digraph.adj(i)) {
				if (incomingVertices.containsKey(k)) {
					ArrayList<Integer> arrayList2 = incomingVertices.get(k);
					arrayList2.add(i);
					//System.out.println("list :" + list);
					incomingVertices.put(k, arrayList2);
				} else {
					ArrayList<Integer> arrayList3 = new ArrayList<Integer>();
					arrayList3.add(i);
					incomingVertices.put(k, arrayList3);
				}
			}
		}
	}
	double getPR(int vertice) {
		if (dg.outdegree(vertice) == 0) {
			return 0.0;
		}
		values = new HashMap<Integer, Double>();
		for (int i = 0; i < dg.V(); i++) {
			values.put(i , 1.0 / dg.V());
		}
		double fvalue = 0.0;
		for (int i = 0; i < 1000; i++) {
			ArrayList<Integer> vert = incomingVertices.get(vertice);
			for (int j = 0; j < vert.size(); j++) {
				int key = vert.get(j);
				fvalue = values.get(key) / dg.outdegree(key);
				values.put(key , fvalue);
			}

		}
		return fvalue;

	}

}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalVertices = Integer.parseInt(sc.nextLine());
		//sc.nextLine();
		Digraph d = new Digraph(totalVertices);
		int i = 0;
		while(i < totalVertices) {
			String[] arra1 = sc.nextLine().split(" ");
			for(int j = 1;j < arra1.length; j++) {
				d.addEdge(Integer.parseInt(arra1[0]), Integer.parseInt(arra1[j]));
			}
			i++;
		}	
		System.out.println(d.toString());	
		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		
		// This part is only for the final test case
		
		// File path to the web content
		ArrayList<PageRank> arrayList1 = new ArrayList<>();
		for (int a = 0; a < totalVertices; a++) {
			PageRank prObject = new PageRank(d, a);
			arrayList1.add(prObject);
			//System.out.println(a);
			prObject.getPR(a);
			
		}
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
