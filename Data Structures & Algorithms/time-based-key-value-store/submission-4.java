/*
Concept:
Design a time-based key-value store where the same key can have multiple
values at different timestamps.

For a given key and timestamp, return the value associated with the
LATEST timestamp that is <= the requested timestamp.

Example:

set("foo", "bar", 1)
set("foo", "bar2", 4)
set("foo", "bar3", 7)

get("foo", 6) → "bar2"

Because:
4 <= 6
7 > 6

So timestamp 4 is the largest timestamp that does not exceed 6.

Key Insight:
The problem has TWO parts:

1. Store the history for every key.
2. Efficiently find the latest valid timestamp during get().

Data Structure:

Use a HashMap:

```
key → list of (timestamp, value)
```

Example:

```
"foo" →
    [(1, "bar"),
     (4, "bar2"),
     (7, "bar3")]
```

Because timestamps for the same key are provided in increasing order,
we can simply append each new Pair to the list.

Therefore, we do NOT need to sort during set().

set():
HashMap lookup → O(1) average
Append Pair → O(1)

This preserves the timestamps in sorted order automatically.

Binary Search Insight:

The get() operation is NOT a normal exact-match Binary Search.

We are NOT asking:

```
"Does timestamp == target exist?"
```

Instead, we need:

```
"What is the largest timestamp <= target?"
```

This is the:

```
RIGHTMOST VALUE <= TARGET
```

Binary Search variant.

Example:

```
timestamps = [1, 4, 7, 10]
target = 8
```

We want:

```
7
```

because:

```
7 <= 8
10 > 8
```

So the search must continue even after finding a valid timestamp.

Binary Search Logic:

Maintain:

```
ans = -1
```

This represents:
"We have not found any valid timestamp yet."

For every mid:

1. If:

   ```
   timestamps[mid] <= target
   ```

   then mid is a VALID candidate.

   Save it:

   ```
   ans = mid
   ```

   But we are looking for the LATEST valid timestamp,
   so there might be a better candidate to the RIGHT.

   ```
   start = mid + 1
   ```

2. If:

   ```
   timestamps[mid] > target
   ```

   then mid is too large.

   Everything after mid is also too large because the
   timestamps are sorted.

   Therefore:

   ```
   end = mid - 1
   ```

The important idea is:

```
VALID candidate
      ↓
  save answer
      ↓
  search RIGHT

INVALID candidate
      ↓
  search LEFT
```

Why do we need ans?

Consider:

```
timestamps = [1, 4, 7]
target = 6
```

There is no exact timestamp 6.

A normal Binary Search would fail because:

```
6 does not exist.
```

But our goal is different:

```
largest timestamp <= 6
```

which is:

```
4
```

So when we encounter 4, we remember it as the current
best candidate and continue searching to the right in case
there is another valid timestamp closer to 6.

Example Trace:

```
timestamps = [1, 4, 7, 10]
target = 8

start = 0
end   = 3

mid = 1
timestamps[mid] = 4

4 <= 8
   ↓
valid candidate
   ↓
ans = 1
   ↓
search right

start = 2


mid = 2
timestamps[mid] = 7

7 <= 8
   ↓
better candidate
   ↓
ans = 2
   ↓
search right

start = 3


mid = 3
timestamps[mid] = 10

10 > 8
   ↓
too large
   ↓
search left

end = 2


start > end

ans = 2

timestamps[2] = 7
```

Therefore:
return value associated with timestamp 7.

Important Edge Cases:

1. Exact timestamp exists:

   [1, 4, 7]
   target = 4

   → return value at timestamp 4

2. Target lies between timestamps:

   [1, 4, 7]
   target = 6

   → return timestamp 4

3. Target is larger than every timestamp:

   [1, 4, 7]
   target = 20

   → return timestamp 7

4. Target is smaller than every timestamp:

   [1, 4, 7]
   target = 0

   → no valid timestamp
   → return ""

5. Key does not exist:

   get("unknown", 5)

   → return ""

Pattern:

```
HashMap
   +
ArrayList of timestamp-value pairs
   +
Binary Search
   +
Rightmost Value <= Target
```

Complexity:

set():
Time: O(1) average
Space: O(n)

get():
HashMap lookup: O(1) average
Binary Search: O(log n)

Overall:
set → O(1) average
get → O(log n)

Space:
O(n) for all stored timestamp-value pairs.

Mental Model:

Think of each key as having its own timeline:

```
"foo"

time →
─────────────────────────────>

1          4          7
│          │          │
```

bar       bar2       bar3

When asked:

```
get("foo", 6)
```

Imagine dropping a pointer at time 6:

```
1          4       6    7
│          │       ↑    │
```

bar       bar2    target bar3

The answer is the value immediately to the LEFT of,
or exactly at, the requested timestamp.

Therefore:

```
"Find the rightmost timestamp <= target."
```

Core Binary Search Pseudocode:

```
start = 0
end = n - 1
ans = -1

while start <= end:

    mid = start + (end - start) / 2

    if timestamp[mid] <= target:

        ans = mid

        // Valid, but maybe there is a later
        // valid timestamp.
        start = mid + 1

    else:

        // Timestamp is too large.
        end = mid - 1

return ans
```

Reusable Binary Search Pattern:

This problem teaches an important variation beyond
"find target exactly".

Normal Binary Search:

```
nums[mid] == target
        ↓
     return
```

TimeMap:

```
nums[mid] <= target
        ↓
   valid candidate
        ↓
   remember it
        ↓
   search RIGHT
```

This same pattern appears whenever the problem asks for:

```
largest value <= target
```

or:

```
latest value <= time
```

or:

```
floor of target
```

or:

```
predecessor of target
```

The key question to ask is:

```
"Is this element valid?"
```

If YES:
save it
continue searching in the direction that could
produce a better valid answer.

If NO:
eliminate the invalid half.

Important Constraint Lesson:

Initially, it is tempting to do:

```
set()
  ↓
insert timestamp
  ↓
sort timestamps
```

But the problem guarantees that timestamps for set()
operations are strictly increasing.

Therefore the input itself maintains the ordering.

This is an important DSA lesson:

```
Read constraints BEFORE choosing the data structure.
```

Instead of:

```
set → insert → sort
```

we get:

```
set → append
```

and preserve sorted order for free.

Final Mental Model:

```
HASHMAP
   ↓
key → timeline
   ↓
[(timestamp, value), ...]
   ↓
GET(timestamp)
   ↓
Find latest timestamp <= requested timestamp
   ↓
Binary Search
   ↓
Valid → save + go RIGHT
Too large → go LEFT
   ↓
Return saved candidate
```

Pattern:
HashMap + Ordered History + Rightmost-Valid Binary Search

Complexity:
set  → O(1) average
get  → O(log n)
space → O(n)
*/

class TimeMap {
    class Pair{
        int timestamp;
        String value;

        public Pair(int timestamp,String value){
            this.timestamp = timestamp;
            this.value = value;
        }

        public int getTimeStamp(){
            return this.timestamp; 
        }

        public String getValue(){
            return this.value;
        }
    }
    Map<String, ArrayList<Pair>> map;
    public TimeMap() {
        map = new HashMap<String, ArrayList<Pair>>();
    }

    public void set(String key, String value, int timestamp) {
        Pair p = new Pair(timestamp,value);
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(p);
    }
    
    public String get(String key, int timestamp) {
        ArrayList<Pair> pairs = map.get(key);
        if (pairs == null) {
            return "";
        }
        if(pairs.size()>0)
        {
            int i = returnIndex(pairs,timestamp);
            if(i!=-1)
                return pairs.get(i).getValue();
            else
                return "";
        }
        return "";
    }
    public int returnIndex(ArrayList<Pair> pairs,int timestamp)
    {
        int start = 0;
        int ans = -1;
        int end = pairs.size()-1;
        while(start<=end)
        {
            int mid = start + (end-start)/2;
            if(pairs.get(mid).getTimeStamp() <= timestamp)
            {
                ans = mid;
                start = mid +1;
            }else
            {
                end = mid-1;
            }
        }
        return ans;
    }
}
