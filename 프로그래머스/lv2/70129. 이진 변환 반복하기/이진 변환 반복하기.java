class Solution {
    public int[] solution(String s) {
        int procCount = 0;
        int zeroCount = 0;
        while (!s.equals("1")){
            
            int count = (int)s.chars().filter(value -> value != '0').count();
            
            zeroCount += s.length() - count;
            s = Integer.toBinaryString(count);
            
            
            procCount += 1;
        }
        int[] answer = {procCount, zeroCount};
        return answer;
    }
}