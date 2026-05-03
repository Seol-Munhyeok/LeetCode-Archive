class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            
            // ch가 현재 윈도우 안에서 이미 나온 문자라면
            if (lastIndex.containsKey(ch) && lastIndex.get(ch) >= left) {
                left = lastIndex.get(ch) + 1;
            }

            // ch의 마지막 등장 위치 갱신
            lastIndex.put(ch, right);

            // 현재 중복 없는 구간 길이 갱신
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}