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

    private static int getIndex(String vertex)
    {
        return binarySearch(VERTEX_LIST, vertex); /// simplest and most efficient method to find an element in a sorted
                                                  /// array in Java
        
    }
    public int calculateTripsCount(String from, String to, Predicate<Integer> p, int stops)
    {
        this.to = getIndex(to);
        this.stops = stops;
        this.tripsCount = 0;
        int startIndex = getIndex(from);
        calculateTripsCount(startIndex, Integer.toString(startIndex), p);
        
        return tripsCount;
    }
    
    private void calculateTripsCount(int from, String path, Predicate<Integer> p)
    {  
        
        List<Edge> edges = adj(from);
        for (Edge e: edges) 
        {
            
            String next = path + e.to();
            int stopCount = next.length()-1; // gives the number of towns you passed
            /**
              * check if the number of towns you have passed is within the acceptable range <= max towns and if you have arrived at the destination
              * if  true increase tripsCount to consider  the path
             */
            if (this.to == e.to() && p.test(stopCount)) 
                tripsCount=tripsCount+1;
            
            if(stopCount <= stops)
                calculateTripsCount(e.to(), next, p);
        }
    }
    public int calculateShortestPath(String from, String to)
    {
        allPath = new ArrayList<>();
        this.to = getIndex(to);
        int startIndex = getIndex(from);
        calculateShortestPath(startIndex, String.valueOf(startIndex));
        
        int shortestDistance = Integer.MAX_VALUE, currentDistance;
        for(String s: allPath)
        {
            currentDistance = calculateDistance(s);
            if(shortestDistance > currentDistance)
                shortestDistance = currentDistance;
        }
        
        if(shortestDistance == Integer.MAX_VALUE) return 0;
        
        return shortestDistance;
    }
    private void calculateShortestPath(int from, String path) 
    {
        List<Edge> edges = adj(from);
        for (Edge e: edges) 
        {
            
            if (path.length()>1 && path.substring(1).contains(String.valueOf(e.to()))) //checked visited or not
                continue;  
            
            String next = path + e.to();
 
            if (this.to == e.to()) 
            {
                allPath.add(getPathName(next)); 
            }
            calculateShortestPath(e.to(), next);
        }
    }
    private String getPathName(String path){
        String arr[] = path.trim().split("");
        String name = "";
        for(String v: arr)
        {
            name += getVertexName(Integer.parseInt(v));
        }
        return name;
    }
    private String getVertexName(int index) {
        if (index < 0 || index >= VERTEX_LIST.length) 
        {
            throw new IllegalArgumentException("Wrong index");

        }
          
 
        return VERTEX_LIST[index];
    }

    public int calculateRoutesCount(String from, String to, int maxDistance)
    {
        this.to = getIndex(to);
        this.maxDistance = maxDistance;
        this.routesCount = 0;
        int startIndex = getIndex(from);
        calculateRoutesCount(startIndex, String.valueOf(startIndex));
        
        return routesCount;
    }
    
    private void calculateRoutesCount(int from, String path)
    {
        List<Edge> edges = adj(from);
        for (Edge e: edges)
         {
            
            String next = path + e.to();
            int distance = calculateDistance(getPathName(next));
            
            if (this.to == e.to() && (distance < maxDistance)) 
                routesCount++;
            
            if(distance < maxDistance)
                calculateRoutesCount(e.to(), next);
        }
    }



}