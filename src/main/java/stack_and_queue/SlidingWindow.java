package stack_and_queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 问题：有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 *
 * eg:数组为[4,3,5,4,3,3,6,7],窗口大小为3时：
 *
 * [4,3,5],4,3,3,6,7    窗口中最大值为5
 * 4,[3,5,4],3,3,6,7    窗口中最大值为5
 * 4,3,[5,4,3],3,6,7    窗口中最大值为5
 * 4,3,5,[4,3,3],6,7    窗口中最大值为4
 * 4,3,5,4,[3,3,6],7    窗口中最大值为6
 * 4,3,5,4,3,[3,6,7]    窗口中最大值为7
 *
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数。输入：整型数组arr，窗口大小为w。
 * 输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的以本题为例，结果应该返回[5,5,5,4,6,7]

 * @author wangcg
 * @date 2022/10/31 12:37
 */
public class SlidingWindow {

    public static void main(String[] args) {
        int[] arr = new int[]{4,3,5,4,3,3,6,7};
        int[] maxWindowNum = getMaxWindowNum(arr, 3);
        System.out.println(Arrays.toString(maxWindowNum));
    }
    //w：窗口大小
    //使用双向队列  队列中存放数组的下标，担保证队列中对应数组元素从大到小
    //我们把从队首到队尾单调递减或递增的队列称之为单调队列
    public static int[] getMaxWindowNum(int[] arr,int w){
        if(arr == null || arr.length < w){
            return null;
        }
        //创建一个双向队列，保存窗口滑动时经过的数据
        Deque<Integer> arrDeque = new ArrayDeque<>();
        int[] res = new int[arr.length - w + 1];//窗口滑动过程产生的结果集
        int i = 0;
        for(;i < arr.length;i++){
            //当前比对元素如果比队列尾元素小，直接添加，否则依次移除最后一个元素，直到队尾元素大于比对元素
            while(!arrDeque.isEmpty() && arr[arrDeque.getLast()] <= arr[i]){
                arrDeque.removeLast();
            }
            arrDeque.addLast(i);
            if(i - w == arrDeque.getFirst()){//左移时,原左边界是否时最大的数
                arrDeque.removeFirst();
            }
            if(i - w + 1 >= 0) {  //放入窗口的的最大值
                res[i - w + 1] = arr[arrDeque.getFirst()];
            }
        }
        return res;
    }
}
