package stack_and_queue;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 数数组中累积和与最小值的乘积，假设叫做指标A
 * 给定一个数组，请返回子数组中，指标A最大的值。
 *
 *
 *
 * 寻找以当前元素为最小值的子数组，找到左边和右边靠近该元素但小于的值下标（左右边界取不到），
 * 这样，该子数组中的最大A指标=该元素 * 子数组的累加和
 * 通过单调栈的方式找到两边靠近且小于其元素的下标
 *
 * 这道题为啥会有这样的转化? 还没有太理解
 * @author wangcg
 * @date 2022/10/31 16:57
 */
public class IndexA {
    //以当前元素为最小，扩充一个数组，知道左右两边都找到比它小的停止，左右两边都取不到  --->  通过单调栈找左右两边最靠近的小于的元素下标
    //求出当前数组的累加和 当前数组的最小值就是当前元素  乘积为A
    public static int getIndex_A(int[] arr){
        if(arr == null){
            return -1;
        }
        int maxIndexA = Integer.MIN_VALUE;
        Integer[][] minArr = getMinLeftAndRight(arr);
        for(int i = 0;i < minArr.length;i++){
            int L = minArr[i][0] == null ? -1 : minArr[i][0]; //返回null，表示左边没有比其小的，因此左边界在0前面
            int R = minArr[i][1] == null ? arr.length : minArr[i][1]; // 返回null，表示右边没有比其小的，因此有边界在最后一个下标后面
            int sum = 0;
            for(int j = L + 1;j < R;j++){ //以当前元素为最小值的数组的累加和
                sum += arr[j];
            }
            int indexA = sum * arr[i]; //指标A
            System.out.println("i = " + i + " ,indexA = " + indexA);
            maxIndexA = maxIndexA > indexA ? maxIndexA : indexA;
        }
        return maxIndexA;
    }

    //通过一个单调栈 （小到大） 找到以每个元素为最小，左边最靠近和右边最靠近比其的小的值的下标
    public static Integer[][] getMinLeftAndRight(int[] arr){  //arr中没有重复的数
        if(arr == null){
            return null;
        }
        //使用单调栈，栈中存放元素下标，使数组元素依次进栈，但在栈中按照从小到大的排序方式
        Stack<LinkedList<Integer>> stack = new Stack<>();
        Integer[][] res = new Integer[arr.length][2];
        LinkedList<Integer> temp = null; //临时变量
        for(int i = 0;i < arr.length;i++){

            while(!stack.isEmpty() && arr[i] < arr[stack.peek().peekFirst()]){
                temp = stack.pop();
                while(!temp.isEmpty()) {
                    int index = temp.pop();
                    res[index][1] = i; //右边第一个小的下标
                    res[index][0] = stack.isEmpty() ? null : stack.peek().peekFirst(); //左边第一个小的下标
                }
            }

            if (!stack.isEmpty() && arr[i] == arr[stack.peek().peekFirst()]){  //如果有重复的，直接在链表对应后面加
                stack.peek().addLast(i);
            }else {
                temp = new LinkedList<>();
                temp.addFirst(i);
                stack.push(temp);
            }
        }
        while (!stack.isEmpty()){//元素以及遍历到最后，但栈中依然还有元素
            temp = stack.pop();
            while(!temp.isEmpty()) {
                int index = temp.pop();
                res[index][1] = null; //右边第一个大的
                res[index][0] = stack.isEmpty() ? null : stack.peek().peekFirst(); //左边第一个小的
            }
        }
        return res;
    }
}
