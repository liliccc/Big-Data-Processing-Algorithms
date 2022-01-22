package algorithm.level4ExternalSorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeKSortedIntervalLists {
    /**
     * @param intervals: the given k sorted interval lists
     * @return: the new sorted interval list
     */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        List<Interval> sortedArray = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return sortedArray;
        }
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < intervals.get(i).size(); j++) {
                sortedArray.add(intervals.get(i).get(j));
            }
        }
        sortedArray.sort((Interval a, Interval b) -> (a.start - b.start));
        List<Interval> ans = new ArrayList<>();
        ans.add(sortedArray.get(0));
        for (int i = 1; i < sortedArray.size(); i++) {
            int lastNodeIndex = ans.size() - 1;
            // (1,2), (1,3) => 2 >= 1 => (1,3) || (1,2), (3,4) => 2 < 3 => ( 1, 2) , (3, 4)
            //error before :ans.get(lastNodeIndex).end > sortedArray.get(i).start
            if (ans.get(lastNodeIndex).end >= sortedArray.get(i).start) {
                ans.get(lastNodeIndex).end = Math.max(ans.get(lastNodeIndex).end, sortedArray.get(i).end);
            }
            else {
                ans.add(sortedArray.get(i));
            }
        }
        return ans;
    }
}

class Interval {
    int start, end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
