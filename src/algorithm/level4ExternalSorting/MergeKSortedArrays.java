package algorithm.level4ExternalSorting;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedArrays {

    private Comparator<Element> elementComparator = new Comparator<>() {
        @Override
        public int compare(Element left, Element right) {
            return left.value - right.value;
        }
    };
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null) {
            return new int[0];
        }
        int size = 0;
        Queue<Element> minHeap = new PriorityQueue<>(arrays.length, elementComparator);
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Element newElement = new Element(i, 0, arrays[i][0]);
                minHeap.offer(newElement);
                size += arrays[i].length;
            }
        }
        int[] results = new int[size];
        int index = 0;
        while (!minHeap.isEmpty()) {
            Element element = minHeap.poll();
            results[index++] = element.value;
            if (element.column + 1 < arrays[element.row].length) {
                element.column += 1;
                element.value = arrays[element.row][element.column];
                minHeap.offer(element);
            }
        }
        return results;
    }
}

class Element {
    int column, row, value;
    public Element(int row, int column, int value) {
        this.column = column;
        this.row = row;
        this.value = value;
    }
}
