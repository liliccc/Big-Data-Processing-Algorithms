package algorithm.level2TopKFrequentElements;

public class KthLargestElement {
    /**
     * @param k: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    // use quick select- O(n)(reduced from O(nlogn) — quick sort)
    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return -1;
        }
        // the result is the same as from small to larger, get the (n - k)th number
        return quickSelect(nums.length - k, nums, 0, nums.length - 1);
    }

    private int quickSelect(int k, int[] nums,  int start, int end) {
        if (start >= end) {
            return nums[k];
        }
        int left = start, right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        // only select from the side where the k is in
        if (k <= right) {
            return quickSelect(k, nums, start, right);
        }
        if (k >= left) {
            return quickSelect(k, nums, left, end);
        }
        return nums[k];
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
