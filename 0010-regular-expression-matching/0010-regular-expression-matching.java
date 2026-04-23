class Solution {

    String text, pattern;
    Boolean[][] memo;

    public boolean isMatch(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
        memo = new Boolean[text.length() + 1][pattern.length() + 1];
        return dp(0, 0);
    }

    // text[i:] 와 pattern[j:]가 완전히 매칭되는가?
    boolean dp(int i, int j) {
        if (memo[i][j] != null) return memo[i][j];

        // Base Case : 패턴이 끝났으면 텍스트도 끝나야만 true
        if (j == pattern.length()) {
            return memo[i][j] = (i == text.length());
        }

        // 현재 문자 매칭 여부
        boolean firstMatch = (i < text.length()) && 
                             (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');

        boolean answer;
        // * 이 있는 경우
        if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
            answer = dp(i, j + 2) || (firstMatch && dp(i + 1, j));
        } else {  // 일반 경우
            answer = firstMatch && dp(i + 1, j + 1);
        }

        return memo[i][j] = answer;
    }
}