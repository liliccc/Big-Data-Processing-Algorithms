package algorithm.level1MapReduce;

import java.util.*;

public class AnagramMapReduce {
    // Write your code here
    // Output the results into output buffer.
    // Ps. output.collect(String key, String value);
    public static class Map {
        public void map(String key, String value,
                        OutputCollector<String, String> output) {
            StringTokenizer tokenizer = new StringTokenizer(value);
            // key should be the sorted one
            while(tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                char[] array = word.toCharArray();
                Arrays.sort(array);
                String sorted = new String(array);
                output.collect(sorted, word);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values,
                           OutputCollector<String, List<String>> output) {
            List<String> results = new ArrayList<>();
            while (values.hasNext()) {
                results.add(values.next());
            }
            output.collect(key, results);
        }
    }
}
