package without_categories;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
 
/**
 * 给定一个数组arr，求差值为k的去重数字对。
 */
public class Code01_DifferentValueEqualsK {
    
    public static List<List<Integer>> allPair(int[] arr,int k){
        HashSet<Integer> hashSet = new HashSet<>();  //哈希表不包含重复值
        for(int i = 0;i < arr.length;i++){
            hashSet.add(arr[i]);
        }
        List<List<Integer>> list = new ArrayList<>();
        for(Integer i : hashSet){
            if(hashSet.contains(i + k)){
                list.add(Arrays.asList(i,i + k));
            }
        }
        return list;
    }
}