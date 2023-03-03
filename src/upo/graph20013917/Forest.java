package upo.graph20013917;

import upo.additionalstructures.Fringe;
import upo.additionalstructures.Vertex;
import upo.additionalstructures.myQueue;
import upo.additionalstructures.myStack;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;

import java.util.ArrayList;

/**
 * Classe che estende WeightedForest per le operazioni di visita
 * su un grafo a liste di adiacenza non pesato
 */
public class Forest extends WeightedForest {

    //Override della funzione solo per togliere dalla foresta la distanza
    //dell'arco
    @Override
    public VisitForest getTree(WeightedGraph graph, String start, VisitForest.VisitType type) {
        if (isCyclic(graph, start)) {
            throw new UnsupportedOperationException();
        }

        Fringe<String> fringe;
        VisitForest forest = new VisitForest(graph, type);

        if (type.equals(VisitForest.VisitType.DFS)) {
            fringe = new myStack<>();
        } else {
            fringe = new myQueue<>();
        }

        int time = 0;
        fringe.add(start);
        forest.setColor(start, VisitForest.Color.GRAY);
        forest.setStartTime(start, time);
        forest.setParent(start, null);
        time += 1;

        while (!fringe.isEmpty()) {
            String u = fringe.first();

            if (forest.getColor(u).equals(VisitForest.Color.BLACK)) {
                fringe.removeFirst();
                forest.setEndTime(u, time);
                time += 1;
            } else {
                for (String neighbour : graph.getAdjacent(u)) {
                    if (forest.getColor(neighbour).equals(VisitForest.Color.WHITE)) {
                        fringe.add(neighbour);
                        forest.setColor(neighbour, VisitForest.Color.GRAY);
                        forest.setStartTime(neighbour, time);
                        forest.setParent(neighbour, u);
                        time += 1;
                    }
                }
                forest.setColor(u, VisitForest.Color.BLACK);
            }
        }

        return forest;
    }

    //Uguale a getTree
    @Override
    public VisitForest getTOTTree(WeightedGraph graph, String[] start, VisitForest.VisitType type) {
        Fringe<String> fringe;
        VisitForest forest = new VisitForest(graph, type);

        if (type.equals(VisitForest.VisitType.DFS)) {
            fringe = new myStack<>();
        } else {
            fringe = new myQueue<>();
        }

        int time = 0;
        for (String vertex : start) {
            if (!forest.getColor(vertex).equals(VisitForest.Color.BLACK)) {
                fringe.add(vertex);
                forest.setColor(vertex, VisitForest.Color.GRAY);
                forest.setStartTime(vertex, time);
                forest.setParent(vertex, null);
                time += 1;

                while (!fringe.isEmpty()) {
                    String u = fringe.first();

                    if (forest.getColor(u).equals(VisitForest.Color.BLACK)) {
                        fringe.removeFirst();
                        forest.setEndTime(u, time);
                        time += 1;
                    } else {
                        for (String neighbour : graph.getAdjacent(u)) {
                            if (forest.getColor(neighbour).equals(VisitForest.Color.WHITE)) {
                                fringe.add(neighbour);
                                forest.setColor(neighbour, VisitForest.Color.GRAY);
                                forest.setStartTime(neighbour, time);
                                forest.setParent(neighbour, u);
                                time += 1;
                            }
                        }
                        forest.setColor(u, VisitForest.Color.BLACK);
                    }
                }
            }
        }

        return forest;
    }

    @Override
    public WeightedGraph getMAR(WeightedGraph graph, String start, ArrayList<Vertex> adjlist) {
        throw new UnsupportedOperationException("L'operazione non è disponibile per un grafo non pesato");
    }
}
