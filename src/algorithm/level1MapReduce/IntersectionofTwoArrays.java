package algorithm.level1MapReduce;

import java.util.HashSet;
import java.util.Set;

public class IntersectionofTwoArrays {
    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        Set<Integer> nums1Set = new HashSet<>();
        for (int num : nums1) {
            nums1Set.add(num);
        }
        Set<Integer> resultsSet = new HashSet<>();
        for (int num : nums2) {
            // O(1), hashset is backed by hashmap, with the keys of objects
            if (nums1Set.contains(num) && !resultsSet.contains(num)) {
                resultsSet.add(num);
            }
        }
        int[] results = new int[resultsSet.size()];
        int index = 0;
        for (int num : resultsSet) {
            results[index++] = num;
        }
        return results;
    }
}
