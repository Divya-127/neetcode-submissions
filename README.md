# NeetCode 150 — Java Solutions

My solutions to the [NeetCode 150](https://neetcode.io/practice), written in Java and synced from [NeetCode.io](https://neetcode.io).

**63 problems solved** · 18 Easy · 37 Medium · 8 Hard

Most solutions open with a short comment block: the concept, the key insight, the pattern, the complexity and a mental model, so each file works as a revision note.

## Progress

| Topic | Solved | Of |
|---|---|---|
| [Arrays & Hashing](#arrays--hashing) | 9 | 9 |
| [Two Pointers](#two-pointers) | 5 | 5 |
| [Sliding Window](#sliding-window) | 6 | 6 |
| [Stack](#stack) | 6 | 6 |
| [Binary Search](#binary-search) | 7 | 7 |
| [Linked List](#linked-list) | 8 | 11 |
| [Trees](#trees) | 15 | 15 |
| [Heap / Priority Queue](#heap--priority-queue) | 7 | 7 |

Arrays & Hashing, Two Pointers, Sliding Window, Stack, Binary Search, Trees and Heap / Priority Queue are complete. Linked List is in progress (LRU Cache, Merge k Sorted Lists and Reverse Nodes in k-Group remain). Backtracking (including Generate Parentheses) and the rest are still to come.

## Pattern index

Revise by pattern instead of by problem number; the middle column is the cue that should make you reach for that pattern. A problem can appear under more than one pattern.

| Pattern | Recognize it by | Problems |
|---|---|---|
| Hash map / set lookup | Need O(1) membership, a "seen before" check, or a complement lookup | [Contains Duplicate](Data%20Structures%20%26%20Algorithms/duplicate-integer/submission-1.java) · [Two Sum](Data%20Structures%20%26%20Algorithms/two-integer-sum/submission-2.java) · [Valid Sudoku](Data%20Structures%20%26%20Algorithms/valid-sudoku/submission-1.java) · [Longest Consecutive Sequence](Data%20Structures%20%26%20Algorithms/longest-consecutive-sequence/submission-3.java) |
| Frequency counting / bucketing | Compare or group by letter/value counts, or "most frequent" | [Valid Anagram](Data%20Structures%20%26%20Algorithms/is-anagram/submission-1.java) · [Group Anagrams](Data%20Structures%20%26%20Algorithms/anagram-groups/submission-3.java) · [Top K Frequent Elements](Data%20Structures%20%26%20Algorithms/top-k-elements-in-list/submission-4.java) |
| Prefix / suffix | The answer at i depends on everything to its left and right | [Product of Array Except Self](Data%20Structures%20%26%20Algorithms/products-of-array-discluding-self/submission-4.java) · [Trapping Rain Water](Data%20Structures%20%26%20Algorithms/trapping-rain-water/submission-2.java) |
| Two pointers | Sorted input or a symmetric condition; a pair or triple hitting a target | [Valid Palindrome](Data%20Structures%20%26%20Algorithms/is-palindrome/submission-6.java) · [Two Sum II](Data%20Structures%20%26%20Algorithms/two-integer-sum-ii/submission-1.java) · [3Sum](Data%20Structures%20%26%20Algorithms/three-integer-sum/submission-3.java) · [Container With Most Water](Data%20Structures%20%26%20Algorithms/max-water-container/submission-1.java) · [Trapping Rain Water](Data%20Structures%20%26%20Algorithms/trapping-rain-water/submission-2.java) |
| Sliding window | Contiguous subarray or substring with a constraint; "longest" or "shortest" window | [Best Time to Buy and Sell Stock](Data%20Structures%20%26%20Algorithms/buy-and-sell-crypto/submission-4.java) · [Longest Substring Without Repeating Characters](Data%20Structures%20%26%20Algorithms/longest-substring-without-duplicates/submission-1.java) · [Longest Repeating Character Replacement](Data%20Structures%20%26%20Algorithms/longest-repeating-substring-with-replacement/submission-1.java) · [Permutation in String](Data%20Structures%20%26%20Algorithms/permutation-string/submission-2.java) · [Minimum Window Substring](Data%20Structures%20%26%20Algorithms/minimum-window-with-characters/submission-3.java) · [Sliding Window Maximum](Data%20Structures%20%26%20Algorithms/sliding-window-maximum/submission-2.java) |
| Monotonic stack / deque | "Next greater/smaller" element, or the max inside a moving window | [Daily Temperatures](Data%20Structures%20%26%20Algorithms/daily-temperatures/submission-1.java) · [Car Fleet](Data%20Structures%20%26%20Algorithms/car-fleet/submission-1.java) · [Largest Rectangle in Histogram](Data%20Structures%20%26%20Algorithms/largest-rectangle-in-histogram/submission-1.java) · [Sliding Window Maximum](Data%20Structures%20%26%20Algorithms/sliding-window-maximum/submission-2.java) |
| Stack for matching / evaluation | Nested structure, last-opened-first-closed, or postfix expressions | [Valid Parentheses](Data%20Structures%20%26%20Algorithms/validate-parentheses/submission-1.java) · [Min Stack](Data%20Structures%20%26%20Algorithms/minimum-stack/submission-0.java) · [Evaluate Reverse Polish Notation](Data%20Structures%20%26%20Algorithms/evaluate-reverse-polish-notation/submission-1.java) |
| Binary search on an index | Sorted (or rotated sorted) data with an O(log n) requirement | [Binary Search](Data%20Structures%20%26%20Algorithms/binary-search/submission-0.java) · [Search a 2D Matrix](Data%20Structures%20%26%20Algorithms/search-2d-matrix/submission-9.java) · [Find Minimum in Rotated Sorted Array](Data%20Structures%20%26%20Algorithms/find-minimum-in-rotated-sorted-array/submission-1.java) · [Search in Rotated Sorted Array](Data%20Structures%20%26%20Algorithms/find-target-in-rotated-sorted-array/submission-5.java) |
| Binary search on the answer or a partition | "Smallest rate or size that works" with monotonic feasibility, or splitting sorted arrays | [Koko Eating Bananas](Data%20Structures%20%26%20Algorithms/eating-bananas/submission-2.java) · [Median of Two Sorted Arrays](Data%20Structures%20%26%20Algorithms/median-of-two-sorted-arrays/submission-1.java) |
| Binary search inside a data structure | Sorted history per key; "latest at or before" a value | [Time Based Key-Value Store](Data%20Structures%20%26%20Algorithms/time-based-key-value-store/submission-5.java) |
| Tree DFS: return information up (post-order) | A node's answer combines its children's results (height, balance, best path) | [Maximum Depth of Binary Tree](Data%20Structures%20%26%20Algorithms/depth-of-binary-tree/submission-1.java) · [Diameter of Binary Tree](Data%20Structures%20%26%20Algorithms/binary-tree-diameter/submission-1.java) · [Balanced Binary Tree](Data%20Structures%20%26%20Algorithms/balanced-binary-tree/submission-4.java) · [Binary Tree Maximum Path Sum](Data%20Structures%20%26%20Algorithms/binary-tree-maximum-path-sum/submission-1.java) |
| Tree DFS: carry state down (pre-order) | A node's answer depends on its ancestors (max so far, depth) | [Count Good Nodes in Binary Tree](Data%20Structures%20%26%20Algorithms/count-good-nodes-in-binary-tree/submission-4.java) · [Binary Tree Right Side View](Data%20Structures%20%26%20Algorithms/binary-tree-right-side-view/submission-3.java) |
| Two-tree recursion | Compare two trees node by node | [Same Tree](Data%20Structures%20%26%20Algorithms/same-binary-tree/submission-1.java) · [Subtree of Another Tree](Data%20Structures%20%26%20Algorithms/subtree-of-a-binary-tree/submission-2.java) |
| BST property | Left < node < right lets you discard half, or an in-order walk is sorted | [Lowest Common Ancestor of a BST](Data%20Structures%20%26%20Algorithms/lowest-common-ancestor-in-binary-search-tree/submission-2.java) · [Validate Binary Search Tree](Data%20Structures%20%26%20Algorithms/valid-binary-search-tree/submission-2.java) · [Kth Smallest Element in a BST](Data%20Structures%20%26%20Algorithms/kth-smallest-integer-in-bst/submission-7.java) |
| Tree BFS (queue, level by level) | Anything asked per level, or the nearest node in a tree | [Binary Tree Level Order Traversal](Data%20Structures%20%26%20Algorithms/level-order-traversal-of-binary-tree/submission-1.java) |
| Recursive restructuring | Apply the same local change to every node (swap children) | [Invert Binary Tree](Data%20Structures%20%26%20Algorithms/invert-a-binary-tree/submission-1.java) |
| Tree construction / serialization (preorder recursion) | Rebuild or encode a tree from traversals; preorder consumes one value per node | [Construct Binary Tree from Preorder and Inorder](Data%20Structures%20%26%20Algorithms/binary-tree-from-preorder-and-inorder-traversal/submission-1.java) · [Serialize and Deserialize Binary Tree](Data%20Structures%20%26%20Algorithms/serialize-and-deserialize-binary-tree/submission-1.java) |
| Heap of size k | "Kth largest" or "top k" over a stream or large input | [Kth Largest Element in a Stream](Data%20Structures%20%26%20Algorithms/kth-largest-integer-in-a-stream/submission-1.java) · [Kth Largest Element in an Array](Data%20Structures%20%26%20Algorithms/kth-largest-element-in-an-array/submission-1.java) · [K Closest Points to Origin](Data%20Structures%20%26%20Algorithms/k-closest-points-to-origin/submission-1.java) |
| Heap-driven greedy simulation | Repeatedly take the best item, with a rule for when it can be reused | [Last Stone Weight](Data%20Structures%20%26%20Algorithms/last-stone-weight/submission-1.java) · [Task Scheduler](Data%20Structures%20%26%20Algorithms/task-scheduling/submission-2.java) |
| Linked list: reverse in place | Reverse all or part of a list, or reorder from the back | [Reverse Linked List](Data%20Structures%20%26%20Algorithms/reverse-a-linked-list/submission-3.java) · [Reorder List](Data%20Structures%20%26%20Algorithms/reorder-linked-list/submission-1.java) |
| Fast / slow pointers | Middle, cycle, nth from end, or a duplicate hidden in an index array | [Linked List Cycle](Data%20Structures%20%26%20Algorithms/linked-list-cycle-detection/submission-1.java) · [Reorder List](Data%20Structures%20%26%20Algorithms/reorder-linked-list/submission-1.java) · [Remove Nth Node From End of List](Data%20Structures%20%26%20Algorithms/remove-node-from-end-of-linked-list/submission-2.java) · [Find the Duplicate Number](Data%20Structures%20%26%20Algorithms/find-duplicate-integer/submission-4.java) |
| Dummy head + splice | Building or editing a list where the head might change | [Merge Two Sorted Lists](Data%20Structures%20%26%20Algorithms/merge-two-sorted-linked-lists/submission-2.java) · [Add Two Numbers](Data%20Structures%20%26%20Algorithms/add-two-numbers/submission-1.java) · [Remove Nth Node From End of List](Data%20Structures%20%26%20Algorithms/remove-node-from-end-of-linked-list/submission-2.java) |
| HashMap from original to copy | Deep copy of nodes that carry extra pointers | [Copy List with Random Pointer](Data%20Structures%20%26%20Algorithms/copy-linked-list-with-random-pointer/submission-1.java) |
| Two heaps | Running median, or splitting data into a small half and a large half | [Find Median from Data Stream](Data%20Structures%20%26%20Algorithms/find-median-in-a-data-stream/submission-1.java) |
| k-way merge with a heap | Merge several sorted streams and take only the top few | [Design Twitter](Data%20Structures%20%26%20Algorithms/design-twitter-feed/submission-1.java) |

## Solutions

Complexity is for the latest submission. `n` is the input size unless noted; `h` is tree height.

### Arrays & Hashing

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Contains Duplicate](https://neetcode.io/problems/duplicate-integer/question) | Easy | Return true if any value appears more than once. | HashSet | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/duplicate-integer/submission-1.java) |
| [Valid Anagram](https://neetcode.io/problems/is-anagram/question) | Easy | Check whether two strings use exactly the same letters with the same counts. | Frequency maps | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/is-anagram/submission-1.java) |
| [Two Sum](https://neetcode.io/problems/two-integer-sum/question) | Easy | Find the two indices whose values add up to the target. | HashMap complement lookup | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/two-integer-sum/submission-2.java) |
| [Group Anagrams](https://neetcode.io/problems/anagram-groups/question) | Medium | Group strings that are anagrams of each other. | Frequency signature as key | O(m·n) | O(m·n) | [Java](Data%20Structures%20%26%20Algorithms/anagram-groups/submission-3.java) |
| [Top K Frequent Elements](https://neetcode.io/problems/top-k-elements-in-list/question) | Medium | Return the k most frequent values in an array. | Bucket sort by frequency | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/top-k-elements-in-list/submission-4.java) |
| [Encode and Decode Strings](https://neetcode.io/problems/string-encode-and-decode/question) | Medium | Design encode/decode that packs a list of strings into one string and back. | Length-prefix encoding | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/string-encode-and-decode/submission-1.java) |
| [Product of Array Except Self](https://neetcode.io/problems/products-of-array-discluding-self/question) | Medium | For each index, the product of every other element, without division. | Prefix products in output + running suffix | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/products-of-array-discluding-self/submission-4.java) |
| [Valid Sudoku](https://neetcode.io/problems/valid-sudoku/question) | Medium | Check a partly filled 9×9 board has no repeats in any row, column or 3×3 box. | HashSet per row / column / box | O(1) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/valid-sudoku/submission-1.java) |
| [Longest Consecutive Sequence](https://neetcode.io/problems/longest-consecutive-sequence/question) | Medium | Length of the longest run of consecutive integers, in O(n). | HashSet + sequence starts | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/longest-consecutive-sequence/submission-3.java) |

### Two Pointers

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Valid Palindrome](https://neetcode.io/problems/is-palindrome/question) | Easy | Check a string reads the same both ways, ignoring case and non-alphanumerics. | Two pointers + filtering | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/is-palindrome/submission-6.java) |
| [Two Sum II](https://neetcode.io/problems/two-integer-sum-ii/question) | Medium | Two Sum on a sorted array, using 1-based indices and O(1) space. | Two pointers on sorted input | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/two-integer-sum-ii/submission-1.java) |
| [3Sum](https://neetcode.io/problems/three-integer-sum/question) | Medium | Find all unique triplets that sum to zero. | Sort + two pointers | O(n²) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/three-integer-sum/submission-3.java) |
| [Container With Most Water](https://neetcode.io/problems/max-water-container/question) | Medium | Pick two bars that hold the most water between them. | Two pointers, move shorter side | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/max-water-container/submission-1.java) |
| [Trapping Rain Water](https://neetcode.io/problems/trapping-rain-water/question) | Hard | Total water trapped between bars after rain. | Two pointers + running maxes | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/trapping-rain-water/submission-2.java) |

### Sliding Window

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Best Time to Buy and Sell Stock](https://neetcode.io/problems/buy-and-sell-crypto/question) | Easy | Maximum profit from one buy followed by a later sell. | Running minimum | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/buy-and-sell-crypto/submission-4.java) |
| [Longest Substring Without Repeating Characters](https://neetcode.io/problems/longest-substring-without-duplicates/question) | Medium | Length of the longest substring with no repeated characters. | Window + HashSet | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/longest-substring-without-duplicates/submission-1.java) |
| [Longest Repeating Character Replacement](https://neetcode.io/problems/longest-repeating-substring-with-replacement/question) | Medium | Longest substring that can become one repeated letter with at most k replacements. | Window + max frequency | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/longest-repeating-substring-with-replacement/submission-1.java) |
| [Permutation in String](https://neetcode.io/problems/permutation-string/question) | Medium | Does s2 contain any permutation of s1 as a substring? | Fixed window + match counter | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/permutation-string/submission-2.java) |
| [Minimum Window Substring](https://neetcode.io/problems/minimum-window-with-characters/question) | Hard | Shortest substring of s that contains every character of t. | Expand / shrink window | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/minimum-window-with-characters/submission-3.java) |
| [Sliding Window Maximum](https://neetcode.io/problems/sliding-window-maximum/question) | Hard | Maximum of each length-k window as it slides across the array. | Monotonic deque | O(n) | O(k) | [Java](Data%20Structures%20%26%20Algorithms/sliding-window-maximum/submission-2.java) |

### Stack

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Valid Parentheses](https://neetcode.io/problems/validate-parentheses/question) | Easy | Check three kinds of brackets are closed correctly and in order. | Stack matching | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/validate-parentheses/submission-1.java) |
| [Min Stack](https://neetcode.io/problems/minimum-stack/question) | Medium | Stack with push, pop, top and get-min all in O(1). | Auxiliary min stack | O(1) / op | O(n) | [Java](Data%20Structures%20%26%20Algorithms/minimum-stack/submission-0.java) |
| [Evaluate Reverse Polish Notation](https://neetcode.io/problems/evaluate-reverse-polish-notation/question) | Medium | Evaluate an arithmetic expression written in postfix form. | Operand stack | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/evaluate-reverse-polish-notation/submission-1.java) |
| [Daily Temperatures](https://neetcode.io/problems/daily-temperatures/question) | Medium | For each day, how many days until a warmer one. | Monotonic decreasing stack | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/daily-temperatures/submission-1.java) |
| [Car Fleet](https://neetcode.io/problems/car-fleet/question) | Medium | Count the fleets that reach the target given positions and speeds. | Sort + monotonic stack | O(n log n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/car-fleet/submission-1.java) |
| [Largest Rectangle in Histogram](https://neetcode.io/problems/largest-rectangle-in-histogram/question) | Hard | Area of the largest rectangle that fits inside a histogram. | Monotonic increasing stack | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/largest-rectangle-in-histogram/submission-1.java) |

### Binary Search

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Binary Search](https://neetcode.io/problems/binary-search/question) | Easy | Find a target in a sorted array, or report it is missing. | Classic binary search | O(log n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/binary-search/submission-0.java) |
| [Search a 2D Matrix](https://neetcode.io/problems/search-2d-matrix/question) | Medium | Search a matrix whose rows are sorted and each row starts after the previous one ends. | Binary search over flattened index | O(log(m·n)) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/search-2d-matrix/submission-9.java) |
| [Koko Eating Bananas](https://neetcode.io/problems/eating-bananas/question) | Medium | Smallest eating speed that finishes all piles within h hours. | Binary search on answer | O(n log max) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/eating-bananas/submission-2.java) |
| [Find Minimum in Rotated Sorted Array](https://neetcode.io/problems/find-minimum-in-rotated-sorted-array/question) | Medium | Find the minimum of a sorted array that has been rotated. | Compare mid with end | O(log n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/find-minimum-in-rotated-sorted-array/submission-1.java) |
| [Search in Rotated Sorted Array](https://neetcode.io/problems/find-target-in-rotated-sorted-array/question) | Medium | Find a target's index in a rotated sorted array. | Identify the sorted half | O(log n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/find-target-in-rotated-sorted-array/submission-5.java) |
| [Time Based Key-Value Store](https://neetcode.io/problems/time-based-key-value-store/question) | Medium | Store values by timestamp and read the latest one at or before a given time. | HashMap + TreeMap floorEntry | O(log n) / get | O(n) | [Java](Data%20Structures%20%26%20Algorithms/time-based-key-value-store/submission-5.java) |
| [Median of Two Sorted Arrays](https://neetcode.io/problems/median-of-two-sorted-arrays/question) | Hard | Median of two sorted arrays in O(log(m + n)) time. | Binary search on partition | O(log min(m,n)) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/median-of-two-sorted-arrays/submission-1.java) |

### Linked List

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Reverse Linked List](https://neetcode.io/problems/reverse-a-linked-list/question) | Easy | Reverse a singly linked list. | Iterative pointer reversal | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/reverse-a-linked-list/submission-3.java) |
| [Merge Two Sorted Lists](https://neetcode.io/problems/merge-two-sorted-linked-lists/question) | Easy | Merge two sorted lists into one sorted list. | Dummy head + tail splice | O(n + m) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/merge-two-sorted-linked-lists/submission-2.java) |
| [Linked List Cycle](https://neetcode.io/problems/linked-list-cycle-detection/question) | Easy | Detect whether a linked list contains a cycle. | Fast / slow pointers | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/linked-list-cycle-detection/submission-1.java) |
| [Reorder List](https://neetcode.io/problems/reorder-linked-list/question) | Medium | Reorder the list as first, last, second, second-last, and so on. | Find middle, reverse second half, interleave | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/reorder-linked-list/submission-1.java) |
| [Remove Nth Node From End of List](https://neetcode.io/problems/remove-node-from-end-of-linked-list/question) | Medium | Remove the nth node from the end in a single pass. | Dummy head + gap between two pointers | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/remove-node-from-end-of-linked-list/submission-2.java) |
| [Copy List with Random Pointer](https://neetcode.io/problems/copy-linked-list-with-random-pointer/question) | Medium | Deep copy a list whose nodes also carry a random pointer. | HashMap original → copy | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/copy-linked-list-with-random-pointer/submission-1.java) |
| [Add Two Numbers](https://neetcode.io/problems/add-two-numbers/question) | Medium | Add two numbers stored as digit lists in reverse order. | Digit-wise sum with carry | O(max(n, m)) | O(1) aux | [Java](Data%20Structures%20%26%20Algorithms/add-two-numbers/submission-1.java) |
| [Find the Duplicate Number](https://neetcode.io/problems/find-duplicate-integer/question) | Medium | Find the repeated value among n + 1 numbers in 1..n without modifying the array. | Floyd's cycle detection on indices | O(n) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/find-duplicate-integer/submission-4.java) |

### Trees

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Invert Binary Tree](https://neetcode.io/problems/invert-a-binary-tree/question) | Easy | Mirror a binary tree by swapping every node's children. | Recursive DFS, swap children | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/invert-a-binary-tree/submission-1.java) |
| [Maximum Depth of Binary Tree](https://neetcode.io/problems/depth-of-binary-tree/question) | Easy | Find the maximum depth of a binary tree. | Recursive DFS | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/depth-of-binary-tree/submission-1.java) |
| [Diameter of Binary Tree](https://neetcode.io/problems/binary-tree-diameter/question) | Easy | Length of the longest path between any two nodes. | DFS returning height, track best path | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/binary-tree-diameter/submission-1.java) |
| [Balanced Binary Tree](https://neetcode.io/problems/balanced-binary-tree/question) | Easy | Check that every node's two subtree heights differ by at most one. | DFS height with early-exit sentinel | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/balanced-binary-tree/submission-4.java) |
| [Same Tree](https://neetcode.io/problems/same-binary-tree/question) | Easy | Check two trees have identical structure and values. | Recursive structural comparison | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/same-binary-tree/submission-1.java) |
| [Subtree of Another Tree](https://neetcode.io/problems/subtree-of-a-binary-tree/question) | Easy | Check whether one tree appears as a subtree of another. | DFS search + Same Tree check | O(n·m) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/subtree-of-a-binary-tree/submission-2.java) |
| [Lowest Common Ancestor of a BST](https://neetcode.io/problems/lowest-common-ancestor-in-binary-search-tree/question) | Medium | Find the lowest node that has both given nodes as descendants, in a BST. | BST ordering, iterative walk down | O(h) | O(1) | [Java](Data%20Structures%20%26%20Algorithms/lowest-common-ancestor-in-binary-search-tree/submission-2.java) |
| [Binary Tree Level Order Traversal](https://neetcode.io/problems/level-order-traversal-of-binary-tree/question) | Medium | Return node values level by level. | BFS with queue | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/level-order-traversal-of-binary-tree/submission-1.java) |
| [Binary Tree Right Side View](https://neetcode.io/problems/binary-tree-right-side-view/question) | Medium | Values visible when the tree is viewed from the right. | DFS, right child first, track depth | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/binary-tree-right-side-view/submission-3.java) |
| [Count Good Nodes in Binary Tree](https://neetcode.io/problems/count-good-nodes-in-binary-tree/question) | Medium | Count nodes that are not smaller than any ancestor on their path. | DFS carrying max so far | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/count-good-nodes-in-binary-tree/submission-4.java) |
| [Validate Binary Search Tree](https://neetcode.io/problems/valid-binary-search-tree/question) | Medium | Check a tree satisfies the BST ordering rule. | DFS with (lower, upper) bounds | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/valid-binary-search-tree/submission-2.java) |
| [Kth Smallest Element in a BST](https://neetcode.io/problems/kth-smallest-integer-in-bst/question) | Medium | Find the kth smallest value in a BST. | In-order DFS, stop at k | O(h + k) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/kth-smallest-integer-in-bst/submission-7.java) |
| [Construct Binary Tree from Preorder and Inorder](https://neetcode.io/problems/binary-tree-from-preorder-and-inorder-traversal/question) | Medium | Rebuild a tree from its preorder and inorder traversals. | Preorder index + inorder HashMap | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/binary-tree-from-preorder-and-inorder-traversal/submission-1.java) |
| [Binary Tree Maximum Path Sum](https://neetcode.io/problems/binary-tree-maximum-path-sum/question) | Hard | Maximum sum over any path of nodes in the tree. | DFS returning best one-branch gain | O(n) | O(h) | [Java](Data%20Structures%20%26%20Algorithms/binary-tree-maximum-path-sum/submission-1.java) |
| [Serialize and Deserialize Binary Tree](https://neetcode.io/problems/serialize-and-deserialize-binary-tree/question) | Hard | Design conversion of a tree to a string and back. | Preorder with null markers | O(n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/serialize-and-deserialize-binary-tree/submission-1.java) |

### Heap / Priority Queue

| Problem | Difficulty | What it asks | Pattern | Time | Space | Solution |
|---|---|---|---|---|---|---|
| [Kth Largest Element in a Stream](https://neetcode.io/problems/kth-largest-integer-in-a-stream/question) | Easy | Track the kth largest value as numbers arrive. | Min-heap of size k | O(log k) / add | O(k) | [Java](Data%20Structures%20%26%20Algorithms/kth-largest-integer-in-a-stream/submission-1.java) |
| [Last Stone Weight](https://neetcode.io/problems/last-stone-weight/question) | Easy | Repeatedly smash the two heaviest stones and return what is left. | Max-heap simulation | O(n log n) | O(n) | [Java](Data%20Structures%20%26%20Algorithms/last-stone-weight/submission-1.java) |
| [K Closest Points to Origin](https://neetcode.io/problems/k-closest-points-to-origin/question) | Medium | Return the k points nearest to the origin. | Max-heap of size k | O(n log k) | O(k) | [Java](Data%20Structures%20%26%20Algorithms/k-closest-points-to-origin/submission-1.java) |
| [Kth Largest Element in an Array](https://neetcode.io/problems/kth-largest-element-in-an-array/question) | Medium | Find the kth largest element of an unsorted array. | Min-heap of size k | O(n log k) | O(k) | [Java](Data%20Structures%20%26%20Algorithms/kth-largest-element-in-an-array/submission-1.java) |
| [Task Scheduler](https://neetcode.io/problems/task-scheduling/question) | Medium | Minimum time to run all tasks when identical tasks need a cooldown gap. | Max-heap + cooldown queue | O(T · log u) | O(u) | [Java](Data%20Structures%20%26%20Algorithms/task-scheduling/submission-2.java) |
| [Find Median from Data Stream](https://neetcode.io/problems/find-median-in-a-data-stream/question) | Hard | Support adding numbers and reading the running median. | Two heaps (max-heap + min-heap) | O(log n) / add, O(1) / median | O(n) | [Java](Data%20Structures%20%26%20Algorithms/find-median-in-a-data-stream/submission-1.java) |
| [Design Twitter](https://neetcode.io/problems/design-twitter-feed/question) | Medium | Design post, follow, unfollow and a feed of the 10 latest tweets. | HashMaps + heap merge of per-user feeds | O(f log f) / feed, O(1) otherwise | O(users + tweets) | [Java](Data%20Structures%20%26%20Algorithms/design-twitter-feed/submission-1.java) |

`T` is total time including idle cycles and `u` is the number of distinct tasks.

## To revisit

- **Subtree of Another Tree**: O(n·m) is fine for the constraints; serializing both trees or hashing subtrees gets it to O(n + m).
- **Validate Binary Search Tree**: the `int` bounds start at `Integer.MIN_VALUE` / `MAX_VALUE` and compare with `<=` / `>=`, so a valid tree holding either extreme value is rejected. Use `long` bounds or nullable `Integer` bounds.
- **Kth Smallest Element in a BST**: the two extra `count` guards after the left recursion are redundant; an early return flag or an iterative in-order walk is cleaner.
- **K Closest Points to Origin**: the `sqrt` is unnecessary; compare squared distances as integers to avoid floating point.
- **Task Scheduler**: the heap simulation is correct; a counting formula based on the max frequency gives O(n) time and O(1) space.
- **Copy List with Random Pointer**: the interleaving technique (copy each node next to its original, then split) removes the HashMap for O(1) extra space.
- **Add Two Numbers**: the `sum >= 10` branch can collapse to `carry = sum / 10` and a node of `sum % 10`.
- **Find the Duplicate Number**: the `while (slow < nums.length)` guard is always true under the constraints; a `do { ... } while (slow != fast)` loop states the intent directly.
- **Stack-based problems**: several solutions use the legacy `Stack` class; `ArrayDeque` is the idiomatic replacement.

## Repository structure

```
Data Structures & Algorithms/
  <problem-id>/
    submission-0.java
    submission-1.java   # later attempts; the highest number is the latest
```

Solutions sync here automatically through NeetCode's GitHub integration.
