package algorithm.level3BloomFilter;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class StandardBloomFilter {

    public BitSet bitSet;
    public int k;
    public List<HashFunction> hashFunctions;

    /*
     * @param k: An integer
     */
    public StandardBloomFilter(int k) {
        this.k = k;
        hashFunctions = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            hashFunctions.add(new HashFunction(100000 + i, 2 * i + 3));
        }
        bitSet = new BitSet(100000 + k);
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        for (int i = 0; i < k; i++) {
            int position = hashFunctions.get(i).hash(word);
            bitSet.set(position);
        }
    }

    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {
        for (int i = 0; i < k; i++) {
            int position = hashFunctions.get(i).hash(word);
            if (!bitSet.get(position)) {
                return false;
            }
        }
        return true;
    }
}

class HashFunction {
    int cap, seed;

    public HashFunction(int cap, int seed) {
        this.cap = cap;
        this.seed = seed;
    }

    public int hash(String value) {
        int result = 0;
        int n = value.length();
        for (int i = 0; i < n; i++) {
            result += seed * result + value.charAt(i);
            result %= cap;
        }
        return result;
    }
}
