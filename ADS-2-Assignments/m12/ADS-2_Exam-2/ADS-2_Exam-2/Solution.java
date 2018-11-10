import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for solution.
 */
public class Solution {
/**
 * main function reads input.
 *
 * @param      args  The arguments
 */
	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner sc = new Scanner(System.in);
		int nCities = Integer.parseInt(sc.nextLine());
		int nRoutes = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph graph = new EdgeWeightedGraph(nCities);
		int i = 0;
		while (i < nRoutes){
			String[] arra1 = sc.nextLine().split(" ");
			graph.addEdge(new Edge(Integer.parseInt(arra1[0]),
			 Integer.parseInt(arra1[1]), Integer.parseInt(arra1[2])));
			i++;		
		}
		String caseToGo = sc.nextLine();
		switch (caseToGo) {
		case "Graph":
		System.out.println(graph.toString());
			
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			break;

		default:
			break;
		}

	}
}
