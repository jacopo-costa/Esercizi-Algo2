package upo.additionalstructures;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Oggetto vertice per il grafo.
 * Contiene una stringa con il nome del vertice e una
 * lista di archi collegati.
 */
public class Vertex {
    private final String label;
    private final LinkedList<Edge> edges;

    public Vertex(String label) {
        this.label = label;
        edges = new LinkedList<>();
    }

    public String getLabel() {
        return label;
    }

    /**
     * Rimuove dalla lista del vertice l'arco passato
     * come parametro
     *
     * @param dest Arco di destinazione da rimuovere
     */
    public void removeEdge(String dest) {
        for (Edge edge : edges) {
            if (edge.getDest().equals(dest)) {
                edges.remove(edge);
                return;
            }
        }
    }

    /**
     * Aggiunge un arco alla lista
     *
     * @param dest   Nome del vertice
     * @param weight Peso dell'arco
     */
    public void addEdge(String dest, double weight) {
        edges.add(new Edge(dest, weight));
    }

    /**
     * Ritorna il peso dell'arco.
     *
     * @param dest Arco da ottenere.
     * @return 0 se l'arco non esiste, altrimenti il suo peso
     */
    public double getEdgeWeight(String dest) {
        for (Edge edge : edges) {
            if (edge.getDest().equals(dest)) {
                return edge.getWeight();
            }
        }
        return 0;
    }

    /**
     * Controlla se l'arco è nella lista del vettore
     *
     * @param dest Arco da controllare
     * @return Booleano
     */
    public boolean containsEdge(String dest) {
        for (Edge edge : edges) {
            if (edge.getDest().equals(dest)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Imposta un peso all'arco scelto.
     *
     * @param dest   Arco da modificare
     * @param weight Peso da attribuire
     */
    public void setEdgeWeight(String dest, double weight) {
        for (Edge edge : edges) {
            if (edge.getDest().equals(dest)) {
                edge.setWeight(weight);
                return;
            }
        }
    }

    /**
     * Ritorna una lista di nome degli archi associati al vertice
     *
     * @return Set di stringe degli archi
     */
    public Set<String> getEdgesLabels() {
        Set<String> edges = new HashSet<>();
        for (Edge edge : this.edges) {
            edges.add(edge.getDest());
        }
        return edges;
    }

    /**
     * Ritorna la lista degli archi del vertice.
     * Utile per la costruzione del MAR
     *
     * @return Lista di archi
     */
    public LinkedList<Edge> getEdges() {
        return edges;
    }
}
