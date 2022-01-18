package algorithm.level2TopKFrequentElements;

import java.util.*;

public class TopKFrequentWordsMapReduce {
    // online problem, use heap
    public static class Map {
        public void map(String s, Document value,
                        OutputCollector<String, Integer> output) {
           String content = value.content;
           String[] array = content.split(" ");
           for (String piece: array) {
                if (piece.length() > 0) {
                    output.collect(piece, 1);
                }
           }
        }
    }

    public static class Reduce {
        private Queue<Pair> minHeap;
        int k;

        private final Comparator<Pair> pairComparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair left, Pair right) {
                if (left.value != right.value) {
                    return left.value - right.value;
                }
                return right.key.compareTo(left.key);
            }
        };

        public void setup(int k) {
            this.k = k;
            this.minHeap = new PriorityQueue<>(k, pairComparator);
        }

        public void reduce(String key, Iterator<Integer> values) {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            Pair newPair = new Pair(key , sum);
            if (minHeap.size() < k) {
                minHeap.add(newPair);
                return;
            }
            if (pairComparator.compare(newPair, minHeap.peek()) > 0) {
                minHeap.poll();
                minHeap.add(newPair);
            }
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            List<Pair> pairs = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                pairs.add(minHeap.poll());
            }
            int n = pairs.size();
            for (int i = n - 1; i >= 0; i--) {
                Pair pair = pairs.get(i);
                output.collect(pair.key, pair.value);
            }
        }
    }
}

class Pair {
    String key;
    int value;
    public Pair(String key, int value) {
        this.key = key;
        this.value = value;
    }
}

class Document {
    public int id;
    public String content;
}