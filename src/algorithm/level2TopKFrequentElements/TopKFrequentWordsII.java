package algorithm.level2TopKFrequentElements;

import java.util.*;

public class TopKFrequentWordsII {
    /*
     * @param k: An integer
     */
    // online problem, use heap
    private Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node left, Node right) {
            if (left.counter != right.counter) {
                // maxHeap
                return right.counter - left.counter;
            }
            // error before, c, b, a
            return left.word.compareTo(right.word);
        }
    };
    private Map<String, Node> map;
    Queue<Node> maxHeap;
    int k;
    public TopKFrequentWordsII(int k) {
        this.map = new HashMap<>();
        this.k = k;
        // error before
        this.maxHeap = new PriorityQueue<>(k == 0 ? 1 : k, comparator);
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        if (!map.containsKey(word)) {
            Node newNode = new Node(word, 1);
            map.put(word, newNode);
            maxHeap.offer(newNode);
        }
        else {
            Node node = map.get(word);
            maxHeap.remove(node);
            node.counter++;
            maxHeap.offer(node);
        }
    }

    /*
     * @return: the current top k frequent words.
     */
    public List<String> topk() {
        List<String> results = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        int index = 0;
        while (index < k && !maxHeap.isEmpty()) {
            index++;
            Node node = maxHeap.poll();
            stack.add(node);
            results.add(node.word);
        }
        while (!stack.isEmpty()) {
            maxHeap.offer(stack.pop());
        }
        return results;
    }
}

class Node {
    String word;
    int counter;

    public Node(String word, int counter) {
        this.word = word;
        this.counter = counter;
    }
}
