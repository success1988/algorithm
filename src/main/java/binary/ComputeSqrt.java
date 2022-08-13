package binary;

import java.math.BigDecimal;

/**
 * 查找一个整数的算术平方根，精确到小数点后6位
 * @author wangcg
 */
public class ComputeSqrt {
    public static void main(String[] args) {
        int n = 2;
        BigDecimal sqrt = computeSqrtOfNum(n);
        System.out.println(n+"的算术平方根为："+sqrt.toPlainString());
    }

    private static BigDecimal computeSqrtOfNum(int n) {
        if(n == 0){
            return BigDecimal.ZERO;
        }

        if(n == 1){
            return BigDecimal.ONE;
        }

        //一、整数部分的查找
        int left = 0;
        int right = n >> 1;
        while(left < right){
            int mid = left+((right-left)>>1);
            if(mid*mid > n){
                right = mid-1;
            }else if(mid*mid < n){
                left = mid+1;
            }else{
                return BigDecimal.valueOf(mid);
            }
        }

        //获取整数部分的结果
        BigDecimal result = BigDecimal.valueOf(left);

        //二、小数部分的查找： 对于小数点后的每一位，从0~9中尝试去选最接近真实值的数字
        //FIXME 这块可以改为0~999999查找小数部分，而不需要逐位查找
        BigDecimal scale = BigDecimal.ONE;
        while(scale.compareTo(BigDecimal.valueOf(0.000001)) > 0){
            scale = scale.divide(BigDecimal.TEN);

            left = 0;
            right = 9;
            while(left <= right){
                int mid = left+((right-left)>>1);
                if(currentSquareResult(result,scale,mid).compareTo(BigDecimal.valueOf(n)) > 0){
                    right = mid-1;
                }else{
                    if(mid == 9 || currentSquareResult(result,scale,mid+1).compareTo(BigDecimal.valueOf(n)) > 0){
                        left = mid;
                        break;
                    }
                    left = mid+1;
                }
            }
            //将该位上的查找结果汇总到最终结果中
            result = scale.multiply(BigDecimal.valueOf(left)).add(result);
        }
        return result;
    }


    /**
     * 试算当前的平方值
     */
    private static BigDecimal currentSquareResult(BigDecimal currentSqrt, BigDecimal scale, int midNum){
        BigDecimal trySqrtValue = scale.multiply(BigDecimal.valueOf(midNum)).add(currentSqrt);
        return trySqrtValue.multiply(trySqrtValue);
    }

}