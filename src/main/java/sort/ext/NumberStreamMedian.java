package sort.ext;

import java.util.PriorityQueue;

/**
 * 数据流的中位数
 * @author admin
 * @date 2022/9/3 10:41
 */
public class NumberStreamMedian {

    /**
     * 小顶堆 存在较大的数字
     */
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    /**
     * 大顶堆 存在较小的数字
     */
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);


    public void addNumber(int num){

        //1.进堆
        if(maxHeap.isEmpty()){
            maxHeap.offer(num);
        }else{
            if(num <= maxHeap.peek()){
                maxHeap.offer(num);
            }else {
                minHeap.offer(num);
            }
        }

        //2.两个堆的数量差达到2时，要把size较大的那个堆的堆顶弹出，加入到另外一个堆中
        //如果小顶堆的个数比大顶堆的个数多2个及2个以上
        if(minHeap.size() - maxHeap.size() >= 2){
            maxHeap.offer(minHeap.poll());
        }else if(maxHeap.size() - minHeap.size() >= 2){
            minHeap.offer(maxHeap.poll());
        }
    }


    /**
     * 返回当前的中位数
     * @return 当前的中位数
     */
    public double getCurrentMedian(){
        if(minHeap.isEmpty() && maxHeap.isEmpty()){
           throw new RuntimeException("当前还没有输入数字");
        }
        if(minHeap.size() == maxHeap.size()){
            return (double) (minHeap.peek()+maxHeap.peek())/2;
        }else if(minHeap.size() > maxHeap.size()){
            return minHeap.peek();
        }else{
            return maxHeap.peek();
        }
    }


    public static void main(String[] args) {

        NumberStreamMedian median = new NumberStreamMedian();
        median.addNumber(1);
        median.addNumber(2);
        median.addNumber(3);
        median.addNumber(4);
        median.addNumber(5);
        median.addNumber(6);
        median.addNumber(7);
        median.addNumber(8);
        System.out.println(median.getCurrentMedian());
    }

}
