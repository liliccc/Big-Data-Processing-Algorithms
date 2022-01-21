package algorithm.level4ExternalSorting;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists1 {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    private Comparator<ListNode> comparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode left, ListNode right) {
            return left.val - right.val;
        }
    };
    // by using heap (the simplest one) - O(nlogk)
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        Queue<ListNode> minHeap = new PriorityQueue<>(lists.size(), comparator);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                minHeap.offer(lists.get(i));
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!minHeap.isEmpty()) {
            // O(logk)
            ListNode node = minHeap.poll();
            tail.next = node;
            tail = node;
            if (node.next != null) {
                // O(logk)
                minHeap.offer(node.next);
            }
        }
        return dummy.next;
    }
}

