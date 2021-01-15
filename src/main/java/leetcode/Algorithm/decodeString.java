package leetcode.Algorithm;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 394. 字符串解码
 */
class decodeString {

    int index;
    public String decodeString(String s) {
        //使用递归 使用index标记，递归函数出来的位置，
        int num = 0;

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                num = num * 10 + s.charAt(i) - '0';
            }else if(s.charAt(i) == '['){
                String tmp = decodeString(s.substring(i+1));
                for(int j = 0 ;j < num; j++){
                    sb.append(tmp);
                    num = 0;
                    i = s.length() - index; //从递归函数结尾处开始计算
                }
            }else if(s.charAt(i) == ']'){
                index = s.length() - i; //由于传入的是后半截，这里保存的是和结束位置相对的距离
                break;
            }else{
                sb.append(s.charAt(i));
            }

        }
        return sb.toString();
    }

    public String decodeString2(String s) {
        //中间替换， 最右边的[ 以及从它索引开始的最左边的 ] 计算好以后替换
        StringBuilder sb = new StringBuilder(s);
        while (sb.indexOf("[") != -1) {
            int leftIndex = sb.lastIndexOf("[");
            int rightIndex = sb.indexOf("]", leftIndex);
            String str = sb.substring(leftIndex + 1, rightIndex);
            int i = leftIndex - 1, count = 0, base = 1;
            while (i >= 0 && Character.isDigit(sb.charAt(i))) {
                count += base * (sb.charAt(i) - '0');
                base *= 10;
                i--;
            }
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < count; j++) {
                tmp.append(str);
            }
            sb = sb.replace(i + 1, rightIndex + 1, tmp.toString());
        }
        return sb.toString();
    }

    public static  String decodeString3(String s) {
        //使用双栈 一个记录数字，一个记录数字之前的字符串
        Stack<Integer> iStack = new Stack<>();
        Stack<String> sStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for(int i = 0;i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                num = num * 10 + s.charAt(i) - '0';
            }else if(s.charAt(i) == '['){ //保存双栈必要信息
                iStack.push(num);
                num = 0;
                sStack.push(sb.toString());
                sb = new StringBuilder();
            }else if(s.charAt(i) == ']'){ //结算sb中全部数据
                int n = iStack.pop();
                String tmp = sb.toString();
                for(int j = 1;j < n; j++){ 
                    sb.append(tmp);
                }
                sb = new StringBuilder(sStack.pop()+ sb.toString()); 
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(getlength2( "abca"));
        System.out.println(getlength1( "abca"));
    }


    public static  int  getlength2(String s){
        Set<Character> c= new HashSet<Character>();
        int n= s.length();
        int rk = -1;
        int ans=0;
        for(int i=0;i<n;++i){
            if(i!=0){
                c.remove(s.charAt(i-1));
            }
            while(rk+1<n&& !c.contains(s.charAt(rk+1))){
                c.add(s.charAt(rk+1));
                ++rk;
            }
            ans=Math.max(ans,rk-i+1);
        }
        return ans;

    }
    public static  StringBuilder  getlength1(String s){
        StringBuilder sb = new StringBuilder();
        Stack<Character> ss = new Stack<>();

        if(s.length()==0||s==""){
            sb.append("");
            return sb;
        }
        ss.push(s.charAt(0));
        for(int i=0;i<s.length();++i){
            if(!ss.isEmpty()&&!ss.peek().equals(s.charAt(i))){
                ss.push(s.charAt(i));
            }else {
                ss.pop();
            }
        }
        return sb;
    }


}