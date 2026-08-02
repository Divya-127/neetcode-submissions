class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String,Integer> count = new HashMap<>();
        for(int i = 0;i< strs.length;i++)
        {
            char[] s = strs[i].toCharArray();
            Arrays.sort(s);
            String sorted = new String(s);
            if(count.containsKey(sorted))
            {
                List<String> toAppend = ans.get(count.get(sorted));
                toAppend.add(strs[i]);
//                ans.set(count.get(sorted),toAppend);
            }else{
                List<String> toAdd = new ArrayList<>();
                toAdd.add(strs[i]);
                ans.add(toAdd);
                count.put(sorted,ans.size()-1);
            }
        }
        return ans;
    }
}
