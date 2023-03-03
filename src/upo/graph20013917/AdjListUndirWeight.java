package upo.graph20013917;

import upo.additionalstructures.Vertex;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Classe per lo sviluppo della lista di adiacenza pesata
 */
public class AdjListUndirWeight implements WeightedGraph {

    final ArrayList<Vertex> adjlist;

    public AdjListUndirWeight() {
        adjlist = new ArrayList<>();
    }

    @Override
    public double getEdgeWeight(String s, String s1) throws IllegalArgumentException, NoSuchElementException {
        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                return vertex.getEdgeWeight(s1);
            }
        }
        return 0;
    }

    @Override
    public void setEdgeWeight(String s, String s1, double v) throws IllegalArgumentException, NoSuchElementException {
        if (!containsEdge(s, s1)) {
            throw new NoSuchElementException("Uno o entrambi i vertici non esistono tra gli archi");
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                vertex.setEdgeWeight(s1, v);
            }
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s1)) {
                vertex.setEdgeWeight(s, v);
            }
        }
    }

    @Override
    public WeightedGraph getBellmanFordShortestPaths(String s) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WeightedGraph getDijkstraShortestPaths(String s) throws UnsupportedOperationException, IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WeightedGraph getPrimMST(String s) throws UnsupportedOperationException, IllegalArgumentException {
        if (!containsVertex(s)) {
            throw new IllegalArgumentException("Il vertice " + s + " non esiste");
        }

        WeightedForest wf = new WeightedForest();
        return wf.getMAR(this, s, this.adjlist);
    }

    @Override
    public WeightedGraph getKruskalMST() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public WeightedGraph getFloydWarshallShortestPaths() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getVertexIndex(String s) {
        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                return adjlist.indexOf(vertex);
            }
        }
        return 0;
    }

    @Override
    public String getVertexLabel(Integer integer) {
        return adjlist.get(integer).getLabel();
    }

    @Override
    public int addVertex(String s) {
        Vertex vertex = new Vertex(s);
        adjlist.add(vertex);
        return adjlist.indexOf(vertex);
    }

    @Override
    public boolean containsVertex(String s) {
        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeVertex(String s) throws NoSuchElementException {

        if (!containsVertex(s)) {
            throw new NoSuchElementException("Il vertice " + s + " non esiste");
        }

        for (int i = 0; i < adjlist.size(); i++) {
            Vertex vertex = adjlist.get(i);
            if (vertex.getLabel().equals(s)) {
                adjlist.remove(vertex);
            }
        }

        for (Vertex vertex : adjlist) {
            vertex.removeEdge(s);
        }

    }

    @Override
    public void addEdge(String s, String s1) throws IllegalArgumentException {
        if (!containsVertex(s) || !containsVertex(s1)) {
            throw new IllegalArgumentException("Uno dei vertici passati non esiste");
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                vertex.addEdge(s1, defaultEdgeWeight);
            }
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s1)) {
                vertex.addEdge(s, defaultEdgeWeight);
            }
        }
    }

    @Override
    public boolean containsEdge(String s, String s1) throws IllegalArgumentException {
        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                return vertex.containsEdge(s1);
            }
        }
        return false;
    }

    @Override
    public void removeEdge(String s, String s1) throws IllegalArgumentException, NoSuchElementException {
        if (!containsVertex(s) || !containsVertex(s1)) {
            throw new NoSuchElementException("Uno dei vertici non è presente nel grafo");
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                vertex.removeEdge(s1);
            }
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s1)) {
                vertex.removeEdge(s);
            }
        }
    }

    @Override
    public Set<String> getAdjacent(String s) throws NoSuchElementException {
        if (!containsVertex(s)) {
            throw new NoSuchElementException("Il vertice " + s + " non esiste");
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                return vertex.getEdgesLabels();
            }
        }
        return null;
    }

    @Override
    public boolean isAdjacent(String s, String s1) throws IllegalArgumentException {
        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                return vertex.containsEdge(s1);
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int size() {
        return adjlist.size();
    }

    @Override
    public boolean isDirected() {
        for (Vertex vertex : adjlist) {
            for (String edge : vertex.getEdgesLabels()) {
                if (!containsEdge(edge, vertex.getLabel())) {
                    return true; // Se anche solo un vertice non ha un arco all'indietro allora è diretto
                }
            }
        }
        return false; // Tutti i vertici sono stati visitati e ognuno ha un arco all'indietro
    }

    @Override
    public boolean isCyclic() {
        WeightedForest v = new WeightedForest();
        return v.isCyclic(this, adjlist.get(0).getLabel());
    }

    @Override
    public boolean isDAG() {
        return isDirected() && !isCyclic(); // Se il grafo è diretto e non contiene cicli è un DAG
    }

    @Override
    public VisitForest getBFSTree(String s) throws UnsupportedOperationException, IllegalArgumentException {
        WeightedForest v = new WeightedForest();
        return v.getTree(this, s, VisitForest.VisitType.BFS);
    }

    @Override
    public VisitForest getDFSTree(String s) throws UnsupportedOperationException, IllegalArgumentException {
        WeightedForest v = new WeightedForest();
        return v.getTree(this, s, VisitForest.VisitType.DFS);
    }

    @Override
    public VisitForest getDFSTOTForest(String s) throws UnsupportedOperationException, IllegalArgumentException {
        WeightedForest v = new WeightedForest();
        return v.getTree(this, s, VisitForest.VisitType.DFS);
    }

    @Override
    public VisitForest getDFSTOTForest(String[] strings) throws UnsupportedOperationException, IllegalArgumentException {
        WeightedForest v = new WeightedForest();
        return v.getTOTTree(this, strings, VisitForest.VisitType.DFS);
    }

    @Override
    public String[] topologicalSort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Il grafo essendo non-orientato non supporta l' ordinamento topologico");
    }

    @Override
    public Set<Set<String>> stronglyConnectedComponents() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Il grafo essendo non-orientato non supporta le componenti fortemente connesse");
    }

    @Override
    public Set<Set<String>> connectedComponents() throws UnsupportedOperationException {
        WeightedForest visitor = new WeightedForest();
        return visitor.getComponents(this);
    }
}
