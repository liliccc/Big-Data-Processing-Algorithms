package algorithm.level2TopKFrequentElements;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPoints {
    /**
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    // use maxHeap to find the k smallest numbers
    public Point[] kClosest(Point[] points, Point origin, int k) {
        if (points == null || points.length == 0 || k == 0) {
            return new Point[0];
        }
        Queue<Point> maxHeap = new PriorityQueue<>(
        Comparator
        .comparing((Point p) -> getDistance(p, origin))
                .thenComparingInt(p -> p.x)
                .thenComparingInt(p -> p.y)
                .reversed() // reverse the minHeap to maxHeap
        );
        for (Point point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        Point[] topK = new Point[k];
        for (int i = k - 1; i >=0; i--) {
            topK[i] = maxHeap.poll();
        }
        return topK;
    }

    private int getDistance(Point a, Point b) {
        int dx = a.x - b.x;
        int dy = a.y - b.y;
        return dx * dx + dy * dy;
    }
}

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}
