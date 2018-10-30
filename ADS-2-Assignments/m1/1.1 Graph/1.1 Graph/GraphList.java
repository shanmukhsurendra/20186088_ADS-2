/**
 * List of graphs.
 */
public class GraphList implements Graph {
    /**
     * NEWLINE.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * v1.
     */
    private final int v1;
    /**
     * e1.
     */
    private int e1;
    /**
     * BAG.
     */
    private Bag<Integer>[] adj;


    /**
     * Constructs the object.
     *
     * @param      v11   The v 11
     */
    public GraphList(final int v11) {
        if (v11 < 0) {
            throw new IllegalArgumentException("Number"
                                               + " of vertices must"
                                               + " be nonnegative");
        }
        this.v1 = v11;
        this.e1 = 0;
        adj = (Bag<Integer>[]) new Bag[v11];
        for (int v = 0; v < v11; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * Returns the number of vertices in this GraphList.
     *
     * @return the number of vertices in this GraphList
     */
    public int v1() {
        return v1;
    }

    /**
     * Returns the number of edges in this GraphList.
     *
     * @return the number of edges in this GraphList
     */
    public int e1() {
        return e1;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < v1}
    /**
     * validateVertex.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= v1) {
            throw new IllegalArgumentException("vertex " + v
                                               + " is not between 0 and "
                                               + (v1 - 1));
        }
    }


    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        // validateVertex(v);
        // validateVertex(w);
        e1++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        int count = 0;
        for (int i : adj[v]) {
            if (i == w) {
                count += 1;
                break;
            }
        }
        for (int i : adj[w]) {
            if (i == v) {
                count += 1;
                break;
            }
        }
        if (count == 2) {
            return true;
        }
        return false;
    }



    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }


    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }



    /**
     * Returns a string representation of this GraphList.
     *
     * @param      data  The data
     *
     * @return     adjacency lists
     */
    public String display(final String[] data) {
        StringBuilder s = new StringBuilder();
        s.append(v1 + " vertices, " + e1 + " edges" + NEWLINE);
        if (e1 > 0) {
            for (int v = 0; v < v1; v++) {
                s.append(data[v] + ": ");
                for (int w : adj[v]) {
                    s.append(data[w] + " ");
                }
                s.append(NEWLINE);
            }
        } else {
            s.append("No edges");
        }
        return s.toString();
    }
}
