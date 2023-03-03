package upo.greedy;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GreedyTest {

    @Test
    void getHuffmanCodes() {
        Character[] characters = new Character[]{'a', 'b', 'c', 'd', 'e', 'f'};
        int[] freq = new int[]{45, 13, 12, 16, 9, 5};
        Map<Character, String> huffman = Greedy.getHuffmanCodes(characters, freq);
        String expected = "{a=0, b=101, c=100, d=111, e=1101, f=1100}";
        assertEquals(expected, Greedy.getHuffmanCodes(characters, freq).toString());
    }

    @Test
    void getMaxDisjointIntervals() {
        Integer[] starting = new Integer[]{2, 5, 6, 9, 10, 14};
        Integer[] ending = new Integer[]{5, 7, 8, 11, 13, 15};
        Integer[] expected = new Integer[]{0, 2, 3, 5};
        assertArrayEquals(expected, Greedy.getMaxDisjointIntervals(starting, ending));
    }

}