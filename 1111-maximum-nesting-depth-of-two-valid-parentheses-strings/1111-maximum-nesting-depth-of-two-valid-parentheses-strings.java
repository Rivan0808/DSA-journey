class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int sequenceLength = seq.length();
        int[] groupAssignment = new int[sequenceLength];
      
        int currentDepth = 0;
      
        for (int index = 0; index < sequenceLength; index++) {
            if (seq.charAt(index) == '(') {
                
                groupAssignment[index] = currentDepth & 1;  // 0 if depth is even, 1 if odd
                currentDepth++;
            } else {
                
                currentDepth--;
                groupAssignment[index] = currentDepth & 1;  
            }
        }
      
        return groupAssignment;
    }
}
