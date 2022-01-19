package algorithm.level3BloomFilter;

import java.util.ArrayList;
import java.util.List;

public class CountingBloomFilter {
    public int[] bits;
    public int k;
    public List<HashFunction2> hashFunction2s;
    /*
     * @param k: An integer
     */
     public CountingBloomFilter(int k) {
        this.k = k;
        hashFunction2s = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            hashFunction2s.add(new HashFunction2(100000 + i, 2 * i + 3));
        }
        bits = new int[100000 + k];
    }
    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        for (int i = 0; i < k ; i++) {
            int position = hashFunction2s.get(i).hash(word);
            bits[position] += 1;
        }
    }
    /*
     * @param word: A string
     * @return: nothing
     */
    public void remove(String word) {
        for (int i = 0; i < k; i++) {
            int position = hashFunction2s.get(i).hash(word);
            bits[position] -= 1;
        }
    }
    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {
        for (int i = 0; i < k; i++) {
            int position = hashFunction2s.get(i).hash(word);
            if (bits[position] <= 0) {
                return false;
            }
        }
        return true;
    }
}

class HashFunction2 {
    public int cap, seed;
    public HashFunction2(int cap, int seed) {
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
