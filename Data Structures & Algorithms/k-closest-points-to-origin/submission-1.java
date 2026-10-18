/*
# K Closest Points to Origin — Max Heap of Size K

## Problem
Given an array of points on a 2D plane, return the `k` points closest to the origin `(0, 0)`.

The Euclidean distance of a point `(x, y)` from the origin is:

\[
d = \sqrt{x^2+y^2}
\]

## Approach: Max Heap of Size K

Maintain a Max Heap containing the `k` closest points encountered so far.

1. Calculate the distance of each point from the origin.
2. Store the distance and original point together in a custom `PointInfo` object.
3. Insert the object into a Max Heap ordered by distance in descending order.
4. If the heap size exceeds `k`, remove the root using `poll()`. The root is the farthest point among the retained candidates.
5. After processing every point, the heap contains the `k` closest points. Extract their original coordinate arrays to build the answer.

## Why a Max Heap?

We want to retain the **k smallest distances**.

A Max Heap makes the largest distance among the retained candidates accessible at the root. Whenever a closer point arrives and the heap exceeds size `k`, we can discard the farthest retained point in `O(log k)` time.

A Min Heap would expose the closest point instead, making it less convenient to remove the farthest candidate when maintaining the `k` closest points.

## Why Create a Custom Class?

Each heap entry needs two pieces of information:

- `distance`: used by the comparator to determine heap ordering.
- `point`: stores the original coordinates so the point can be returned later.

Keeping both values together means a separate `HashMap` is unnecessary.

## Java Implementation

```java
*/
class Solution {
    class PointInfo {
        double distance;
        int[] point;

        PointInfo(double distance, int[] point) {
            this.distance = distance;
            this.point = point;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<PointInfo> maxHeap =
            new PriorityQueue<>(
                (a, b) -> Double.compare(b.distance, a.distance)
            );

        for (int[] point : points) {
            PointInfo pointInfo =
                new PointInfo(getDistance(point), point);

            maxHeap.offer(pointInfo);

            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] ans = new int[k][2];
        int i = 0;

        while (maxHeap.size() != 0) {
            ans[i++] = maxHeap.poll().point;
        }

        return ans;
    }

    public double getDistance(int[] point) {
        return Math.sqrt(
            (point[0] * point[0]) + (point[1] * point[1])
        );
    }
}
/*
```

## Optimization

Square roots are unnecessary when comparing distances because square root preserves the ordering of nonnegative values.

Instead of comparing:

\[
\sqrt{x^2+y^2}
\]

compare squared distances:

\[
x^2+y^2
\]

This avoids unnecessary square-root calculations. For the given coordinate constraints, an integer type is sufficient for squared distances.

## Complexity Analysis

Let `n` be the number of points.

- **Time:** `O(n log k)` — each insertion and possible removal costs `O(log k)`.
- **Auxiliary space:** `O(k)` — the heap retains at most `k` entries.
- **Output space:** `O(k)` — the answer contains `k` points.

## Key Takeaways

- Use a Max Heap of size `k` when retaining the `k` smallest values.
- The root is the worst candidate among the retained elements and can be removed efficiently.
- A custom class can bundle the priority value with the original data.
- Choose a comparator that matches the element you need to discard.
- When only comparing Euclidean distances, squared distances preserve the same ordering.

**Pattern to remember:** For Top K problems, identify the candidates you want to retain, then put the *worst retained candidate* at the heap root so you can efficiently discard it when a better candidate arrives.
*/