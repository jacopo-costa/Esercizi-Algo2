package upo.graph20013917;

import upo.additionalstructures.Vertex;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;

import java.util.NoSuchElementException;

/**
 * Classe che estende AdjListUndirWeight.
 * Caso speciale per liste pesate.
 * Al posto di un peso per l'arco viene messo un valore nullo e le operazioni
 * non supportate lanciano una eccezione
 */
public class AdjListUndir extends AdjListUndirWeight {

    @Override
    public double getEdgeWeight(String s, String s1) throws IllegalArgumentException, NoSuchElementException {
        throw new UnsupportedOperationException("Operazione per grafo non pesato non supportata");
    }

    @Override
    public void setEdgeWeight(String s, String s1, double v) throws IllegalArgumentException, NoSuchElementException {
        throw new UnsupportedOperationException("Operazione per grafo non pesato non supportata");
    }

    @Override
    public void addEdge(String s, String s1) throws IllegalArgumentException {
        if (!containsVertex(s) || !containsVertex(s1)) {
            throw new IllegalArgumentException("Uno dei vertici passati non esiste");
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s)) {
                vertex.addEdge(s1, 0);
            }
        }

        for (Vertex vertex : adjlist) {
            if (vertex.getLabel().equals(s1)) {
                vertex.addEdge(s, 0);
            }
        }
    }

    @Override
    public boolean isCyclic() {
        Forest forest = new Forest();
        return forest.isCyclic(this, this.adjlist.get(0).getLabel());
    }

    @Override
    public VisitForest getBFSTree(String s) throws UnsupportedOperationException, IllegalArgumentException {
        Forest v = new Forest();
        return v.getTree(this, s, VisitForest.VisitType.BFS);
    }

    @Override
    public VisitForest getDFSTree(String s) throws UnsupportedOperationException, IllegalArgumentException {
        Forest v = new Forest();
        return v.getTree(this, s, VisitForest.VisitType.DFS);
    }

    @Override
    public VisitForest getDFSTOTForest(String s) throws UnsupportedOperationException, IllegalArgumentException {
        Forest v = new Forest();
        return v.getTree(this, s, VisitForest.VisitType.DFS);
    }

    @Override
    public VisitForest getDFSTOTForest(String[] strings) throws UnsupportedOperationException, IllegalArgumentException {
        Forest v = new Forest();
        return v.getTOTTree(this, strings, VisitForest.VisitType.DFS);
    }

    @Override
    public WeightedGraph getPrimMST(String s) throws UnsupportedOperationException, IllegalArgumentException {
        Forest v = new Forest();
        return v.getMAR(this, s, adjlist);
    }
}
