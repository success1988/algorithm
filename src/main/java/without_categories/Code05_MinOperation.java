package without_categories;
 
/**
 * 假设s和m初始化，s=”a”;m=s;
 * 再定义两种操作，第一种操作：
 * m=s;
 * s=s+s;
 * 第二种操作：
 * s=s+m;
 * 求最小的操作步骤数，可以将s拼接到长度等于n
 */
public class Code05_MinOperation {
    
    //判断n是不是质数  n >= 2
    public boolean isPrim(int n){
        for(int i = 2;i < n;i++){  //质数除了自己和1的乘积，其余没有
            while (n % i == 0){
                return false;
            }
        }
        return true;
    }
    
    public int[] divsSumAndCount(int n){
        int sum = 0;
        int count = 0;
        for(int i = 2;i <= n;i++){  //求出n分解为质数的乘积，质数之和，以及质数的个数
            while (n % i == 0){
                sum += i;
                count++;
                n /= i;
            }
        }
        return new int[]{sum,count};
    }
 
    //如果n时质数，调用操作二就是最小的操作步骤
    //n = a * b * c * d  合数分解为质数的乘积
    public int minOps(int n){
        if(n < 2){
            return 0;
        }
        if(isPrim(n)){
            return n - 1;
        }
        int[] divsSumAndCount = divsSumAndCount(n);
        return divsSumAndCount[0] - divsSumAndCount[1];
    }
}