

public class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        
        
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int numMod = num % k;
            
            nextDp[numMod] = 1;
            
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextMod = (int) (( (long) r * numMod) % k);
                    nextDp[nextMod] += dp[r];
                }
            }
            
            for (int r = 0; r < k; r++) {
                ans[r] += nextDp[r];
            }
            
            dp = nextDp;
        }
        
        return ans;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 4, 5};
        int k = 3;
        System.out.println(Arrays.toString(sol.resultArray(nums, k))); 
    }
}
