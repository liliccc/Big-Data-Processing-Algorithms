package algorithm.level4ExternalSorting;

import java.util.ArrayList;
import java.util.List;

public class MergeKSortedLists2 {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
     // by merge two by two - O(nlogk) from bottom to top
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        while (lists.size() > 1) {
            List<ListNode> newList = new ArrayList<>();
            for (int i = 0; i + 1 < lists.size(); i += 2) {
                ListNode newNode = merge(lists.get(i), lists.get(i + 1));
                newList.add(newNode);
            }
            if (lists.size() % 2 == 1) {
                newList.add(lists.get(lists.size() - 1));
            }
            lists = newList;
        }
        return lists.get(0);
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                // next node in a linkedlist
                a = a.next;
            }
            else {
                tail.next = b;
                b = b.next;
            }
            // tail = a || b
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