import java.util.Scanner;
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
/**
 * main function reads input.
 *
 * @param      args  The arguments
 */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int nCities = Integer.parseInt(sc.nextLine());
        int nRoutes = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph graph = new EdgeWeightedGraph(nCities);
        int i = 0;
        while (i < nRoutes) {
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
            // Handle the case of DirectedPaths, where two integers are
            // given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] arra2 = sc.nextLine().split(" ");
            DijkstraUndirectedSP dj = new DijkstraUndirectedSP(graph,
             Integer.parseInt(arra2[0]));
            if (dj.hasPathTo(Integer.parseInt(arra2[1]))) {
                System.out.println(dj.distTo(Integer.parseInt(arra2[1])));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where
            // path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] arra3 = sc.nextLine().split(" ");
            DijkstraUndirectedSP djj = new DijkstraUndirectedSP(graph, Integer.parseInt(arra3[0]));
            int x = Integer.parseInt(arra3[1]);
            int y = Integer.parseInt(arra3[arra3.length - 1]);
            if(djj.hasPathTo(y)) {
                Queue<Integer> q = new Queue<Integer>();
                for(Edge e : djj.pathTo(x)) {
                    //System.out.println("superman here");
                    int s = 0, d = 0;
                    int a = e.either();
                    int b = e.other(a);

                    for (Integer c : q) {
                        //System.out.println("i am superman millenium");
                        if (a == c) {
                            s = 1;
                        }
                        if (b == c) {
                            d = 1;
                        }

                    }

                    if (d == 0) {
                        q.enqueue(b);
                    }
                    if (s == 0) {
                        q.enqueue(a);
                    }
                }
                DijkstraUndirectedSP djj2 = new DijkstraUndirectedSP(graph, x);
                for (Edge e : djj2.pathTo(y)) {
                    int a = e.either();
                    int b = e.other(a);
                    int s = 0;
                    int d = 0;

                    for (Integer j : q) {
                        if (a == j) {
                            s = 1;
                        }
                        if (b == j) {
                            d = 1;
                        }

                    }
                    if (s == 0) {
                        q.enqueue(a);
                    }
                    if (d == 0) {
                        q.enqueue(b);
                    }
                }
                System.out.println(djj2.distTo(x) + djj2.distTo(y));
                while (!q.isEmpty()) {
                    System.out.print(q.dequeue() + " ");
                }
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}
