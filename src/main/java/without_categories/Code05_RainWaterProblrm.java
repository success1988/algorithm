package without_categories;
 
/**
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水。
 * eg：arr=[3,1,2,5,2,4]，根据值画出的直方图就是容器形状，该容器可以装下5格水
 * eg：arr=[4,5,1,3,2]，该容器可以装下2格水
 */
public class Code05_RainWaterProblrm {
 
    //看i位置的左侧最大值，右侧最大值，判断其可以接多少雨水
    //左右两边同时进行判断，那边的最大值小，进行那边的判断
    public int getMaxRain(int[] arr){  //arr中元素均是非负的
        if(arr == null || arr.length < 3){
            return 0;
        }
        //arr[0] 和 arr[arr.length - 1]位置是没有办法接雨水的，直接略过即可
        int maxLeft = arr[0];
        int maxRight = arr[arr.length - 1];
        int left = 1;
        int right = arr.length - 2;
        int rainAll = 0;  //记录雨水的总量
        while (left <= right){
            if(maxLeft >= maxRight){  //右边的最大值小，先进行右边的判断
                rainAll += Math.max(maxRight - arr[right],0);
                maxRight = Math.max(maxRight,arr[right]);
                right--;
            }else { //左边的最大值小，先判断左边
                rainAll += Math.max(maxLeft - arr[left],0);
                maxLeft = Math.max(maxLeft,arr[left]);
                left++;
            }
        }
        return rainAll;
    }
}