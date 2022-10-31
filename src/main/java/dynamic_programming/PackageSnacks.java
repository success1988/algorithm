package dynamic_programming;
 
import org.junit.Test;
 
import java.util.Arrays;
 
/**
 * 牛牛准备参加学校组织的春游，出发前牛牛准备往背包中装入一些零食，牛牛的背包容量为w。
 * 牛牛家里一共有n袋零食。第i袋零食体积为v[i]
 * 牛牛想知道在总体积不超过背包容量的情况下，他一共有多少种零食放法(总体积为0也算一种)
 */
public class PackageSnacks {
 
    //v 零食体积数组 一共n长度
    //w 背包容量
    //返回背包可以装零食的方法总数
    public int getAll(int[] v,int w){
        if(w == 0){
            return 0;
        }
        int sum = 0;
        for(int i : v){
            sum += i;
        }
        if(sum <= w){ //背包容量比所有零食总和都要大，直接是2^n次
            return (int) Math.pow(2,v.length);
        }
        int res = 0;
        for (int i = 1;i <= w;i++){
            res += process(v,0,i,0);
        }
        return res;
    }
 
    //v 零食体积数组
    //index 当前在第index位置的零食，是否要装下
    //capacity 背包容量
    //total  当前已经装的容量
    public int process(int[] v,int index,int capacity,int total){
        if(index == v.length){
            return total == capacity ? 1 : 0;
        }
        int res = 0;
        //每个index都有放和不放的可能，放进背包的话 要满足当前背包容量加index物品的重量小于背包容量 才能放入
        if(v[index] + total <= capacity){
            res += process(v,index + 1,capacity,v[index] + total);
        }
        res += process(v,index + 1,capacity,total);
        return res;
    }
 
    @Test
    public void test(){
        int[] v = {4,1,5,2,1};
        int all = getAll(v, 8);
        System.out.println(all);
    }
}