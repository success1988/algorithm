package make_charts_skill;
 
/**
 * 牛羊吃草问题
 * 先手和后手均可以以4的倍数进行吃草，给定一份n的草，问谁赢？
 */
public class Eat {
 
    // n份青草放在一起
    // 先手后手都决定聪明
    // string "先手" "后手"   都可以选择吃 1、4、16 、4^n 份
    public static String winner1(int n){
        // 0  1  2  3  4
        // 后 先  后 先 先
        if(n < 5){  //base case
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        // n >= 5 时
        int base = 1;
        while(base <= n){
            // 当前一共n份草，先手吃掉的是base份，n-base 是留给后手的草
            // 母过程 先手   在子过程里面是  后手
            if(winner1(n - base).equals("后手")){
                return "先手";
            }
            if(base > n * 4){  //防止base*4溢出
                break;
            }
            base *= 4;    //以4的倍数吃草
        }
        return "后手";
    }
 
    //  根据winner1() 方法的输出规律得到以下的方法
    public static String winner2(int n){
        if(n % 5 == 0 || n % 5 == 2){
            return "后手";
        }else {
            return "先手";
        }
    }
}