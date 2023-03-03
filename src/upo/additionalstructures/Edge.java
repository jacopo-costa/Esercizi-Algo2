package upo.additionalstructures;

/**
 * Classe oggetto per gli archi del grafo.
 * Contiene una stringa con la destinazione e un double
 * per il peso dell'arco.
 */
public class Edge {
    private final String dest;
    private double weight;

    public Edge(String dest, double weight) {
        this.dest = dest;
        this.weight = weight;
    }

    public String getDest() {
        return dest;
    }

    public double getWeight() {
        return weight;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }
}
