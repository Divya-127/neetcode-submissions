/*
# LeetCode 355 — Design Twitter

## Problem Summary

Design a simplified Twitter system supporting four operations:

- `postTweet(userId, tweetId)`: Publish a tweet.
- `getNewsFeed(userId)`: Return the 10 most recent tweets from the user and everyone they follow, newest first.
- `follow(followerId, followeeId)`: Follow another user.
- `unfollow(followerId, followeeId)`: Unfollow another user.

## 1. Core Data Structures

### User

Maintain the relationships between users using sets.

```java
class User {
    int userId;
    Set<Integer> followers;
    Set<Integer> following;

    User(int userId) {
        this.userId = userId;
        followers = new HashSet<>();
        following = new HashSet<>();
    }
}
```

- `followers`: Users who follow this user.
- `following`: Users this user follows.
- `HashSet` prevents duplicate relationships and provides average O(1) membership updates.

### Tweet

Each tweet stores its ID, author, and posting order.

```java
class Tweet {
    int tweetId;
    int timeId;
    int userId;

    Tweet(int tweetId, int timeId, int userId) {
        this.tweetId = tweetId;
        this.timeId = timeId;
        this.userId = userId;
    }
}
```

A globally increasing timestamp is essential because tweet IDs do not necessarily indicate posting order.

### Global state

```java
Map<Integer, User> users = new HashMap<>();
Map<Integer, List<Tweet>> tweets = new HashMap<>();
int timeId = 0;
```

- `users`: Maps user IDs to their user objects.
- `tweets`: Maps each author's ID to their tweets in posting order.
- `timeId`: Assigns a unique, increasing timestamp to each new tweet.

## 2. Basic Implementation

The straightforward approach to `getNewsFeed()` is to collect all relevant tweets, insert them into a max-heap, and poll the 10 newest tweets.

The heap comparator is:

```java
PriorityQueue<Tweet> maxHeap =
    new PriorityQueue<>(
        (a, b) -> Integer.compare(b.timeId, a.timeId)
    );
```

This works, but it inserts every relevant tweet into the heap, even though the feed only needs ten.

If T is the total number of relevant tweets, the time complexity is O(T log T), with O(T) auxiliary heap space.

## 3. Optimized Approach — K-Way Merge Using a Max-Heap

**Key observation:** Each user's tweet list is already sorted by posting time because new tweets are appended to the end.

Instead of adding every tweet to the heap, we only need the newest tweet from each relevant user.

### Step 1: Identify relevant users

Include:
- The requested user's own ID.
- Every ID in the requested user's `following` set.

### Step 2: Add only the newest tweet from each user

For each relevant user, inspect their tweet list. If the list is nonempty, insert a heap entry representing its latest tweet.

Each heap entry stores:
- The tweet.
- The index of that tweet in its author's list.

### Step 3: Extract the newest tweet

Poll the max-heap. The returned tweet is the newest currently available tweet across all relevant users.

Append its `tweetId` to the result.

### Step 4: Add the previous tweet from that author

If the extracted tweet was at index `i`, check whether index `i - 1` exists in the same author's list.

If it does, add that tweet to the heap.

This exposes the next-newest tweet from that author.

### Step 5: Repeat until the feed contains 10 tweets

Stop when either:
- Ten tweet IDs have been collected.
- The heap becomes empty.

This is a **K-way merge**, similar to merging multiple sorted lists using a priority queue.

## 4. Optimized Java Implementation

```java
import java.util.*;
*/
class Twitter {

    class User {
        int userId;
        Set<Integer> followers;
        Set<Integer> following;

        User(int userId) {
            this.userId = userId;
            followers = new HashSet<>();
            following = new HashSet<>();
        }
    }

    class Tweet {
        int tweetId;
        int timeId;
        int userId;

        Tweet(int tweetId, int timeId, int userId) {
            this.tweetId = tweetId;
            this.timeId = timeId;
            this.userId = userId;
        }
    }

    class TweetEntry {
        Tweet tweet;
        int index;

        TweetEntry(Tweet tweet, int index) {
            this.tweet = tweet;
            this.index = index;
        }
    }

    Map<Integer, User> users;
    Map<Integer, List<Tweet>> tweets;
    int timeId;

    public Twitter() {
        users = new HashMap<>();
        tweets = new HashMap<>();
        timeId = 0;
    }

    public void postTweet(int userId, int tweetId) {
        users.putIfAbsent(userId, new User(userId));

        Tweet tweet = new Tweet(tweetId, timeId++, userId);

        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();

        User user = users.get(userId);
        if (user == null) {
            return result;
        }

        PriorityQueue<TweetEntry> maxHeap =
            new PriorityQueue<>(
                (a, b) -> Integer.compare(
                    b.tweet.timeId,
                    a.tweet.timeId
                )
            );

        Set<Integer> relevantUsers =
            new HashSet<>(user.following);

        relevantUsers.add(userId);

        // Step 1: Add the newest tweet from each relevant user.
        for (int id : relevantUsers) {
            List<Tweet> userTweets = tweets.get(id);

            if (userTweets != null && !userTweets.isEmpty()) {
                int lastIndex = userTweets.size() - 1;

                maxHeap.offer(
                    new TweetEntry(
                        userTweets.get(lastIndex),
                        lastIndex
                    )
                );
            }
        }

        // Step 2: Extract the newest tweet and expose its predecessor.
        while (!maxHeap.isEmpty() && result.size() < 10) {
            TweetEntry entry = maxHeap.poll();

            result.add(entry.tweet.tweetId);

            int previousIndex = entry.index - 1;

            if (previousIndex >= 0) {
                List<Tweet> userTweets =
                    tweets.get(entry.tweet.userId);

                maxHeap.offer(
                    new TweetEntry(
                        userTweets.get(previousIndex),
                        previousIndex
                    )
                );
            }
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        users.putIfAbsent(followerId, new User(followerId));
        users.putIfAbsent(followeeId, new User(followeeId));

        User follower = users.get(followerId);
        User followee = users.get(followeeId);

        follower.following.add(followeeId);
        followee.followers.add(followerId);
    }

    public void unfollow(int followerId, int followeeId) {
        User follower = users.get(followerId);
        User followee = users.get(followeeId);

        if (follower == null || followee == null) {
            return;
        }

        follower.following.remove(followeeId);
        followee.followers.remove(followerId);
    }
}
/*
```

## 5. Complexity Analysis

Let:
- F = number of users followed by the requested user.
- K = 10, the maximum feed size.
- M = number of relevant users who have at least one tweet.
- T = total number of tweets belonging to relevant users.

| Operation | Time complexity |
|---|---|
| `postTweet()` | O(1) amortized |
| `follow()` | O(1) average |
| `unfollow()` | O(1) average |
| `getNewsFeed()` | O(F + M + K log(M + 1)) |

Since M ≤ F + 1 and K = 10 is fixed, retrieving the feed takes O(F + K log(F + 2)) time.

Auxiliary space for `getNewsFeed()` is O(F + K), including the relevant-user set, heap, and result list.

The optimized method avoids loading all T tweets into the heap. It processes at most one initial tweet per relevant user and then at most ten additional heap removals.

## 6. Patterns Learned

- **HashMap:** Efficient lookup of users and their tweet lists.
- **HashSet:** Track follow relationships without duplicates.
- **PriorityQueue / Max-Heap:** Always retrieve the newest available tweet.
- **K-way merge:** Merge multiple individually sorted lists without loading every element into the heap.
- **Top-K retrieval:** Stop after collecting the ten newest tweets.

### Key takeaway

When merging multiple sorted lists, don't insert every element into the heap. Insert only the current best candidate from each list, then expose the next candidate from the list you just removed. This pattern also applies to merging sorted arrays, log streams, and feeds from multiple sources.
*/