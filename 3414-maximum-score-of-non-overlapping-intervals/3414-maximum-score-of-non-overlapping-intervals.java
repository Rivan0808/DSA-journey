import java.util.*;

class Solution {
    class Interval {
        int l, r, w, id;
        Interval(int l, int r, int w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }

    class State {
        long score;
        List<Integer> ids;

        State(long score, List<Integer> ids) {
            this.score = score;
            this.ids = new ArrayList<>(ids);
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        
        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(
                intervalsList.get(i).get(0),
                intervalsList.get(i).get(1),
                intervalsList.get(i).get(2),
                i
            );
        }

        // Sort intervals primary by start time 'l' ascending.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.l, b.l));

        // nxt[i] will store the index of the first interval that starts strictly after intervals[i] ends
        int[] nxt = new int[n];
        for (int i = 0; i < n; i++) {
            int target = intervals[i].r;
            int low = i + 1, high = n, ans = n;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (intervals[mid].l > target) {
                    ans = mid;
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            nxt[i] = ans;
        }

        State[][] dp = new State[n + 1][5];
        
        for (int j = 0; j <= 4; j++) {
            dp[n][j] = new State(0, new ArrayList<>());
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new ArrayList<>());
        }

        // Bottom-up DP processing
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= 4; j++) {
                // Option 1: Skip the current interval
                State skipState = dp[i + 1][j];

                // Option 2: Take the current interval
                int nextIdx = nxt[i];
                State nextState = dp[nextIdx][j - 1];
                
                long takeScore = intervals[i].w + nextState.score;
                List<Integer> takeIds = new ArrayList<>();
                takeIds.add(intervals[i].id);
                takeIds.addAll(nextState.ids);
                Collections.sort(takeIds); 

                State takeState = new State(takeScore, takeIds);

                if (takeState.score > skipState.score) {
                    dp[i][j] = takeState;
                } else if (skipState.score > takeState.score) {
                    dp[i][j] = skipState;
                } else {
                    if (isLexicographicallySmaller(takeState.ids, skipState.ids)) {
                        dp[i][j] = takeState;
                    } else {
                        dp[i][j] = skipState;
                    }
                }
            }
        }

        List<Integer> finalIds = dp[0][4].ids;
        int[] result = new int[finalIds.size()];
        for (int i = 0; i < finalIds.size(); i++) {
            result[i] = finalIds.get(i);
        }
        return result;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}
