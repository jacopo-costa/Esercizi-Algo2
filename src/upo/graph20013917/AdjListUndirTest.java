package upo.graph20013917;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upo.graph.base.VisitForest;

import static org.junit.jupiter.api.Assertions.*;

class AdjListUndirTest {

    private AdjListUndir adjlist;

    @BeforeEach
    void creteAdjList() {
        adjlist = new AdjListUndir();


        /* A -- B
           |
           |       E
           |     /
           C -- D
         */

        adjlist.addVertex("a");
        adjlist.addVertex("b");
        adjlist.addVertex("c");
        adjlist.addVertex("d");
        adjlist.addVertex("e");

        adjlist.addEdge("a", "b");
        adjlist.addEdge("a", "c");
        adjlist.addEdge("c", "d");
        adjlist.addEdge("d", "e");
    }

    @Test
    void getEdgeWeight() {
        assertThrows(UnsupportedOperationException.class, () -> adjlist.getEdgeWeight("a", "b"));
    }

    @Test
    void setEdgeWeight() {
        assertThrows(UnsupportedOperationException.class, () -> adjlist.setEdgeWeight("a", "b", 12.2));
    }

    @Test
    void getVertexIndex() {
        assertEquals(3, adjlist.getVertexIndex("d"));
    }

    @Test
    void getVertexLabel() {
        assertEquals("c", adjlist.getVertexLabel(2));
    }

    @Test
    void addVertex() {
        adjlist.addVertex("test");
        assertTrue(adjlist.containsVertex("test"));
    }

    @Test
    void containsVertex() {
        assertTrue(adjlist.containsVertex("e"));
    }

    @Test
    void removeVertex() {
        adjlist.removeVertex("e");
        assertFalse(adjlist.containsVertex("e"));
    }

    @Test
    void addEdge() {
        adjlist.addEdge("b", "c");
        assertTrue(adjlist.containsEdge("b", "c"));
    }

    @Test
    void containsEdge() {
        assertTrue(adjlist.containsEdge("a", "b"));
    }

    @Test
    void removeEdge() {
        adjlist.removeEdge("a", "c");
        assertFalse(adjlist.containsEdge("a", "c"));
        assertFalse(adjlist.containsEdge("c", "a"));
    }

    @Test
    void getAdjacent() {
        assertEquals("[b, c]", adjlist.getAdjacent("a").toString());
    }

    @Test
    void isAdjacent() {
        assertTrue(adjlist.isAdjacent("a", "c"));
        assertFalse(adjlist.isAdjacent("e", "a"));
    }

    @Test
    void size() {
        assertEquals(5, adjlist.size());
    }

    @Test
    void isDirected() {
        assertFalse(adjlist.isDirected());
    }

    @Test
    void isCyclic() {
        assertFalse(adjlist.isCyclic());
        adjlist.addEdge("b", "e");
        assertTrue(adjlist.isCyclic());
    }

    @Test
    void isDAG() {
        assertFalse(adjlist.isDAG());
    }

    @Test
    void getBFSTree() {
        VisitForest forest = adjlist.getBFSTree(adjlist.getVertexLabel(0));
        assertEquals("a", forest.getPartent("b"));
        assertEquals(7, forest.getStartTime("e"));
        assertEquals(9, forest.getEndTime("e"));
    }

    @Test
    void getDFSTree() {
        VisitForest forest = adjlist.getDFSTree(adjlist.getVertexLabel(0));
        assertEquals("a", forest.getPartent("b"));
        assertEquals(0, forest.getStartTime("a"));
        assertEquals(9, forest.getEndTime("a"));
    }

    @Test
    void getDFSTOTForest() {
    }

    @Test
    void testGetDFSTOTForest() {
    }

    @Test
    void topologicalSort() {
        assertThrows(UnsupportedOperationException.class, () -> adjlist.topologicalSort());
    }

    @Test
    void stronglyConnectedComponents() {
        assertThrows(UnsupportedOperationException.class, () -> adjlist.stronglyConnectedComponents());
    }

    @Test
    void connectedComponents() {
        assertEquals("[[a, b, c, d, e]]", adjlist.connectedComponents().toString());

        adjlist.removeEdge("d", "e");
        assertEquals("[[e], [a, b, c, d]]", adjlist.connectedComponents().toString());
    }
}