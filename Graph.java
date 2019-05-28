import static java.util.Arrays.binarySearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * An adjacency list is an array A of separate lists. Each element of the array
 * Ai is a list, which contains all the vertices that are adjacent to vertex i.
 */

public class Graph {
    private static final String[] VERTEX_LIST = { "A", "B", "C", "D", "E" };
    private final List<Edge>[] adj;
    private List<String> allPath;
    private int to;
    private int maxDistance;
    private int stops;
    private int routesCount;
    private int tripsCount;

    private Graph(int n) {
        if (n < 0)
            throw new IllegalArgumentException("have atleast one town to allow for the  creation of  the graph");
        adj = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
    }

    public Graph(String inputGraph) {
        this(VERTEX_LIST.length);
        initializeGraph(inputGraph);
    }

    private void initializeGraph(String inputGraph) {
        String[] connectedTowns = inputGraph.split(",");
        for (String s : connectedTowns) {
            s = s.trim();
            int from = getIndex(s.substring(0, 1)); // strips the first town
            int to = getIndex(s.substring(1, 2)); // strips the second town
            int weight = Integer.parseInt(s.substring(2)); // distance between the two towns
            Edge e = new Edge(from, to, weight); // edge between the two connecting towns
            addEdge(e);
        }
    }

    // since its a one way route. we add the edge
    private void addEdge(Edge e) {
        int v = e.from();
        adj[v].add(e);
    }

    private List<Edge> adj(int v) {
        if (v < 0 || v >= VERTEX_LIST.length)
            throw new IndexOutOfBoundsException("vertex " + v + " not exists");
        return adj[v];
    }

    public String displayDistance(String route) {
        int distance = calculateDistance(route);
        if (distance == -1) {
            return " NO SUCH ROUTE";
        } else {
            return Integer.toString(distance);
        }

    }

    private int calculateDistance(String route) {

        int distance = 0;
        String[] towns = route.trim().split("");
        int from, to;

        for (int i = 0; i < towns.length - 1;) {
            boolean hasPath = false;
            from = getIndex(towns[i++]);
            to = getIndex(towns[i]);
            List<Edge> edgeList = adj(from);
            for (Edge edge : edgeList)
                if (edge.to() == to) {
                    distance += edge.weight();
                    hasPath = true;
                    break;
                }
            if (hasPath==false)   // the is no route
                return -1;
        }

        return distance;
    }

    private static int getIndex(String vertex) {
        return binarySearch(VERTEX_LIST, vertex); /// simplest and most efficient method to find an element in a sorted
                                                  /// array in Java
        
    }
}