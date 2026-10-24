class Twitter {

    class User {
        int userId;
        Set<Integer> followers;
        Set<Integer> following;

        User(int userId) {
            this.userId = userId;
            this.followers = new HashSet<>();
            this.following = new HashSet<>();
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

    Map<Integer, User> users;
    Map<Integer, List<Tweet>> tweets;
    int timeId;

    public Twitter() {
        users = new HashMap<>();
        tweets = new HashMap<>();
        timeId = 0;
    }

    public void postTweet(int userId, int tweetId) {
        // Create the user if they don't exist
        users.putIfAbsent(userId, new User(userId));

        // Create a tweet with a globally increasing timestamp
        Tweet tweet = new Tweet(tweetId, timeId++, userId);

        // Store the tweet under its author's ID
        tweets.putIfAbsent(userId, new ArrayList<>());
        tweets.get(userId).add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList<>();

        User user = users.get(userId);
        if (user == null) {
            return ans;
        }

        PriorityQueue<Tweet> maxHeap =
            new PriorityQueue<>(
                (a, b) -> Integer.compare(b.timeId, a.timeId)
            );

        // Include the user's own tweets and tweets from followed users
        Set<Integer> relevantUserIds = new HashSet<>(user.following);
        relevantUserIds.add(userId);

        for (int id : relevantUserIds) {
            List<Tweet> userTweets = tweets.get(id);

            if (userTweets != null) {
                maxHeap.addAll(userTweets);
            }
        }

        // Return at most 10 newest tweet IDs
        while (!maxHeap.isEmpty() && ans.size() < 10) {
            Tweet tweet = maxHeap.poll();
            ans.add(tweet.tweetId);
        }

        return ans;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        users.putIfAbsent(followerId, new User(followerId));
        users.putIfAbsent(followeeId, new User(followeeId));
        User followee = users.get(followeeId);
        User follower = users.get(followerId);
        follower.following.add(followeeId);
        followee.followers.add(followerId);    
    }

    public void unfollow(int followerId, int followeeId) {
        User follower = users.get(followerId);

        if (follower == null) return;

        User followee = users.get(followeeId);

        if (followee == null) return;

        follower.following.remove(followeeId);
        followee.followers.remove(followerId);
    }
}
