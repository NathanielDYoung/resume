import java.util.List;
import java.util.Stack;
import java.util.Queue;
/**
 * Interface used for creating graphs.
 * @param <T> type of item beign added to the graph.
 */
interface GraphInterface<T> {
    /**
     * addVertex adds a vertex to the graph.
     * @param vertexLabel is the label of the vertex being added.
     * @return true if successfully added, fasle otherwise.
     */
    boolean addVertex(T vertexLabel);
    /**
     * removeVertex removes a vertex from the graph.
     * @param vertexLabel is the label to be removed.
     * @return return vertex that was removed.
     */
    VertexInterface<T> removeVertex(T vertexLabel);
    /**
     * addEdge adds a weighted edge to the graph.
     * @param begin is the begining vertex.
     * @param end is the end vertex.
     * @param edgeWeight is the weight of the edge being added.
     * @return true if addition of edge is successful and false otherwise.
     */
    boolean addEdge(T begin, T end, double edgeWeight);
    /**
     * addEdge adds an unweighted edge to the graph.
     * @param begin is the begining bertex.
     * @param end is the end vertex.
     * @return true if addition of edge is successful and false otherwise.
     */
    boolean addEdge(T begin, T end);
    /**
     * removeEdge removes a weighted edge from the graph.
     * @param begin is the begining vertex.
     * @param end is the end vertex.
     * @param edgeWeight is the weight of the edge being removed.
     * @return true if removal of edge is successful and false otherwise.
     */
    boolean removeEdge(T begin, T end, double edgeWeight);
    /**
     * removeEdge removes an unweighted edge from the graph.
     * @param begin is the beginning vertex.
     * @param end is the end vertex.
     * @return true if removal of edge is successful and false otherwise.
     */
    boolean removeEdge(T begin, T end);
    /**
     * hasEdge determines if the graph has an edge between specified begining and end vertecies.
     * @param begin is the beginning vertex.
     * @param end is the end vertex.
     * @return true if graph has the edge, false otherrwise. 
     */
    boolean hasEdge(T begin, T end);
    /**
     * getNumberofVerticies gets the number of verticies in the graph.
     * @return int value that holds the number of verticies.
     */
    int getNumberOfVertices();
    /**
     * getNumberOfEdges gets the nummber of edges in the graph. 
     * @return int value that holds the number of edges.
     */
    int getNumberOfEdges();
    /**
     * isEmpty determines if graph has any verticies.
     * @return true if graph has no verticies and false if it does.
     */
    boolean isEmpty();
    /**
     * getVerticies gets a list of all verticies in the graph.
     * @return List of Verticies in graph.
     */
    List<VertexInterface<T>> getVertices();
    /**
     * clears the graph of all verticies and edges.
     */
    void clear();
    /**
     * Traverses graph from origin point and returns queue with proper ordering of verticies.
     * @param origin is the sgtart point for traversal.
     * @return is the queue returned after traversal.
     */
    Queue<T> getBreadthFirstTraversal(T origin);
    /**
     * getShortestPath returns an int value of how many verticies must be traversed to get from origin to destination.
     * @param origin is the origin vertex.
     * @param destination is the destination of traversal.
     * @param path is the path used to get from origin to destination.
     * @return int value of how may verticies were traversed.
     */
    int getShortestPath(T origin, T destination, Stack<T> path);
}