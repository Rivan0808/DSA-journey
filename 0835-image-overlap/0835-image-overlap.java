class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) {
                    ones1.add(new int[]{r, c});
                }
                if (img2[r][c] == 1) {
                    ones2.add(new int[]{r, c});
                }
            }
        }
        
        Map<String, Integer> transformCount = new HashMap<>();
        int maxOverlap = 0;
        
        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int rowDiff = p1[0] - p2[0];
                int colDiff = p1[1] - p2[1];
                
                String key = rowDiff + "," + colDiff;
                
                transformCount.put(key, transformCount.getOrDefault(key, 0) + 1);
                
                maxOverlap = Math.max(maxOverlap, transformCount.get(key));
            }
        }
        
        return maxOverlap;
    }
}