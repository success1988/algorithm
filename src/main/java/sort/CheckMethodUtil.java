package sort;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * 对数器
 * @Author admin
 * @Date 2022/8/6
 */
public class CheckMethodUtil {

    public static void compareMethod(Consumer<int[]> testMethod, Consumer<int[]> rightMethod){
        int testTime = 50000;
        int size = 10;
        int value = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++){
            //生成随机的入参
            int[] arr = generateRandomArray(size, value);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            //分别调用两个方法：testMethod和rightMethod
            testMethod.accept(arr1);
            rightMethod.accept(arr2);
            //对比两者的结果
            if (!isEqual(arr1, arr2)){
                succeed = false;
                System.out.println(Arrays.asList(arr));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "error----");
    }

    private static int[] copyArray(int[] arr) {
        if(arr == null){
            return null;
        }
        int[] copyArr = new int[arr.length];
        if(arr.length == 0){
            return copyArr;
        }
        System.arraycopy(arr, 0 , copyArr, 0, arr.length);
        return copyArr;
    }

    // 随机数生成器
    public static int[] generateRandomArray(int size, int value){
        //Math.random() -> double [0,1)
        //(int) ((size + 1) * Math.random()) -> [0,size]整数
        // 生成长度随机[0, size]的数组
        int[] arr = new int[(int) ((size+1) * Math.random())];
        for (int i = 0; i < arr.length; i++){
            // 一个随机数减去另一个随机数，生成[-value, value]的随机数
            arr[i] = (int) ((value+1) * Math.random()) - (int) (value * Math.random());
        }
        return arr;
    }


    // 判断两个数组是否相等
    public static boolean isEqual(int[] arr1, int[] arr2){
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if (arr1 == null && arr2 == null){
            return true;
        }

        if (arr1.length != arr2.length){
            return  false;
        }

        for (int i = 0; i < arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }
}
