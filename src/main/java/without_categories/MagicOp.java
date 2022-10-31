package without_categories;
 
import java.util.Arrays;
import java.util.HashSet;
 
/**
 *   给一个包含n个整数元素的集合a，一个包含m个元素的集合b。
 *   定义一个magic操作位，从一个集合中取出一个元素，放到另一个集合里，且操作过后每个集合的平均值都大于操作前。
 */
public class MagicOp {
    
    //请保证arr1无重复值，arr2无重复值，且arr1和arr2肯定有数字
    public static int maxOps(int[] arr1,int[] arr2){
        double sum1 = 0;
        for(int i = 0;i < arr1.length;i++){
            sum1 += (double)arr1[i];
        }
        double sum2 = 0;
        for(int i = 0;i < arr2.length;i++){
            sum2 += (double)arr2[i];
        }
        //如果两个数组的平均值相等，则没有办法继续进行magic操作
        if(avg(sum1,arr1.length) == avg(sum2,arr2.length)){
            return 0;
        }
        //两个数组的平均值不相等，平均值小的数组不可以拿数出来
        //只有平均值大的数组 才能拿数据 放入小的平均值数组  并且不能拿小数组中已有的值
        //拿的数据范围     小的平均值  < x <  大的平均值  （拿其中较小的）
        int[] arrBig = null;
        int[] arrSmall = null;
        double sumBig = 0;
        double sumSmall = 0;
        // 把平均值大的数组给arrBig，平均值小的给arrSmall
        if(avg(sum1,arr1.length) > avg(sum2,arr2.length)){
            arrBig = arr1;
            arrSmall = arr2;
            sumBig = sum1;
            sumSmall = sum2;
        }else {
            arrBig = arr2;
            arrSmall = arr1;
            sumBig = sum2;
            sumSmall = sum1;
        }
        //把   小的平均值 < x < 大的平均值  中最小的取出，且在小的数组中没出现过的数
        Arrays.sort(arrBig);  //大平均值数组排序，方便取数
        HashSet<Integer> setSmall = new HashSet<>(); //保存小平均值的数据，保证后序加入的每个数不重复
        for(Integer i : arrSmall){
            setSmall.add(i);
        }
        int magicOps = 0;  //记录一共操作多少次
        // 注意操作过后两个数组的平均值都是要变大的
        int bigSize = arrBig.length;
        int smallSize = arrSmall.length;
        //大平均值的数据已经从小到大排序，而平均值是逐渐增大的，因此可以直接从左向右挑即可
        for (int i = 0;i < arrBig.length;i++){ 
            double cur = (double)arrBig[i];
            if(cur < avg(sumBig,bigSize) 
                    && cur > avg(sumSmall,smallSize) 
                    && !setSmall.contains(cur)){
                bigSize--; //大数组拿出当前数据，总和减少
                sumBig -= cur; 
                smallSize++;  //小数组得到当前数据，总和增加
                sumSmall += cur;
                setSmall.add(arrBig[i]);  //将数据记录在小数组中
                magicOps++;  //当前操作加一
            }
        }
        return magicOps;
    }
    
    public static double avg(double sum,int length){
        return sum / (double) length;
    }
}