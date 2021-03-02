package leetcode.Algorithm.number;

/**
 * 给定一个有n个不同数字，是0-n，其中有一个数字是缺少的，找出这个数字，最好是线性的时间结构，不使用额外的内存空间
 *
 *
 */
public class MissingNum {

        public static int missingNumber ( int[] nums){
            int res = (nums.length) * (nums.length + 1) / 2;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];

            }
            return res - sum;

        }

        //与法
        public static int missingNumber2 ( int[] nums){
            int res = nums.length;
            for (int i = 0; i < nums.length; i++) {
                res ^= nums[i];
                res ^= i;
            }
            return res;
        }

    public static void main(String[] args) {
//        int[] nums = {9, 6, 4, 2, 8, 5, 7, 0, 1};
        int[] nums = {0,1, 2};
        System.out.println(missingNumber(nums));
        System.out.println(missingNumber2(nums));
    }
}