package leetcode.Algorithm.max;

import java.util.*;
public class longestValidParentheses {
    public static void main(String[] args) {
     longVail("(()");
    }
    public static int  longVail(String s){
        Stack<Integer> stack  = new Stack();
        stack.push(-1);
        int max =0;
        for(int i = 0 ;i<s.length();i++){
            if('('==s.charAt(i)){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    max = Math.max(max,i-stack.peek());
                }
            }
        }
        return max;
    }
    
    
    
}