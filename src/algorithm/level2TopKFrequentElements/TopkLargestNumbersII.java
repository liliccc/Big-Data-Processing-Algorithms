package algorithm.level2TopKFrequentElements;

import java.util.*;

public class TopkLargestNumbersII {
    /*
     * @param k: An integer
     */
     // online problem, using min heap
     private int k;
     private Queue<Integer> minHeap;
     public TopkLargestNumbersII(int k) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
     }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        if (minHeap.size() < k) {
            minHeap.offer(num);
            return;
        }
        if (num > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(num);
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        List<Integer> results = new ArrayList<>();
        for (Integer integer : minHeap) {
            results.add((int) integer);
        }
        results.sort(Collections.reverseOrder());
        return results;
    }
}
