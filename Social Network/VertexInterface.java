import java.util.Iterator;
/**
 * VertexInterface is the interface that is used to create a Vertex.
 * @param <T> is the type of value used to create the vertex.
 */
interface VertexInterface<T> {
    /**
     * getLabel returns the label of vertex.
     * @return returns the label.
     */
    T getLabel();
    /**
     * getNumberOfNeighbors returns number of neighbors.
     * @return int value of neighbors.
     */
    int getNumberOfNeighbors();
    /**
     * visit visits a vertex and marks it as visited.
     */
    void visit();
    /**
     * unvisit marks a vertex as unvisited.
     */
    void unvisit();
    /**
     * isVisited returns whether or not vertex is marked visited or not.
     * @return true if visited, fasle if otherwise or Does not Exist.
     */
    boolean isVisited();
    /**
     * connect connects two verticies with a weight.
     * @param endVertex vertex to be connected to.
     * @param edgeWeight weight of edge being created.
     * @return true if successful, false if otherwise or if edges DNE.
     */
    boolean connect(VertexInterface<T> endVertex, double edgeWeight);
    /**
     * connect connects two verticies without a weight.
     * @param endVertex vertex to be connected to.
     * @return true if successful, false if otherwise or if edges DNE.
     */
    boolean connect(VertexInterface<T> endVertex);
    /**
     * disconnects two verticies with a weight.
     * @param endVertex vertex to be disconnected from.
     * @param edgeWeight weight of edge being disconnected.
     * @return true if disconnection is successful and false if otherwise or if edges DNE.
     */
    boolean disconnect(VertexInterface<T> endVertex, double edgeWeight);
    /**
     * disconnects two verticies without a weight.
     * @param endVertex vertex to be disconnected from.
     * @return true if disconnection is successful and false if otherwise or if edges DNE.
     */
    boolean disconnect(VertexInterface<T> endVertex);
    /**
     * getNeighborIterator creates and returns an iterator that traverses through all neighbors of given vertex.
     * @return a neighbor iterator.
     */
    Iterator<VertexInterface<T>> getNeighborIterator();
    /**
     * getWeightIterator creates and returns an iterator that traverses through all neighbors of given vertex and returns cost of path between them.
     * @return a Double iterator.
     */
    Iterator<Double> getWeightIterator();
    /**
     * hasNeighbor determines if vertex has a neighbor or not. 
     * @return true if neighbor exists and fasle otherwise.
     */
    boolean hasNeighbor();
    /**
     * getUnvisitedNeighbor returns a neighbor that has not been visited, in no particular order.
     * @return VertexInterface returns vertex that is unvisited or null if unvisited neighbor DNE.
     */
    VertexInterface<T> getUnvisitedNeighbor();
    /**
     * setPredecessor sets vertex that comes before the vertex.
     * @param predecessor is the predecessor.
     */
    void setPredecessor(VertexInterface<T> predecessor);
    /**
     * getPredecessor gets the vertex that comes before the vertex.
     * @return the predecessor.
     */
    VertexInterface<T> getPredecessor();
    /**
     * hasPredecessor returns true or false if a predecessor exists for the vertex.
     * @return true if predecessor exists and fasle otherwise.
     */
    boolean hasPredecessor();
    /**
     * setCost sets the cost of a vertex.
     * @param newCost is the cost.
     */
    void setCost(double newCost);
    /**
     * getCost returns the cost of the vertex.
     * @return double value of cost.
     */
    double getCost();
}
