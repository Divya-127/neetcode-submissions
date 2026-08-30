class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> freq = new HashMap<Integer,Integer>();

        for(int num: nums)
        {
            freq.merge(num,1,Integer::sum);
        }

        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        for(Map.Entry<Integer,Integer> entry: freq.entrySet())
        {
            int num = entry.getKey();
            int frequency = entry.getValue();

            if(bucket[frequency]==null)
            {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(num);
        }
        
        int[] ans = new int[k];
        int index = 0;
        for(int frequency = bucket.length-1;frequency>=0 && index<k;frequency--)
        {
            if(bucket[frequency]!=null)
            {
                for(int num: bucket[frequency])
                {
                    ans[index++] = num;
                    if(index>k)
                    {
                        break;
                    }
                }
            }
        }
        return ans; 
    }
}
