class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentList, int[] candidates, int remainingTarget, int start) {
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
       
            if (candidates[i] > remainingTarget) {
                break;
            }

        
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            currentList.add(candidates[i]);

            backtrack(result, currentList, candidates, remainingTarget - candidates[i], i + 1);

            currentList.remove(currentList.size() - 1);
        }
    }
}
