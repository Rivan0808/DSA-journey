
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        
        backtrack(nums, new ArrayList<>(), result, used);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); 
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            current.add(nums[i]);
            used[i] = true;

            backtrack(nums, current, result, used);

            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}
