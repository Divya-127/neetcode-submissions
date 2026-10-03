/*
FOLLOW-UP VARIATION:
What if timestamps are NOT guaranteed to arrive in sorted order?

Original problem:
set("foo", "A", 7)
set("foo", "B", 2)
set("foo", "C", 5)

With the original problem's guarantee, an ArrayList works because timestamps
are already sorted. We can then use Binary Search.

Without that guarantee:

```
[7, 2, 5]
```

is not sorted, so Binary Search cannot be used directly.

Instead, we need a data structure that maintains ordering for us.

Concept:
Use a HashMap of TreeMaps.

```
Map<String, TreeMap<Integer, String>>
```

Each key has its own TreeMap:

```
"foo" → TreeMap

          timestamp → value

             2 → "B"
             5 → "C"
             7 → "A"
```

TreeMap maintains its keys in sorted order even if values are inserted
in an arbitrary order.

Why TreeMap?

The get() operation asks:

```
"Give me the value associated with the largest timestamp
 that is <= the requested timestamp."
```

This is called finding the FLOOR of a value.

For example:

```
timestamps = [2, 5, 7, 10]
target = 6
```

We want:

```
5
```

because:

```
5 <= 6
7 > 6
```

Java's TreeMap provides exactly this operation:

```
floorEntry(target)
```

floorEntry(x) returns the key-value entry whose key is the
largest key <= x.

Therefore:

```
tree.floorEntry(6)
```

returns:

```
5 → "C"
```

Implementation:
*/
class TimeMap {

Map<String, TreeMap<Integer, String>> map;

public TimeMap() {
    map = new HashMap<>();
}

public void set(String key, String value, int timestamp) {

    map.computeIfAbsent(
        key,
        k -> new TreeMap<>()
    ).put(timestamp, value);
}

public String get(String key, int timestamp) {

    TreeMap<Integer, String> tree = map.get(key);

    if (tree == null) {
        return "";
    }

    Map.Entry<Integer, String> entry =
        tree.floorEntry(timestamp);

    if (entry == null) {
        return "";
    }

    return entry.getValue();
}


}
/*
```
How set() works:

Suppose we receive:

```
set("foo", "A", 7)
set("foo", "B", 2)
set("foo", "C", 5)
```

The timestamps arrive in an arbitrary order.

After the first:

```
"foo" → {7 → "A"}
```

After the second:

```
"foo" → {2 → "B", 7 → "A"}
```

After the third:

```
"foo" → {2 → "B", 5 → "C", 7 → "A"}
```

We never explicitly call sort().

TreeMap maintains the ordering internally.

How get() works:

Suppose:

```
get("foo", 6)
```

The TreeMap contains:

```
2 → "B"
5 → "C"
7 → "A"
```

We need:

```
largest timestamp <= 6
```

Therefore:

```
floorEntry(6)
```

returns:

```
5 → "C"
```

So the answer is:

```
"C"
```

Why floorEntry() is related to our Binary Search solution:

In the original implementation, we manually searched a sorted ArrayList.

We were solving:

```
Find the rightmost timestamp <= target.
```

Our Binary Search logic was:

```
if timestamp[mid] <= target:

    ans = mid
    search RIGHT

else:

    search LEFT
```

TreeMap solves the same conceptual problem using its
ordered tree structure.

Instead of:

```
sorted ArrayList
      ↓
  Binary Search
      ↓
rightmost <= target
```

we now have:

```
TreeMap
   ↓
floorEntry(target)
   ↓
largest key <= target
```

Important distinction:

TreeMap does NOT perform array-style Binary Search.

It uses a balanced Binary Search Tree (Java TreeMap is based on a
Red-Black Tree) to maintain ordered keys and efficiently locate the
floor entry.

The underlying idea is still the same:

```
Use ordering to eliminate irrelevant possibilities.
```

Example:

TreeMap:

```
          7
         / \
        5   10
       /
      2
```

Searching for floor(6):

```
6 < 7
   ↓
go LEFT

6 > 5
   ↓
5 is a valid candidate

Search for a larger valid value on the right.

No better candidate exists.

Answer = 5
```

Complexity:

set():

```
TreeMap insertion → O(log n)
```

get():

```
floorEntry() → O(log n)
```

Space:

```
O(n)
```

where n is the number of timestamp-value pairs stored.

Comparison with the original sorted-timestamp version:

Original guarantee:
timestamps arrive in increasing order

```
HashMap
   ↓
ArrayList
   ↓
Binary Search

set → O(1) average
get → O(log n)
```

Unsorted timestamps:
timestamps can arrive in any order

```
HashMap
   ↓
TreeMap
   ↓
floorEntry()

set → O(log n)
get → O(log n)
```

Why we don't simply sort after every set():

Suppose we have:

```
[2, 5, 7, 10]
```

and insert:

```
6
```

We would need:

```
[2, 5, 6, 7, 10]
```

Sorting after every insertion repeatedly performs unnecessary work.

TreeMap maintains the ordering as elements are inserted.

Data Structure Evolution:

The reasoning behind the choice is:

```
Need:
"largest timestamp <= target"

          ↓

Need timestamps to be ordered

          ↓

Original problem gives sorted timestamps
          ↓
ArrayList + Binary Search


If timestamps are NOT sorted:

          ↓

We need a structure that maintains ordering

          ↓

Balanced Binary Search Tree

          ↓

Java TreeMap

          ↓

floorEntry(timestamp)
```

Reusable Pattern:

Whenever a problem asks for:

```
largest key <= target
```

think:

```
FLOOR
```

Whenever it asks for:

```
smallest key >= target
```

think:

```
CEILING
```

Useful TreeMap navigation methods:

```
floorEntry(x)
    → largest key <= x

ceilingEntry(x)
    → smallest key >= x

lowerEntry(x)
    → largest key < x

higherEntry(x)
    → smallest key > x
```

Mental Model:

ArrayList version:

```
Sorted array
    ↓
Binary Search
    ↓
Rightmost <= target
```

TreeMap version:

```
Ordered tree
    ↓
floorEntry(target)
    ↓
Largest key <= target
```

Core Lesson:

Do not choose a data structure just because you know it.

Start with the operation you need.

Here:

```
Required operation:
"Find the largest timestamp <= target."
```

Then ask:

```
"What data structure supports this efficiently
 while timestamps can arrive in arbitrary order?"
```

Answer:

```
TreeMap.
```

Final Pattern:

```
HashMap
    +
TreeMap
    +
Floor Search
```

Complexity:

```
set  → O(log n)
get  → O(log n)
space → O(n)
```
*/