import java.util.ArrayList;
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        
        boolean[] used = new boolean[nums.length];
        
        backtrack(nums, used, new ArrayList<>(), result);
        
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> currentPermutation, List<List<Integer>> result) {
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

           
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Make choice
            used[i] = true;
            currentPermutation.add(nums[i]);

            backtrack(nums, used, currentPermutation, result);

            currentPermutation.remove(currentPermutation.size() - 1);
            used[i] = false;
        }
    }
}
