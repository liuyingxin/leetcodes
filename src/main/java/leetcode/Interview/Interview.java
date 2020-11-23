package leetcode.Interview;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Interview {
    //    判定字符是否唯一
    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < astr.length(); i++) {
            set.add(astr.charAt(i));
        }
        return astr.length() == set.size();
    }


    public static void main(String[] args) {
        Boolean isUnique = new Interview().isUnique("sterwa");
        System.out.println("1、是否唯一：" + isUnique);
        isUnique = new Interview().isUnique("aaaalijhn");
        System.out.println("2、是否唯一：" + isUnique);
    }
}
