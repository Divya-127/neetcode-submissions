class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++)
        {
            HashSet<Character> hash = new HashSet<Character>();
            int j=0;
            while(j<9)
            {
                if(board[i][j]!='.')
                {
                    boolean ans = hash.add(board[i][j]);
                    if(!ans)
                    {
                        return false;
                    }
                }
                j++;
            }
        }
        for(int j=0;j<9;j++)
        {
            HashSet<Character> hash = new HashSet<Character>();
            int i=0;
            while(i<9)
            {
                if(board[i][j]!='.')
                {
                    boolean ans = hash.add(board[i][j]);
                    if(!ans)
                    {
                        return false;
                    }
                }
                i++;
            }
        }
        for(int i=0;i<9;i=i+3)
        {
            for(int j=0;j<9;j=j+3)
            {
                HashSet<Character> hash = new HashSet<Character>();
                for (int k = i; k < i + 3; k++)
                {
                    for (int l = j; l < j + 3; l++)
                    {
                        if(board[k][l]!='.')
                        {
                            boolean ans = hash.add(board[k][l]);
                            if(!ans)
                            {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
