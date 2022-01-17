package algorithm.level1MapReduce;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class InvertedIndexMapReduce {
    // Write your code here
    // Output the results into output buffer.
    // Ps. output.collect(String key, int value);
    public static class Map {
        public void map(String s, Document value,
                        OutputCollector<String, Integer> output) {
            int id = value.id;
            StringTokenizer tokenizer = new StringTokenizer(value.content);
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                output.collect(word, id);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, List<Integer>> output) {
            List<Integer> results = new ArrayList<>();
            int previous = -1;
            while(values.hasNext()) {
                int now = values.next();
                if (now != previous) {
                    results.add(now);
                }
                previous = now;
            }
            output.collect(key, results);
        }
    }
}
class Document {
      public int id;
      public String content;
}
