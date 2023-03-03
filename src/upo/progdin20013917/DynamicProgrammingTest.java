package upo.progdin20013917;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProgrammingTest {

    @Test
    void getKnapsack01() {
        int[] weights = new int[]{2, 3, 4, 5};
        int[] values = new int[]{1, 2, 5, 6};
        int maxWeight = 8;
        boolean[] expected = new boolean[]{false, true, false, true};
        boolean[] result = DynamicProgramming.getKnapsack01(weights, values, maxWeight);

        assertArrayEquals(expected, result);
    }
}