class Solution {

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str: strs)
        {
            sb.append(str.length());
            sb.append("#");
            sb.append(str);
        }
       return sb.toString();
    }

    public List<String> decode(String str) {
    List<String> result = new ArrayList<>();

    int i = 0;

    while (i < str.length()) {

        // 1. Find '#'
        int j = str.indexOf('#', i);

        // 2. Get length
        int length = Integer.parseInt(str.substring(i, j));

        // 3. Extract the actual string
        String word = str.substring(j + 1, j + 1 + length);

        result.add(word);

        // 4. Move i to the beginning of next encoded string
        i = j + 1 + length;
    }

    return result;
    }
}
