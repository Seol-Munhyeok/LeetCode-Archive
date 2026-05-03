class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; ) {
            int x = nums[i];
            if (x >= 1 && x <= n && nums[x - 1] != x) {
                int temp = nums[i];
                nums[i] = nums[x - 1];
                nums[x - 1] = temp;
            } else {
                i++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }
}