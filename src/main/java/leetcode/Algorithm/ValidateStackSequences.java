package leetcode.Algorithm;

import java.util.Stack;

/**
 *
 */
class ValidateStackSequences {
 public static boolean validateStackSequences(String[] pushed, String[] popped) {
    int size = 0;
    for (int i = 0, j = 0; i < pushed.length; i++) {
        pushed[size++] = pushed[i];
        while (size != 0 && pushed[size - 1] == popped[j]) {
            size--; j++;
        }
    }
    return size == 0;
   }

    /**
     * Stack.Peek 取栈顶的值  与 stack.pop  弹出栈顶的值
     * @param pushed
     * @param popped
     * @return
     */
    public static boolean validateStackSequences2(String[] pushed, String[] popped) {

        Stack<String> s = new Stack<String>();
        int N = pushed.length;
        int  j =0;
        for (int i = 0; i < N; i++) {
            s.push(pushed[i]);
            while (!s.empty() && j<N && s.peek() == popped[j]) {
                s.pop();
                j++;
            }
        }
        return j == N;
    }

    public static void main(String[] args) {
     String[] i={"a","b","c","d"};
     String[] i1={"d","c","b","a"};
     System.out.println( validateStackSequences(i,i1));
     System.out.println( validateStackSequences2(i,i1));
    }
}