package without_categories;
 
/**
 * 实现算法找到[1,n]中所有未出现在A中的整数。
 */
public class FindEmptyNumber {
    
    public void printEmptyNumber(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        for (int value : arr){ //i位置上的数不是i+1，给它移到对应的位置
            modify(value,arr);
        }
        for (int i = 0;i < arr.length;i++){
            if(arr[i] != i + 1){
                System.out.print((i + 1) + "\t");
            }
        }
        System.out.println();
    }
    
    //对应数组i位置上放i+1
    public void modify(int value,int[] arr){
        while (arr[value - 1] != value){
            int temp = arr[value - 1];
            arr[value - 1] = value;
            value = temp;
        }
    }
}