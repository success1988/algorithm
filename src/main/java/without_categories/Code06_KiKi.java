package without_categories;

/**
 * CC里面有一个土豪很喜欢一位女直播KiKi唱歌，平时就经常给她点赞、送礼、私聊。最近CC直播平台在举行中秋之星主播歌唱比赛，假设一开始该女主播的初始人气值为start，能够晋升下一轮人气需要刚好达到end，土豪给主播增加人气的可以采取的方法有：
 *
 * a.       点赞  花费x  C币，人气  +2
 *
 * b.       送礼  花费y  C币，人气  *2
 *
 * c.       私聊  花费z  C币，人气  -2
 *
 * 其中 end远大于start且end为偶数，请写一个程序帮助土豪计算以下，最少花费多少C币就能帮助该主播KiKi将人气刚好达到end，从而能够晋升下一轮？
 *
 * 输入描述：
 *
 * 第一行输入5个数据，分别为：x y z start end，每项数据以空格隔开
 *
 * 其中：0 < x,y,z <= 10000,0 < start,end <= 1000000
 *
 * 输出描述，需要花费的最少c币
 *
 * eg：输入： 3 100 1 2 6     输出：6
 */
public class Code06_KiKi {
    
    //start end 为偶数
    public int getKiKi(int add,int times,int del,int start,int end){
        if(start > end){
            return -1;
        }
        return process(0,end,add,times,del,start,end * 2,((end - start) / 2) * add);
    }
 
    /**
     * 
     * @param preMoney   之前已经花了多少钱    可变
     * @param aim        目标   固定
     * @param add，times，del  固定参数 
     * @param cur         当前来到的人气
     * @param limitAim    人气大到什么程度不需要再尝试了   固定
     * @param limitCoin   已经使用的币大到什么程度不需要再尝试了  固定
     * @return
     */
    public int process(int preMoney,int aim,
                       int add,int times,int del,
                       int cur,
                       int limitAim,int limitCoin){
        if(preMoney > limitCoin){
            return Integer.MAX_VALUE;
        }
        if(cur < 0){
            return Integer.MAX_VALUE;
        }
        if(cur > limitAim){
            return Integer.MAX_VALUE;
        }
        if (aim == cur){
            return preMoney;
        }
        int min = Integer.MIN_VALUE;
        //人气 +2的方式
        int p1 = process(preMoney + add,aim,add,times,del,cur+2,limitAim,limitCoin);
        if(p1 != Integer.MAX_VALUE){
            min = p1;
        }
        //人气 *2的方式
        int p2 = process(preMoney + times,aim,add,times,del,cur*2,limitAim,limitCoin);
        if(p2 != Integer.MAX_VALUE){
            min = Math.min(min,p2);
        }
        //人气 -2的方式
        int p3 = process(preMoney + del,aim,add,times,del,cur-2,limitAim,limitCoin);
        if (p3 != Integer.MAX_VALUE){
            min = Math.min(min,p3);
        }
        return min;
    }
    
    //动态规划
    //两个可变参数  preMoney  cur
    public int minKiKi(int add,int times,int del,int start,int end){
        if (start > end){
            return -1;
        }
        int limitAim = end * 2;
        int limitCoin = ((end - start) / 2) * add;
        int[][] dp = new int[limitCoin + 1][limitAim + 1];
        for (int pre = 0;pre <= limitCoin;pre++){
            for (int aim = 0;aim <= limitAim;aim++){
                if (aim == start){
                    dp[pre][aim] = pre;
                }else {
                    dp[pre][aim] = Integer.MAX_VALUE;
                }
            }
        }
        for (int pre = limitCoin;pre >= 0;pre--){
            for (int aim = 0;aim <= limitAim;aim++){
                //人气值 +2的方式
                if (aim - 2 >= 0 && pre + add <= limitCoin){
                    dp[pre][aim] = Math.min(dp[pre][aim],dp[pre + add][aim - 2]);
                }
                //人气值 -2的方式
                if (aim + 2 <= limitAim && pre + del <= limitCoin){
                    dp[pre][aim] = Math.min(dp[pre][aim],dp[pre + del][aim + 2]);
                }
                //人气值 *2的方式
                if ((aim & 1) == 0){  //奇数不用判断，只需要判断偶数
                    if (aim / 2 >= 0 && pre + times <= limitCoin){
                        dp[pre][aim] = Math.min(dp[pre][aim],dp[pre + times][aim * 2]);
                    }
                }
            }
        }
        return dp[0][end];
    }
}