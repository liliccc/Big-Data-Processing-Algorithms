package algorithm.level4ExternalSorting;

import java.util.List;

public class MergeKSortedLists3 {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    // important! divide and conquer O(nlogk), the worst case  - O(nlogn) from top to bottom
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        return divide(lists, 0, lists.size() - 1);
    }
    // similar to merge sort
    private ListNode divide(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        int mid = start + (end -start) / 2;
        ListNode left = divide(lists, start, mid);
        ListNode right = divide(lists, mid + 1, end);
        return merge(left, right);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            }
            else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        if (a != null) {
            tail.next = a;
        }
        else {
            tail.next = b;
        }
        return dummy.next;
    }
}