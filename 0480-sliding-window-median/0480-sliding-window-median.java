/**
두 개의 PQ 사용
    minHeap = 큰 값 절반 저장
    maxHeap = 작은 값 절반 저장
PQ는 중간값 삭제가 O(N) -> lazy deletion
 */

class Solution {

    PriorityQueue<Integer> minHeap, maxHeap;
    Map<Integer, Integer> removed;
    int minHeapSize, maxHeapSize;

    public double[] medianSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        double[] result = new double[N - k + 1];
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        removed = new HashMap<>();
        minHeapSize = 0; maxHeapSize = 0;
        int idx = 0;

        for (int right = 0; right < N; right++) {
            append(nums[right]);

            if (right >= k) {
                erase(nums[right - k]);
            }

            balance();

            prune(maxHeap);
            prune(minHeap);

            if (right >= k - 1) {
                result[idx++] = getMedian(k);
            }
        }

        return result;
    }

    private void append(int x) {
        if (maxHeap.isEmpty() || x <= maxHeap.peek()) {
            maxHeap.add(x);
            maxHeapSize++;
        } else {
            minHeap.add(x);
            minHeapSize++;
        }

        balance();
    }

    private void erase(int x) {
        removed.put(x, removed.getOrDefault(removed.get(x), 0) + 1);

        if (x <= maxHeap.peek()) {
            maxHeapSize--;
            if (x == maxHeap.peek()) {
                prune(maxHeap);
            }
        } else {
            minHeapSize--;
            if (!minHeap.isEmpty() && x == minHeap.peek()) {
                prune(minHeap);
            }
        }
    }

    // heap의 top에 올라와 있는 논리적으로 삭제된 값을 실제로 제거해서,
    // peek() / poll()이 항상 유효한 원소를 보게 만드는 함수
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int x = heap.peek();
            if (!removed.containsKey(x) || removed.get(x) == 0) {
                break;
            }

            removed.put(x, removed.get(x) - 1);
            heap.poll();
        }
    }

    private void balance() {
        if (maxHeapSize > minHeapSize + 1) {
            minHeap.add(maxHeap.poll());
            minHeapSize++;
            maxHeapSize--;
            prune(maxHeap);
        } else if (maxHeapSize < minHeapSize) {
            maxHeap.add(minHeap.poll());
            minHeapSize--;
            maxHeapSize++;
            prune(minHeap);
        }
    }

    private double getMedian(int k) {
        prune(minHeap);
        prune(maxHeap);

        if (k % 2 == 1) {   
            return (double) maxHeap.peek();
        }
        return (double) (minHeap.peek() / 2.0) + (maxHeap.peek() / 2.0);
    }
}