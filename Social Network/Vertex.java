import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Vertex class implements VertexInterface to create a vertex.
 * @param <T> type value of vertex.
 */
public class Vertex<T> implements VertexInterface<T> {
    /**
     * label of each vertex.
     * @param label is the label assigned to every vertex.
     */
    T label;
    /**
     * determines if vertex has been visited.
     * @param visited is boolean value assigned to each vertex to determine if it's been visited or not.
     */
    boolean visited;
    /**
     * Is the previous vertex.
     * @param previousVertex is the location that the vertex was visited from.
     */
    VertexInterface<T> previousVertex;
    /**
     * Is the cost.
     * @param cost is how expensive it is to get to this vertex.
     */
    double cost;
    /**
     * The list of edges.
     * @param  edgeList is the list of edges that connect to this vertex.
     */
    List<Edge> edgeList;
    /**
     * Constructor to create vertex.
     * @param vertexLabel is the label of created vertex.
     */
    public Vertex(T vertexLabel){
        this.label = vertexLabel;
        this.visited = false;
        this.cost = 0.0;
        previousVertex = null;
        this.edgeList = new ArrayList<Edge>();
    }
    
    @Override
    public T getLabel() {
        return label;
    }


    @Override
    public int getNumberOfNeighbors() {
        return edgeList.size();
    }


    @Override
    public void visit() {
        this.visited = true;        
    }


    @Override
    public void unvisit() {
        this.visited = false;
    }


    @Override
    public boolean isVisited() {
        return this.visited;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean end = false;
        if(!this.equals(endVertex)){
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;
            while(duplicateEdge == false && neighbors.hasNext()){
                VertexInterface<T> nextNeighbor = neighbors.next();
                if(endVertex.equals(nextNeighbor)){
                    duplicateEdge = true;
                }
            }
            if(duplicateEdge == false){
                Edge e = new Edge(endVertex, edgeWeight);
                ((Vertex<T>) endVertex).edgeList.add(new Edge(this, edgeWeight));
                edgeList.add(e);
                end = true;
            }
        }
        return end;
    }


    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }

    @Override
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        if(!this.equals(endVertex)){
            for(Edge e : edgeList){
                if(e.getEndVertex() == endVertex){
                    edgeList.remove(e);
                    ((Vertex<T>) endVertex).edgeList.remove(e);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean disconnect(VertexInterface<T> endVertex) {
        return disconnect(endVertex, 0);
    }


    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }

    @Override
    public Iterator<Double> getWeightIterator() {
        return new WeightIterator();
    }


    @Override
    public boolean hasNeighbor() {
        return getNeighborIterator().hasNext();
    }


    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while(neighbors.hasNext() && result == null){
            VertexInterface<T> nextNeighbor = neighbors.next();
            if(nextNeighbor.isVisited() == false){
                result = nextNeighbor;
            }
        }
        return result;
    }


    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }


    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        if(previousVertex != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void setCost(double newCost) {
        this.cost = newCost;
    }


    @Override
    public double getCost() {
        return this.cost;
    }

    /**
     * Edge class creates an edge.
     */
    protected class Edge{
        /**
         * ending vertex.
         * @param vertex variable is the ending vertex.
         */
        private VertexInterface<T> vertex;
        /**
         * weight of edge.
         * @param weight double
         */
        private double weight;
        /**
         * Edge constructor creates an edge.
         * @param vertex end vertex of edge.
         * @param weight weight of edge.
         */
        protected Edge(VertexInterface<T> vertex, double weight){
            this.vertex = vertex;
            this.weight = weight;
        }
        /**
         * getEndVertex gets the end vertex.
         * @return the vertex value.
         */
        protected VertexInterface<T> getEndVertex(){
            return vertex;
        }
        /**
         * getWeight gets the weight of the edge.
         * @return the double value of edge.
         */
        protected double getWeight(){
            return weight;
        }
    }
    /**
     * NeighborIterator class creates a neighbor iterator object.
     */
    protected class NeighborIterator implements Iterator<VertexInterface<T>> {
        /**
         * The edge Iterator.
         * @param edge is an iterator of edgeList.
         */
        private Iterator<Edge> edges;
        /**
         * NeighborIterator constructor initializes the edge Iterator.
         */
        private NeighborIterator(){
            edges = edgeList.iterator();
        }
        /**
         * determines if edge Iterator has a next value.
         * @return true if next edge exists in iterator, fasle otherwise.
         */
        public boolean hasNext(){
            return edges.hasNext();
        }
        /**
         * returns the next edge in the iterator.
         * @return the next edge.
         */
        public VertexInterface<T> next(){
            VertexInterface<T> nextNeighbor = null;
            if(edges.hasNext()){
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else{
                throw new NoSuchElementException();
            }
            return nextNeighbor;
        }
    }
    /**
     * WeightIterator class creates an iterator to ger edgeWeights.
     */
    private class WeightIterator implements Iterator<Double> {
        /**
         * initialize an iterator of edges.
         */
        private Iterator<Edge> edges;
        /**
         * WeightIterator constructor initializes edgeList.
         */
        private WeightIterator(){
            edges = edgeList.iterator();
        }
        /**
         * hasNext determines if iterator has next value.
         * @return true if next edge exists, false otherwise.
         */
        public boolean hasNext(){
            return edges.hasNext();
        }
        /**
         * next returns the weight of the nextEdge.
         * @return Double value of edges weight.
         */
        public Double next(){
            double nextWeight = 0.0;
            if(edges.hasNext()){
                Edge edgeToNextNeighbor = edges.next();
                nextWeight = edgeToNextNeighbor.getWeight();
            }
            else{
                throw new NoSuchElementException();
            }
            return nextWeight;
        }
    }
}
