class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        char[][] arr = new char[s.length()][s.length()];
        
        int r = 0, c = 0;
        boolean down = true;
        for (int i = 0; i  < s.length(); i++) {
            arr[r][c] = s.charAt(i);
            
            if (down) {
                if (r == numRows - 1) {
                    down = false;
                    r--;
                    c++;
                } else {
                    r++;
                }
            } else {
                if (r == 0) {
                    down = true;
                    r++;
                } else {
                    r--;
                    c++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (arr[i][j] != 0) sb.append(arr[i][j]);
            }
        }

        return sb.toString();
    }
}