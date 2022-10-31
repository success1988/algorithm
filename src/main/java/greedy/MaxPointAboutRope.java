package greedy;

/**
 * 给定一个有序数组arr，代表数轴上个从左到右有n个点arr[0]、arr[1]...arr[n-1]，
 * 给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点。
 *
 *   可以用二分，也可以用滑动窗口
 * @author wangcg
 * @date 2022/10/31 18:59
 */
public class MaxPointAboutRope {


    //方法一：绳子每占一个点，查找大于等于绳子最左边的点，计算个数
    public static int getMaxPointWithRope(int[] arr,int L){
        if(arr == null || arr.length == 0 || L <= 0){
            return -1;
        }
        int maxPointNum = 0;
        int leftIndex = -1;
        for(int i = 0;i < arr.length;i++){
            leftIndex = getLeftIndex(arr,arr[i]-L);
            maxPointNum = Math.max(maxPointNum,i - leftIndex + 1);
        }
        return maxPointNum;
    }

    //对于有序数组arr，找到 大于等于num 的最左一个数
    public static int getLeftIndex(int[] arr,int num){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int middle = (left + right) / 2;
            if(arr[middle] > num){
                right = middle - 1;
            }else if(arr[middle] < num){
                left = middle + 1;
            }else if(arr[middle] == num){
                return middle;
            }
        }
        return left; //如果二分没有找到那个数，那么现在left指针肯定指在大于等于这个数的下标上
    }

    //方法二：滑动窗口的办法，绳子左端先固定，查看右边距离是否大于数组元素，大于可以，小于则长度不够，结束
    public static int getMaxPointNum(int[] arr,int L){
        if(arr == null || arr.length == 0 || L <= 0){
            return -1;
        }
        int maxPointNum = 0;  //记录最大点数
        int index = -1;
        for(int i = 0;i < arr.length;i++){
            index = i;
            int num = 0;
            while (index < arr.length && arr[i] + L >= arr[index]){
                num++;
                index++;
            }
            maxPointNum = Math.max(maxPointNum,num);
        }
        return maxPointNum;
    }

}
