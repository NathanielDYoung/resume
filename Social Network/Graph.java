import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Map;

/**
 * Graph class uses a map of T and Verticies to establish a list of verticies and their labels.
 * 
 * @param <T> is generic type to represent the data type verticies.
 * @param <T> generic type to represent the data type verticies. 
 */
public class Graph<T> implements GraphInterface<T> {
    
    /**
     * Is the map of verticies with key being the vertex's label, and value being the vertex itsself.
     * @param verticies map matches a passed label to a new Vertex.
     */
    public Map<T, VertexInterface<T>> verticies;

    /**
     * Is the number of edges currently in the graph.
     * @param numberOfEdges is used to track the number of edges currently in the graph for easier return.
     */
    private int numberOfEdges;

    /**
     * Graph constructor instatiates the verticies Map as an empty HashMap.
     */
    public Graph(){
        verticies = new HashMap<>();
    }

    @Override
    public boolean addVertex(T vertexLabel) {
        if(vertexLabel == null){
            return false;
        }
        else{
            verticies.put(vertexLabel, new Vertex<>(vertexLabel));
            return true;
        }
    }

    @Override
    public VertexInterface<T> removeVertex(T vertexLabel) {
        if(verticies.get(vertexLabel) == null){
            return null;
        }
        else{
            VertexInterface<T> removed = verticies.get(vertexLabel);
            verticies.remove(vertexLabel);
            return removed;
        }
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        if(verticies.get(begin) != null && verticies.get(end) != null){
            numberOfEdges++;
            return verticies.get(begin).connect(verticies.get(end), edgeWeight);
        }
        else if(hasEdge(begin, end)){
            return false;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0.0);
    }

    @Override
    public boolean removeEdge(T begin, T end, double edgeWeight) {
        if(hasEdge(begin, end)){
            numberOfEdges--;
            verticies.get(begin).disconnect(verticies.get(end));
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean removeEdge(T begin, T end) {
        return removeEdge(begin, end, 0);
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        VertexInterface<T> beginVert = verticies.get(begin);
        VertexInterface<T> endVert = verticies.get(end);
        boolean found = false;
        if(beginVert != null && endVert != null){
            Iterator<VertexInterface<T>> beginNI = beginVert.getNeighborIterator();
            while(!found && beginNI.hasNext()){
                if(beginNI.hasNext()){
                    VertexInterface<T> beginNext = beginNI.next();
                    if(endVert.equals(beginNext)){
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    @Override
    public int getNumberOfVertices() {
        return verticies.size();
    }

    @Override
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    @Override
    public boolean isEmpty() {
        return verticies.isEmpty();
    }

    @Override
    public List<VertexInterface<T>> getVertices() {
        List<VertexInterface<T>> v = new LinkedList<>();
        for(Map.Entry<T, VertexInterface<T>> item : verticies.entrySet()){
            v.add(item.getValue());
        }
        return v;
    }

    @Override
    public void clear() {
        verticies.clear();
        numberOfEdges = 0;        
    }

    @Override
    public Queue<T> getBreadthFirstTraversal(T origin) {
        Queue<T> order = new LinkedList<>();
        Queue<T> vertexQ = new LinkedList<>();
        for(VertexInterface<T> v : this.getVertices()){
            v.unvisit();
        }
        
        VertexInterface<T> originVert = verticies.get(origin);
        originVert.visit();
        order.add(origin);
        vertexQ.add(origin);
        while(!vertexQ.isEmpty()){
            T firstVertex = vertexQ.remove();
            VertexInterface<T> first = verticies.get(firstVertex);
            Iterator<VertexInterface<T>> iterateNI = first.getNeighborIterator();
            while(iterateNI.hasNext()){
                VertexInterface<T> nextNeighbor = iterateNI.next();
                T nextNeighborLabel = nextNeighbor.getLabel();
                if(!nextNeighbor.isVisited()){
                    nextNeighbor.visit();
                    order.add(nextNeighborLabel);
                    vertexQ.add(nextNeighborLabel);
                }
            }
        }
        return order;
    }

    @Override
    public int getShortestPath(T origin, T destination, Stack<T> path) {
        if(verticies.get(origin) == null || verticies.get(destination) == null){
            return -1;
        }
        if(hasEdge(origin, destination)){
            return 1;
        }

        Queue<T> order = new LinkedList<>();
        path = new Stack<>();
        for(VertexInterface<T> v : this.getVertices()){
            v.unvisit();
        }
        
        VertexInterface<T> originVert = verticies.get(origin);

        originVert.visit();
        originVert.setPredecessor(null);
        originVert.setCost(0);
        order.add(origin);
        while(!order.isEmpty()){
            T currentLabel = order.remove();
            VertexInterface<T> current = verticies.get(currentLabel);
            Iterator<VertexInterface<T>> iterateNI = current.getNeighborIterator();
            while(iterateNI.hasNext()){
                VertexInterface<T> nextNeighbor = iterateNI.next();
                T nextNeighborLabel = nextNeighbor.getLabel();
                if(!nextNeighbor.isVisited()){
                    nextNeighbor.visit();
                    nextNeighbor.setPredecessor(current);
                    nextNeighbor.setCost(current.getCost() + 1);
                    path.add(nextNeighborLabel);
                    order.add(nextNeighborLabel);
                }
                if(nextNeighbor == verticies.get(destination)){
                    break;
                }
            }
        }
        
        if(!path.contains(destination)){
            return -1;
        }

        return (int) verticies.get(destination).getCost();
    }
    
}
