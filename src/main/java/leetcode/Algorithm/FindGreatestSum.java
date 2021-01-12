package leetcode.Algorithm;

/**
 * 最大连续子序列和
 */
public class FindGreatestSum {
    public static void main(String[] args) {
        int[] array = {6,8,-2,7,-15,1,2,2};
        System.out.println(FindGreatestSumOfSubArray(array));
        System.out.println(maxSubArray(array));

    }
    public static int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length <= 0){
            return 0;
        }
        int temp = array[0];
        int num = array[0];
        for (int i = 1; i < array.length; i++){
            if (temp >= 0){
                temp+=array[i];
            }else{
                temp = array[i];
            }
            if (temp > num){
                num = temp;
            }
        }
        return num;
    }
    public static int maxSubArray(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int max =nums[0];
        for (int i =1;i<nums.length;i++){
            nums[i] +=Math.max(nums[i-1],0);
            max =Math.max(nums[i],max);
        }
        return max;
    }
}
