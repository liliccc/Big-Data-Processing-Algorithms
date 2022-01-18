package algorithm.level2TopKFrequentElements;

import java.util.*;

public class TopKFrequentWords {
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    // use minHeap
    public String[] topKFrequentWords(String[] words, int k) {
        if (words == null || words.length < k || k < 1) {
            return new String[0];
        }
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            }
            else {
                int counter = map.get(word);
                map.put(word, counter + 1);
            }
        }
        // minheap
        Queue<Pair2> minHeap = new PriorityQueue<>(k, pair2Comparator);
        for (String key : map.keySet()) {
            Pair2 pair = new Pair2(key, map.get(key));
            if (minHeap.size() < k) {
                minHeap.offer(pair);
            }
            else if (pair2Comparator.compare(pair, minHeap.peek()) > 0) {
                minHeap.offer(pair);
                minHeap.poll();
            }
        }
        String[] KFrequentNumbers = new String[k];
        for (int i = k - 1; i >= 0; i--) {
            KFrequentNumbers[i] = minHeap.poll().word;
        }
        return KFrequentNumbers;
    }

    private final Comparator<Pair2> pair2Comparator = new Comparator<Pair2>() {
        @Override
        public int compare(Pair2 left, Pair2 right) {
            if (left.counter != right.counter) {
                return left.counter - right.counter;
            }
            // start from a,b,c...
            return right.word.compareTo(left.word);
        }
    };
}

class Pair2 {
    String word;
    int counter;
    public Pair2(String word, int counter) {
        this.word = word;
        this.counter = counter;
    }
}
