package leetcode.Interview;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Interview {
    /**
     * 面试题 01.01. 判定字符是否唯一
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     */
    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < astr.length(); i++) {
            set.add(astr.charAt(i));
        }
        return astr.length() == set.size();
    }

    /**
     * 面试题 01.02. 判定是否互为字符重排
     * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     */

    public boolean CheckPermutation(String s1, String s2) {

        Boolean s = false;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = s2.length(); j < 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    s = true;
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        Boolean isUnique = new Interview().isUnique("sterwa");
        System.out.println("1、是否唯一：" + isUnique);
        isUnique = new Interview().isUnique("aaaalijhn");
        System.out.println("2、是否唯一：" + isUnique);

        Boolean  checkPermutation = new Interview().CheckPermutation("acc","cca");
        System.out.println("3、是否重排" + checkPermutation);
        checkPermutation = new Interview().CheckPermutation("acc","cll");
        System.out.println("4、是否重排" + checkPermutation);
    }
}
