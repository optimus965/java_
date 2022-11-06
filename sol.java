import java.util.*;
class solution {
    public List<String> findWords(char[][] board, String[] words) {
        return this.sol(board,words);
    }
    class TrieNode {
        TrieNode[] tree = new TrieNode[26];
        boolean isEnd = false;
        String sc = "";
        public TrieNode() {
            for(int i =0;i < 26;i++) {
                tree[i] = null;
            }
        }
    }
    boolean[][] visited;
    int rowLen;
    int colLen;
    TrieNode root = new TrieNode();
    TrieNode tem;
    List<String> cv;
    public List<String> sol(char[][] board,String[] words) {
        rowLen = board.length;
        colLen = board[0].length;
        cv = new ArrayList<>();
        TrieCon(words);
        for(int i =0;i < board.length;i++) {
            for(int j =0;j < board[0].length;j++) {
                visited = new boolean[board.length][board[0].length];
                tem = root;
                stack = new Stack<>();
                int  c = board[i][j] - 'a';
                if(tem.tree[c] == null) {
                    continue;
                }
                if(tem.tree[c].isEnd) {
                    cv.add(board[i][j] + "");
                    tem.tree[c].isEnd = false;
                    continue;
                }
                dfs(board,i,j);
            }
        }
        return cv;
    }
    public void TrieCon(String[] words) {
        for(int i = 0;i < words.length;i++) {
            this.TreeC(words[i]);
        }
    }
    Stack<Character> stack =new Stack<>();
    public void TreeC(String word) {
        TrieNode temp = root;
        for(int i=0;i < word.length();i++) {
            int c = word.charAt(i) - 'a';
            if(temp.tree[c] == null) {
                temp.tree[c] = new TrieNode();
            }
            temp = temp.tree[c];
        }
        temp.isEnd = true;
        temp.sc = new String(word);
    }
    int[][] directions = {
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
    };
    public void dfs(char[][] board,int row,int column) {
        for(int i =0;i < directions.length;i++) {
            int x= row + directions[i][0];
            int y = column + directions[i][1];
            if(x < 0 || x >= this.rowLen) {
                continue;
            }
            if(y < 0 || y >= this.colLen) {
                continue;
            }
            if(visited[row][column]) {
                continue;
            }
            int c = board[row][column] - 'a';
            if(tem.tree[c] == null) {
                visited[row][column] = false;
                return;
            }
            if(tem.tree[c].isEnd) {
                cv.add(tem.tree[c].sc);
                tem.tree[c].isEnd = false;
            }
            TrieNode cvb = tem;
            tem = tem.tree[c];
            visited[row][column] = true;
            dfs(board,x,y);
            tem = cvb;
            visited[row][column] = false;
        }
    }
}