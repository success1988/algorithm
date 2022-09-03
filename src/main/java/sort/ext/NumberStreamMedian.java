package sort.ext;

import java.util.PriorityQueue;

/**
 * 数据流的中位数
 * @author admin
 * @date 2022/9/3 10:41
 */
public class NumberStreamMedian {

    // 1  2  3  4  5
    /**
     * 小顶堆 存在较大的数字
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    /**
     * 大顶堆 存在较小的数字
     */
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>();


    public void addNumber(int num){
        //默认放到小顶堆
        if(!maxHeap.isEmpty() && num > maxHeap.peek()){
            minHeap.offer(num);
            //如果小顶堆的个数比大顶堆的个数多2个及2个以上
            if(minHeap.size() - maxHeap.size() >= 2){
                maxHeap.offer(minHeap.poll());
            }
        }else {
            maxHeap.offer(num);
        }
    }


    /**
     * 返回当前的中位数
     * @return 当前的中位数
     */
    public int getCurrentMedian(){
        if(minHeap.isEmpty() && maxHeap.isEmpty()){
            return -1;
        }
        return minHeap.isEmpty()?maxHeap.peek():minHeap.peek();
    }


    public static void main(String[] args) {
        NumberStreamMedian median = new NumberStreamMedian();
        median.addNumber(1);
        median.addNumber(2);
        median.addNumber(3);
        median.addNumber(4);
        median.addNumber(5);
        System.out.println(median.getCurrentMedian());
    }

}
