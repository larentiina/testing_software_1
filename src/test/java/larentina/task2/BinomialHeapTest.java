package larentina.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinomialHeapTest {

@Test
    public void testEmptyHeap() {
    BinomialHeap bh = new BinomialHeap();
    int expected = -1;
    int actual = bh.extractMin();
    Assertions.assertEquals(expected, actual);
}

@Test
    public void testMakeEmptyHeap() {
    BinomialHeap bh = new BinomialHeap();
    bh.insert(1);
    bh.insert(2);
    bh.insert(3);
    bh.makeEmpty();
    Assertions.assertTrue(bh.isEmpty());

}

@Test
    public void testSizeHeap() {
    BinomialHeap bh = new BinomialHeap();
    bh.insert(1);
    bh.insert(2);
    int expected = 2;
    int actual = bh.getSize();
    Assertions.assertEquals(expected, actual);
    Assertions.assertFalse(bh.isEmpty());
}

@Test
    public void testExtractMin() {
    BinomialHeap bh = new BinomialHeap();
    bh.insert(1);
    bh.insert(3);
    bh.insert(4);
    bh.insert(15);
    bh.insert(7);
    bh.insert(2);
    bh.insert(9);
    bh.insert(-5);
    int expected = 1;
    int actual = bh.extractMin();
    Assertions.assertEquals(expected, actual);

    bh = new BinomialHeap();
    bh.insert(10);
    actual = bh.extractMin();
    Assertions.assertEquals(10, actual);

    bh.insert(30);
    bh.insert(20);
    bh.insert(10);
    actual = bh.extractMin();
    Assertions.assertEquals(10, actual);
}

    @Test
    public void testFindMin() {
        BinomialHeap bh = new BinomialHeap();
        bh.insert(1);
        bh.insert(3);
        bh.insert(4);
        bh.insert(15);
        bh.insert(7);
        bh.insert(2);
        bh.insert(9);
        bh.insert(-5);
        int expected = 1;
        int actual = bh.findMinimum();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testDisplayHeap() {
        BinomialHeap binHeap = new BinomialHeap();
        binHeap.insert(2);
        binHeap.insert(2);
        binHeap.insert(3);
        binHeap.insert(4);
        String actual = binHeap.displayHeap().trim();
        String expected = "Heap: (2 -> [3 -> [4], 2])";

        Assertions.assertEquals(expected, actual);

        binHeap.makeEmpty();
        actual = binHeap.displayHeap();
        Assertions.assertEquals("Heap: ", actual);

    }




}
