package stack_and_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个不含有重复值的数组 arr，
 * 找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i]小的位置。
 * 返回所有位置相应的信息。
 *
 * 比如：
 * 输入： arr = [3,4,1,5,6,2,7]
 * 返回：{[-1, 2], [0, 2], [-1, -1], [2, 5], [3, 5], [2, -1], [5, -1]}
 *
 *这个题目利用单调栈结构可以使时间复杂度降低到O(N)。
 * 首先题目是没有重复值的数组，注意，如果是有重复值的数组稍有区别。
 * 另外，求比两边都大的位置可以用从栈顶到栈底递增的结构
 */
public class GetNearLessNoRepeat {

    public static int[][] getNearLessNoRepeat(int[]arr){
        int[][]res = new int[arr.length][2];
        Stack<Integer> s = new Stack<>();
        //循环遍历数组元素
        for (int i = 0; i < arr.length; i++) {
            //这段代码就是违反了单调栈原则之后产生的弹出元素操作
            while(!s.isEmpty() && arr[i] <  arr[s.peek()]){
                int popIndex = s.pop();
                //如果栈中只有一个元素，且被弹出了，则它的左侧要求的值为-1，
                //否则就是弹出之后栈中还有元素，则它的左侧要求的值为弹出值的下面一个元素
                int leftLessIndex = s.isEmpty() ? -1 : s.peek();
                res[popIndex][0] = leftLessIndex;
                //右侧值为当前要压入的元素位置
                res[popIndex][1] = i;
            }
            s.push(i);
        }
        //当所有元素全部压入，部分弹出时，栈中还剩余一部分元素
        while(!s.isEmpty()){
            int popIndex = s.pop();
            int leftLessIndex = s.isEmpty() ? -1 : s.peek();
            res[popIndex][0] = leftLessIndex;
            //此时栈中所有元素右侧都没有要求的值了，所以都为-1
            res[popIndex][1] = -1;
        }
        return res;
    }


    public static void main(String[] args) {
        int []arr = new int[]{3,4,1,5,6,2,7};
        System.out.println(Arrays.deepToString(getNearLessNoRepeat(arr)));
    }
}
