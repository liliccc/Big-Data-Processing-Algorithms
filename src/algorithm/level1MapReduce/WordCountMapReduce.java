package algorithm.level1MapReduce;

import java.util.Iterator;
import java.util.StringTokenizer;

public class WordCountMapReduce {
    // Write your code here
    // Output the results into output buffer.
    // Ps. output.collect(String key, int value);
    public static class Map {
        public void map(String key, String value, OutputCollector<String, Integer> output) {
            StringTokenizer tokenizer = new StringTokenizer(value);
            while (tokenizer.hasMoreTokens()) {
                String outputKey = tokenizer.nextToken();
                output.collect(outputKey, 1);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            output.collect(key, sum);
        }
    }
}

class OutputCollector<K, V> {
    public void collect(K key, V value) {}
    // Adds a key/value pair to the output buffer
 }
