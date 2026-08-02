class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String,List<String>> count = new HashMap<>();
        for(int i = 0;i< strs.length;i++)
        {
            char[] s = strs[i].toCharArray();
            Arrays.sort(s);
            String sorted = new String(s);
            if(count.containsKey(sorted))
            {
                List<String> list = count.get(sorted);
                list.add(strs[i]);
            }else{
                List<String> toAdd = new ArrayList<>();
                toAdd.add(strs[i]);
                count.put(sorted,toAdd);
            }
        }
        for (List<String> value : count.values()) {
            ans.add(value);
        }
        return ans;
    }
}
