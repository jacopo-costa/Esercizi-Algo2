package upo.graph20013917;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.VisitForest;
import upo.graph.base.WeightedGraph;

import static org.junit.jupiter.api.Assertions.*;

class AdjListUndirWeightTest {
    private AdjListUndirWeight adjweightedlist;

    @BeforeEach
    void creteAdjList() {

        adjweightedlist = new AdjListUndirWeight();

        /* A -- B
           |
           |       E
           |     /
           C -- D
         */

        adjweightedlist.addVertex("a");
        adjweightedlist.addVertex("b");
        adjweightedlist.addVertex("c");
        adjweightedlist.addVertex("d");
        adjweightedlist.addVertex("e");

        adjweightedlist.addEdge("a", "b");
        adjweightedlist.addEdge("a", "c");
        adjweightedlist.addEdge("c", "d");
        adjweightedlist.addEdge("d", "e");
    }

    @Test
    void getEdgeWeight() {
        assertEquals(1.0, adjweightedlist.getEdgeWeight("a", "c"));
    }

    @Test
    void setEdgeWeight() {
        adjweightedlist.setEdgeWeight("a", "b", 2.0);
        assertEquals(2.0, adjweightedlist.getEdgeWeight("a", "b"));
    }

    @Test
    void getVertexIndex() {
        assertEquals(3, adjweightedlist.getVertexIndex("d"));
    }

    @Test
    void getVertexLabel() {
        assertEquals("c", adjweightedlist.getVertexLabel(2));
    }

    @Test
    void addVertex() {
        adjweightedlist.addVertex("test");
        assertTrue(adjweightedlist.containsVertex("test"));
    }

    @Test
    void containsVertex() {
        assertTrue(adjweightedlist.containsVertex("e"));
    }

    @Test
    void removeVertex() {
        adjweightedlist.removeVertex("e");
        assertFalse(adjweightedlist.containsVertex("e"));
    }

    @Test
    void addEdge() {
        adjweightedlist.addEdge("b", "c");
        assertTrue(adjweightedlist.containsEdge("c", "b"));
    }

    @Test
    void containsEdge() {
        assertTrue(adjweightedlist.containsEdge("a", "b"));
    }

    @Test
    void removeEdge() {
        adjweightedlist.removeEdge("a", "c");
        assertFalse(adjweightedlist.containsEdge("a", "c"));
        assertFalse(adjweightedlist.containsEdge("c", "a"));
    }

    @Test
    void getAdjacent() {
        assertEquals("[b, c]", adjweightedlist.getAdjacent("a").toString());
    }

    @Test
    void isAdjacent() {
        assertTrue(adjweightedlist.isAdjacent("a", "c"));
        assertFalse(adjweightedlist.isAdjacent("e", "a"));
    }

    @Test
    void size() {
        assertEquals(5, adjweightedlist.size());
    }

    @Test
    void isDirected() {
        assertFalse(adjweightedlist.isDirected());
    }

    @Test
    void isCyclic() {
        assertFalse(adjweightedlist.isCyclic());
        adjweightedlist.addEdge("b", "e");
        assertTrue(adjweightedlist.isCyclic());
    }

    @Test
    void isDAG() {
        assertFalse(adjweightedlist.isDAG());
    }

    @Test
    void getBFSTree() {
        VisitForest forest = adjweightedlist.getBFSTree(adjweightedlist.getVertexLabel(0));
        assertEquals("a", forest.getPartent("b"));
        assertEquals(7, forest.getStartTime("e"));
        assertEquals(9, forest.getEndTime("e"));
    }

    @Test
    void getDFSTree() {
        VisitForest forest = adjweightedlist.getDFSTree(adjweightedlist.getVertexLabel(0));
        assertEquals("a", forest.getPartent("b"));
        assertEquals(0, forest.getStartTime("a"));
        assertEquals(9, forest.getEndTime("a"));
    }

    @Test
    void topologicalSort() {
        assertThrows(UnsupportedOperationException.class, () -> adjweightedlist.topologicalSort());
    }

    @Test
    void stronglyConnectedComponents() {
        assertThrows(UnsupportedOperationException.class, () -> adjweightedlist.stronglyConnectedComponents());
    }

    @Test
    void connectedComponents() {
        assertEquals("[[a, b, c, d, e]]", adjweightedlist.connectedComponents().toString());

        adjweightedlist.removeEdge("d", "e");
        assertEquals("[[e], [a, b, c, d]]", adjweightedlist.connectedComponents().toString());
    }

    @Test
    void getPrimMST() {
        /* Modifica del grafo di test per ottenere un MAR
             2.5
           G --- F
      4.3 /      |
         H       | 20.4
      7.2 \  2.1 |
           A --- B
           |      \ 1.4
       4.5 |       E
           |      / 5.7
           C --- D
             3.7
         */
        adjweightedlist.addVertex("f");
        adjweightedlist.addVertex("g");
        adjweightedlist.addVertex("h");

        adjweightedlist.addEdge("a", "h");
        adjweightedlist.addEdge("b", "f");
        adjweightedlist.addEdge("b", "e");
        adjweightedlist.addEdge("f", "g");
        adjweightedlist.addEdge("g", "h");

        adjweightedlist.setEdgeWeight("a", "b", 2.1);
        adjweightedlist.setEdgeWeight("a", "c", 4.5);
        adjweightedlist.setEdgeWeight("a", "h", 7.2);
        adjweightedlist.setEdgeWeight("b", "f", 20.4);
        adjweightedlist.setEdgeWeight("b", "e", 1.4);
        adjweightedlist.setEdgeWeight("e", "d", 5.7);
        adjweightedlist.setEdgeWeight("c", "d", 3.7);
        adjweightedlist.setEdgeWeight("f", "g", 2.5);
        adjweightedlist.setEdgeWeight("g", "h", 4.3);
        WeightedGraph aj = adjweightedlist.getPrimMST("a");

        assertEquals("[b, c, h]", aj.getAdjacent("a").toString());
        assertEquals("[a, e]", aj.getAdjacent("b").toString());
        assertEquals("[a, d]", aj.getAdjacent("c").toString());
        assertEquals("[c]", aj.getAdjacent("d").toString());
        assertEquals("[b]", aj.getAdjacent("e").toString());
        assertEquals("[g]", aj.getAdjacent("f").toString());
        assertEquals("[f, h]", aj.getAdjacent("g").toString());
        assertEquals("[a, g]", aj.getAdjacent("h").toString());

        /* MAR ottenuto:
             2.5
           G --- F
      4.3 /
         H
      7.2 \  2.1
           A --- B
           |      \ 1.4
       4.5 |       E
           |
           C --- D
             3.7
         */
    }
}