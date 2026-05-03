class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean hasOne = false;
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }
        if (!hasOne) return 1;

        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (nums[i] <= 0) nums[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (idx >= N) continue;
            nums[idx] = -Math.abs(nums[idx]);
        }

        for (int i = 0; i < N; i++) {
            if (nums[i] > 0) return i + 1;
        }

        return N + 1;
    }
}