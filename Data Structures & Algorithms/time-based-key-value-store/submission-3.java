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
