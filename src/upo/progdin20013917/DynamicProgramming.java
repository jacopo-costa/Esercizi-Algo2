package upo.progdin20013917;

import java.util.Arrays;

public class DynamicProgramming {

    /**
     * Calcola la LCS tra <code>s1</code> e <code>s2</code> utilizzando l'algoritmo visto a lezione.
     * </br>CONSIGLIO: potete usare i metodi di String per accedere alle posizioni di s1 ed s2.
     * </br>CONSIGLIO2: potete costruire l'output come un array di caratteri, e poi trasformarlo in stringa,
     * oppure usare le concatenazioni di stringhe nelle chiamate ricorsive (vedi slide).
     *
     * @param s1 una sequenza di caratteri
     * @param s2 una sequenza di caratteri
     * @return una LCS di <code>s1</code> e <code>s2</code>
     */
    public static String LongestCommonSubsequence(String s1, String s2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Risolve il problema dello zaino 0-1 con l'algoritmo di programmazione dinamica visto a lezione.
     *
     * @param weights   un vettore contenente in posizione i-esima, per ogni oggetto oi, il suo peso.
     * @param values    un vettore contenente in posizione i-esima, per ogni oggetto oi, il suo valore.
     * @param maxWeight la capienza dello zaino.
     * @return un vettore di boolean che contiene, in posizione i-esima, true se l'oggetto i-esimo è
     * incluso nella soluzione, false altrimenti.
     */
    public static boolean[] getKnapsack01(int[] weights, int[] values, int maxWeight) {
        int n = weights.length; //Quanti elementi ci sono
        int[][] ks = new int[n + 1][maxWeight + 1]; //Matrice dei risultati
        boolean[][] keep = new boolean[n + 1][maxWeight + 1]; //Matrice degli oggetti selezionati

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) { //Itera la matrice dei risultati
                if (i == 0 || w == 0) { //Caso base nelle colonne o righe 0
                    ks[i][w] = 0;
                } else if (weights[i - 1] <= w) { //Se il peso dell'elemento precedente è minore di quello in questione
                    ks[i][w] = Math.max(values[i - 1] + ks[i - 1][w - weights[i - 1]], ks[i - 1][w]); //Parte ricorsiva con prelievo del valore maggiore
                    keep[i][w] = true; //L'elemento in quella cella viene quindi selezionato
                } else {
                    ks[i][w] = ks[i - 1][w]; //Altrimenti la cella viene copiata con quella presente di sopra
                }
            }
        }

        boolean[] result = new boolean[n + 1]; //Array degli oggetti selezionati
        int e = n;
        int d = maxWeight;
        while (e >= 1) { //Itera gli oggetti nella matrice a partine da quello in ultima posizione
            if (keep[e][d]) {
                result[e] = true;
                d = d - weights[e - 1]; //Diminuisce il peso disponibile nel sacco con quello dell'oggetto
            }
            e--;
        }
        return Arrays.copyOfRange(result, 1, n + 1);
    }

}
