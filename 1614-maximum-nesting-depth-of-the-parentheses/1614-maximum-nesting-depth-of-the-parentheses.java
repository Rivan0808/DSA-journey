
class Solution {
    
    public int maxDepth(String s) {
        int maxDepth = 0;      // Tracks the maximum depth encountered
        int currentDepth = 0;  // Tracks the current nesting level
      
        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
          
            // Opening parenthesis increases the current depth
            if (currentChar == '(') {
                currentDepth++;
                // Update maximum depth if current depth exceeds it
                maxDepth = Math.max(maxDepth, currentDepth);
            } 
            // Closing parenthesis decreases the current depth
            else if (currentChar == ')') {
                currentDepth--;
            }
            // Other characters are ignored
        }
      
        return maxDepth;
    }
}
