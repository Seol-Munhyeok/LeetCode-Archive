/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {

    MountainArray mountainArr;
    int target;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        this.mountainArr = mountainArr;
        this.target = target;

        int peakIndex = getPeakIndex();

        int answer = binarySearch(0, peakIndex, true);  // 오름차순 정렬 구간
        if (answer != -1) return answer;

        return binarySearch(peakIndex + 1, mountainArr.length() - 1, false);  // 내림차순 정렬 구간
    }

    int getPeakIndex() {
        int left = 0, right = mountainArr.length() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {  // 오르막 구간에 있는 경우
                left = mid + 1;
            } else {  // 내리막 구간에 있는 경우
                right = mid;
            }
        }
        return left;
    }

    int binarySearch(int left, int right, boolean isAsc) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isAsc) {
                if (mountainArr.get(mid) == target) return mid;
                else if (mountainArr.get(mid) > target) right = mid - 1;
                else left = mid + 1;
            } else {
                if (mountainArr.get(mid) == target) return mid;
                else if (mountainArr.get(mid) > target) left = mid + 1;
                else right = mid - 1;
            }
        }

        return -1;
    }
}