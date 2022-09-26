package dynamic_programming;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组arr：表示几个咖啡机，这几个咖啡机生产一杯咖啡所需要的时间就是数组中的值，例如arr=[2,3,7]就表示第一台咖啡机生产一杯咖啡需要2单位时间，第二台需要3单位时间，第三台需要7单位时间。
 * int N：表示有N个人需要用咖啡机制作咖啡，每人一杯，同时，假设制作完咖啡后，喝咖啡时间为0，一口闷。
 * int a：表示用洗碗机洗一个咖啡杯需要的时间，串行运行。
 * int b：表示咖啡杯也可以不洗，自然晾干的时间，并行运行
 * 咖啡杯要么洗，要么晾干。
 * 现在，请你求出这N个人从开始用咖啡杯制作咖啡到杯子洗好或者晾干的最少时间？
 *
 *  分析： 问题分为两阶段：
 *    第一阶段：煮咖啡，需要考虑的是怎么样安排才能让这N个人都尽早喝上咖啡呢?
 *    第二阶段：洗/晾杯子,
 * @author wangcg
 * @date 2022/9/26 11:03
 */
public class CoffeeMachine {

    public static void main(String[] args) {
        int[] arr = {2,3,6,4,7,13};
        int N = 30;
        int a = 3;
        int b = 7;


        int allTime = computeAllTime(arr, N, a, b);
        System.out.println("【贪心+暴力递归】从开始到杯子全部干净的最少耗时为:"+allTime);

        int allTimeDp = computeAllTimeDp(arr, N, a, b);
        System.out.println("【贪心+动态规划】从开始到杯子全部干净的最少耗时为:"+allTimeDp);
    }


    public static class CoffeeMaker{
        //闲置下来的时间点
        int timePoint;
        //煮好一杯☕的耗时
        int workTimeCost;

        public CoffeeMaker(int cost){
            this.workTimeCost = cost;
        }
    }


    private static int computeAllTime(int[] arr, int n, int a, int b) {
        //计算n个小人喝上☕的时间点数组
        int[] drinkTimePoints = computeDrinkTime(arr,n);
        //计算n个小人洗晾完☕杯子的最小耗时
        //int allTime = process1(drinkTimePoints, a, b, 0, 0, 0);

        //由于 drinkTimePoints是升序的，而自然挥发的耗时的固定的，所以自然挥发时间点dryTimePoint【=drinkTimePoints[index]+b】这个参数也是升序的，
        //故而将自然挥发时间点dryTimePoint这个参数向后传递用于PK时，总是位置靠后的元素获胜，所以没有必要向后传递了
        int allTime = process1WithoutDryTimePoint(drinkTimePoints, a, b, 0, 0);

        return allTime;
    }


    /**
     * 计算各个小人喝上咖啡的时间点数组
     * @param arr 咖啡机煮一杯咖啡的耗时数组
     * @param n  等着喝咖啡的小人儿数量
     * @return 各个小人喝上咖啡的时间点数组，  由于该数组是从前到后安排的所以该数组是【升序】的
     */
    private static int[] computeDrinkTime(int[] arr, int n) {
        //小根堆 按照“闲置时间点+煮一杯咖啡耗时”进行组织
        //统计每台咖啡机最早空闲时间，然后对最早空闲时间排序，每次让最早结束的咖啡机先服务。
        PriorityQueue<CoffeeMaker> minHeap = new PriorityQueue<>((x,y) -> {
            return (x.timePoint+x.workTimeCost) - (y.timePoint+y.workTimeCost);
        });

        //初始化咖啡机
        for (int i = 0; i < arr.length; i++) {
            CoffeeMaker maker = new CoffeeMaker(arr[i]);
            minHeap.add(maker);
        }

        //用贪心算法给n个小人安排咖啡机
        int[] drinkTimePoints = new int[n];
        for (int i = 0; i < n; i++) {
            //选择当前等待时间最短的咖啡机
            CoffeeMaker bestMaker = minHeap.poll();
            bestMaker.timePoint += bestMaker.workTimeCost;
            drinkTimePoints[i] = bestMaker.timePoint;
            minHeap.add(bestMaker);
        }

        return drinkTimePoints;
    }


    /**
     * 从index开始，到所有杯子都洗晾结束的总耗时
     * @param drinkTimePoints   n个小人喝上☕的时间点数组
     * @param a 洗碗机洗一个杯子的耗时
     * @param b 杯子自然晾干的耗时
     * @param index 当前小人的索引
     * @param cleanMachineTimePoint  洗碗机闲置下来的时间点
     * @param dryTimePoint  晾干的时间点
     * @return 从index开始，到所有杯子都洗晾结束的总耗时
     */
    private static int process1(int[] drinkTimePoints, int a, int b, int index, int cleanMachineTimePoint, int dryTimePoint) {
        if(index == drinkTimePoints.length-1){
            //洗干
            cleanMachineTimePoint = Math.max(drinkTimePoints[index], cleanMachineTimePoint)+a;
            //晾干
            dryTimePoint = Math.max(drinkTimePoints[index] + b, dryTimePoint);
            return Math.min(cleanMachineTimePoint, dryTimePoint);
        }

        //当前这个小人可以有两种选择，要么洗杯子，要么自然晾干
        //①当前这个小人选择洗杯子
        int nextCleanMachineTimePoint = Math.max(drinkTimePoints[index], cleanMachineTimePoint)+a;
        int restAllTime1 = process1(drinkTimePoints, a, b, index+1, nextCleanMachineTimePoint, dryTimePoint);
        int p1 = Math.max(nextCleanMachineTimePoint, restAllTime1);

        //②当前这个小人选择自然晾干
        int autoDryTimePoint = drinkTimePoints[index] + b;
        int restAllTime2 = process1(drinkTimePoints, a, b, index+1, cleanMachineTimePoint, Math.max(autoDryTimePoint, dryTimePoint));
        int p2 = Math.max(autoDryTimePoint, restAllTime2);

        return Math.min(p1, p2);
    }


    /**
     * 从index开始，到所有杯子都洗晾结束的总耗时
     * @param drinkTimePoints   n个小人喝上☕的时间点数组
     * @param a 洗碗机洗一个杯子的耗时
     * @param b 杯子自然晾干的耗时
     * @param index 当前小人的索引
     * @param cleanMachineTimePoint  洗碗机闲置下来的时间点
     * @return 从index开始，到所有杯子都洗晾结束的总耗时
     */
    private static int process1WithoutDryTimePoint(int[] drinkTimePoints, int a, int b, int index, int cleanMachineTimePoint) {
        if(index == drinkTimePoints.length){
            return 0;
        }

        //当前这个小人可以有两种选择，要么洗杯子，要么自然晾干
        //①当前这个小人选择洗杯子
        int cleanTimePoint = Math.max(drinkTimePoints[index], cleanMachineTimePoint)+a;
        int restAllTime1 = process1WithoutDryTimePoint(drinkTimePoints, a, b, index+1, cleanTimePoint);
        //由于要确保所有的杯子都干净，所以取Max(当前杯子洗干净的时间, 当前杯子选择“洗”时剩余杯子干净的最短时间)
        int p1 = Math.max(cleanTimePoint, restAllTime1);

        //②当前这个小人选择自然晾干
        int autoDryTimePoint = drinkTimePoints[index] + b;
        int restAllTime2 = process1WithoutDryTimePoint(drinkTimePoints, a, b, index+1, cleanMachineTimePoint);
        //由于要确保所有的杯子都干净，所以取Max(当前杯子晾干净的时间, 当前杯子选择“晾”时剩余杯子干净的最短时间)
        int p2 = Math.max(autoDryTimePoint, restAllTime2);

        return Math.min(p1, p2);
    }



    //====================================动态规划计算洗晾时间========================

    private static int computeAllTimeDp(int[] arr, int n, int a, int b) {
        //计算n个小人喝上☕的时间点数组
        int[] drinkTimePoints = computeDrinkTime(arr,n);
        int allTimeDp = processDp(drinkTimePoints, a, b);

        return allTimeDp;
    }

    private static int processDp(int[] drinkTimePoints, int a, int b) {
        int n = drinkTimePoints.length;
        int limit = 0;
        // 为啥最大值是  所有的杯子都用机器洗 ?
        for (int i = 0; i < n; i++) {
            limit = Math.max(limit, drinkTimePoints[i])+a;
        }

        int[][] dp = new int[n+1][limit+1];
        //dp[n][...] 均为0
        for (int index = n-1; index >= 0; index--) {
            for (int cleanMachineTimePoint = 0; cleanMachineTimePoint <= limit; cleanMachineTimePoint++) {
                //当前这个小人可以有两种选择，要么洗杯子，要么自然晾干
                //①当前这个小人选择洗杯子
                int cleanTimePoint = Math.max(drinkTimePoints[index], cleanMachineTimePoint)+a;
                //避免下标越界异常，因为limit已然是最大值，cleanTimePoint竟然超过了limit，显然是无需考虑的垃圾数据
                if(cleanTimePoint > limit){
                    continue;
                }
                int restAllTime1 = dp[index+1][cleanTimePoint];
                //由于要确保所有的杯子都干净，所以取Max(当前杯子洗干净的时间, 当前杯子选择“洗”时剩余杯子干净的最短时间)
                int p1 = Math.max(cleanTimePoint, restAllTime1);

                //②当前这个小人选择自然晾干
                int autoDryTimePoint = drinkTimePoints[index] + b;
                int restAllTime2 = dp[index+1][cleanMachineTimePoint];
                //由于要确保所有的杯子都干净，所以取Max(当前杯子晾干净的时间, 当前杯子选择“晾”时剩余杯子干净的最短时间)
                int p2 = Math.max(autoDryTimePoint, restAllTime2);

                dp[index][cleanMachineTimePoint] =  Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


}