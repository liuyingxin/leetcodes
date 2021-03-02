package leetcode.Algorithm.String;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 子串的位置
 */
public class SubString {
    public static void main(String[] args) {
        String[] str = {"a", "b", "c", "d"};
        String[] strSub = {"b", "a","c","d"};
//        System.out.println(subIndex(str, strSub));
//        System.out.println(getIndex(str, strSub));
//        System.out.println(getIndex("abcd", " "));
//        System.out.println("abcd".indexOf("bc"));
        System.out.println(test(str, strSub));
    }

    public static boolean test(String[] s1, String[] s2) {
        Stack<String> s = new Stack<>();
        int j = 0;
        for (int i = 0; i < s1.length; i++) {
            s.push(s1[i]);
            while (!s.isEmpty()&&j<s2.length&&s.peek().equals(s2[j])) {
                s.pop();
                j++;
            }
        }
        return j == s1.length;
    }


    public static int getIndex(String[] str, String[] strSub) {
        int index = -1;
        if (str.length == 0 || str.length < strSub.length) {
            return index;
        }
        List<Integer> a = new ArrayList<Integer>();
        int j = 0;
        for (int i = 0; i < str.length; i++) {
            if (j >= strSub.length) {
                break;
            }
            if (str[i].equals("") || strSub[j].equals("")) {
                a.add(0, -1);
                index = a.get(0);
            }
            if (str[i].equals(strSub[j])) {
                a.add(i);
                j++;
                index = a.get(0);
            }
        }

        return index;
    }

    public static int getIndex(String str, String strSub) {
        if (str == null || str == "" || strSub == null || strSub == "") {
            return -1;
        }
        int j = 0;
        List<Integer> ine = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (j >= strSub.length()) {
                break;
            }
            if (str.charAt(i) == strSub.charAt(j)) {
                ine.add(i);
                j++;
            }

        }
        return ine.get(0);
    }

    public static int subIndex(String[] str, String[] strSub) {
        int lenStr = str.length;
        int lenSub = strSub.length;

        if (lenSub > lenStr) return -1;

        int len = lenStr - lenSub + 1;

        for (int i = 0; i < len; i++) {
            int j = 0;
            for (; j < lenSub; j++) {
                if (str[i + j] != strSub[j]) break;
            }

            if (j == lenSub) return i;
        }
        return -1;
    }
}
