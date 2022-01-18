package algorithm.level2TopKFrequentElements;

import java.util.PriorityQueue;
import java.util.Queue;

public class TopkLargestNumbers {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    // use quicksort or minHeap
    public int[] topk(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return null;
        }
        //  PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
        //             public int compare(Integer o1, Integer o2) {
        //                 return o1 - o2;
        //             }
        //         });
        Queue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            // 0 -> k-1
            results[k - i - 1] = minHeap.poll();
        }
        return results;
    }
}
