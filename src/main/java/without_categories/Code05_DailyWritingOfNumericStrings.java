package without_categories;
 
/**
 * 给定一个字符串，如果该字符串符合人们日常书写一个整数的形式，返回int类型的这个数；
 * 如果不符合或者越界返回-1或者报错。
 */
public class Code05_DailyWritingOfNumericStrings {
 
    public int convert(String str){
        if(str == null || "".equals(str)){
            return 0;
        }
        char[] chars = str.toCharArray();
        if(!isVaild(chars)){
            throw new RuntimeException("can not convert");
        }
        boolean neg = chars[0] == '-' ? true : false; //是否有负号
        // 转换用负数表示，因为负数的绝对值范围比正数的绝对值范围大一个
        // 数字转换过程中，可能会存在溢出的情况
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for(int i = neg ? 1 : 0;i < chars.length;i++){ //负数的有效下标从1开始，正数从0开始
            cur = '0' - chars[i];  //以负数的形式接着
            //判断是否会溢出
            if(res < minq || (res == minq && cur < minr)){
                throw new RuntimeException("can not convert");
            }
            res = res * 10 + cur;
        }
        // res数转换数字绝对值的负数表达，
        // Integer.MIN_VALUE  -2147483648
        // Integer.MAX_VALUE  2147483647
        // 2147483648越界 -> res=-2147483648不越界
        if(!neg && res == Integer.MIN_VALUE){
            throw new RuntimeException("can not convert");
        }
        return neg ? res : -res;
    }
 
    //判断是否是有效数字表达式
    public boolean isVaild(char[] chars){
        if(chars[0] != '-' && chars[0] < '0' || chars[0] > '9'){
            return false;
        }
        if(chars[0] == '-' && (chars.length == 1 || chars[1] == '0')){
            return false;
        }
        for (int i = 1;i < chars.length;i++){
            if(chars[i] < '0' || chars[i] > '9'){
                return false;
            }
        }
        return true;
    }
}