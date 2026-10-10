# NeetCode 150 — Java Solutions

My solutions to the [NeetCode 150](https://neetcode.io/practice), written in Java and synced from [NeetCode.io](https://neetcode.io).

**43 problems solved** · 13 Easy · 25 Medium · 5 Hard

Most solutions open with a short comment block: the concept, the key insight, the pattern, the complexity and a mental model, so each file works as a revision note.

## Progress

| Topic | Solved | Of |
|---|---|---|
| [Arrays & Hashing](#arrays--hashing) | 9 | 9 |
| [Two Pointers](#two-pointers) | 5 | 5 |
| [Sliding Window](#sliding-window) | 6 | 6 |
| [Stack](#stack) | 6 | 6 |
| [Binary Search](#binary-search) | 7 | 7 |
| [Trees](#trees) | 10 | 15 |

Arrays & Hashing, Two Pointers, Sliding Window, Stack and Binary Search are complete, and Trees is in progress. Linked List, Backtracking (including Generate Parentheses) and the rest are still to come.

## Solutions

Complexity is for the latest submission. `n` is the input size unless noted; `h` is tree height.

### Arrays & Hashing

| # | Problem | Difficulty | Pattern | Time | Space |
|---|---|---|---|---|---|
| 1 | [Contains Duplicate](Data%20Structures%20%26%20Algorithms/duplicate-integer/submission-1.java) | Easy | HashSet | O(n) | O(n) |
| 2 | [Valid Anagram](Data%20Structures%20%26%20Algorithms/is-anagram/submission-1.java) | Easy | Frequency maps | O(n) | O(n) |
| 3 | [Two Sum](Data%20Structures%20%26%20Algorithms/two-integer-sum/submission-2.java) | Easy | HashMap complement lookup | O(n) | O(n) |
| 4 | [Group Anagrams](Data%20Structures%20%26%20Algorithms/anagram-groups/submission-3.java) | Medium | Frequency signature as key | O(m·n) | O(m·n) |
| 5 | [Top K Frequent Elements](Data%20Structures%20%26%20Algorithms/top-k-elements-in-list/submission-3.java) | Medium | Bucket sort by frequency | O(n) | O(n) |
| 6 | [Encode and Decode Strings](Data%20Structures%20%26%20Algorithms/string-encode-and-decode/submission-1.java) | Medium | Length-prefix encoding | O(n) | O(n) |
| 7 | [Product of Array Except Self](Data%20Structures%20%26%20Algorithms/products-of-array-discluding-self/submission-3.java) | Medium | Prefix + suffix products | O(n) | O(n) |
| 8 | [Valid Sudoku](Data%20Structures%20%26%20Algorithms/valid-sudoku/submission-1.java) | Medium | HashSet per row / column / box | O(1) | O(1) |
| 9 | [Longest Consecutive Sequence](Data%20Structures%20%26%20Algorithms/longest-consecutive-sequence/submission-2.java) | Medium | HashSet + sequence starts | O(n)* | O(n) |

### Two Pointers

| # | Problem | Difficulty | Pattern | Time | Space |
|---|---|---|---|---|---|
| 1 | [Valid Palindrome](Data%20Structures%20%26%20Algorithms/is-palindrome/submission-6.java) | Easy | Two pointers + filtering | O(n) | O(1) |
| 2 | [Two Sum II](Data%20Structures%20%26%20Algorithms/two-integer-sum-ii/submission-1.java) | Medium | Two pointers on sorted input | O(n) | O(1) |
| 3 | [3Sum](Data%20Structures%20%26%20Algorithms/three-integer-sum/submission-3.java) | Medium | Sort + two pointers | O(n²) | O(1) |
| 4 | [Container With Most Water](Data%20Structures%20%26%20Algorithms/max-water-container/submission-1.java) | Medium | Two pointers, move shorter side | O(n) | O(1) |
| 5 | [Trapping Rain Water](Data%20Structures%20%26%20Algorithms/trapping-rain-water/submission-1.java) | Hard | Prefix / suffix max | O(n) | O(n) |

### Sliding Window

| # | Problem | Difficulty | Pattern | Time | Space |
|---|---|---|---|---|---|
| 1 | [Best Time to Buy and Sell Stock](Data%20Structures%20%26%20Algorithms/buy-and-sell-crypto/submission-4.java) | Easy | Running minimum | O(n) | O(1) |
| 2 | [Longest Substring Without Repeating Characters](Data%20Structures%20%26%20Algorithms/longest-substring-without-duplicates/submission-1.java) | Medium | Window + HashSet | O(n) | O(n) |
| 3 | [Longest Repeating Character Replacement](Data%20Structures%20%26%20Algorithms/longest-repeating-substring-with-replacement/submission-1.java) | Medium | Window + max frequency | O(n) | O(1) |
| 4 | [Permutation in String](Data%20Structures%20%26%20Algorithms/permutation-string/submission-1.java) | Medium | Fixed window + frequency match | O(n·m) | O(1) |
| 5 | [Minimum Window Substring](Data%20Structures%20%26%20Algorithms/minimum-window-with-characters/submission-3.java) | Hard | Expand / shrink window | O(n) | O(1) |
| 6 | [Sliding Window Maximum](Data%20Structures%20%26%20Algorithms/sliding-window-maximum/submission-2.java) | Hard | Monotonic deque | O(n) | O(k) |

### Stack

| # | Problem | Difficulty | Pattern | Time | Space |
|---|---|---|---|---|---|
| 1 | [Valid Parentheses](Data%20Structures%20%26%20Algorithms/validate-parentheses/submission-1.java) | Easy | Stack matching | O(n) | O(n) |
| 2 | [Min Stack](Data%20Structures%20%26%20Algorithms/minimum-stack/submission-0.java) | Medium | Auxiliary min stack | O(1) / op | O(n) |
| 3 | [Evaluate Reverse Polish Notation](Data%20Structures%20%26%20Algorithms/evaluate-reverse-polish-notation/submission-1.java) | Medium | Operand stack | O(n) | O(n) |
| 4 | [Daily Temperatures](Data%20Structures%20%26%20Algorithms/daily-temperatures/submission-1.java) | Medium | Monotonic decreasing stack | O(n) | O(n) |
| 5 | [Car Fleet](Data%20Structures%20%26%20Algorithms/car-fleet/submission-1.java) | Medium | Sort + monotonic stack | O(n log n) | O(n) |
| 6 | [Largest Rectangle in Histogram](Data%20Structures%20%26%20Algorithms/largest-rectangle-in-histogram/submission-1.java) | Hard | Monotonic increasing stack | O(n) | O(n) |

### Binary Search

| # | Problem | Difficulty | Pattern | Time | Space |
|---|---|---|---|---|---|
| 1 | [Binary Search](Data%20Structures%20%26%20Algorithms/binary-search/submission-0.java) | Easy | Classic binary search | O(log n) | O(1) |
| 2 | [Search a 2D Matrix](Data%20Structures%20%26%20Algorithms/search-2d-matrix/submission-8.java) | Medium | Staircase search | O(m + n) | O(1) |
| 3 | [Koko Eating Bananas](Data%20Structures%20%26%20Algorithms/eating-bananas/submission-1.java) | Medium | Binary search on answer | O(n log max) | O(1) |
| 4 | [Find Minimum in Rotated Sorted Array](Data%20Structures%20%26%20Algorithms/find-minimum-in-rotated-sorted-array/submission-1.java) | Medium | Compare mid with end | O(log n) | O(1) |
| 5 | [Search in Rotated Sorted Array](Data%20Structures%20%26%20Algorithms/find-target-in-rotated-sorted-array/submission-5.java) | Medium | Identify the sorted half | O(log n) | O(1) |
| 6 | [Time Based Key-Value Store](Data%20Structures%20%26%20Algorithms/time-based-key-value-store/submission-5.java) | Medium | HashMap + TreeMap floorEntry | O(log n) / get | O(n) |
| 7 | [Median of Two Sorted Arrays](Data%20Structures%20%26%20Algorithms/median-of-two-sorted-arrays/submission-1.java) | Hard | Binary search on partition | O(log min(m,n)) | O(1) |

### Trees

| # | Problem | Difficulty | Pattern | Time | Space |
|---|---|---|---|---|---|
| 1 | [Invert Binary Tree](Data%20Structures%20%26%20Algorithms/invert-a-binary-tree/submission-1.java) | Easy | Recursive DFS, swap children | O(n) | O(h) |
| 2 | [Maximum Depth of Binary Tree](Data%20Structures%20%26%20Algorithms/depth-of-binary-tree/submission-1.java) | Easy | Recursive DFS | O(n) | O(h) |
| 3 | [Diameter of Binary Tree](Data%20Structures%20%26%20Algorithms/binary-tree-diameter/submission-1.java) | Easy | DFS returning height, track best path | O(n) | O(h) |
| 4 | [Balanced Binary Tree](Data%20Structures%20%26%20Algorithms/balanced-binary-tree/submission-4.java) | Easy | DFS height with early-exit sentinel | O(n) | O(h) |
| 5 | [Same Tree](Data%20Structures%20%26%20Algorithms/same-binary-tree/submission-1.java) | Easy | Recursive structural comparison | O(n) | O(h) |
| 6 | [Subtree of Another Tree](Data%20Structures%20%26%20Algorithms/subtree-of-a-binary-tree/submission-2.java) | Easy | DFS search + Same Tree check | O(n·m) | O(h) |
| 7 | [Lowest Common Ancestor of a BST](Data%20Structures%20%26%20Algorithms/lowest-common-ancestor-in-binary-search-tree/submission-1.java) | Medium | BST ordering, walk down | O(h) | O(h) |
| 8 | [Binary Tree Level Order Traversal](Data%20Structures%20%26%20Algorithms/level-order-traversal-of-binary-tree/submission-1.java) | Medium | BFS with queue | O(n) | O(n) |
| 9 | [Binary Tree Right Side View](Data%20Structures%20%26%20Algorithms/binary-tree-right-side-view/submission-3.java) | Medium | DFS, right child first, track depth | O(n) | O(h) |
| 10 | [Count Good Nodes in Binary Tree](Data%20Structures%20%26%20Algorithms/count-good-nodes-in-binary-tree/submission-1.java) | Medium | DFS carrying max so far | O(n) | O(h) |

\* Worst case is O(n²) if the input contains many duplicates of a sequence start. Iterating over the set instead of the array fixes this; it is on the revisit list below.

## To revisit

- **Search a 2D Matrix**: redo as a single binary search over the flattened matrix for O(log(m·n)).
- **Permutation in String**: replace the per-window frequency rebuild with a sliding window and match counter for O(n).
- **Top K Frequent Elements**: tighten the loop exit so ties at the k boundary cannot overflow the result array.
- **Longest Consecutive Sequence**: iterate over the set to remove the duplicate-input worst case.
- **Koko Eating Bananas**: accumulate hours in a `long` to avoid overflow on large piles.
- **Product of Array Except Self / Trapping Rain Water**: reduce to O(1) extra space.
- **Lowest Common Ancestor of a BST**: an iterative loop gets the space down from O(h) to O(1).
- **Subtree of Another Tree**: O(n·m) is fine for the constraints; serializing both trees or hashing subtrees gets it to O(n + m).

## Repository structure

```
Data Structures & Algorithms/
  <problem-id>/
    submission-0.java
    submission-1.java   # later attempts; the highest number is the latest
```

Solutions sync here automatically through NeetCode's GitHub integration.
