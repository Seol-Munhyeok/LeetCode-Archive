/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int prev, count, maxCount;
    List<Integer> answerList;

    public int[] findMode(TreeNode root) {
        prev = root.val;
        count = 0;
        maxCount = 0;
        inorder(root, false);

        prev = root.val;
        count = 0;
        answerList = new ArrayList<>();
        inorder(root, true);

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    private void inorder(TreeNode root, boolean flag) {
        if (root == null) return;

        inorder(root.left, flag);
        if (root.val == prev) {
            count++;
        } else {
            count = 1;
            prev = root.val;
        }

        if (flag) {
            if (count == maxCount) {
                answerList.add(root.val);
            }
        } else {
            maxCount = Math.max(maxCount, count);
        }
        inorder(root.right, flag);
    }


}