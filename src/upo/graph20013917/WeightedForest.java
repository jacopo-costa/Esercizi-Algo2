package upo.graph20013917;

import upo.additionalstructures.*;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;

import java.util.*;

/**
 * Classe di supporto per le visite della lista di adiacenza
 */
public class WeightedForest {

    /**
     * Controlla se un grafo è ciclico o meno
     *
     * @param graph Grafo da controllare
     * @param start Vertice di partenza
     * @return True se è ciclico altrimenti False
     */
    public boolean isCyclic(WeightedGraph graph, String start) {
        VisitForest forest = new VisitForest(graph, VisitForest.VisitType.DFS);
        Fringe<String> fringe = new myStack<>();
        fringe.add(start);
        forest.setColor(start, VisitForest.Color.GRAY);

        while (!fringe.isEmpty()) {
            String u = fringe.first();

            if (forest.getColor(u).equals(VisitForest.Color.BLACK)) {
                fringe.removeFirst();
            } else {
                for (String neighbour : graph.getAdjacent(u)) {
                    if (forest.getColor(neighbour).equals(VisitForest.Color.WHITE)) {
                        fringe.add(neighbour);
                        forest.setColor(neighbour, VisitForest.Color.GRAY);
                    } else if (forest.getColor(neighbour).equals(VisitForest.Color.GRAY)) { // Se negli adiacenti c'è un vertice grigio allora è ciclico
                        return true;
                    }
                }
                forest.setColor(u, VisitForest.Color.BLACK);
            }
        }
        return false; // A questo punto ha già visitato tutto il grafo senza trovare cicli
    }

    /**
     * Metodo che ritorna l'albero di visita dal grafo
     *
     * @param graph Grafo da visitare
     * @param start Vertice di partenza
     * @param type  Tipo di visita
     * @return Oggetto VisitForest dell'albero
     */
    public VisitForest getTree(WeightedGraph graph, String start, VisitForest.VisitType type) {
        if (isCyclic(graph, start)) { //Controlla se è ciclico altrimenti non si puù visitare
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
                        forest.setDistance(u, graph.getEdgeWeight(u, neighbour));
                        time += 1;
                    }
                }
                forest.setColor(u, VisitForest.Color.BLACK);
            }
        }

        return forest;
    }

    /**
     * Visita del grafo totale, per ogni vertice passato come argomento si effettua una DFS
     *
     * @param graph Grafo da visitare
     * @param start Array di vertici da visitare come punti di partenza
     * @param type  Tipo di visita
     * @return Oggetto foresta contenente la visita completa
     */
    public VisitForest getTOTTree(WeightedGraph graph, String[] start, VisitForest.VisitType type) {

        Fringe<String> fringe;
        VisitForest forest = new VisitForest(graph, type);

        if (type.equals(VisitForest.VisitType.DFS)) {
            fringe = new myStack<>();
        } else {
            fringe = new myQueue<>();
        }

        int time = 0;
        for (String vertex : start) { // Per ogni vertice passato come argomento si effettua la visita
            if (!forest.getColor(vertex).equals(VisitForest.Color.BLACK)) { //Se il vertice è nero è già stato visitato e passo oltre
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
                                forest.setDistance(u, graph.getEdgeWeight(u, neighbour));
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

    /**
     * Metodo che visita il grafo e aggiunge ad un set le componenti connesse di esso
     *
     * @param graph Grafo da visitare
     * @return Set di componenti connessi
     */
    public Set getComponents(WeightedGraph graph) {
        Set<Set<String>> cc = new HashSet<>(); //Nuovo set da ritornare
        boolean[] visited = new boolean[graph.size()]; //Array di vertici visitati

        for (int i = 0; i < visited.length; i++) { //Ciclo per ogni vertice
            if (!visited[i]) { //Se il vertice è già stato visitato non effettuo un'altra visita
                myStack<String> fringe = new myStack<>();
                fringe.add(graph.getVertexLabel(i));
                VisitForest forest = new VisitForest(graph, VisitForest.VisitType.DFS);
                HashSet<String> components = new HashSet<>();
                while (!fringe.isEmpty()) {
                    String u = fringe.first();
                    components.add(u);

                    if (forest.getColor(u).equals(VisitForest.Color.BLACK)) {
                        fringe.removeFirst();
                    } else {
                        for (String neighbour : graph.getAdjacent(u)) {
                            if (forest.getColor(neighbour).equals(VisitForest.Color.WHITE)) {
                                fringe.add(neighbour);
                                forest.setColor(neighbour, VisitForest.Color.GRAY);
                            }
                        }
                        forest.setColor(u, VisitForest.Color.BLACK);
                        visited[graph.getVertexIndex(u)] = true;
                    }
                }
                cc.add(components);
            }
        }
        return cc;
    }

    public WeightedGraph getMAR(WeightedGraph graph, String start, ArrayList<Vertex> adjlist) {
        // Dichiarazione rispettivamente del MAR da creare, degli array di booleani dei vertici visitati
        // e dei suoi predecessori
        AdjListUndirWeight mar = new AdjListUndirWeight();
        boolean[] def = new boolean[graph.size()];
        int[] parent = new int[graph.size()];

        Arrays.fill(parent, -1);

        // Priority queue che ordina i suoi elementi secondo il comparatore passato, in questo caso
        // ordina in modo ascendente per i pesi degli archi
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.getWeight()));

        // Aggiunge il vertice passato con un arco a 0 nella queue
        // e lo aggiunge al MAR
        pq.add(new Edge(start, 0));
        mar.addVertex(start);


        while (!pq.isEmpty()) {
            Edge curr = pq.poll(); // Rimuove dalla coda l'arco con peso minore
            int vertex = graph.getVertexIndex(curr.getDest()); // Ottiene l'index della destinazione dell'arco

            if (def[vertex]) { // Se il vertice è nell'array dei visitati continua con il prossimo nella coda
                continue;
            }

            def[vertex] = true;
            // Se il predecessore è diverso da -1 lo aggiunge al MAR con il suo discendente come arco
            if (parent[vertex] != -1) {
                String p = graph.getVertexLabel(parent[vertex]);
                String c = graph.getVertexLabel(vertex);
                if (!mar.containsVertex(p)) {
                    mar.addVertex(p);
                } else if (!mar.containsVertex(c)) {
                    mar.addVertex(c);
                }
                mar.addEdge(p, c);
                mar.setEdgeWeight(p, c, curr.getWeight());
            }

            // Per tutti gli archi adiacenti se non sono già stati visitati gli aggiunge alla coda
            for (Edge neighbor : adjlist.get(vertex).getEdges()) {
                int destIndex = graph.getVertexIndex(neighbor.getDest());
                if (!def[destIndex]) {
                    pq.add(neighbor);
                    // Se non esiste un suo predecessore o la distanza tra il predecessore e l'adiacente in questione
                    // è minore della distanza tra il nodo corrente e il suo adiacente aggiorna il predecessore nell'array
                    if (parent[destIndex] == -1 || neighbor.getWeight() < graph.getEdgeWeight(graph.getVertexLabel(parent[destIndex]), neighbor.getDest())) {
                        parent[destIndex] = graph.getVertexIndex(curr.getDest());
                    }
                }
            }
        }

        return mar;
    }

}
