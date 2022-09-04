package greedy;

import java.util.PriorityQueue;

/**
 * 输入： 参数1，正数数组costs    costs[i]表示i号项目的花费
 *       参数2，正数数组profits  profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 *       参数3，正数k     k表示你不能并行、 只能串行的最多做k个项目
 *       参数4，正数m     m表示你初始的资金
 *       说明： 你每做完一个项目， 马上获得的收益， 可以支持你去做下一个 项目。
 *
 * 输出： 你最后获得的最大钱数
 *
 * Input: Costs=[0,1,1], Profits=[1,2,3], k=2, m=0
 * Output: 4
 *
 * @author admin
 * @date 2022/9/4 7:13
 */
public class IPO {

    public static class Project{
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }


    public static int maxMoney(int[] costs, int[] profits, int k, int m){

        //按花费组织的小顶堆，用于存放剩余的项目
        PriorityQueue<Project> remainMinCostHeap = new PriorityQueue<>((p1,p2) -> p1.cost - p2.cost);
        //按利润组织的大顶堆，用于存放解锁的项目
        PriorityQueue<Project> unlockMaxProfitHeap = new PriorityQueue<>((p1,p2) -> p2.profit - p1.profit);

        for (int i = 0; i < costs.length; i++) {
            Project project = new Project(costs[i], profits[i]);
            remainMinCostHeap.add(project);
        }

        int currentMoney = m;
        int projectNum = 0;
        do{
            //如果剩余项目集合非空，并且现有金额可以覆盖堆顶项目的cost，那就将其解锁，加入大顶堆
            while (!remainMinCostHeap.isEmpty() && remainMinCostHeap.peek().cost <= currentMoney){
                unlockMaxProfitHeap.add(remainMinCostHeap.poll());
            }
            //如果大顶堆为空，则表示当前无项目被解锁，只能结束
            if(unlockMaxProfitHeap.isEmpty()){
               break;
            }
            Project toDoProject = unlockMaxProfitHeap.poll();
            currentMoney += toDoProject.profit;
            projectNum++;
        }while (projectNum < k);

        return currentMoney;
    }

    public static void main(String[] args) {

        int maxMoney = maxMoney(new int[]{0, 1, 1}, new int[]{1, 2, 3}, 2, 0);
        System.out.println("maxMoney:"+maxMoney);
    }



}
