/*
 * Concept:
 * - Use a HashSet to detect duplicate values.
 * - Check every row, every column, and every 3x3 sub-box.
 * - Ignore '.' because it represents an empty cell.
 *
 * Key Insight:
 * A valid Sudoku requires each digit to appear at most once
 * in every row, column, and 3x3 box.
 *
 * Pattern:
 * HashSet + Matrix Traversal
 *
 * Complexity:
 * Time: O(1)  // fixed 9x9 board
 * Space: O(1) // at most 9 elements in each HashSet
 */
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
