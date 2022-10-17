package dynamic_programming.death_probability;

/**
 * 给定3个参数: N,M,K
 * 怪兽有N滴血, 等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少呢？每一次在[0~M]上等概率的获取一个值
 * 求k次打击之后，英雄把怪兽砍死的概率
 * @author admin
 * @date 2022/10/7 20:51
 */
public class HeroKillMonster {


    public static void main(String[] args) {
        int N = 10;
        int M = 3;
        int k = 5;

        double deathProbability = getDeathProbability(N, M, k);
        System.out.println("[暴力递归]英雄砍死怪兽的概率为:"+deathProbability);

    }

    private static double getDeathProbability(int n, int m, int k) {
        // m+1种可能性
        int totalWays = (int)Math.pow(m+1, k);
        int aliveWays = process(n, m, k);
        return (double)(totalWays-aliveWays)/(double)totalWays;
    }

    private static int process(int restBlood, int m, int restHit) {
        if(restBlood <= 0){
            return 0;
        }
        if(restHit == 0){
            return 1;
        }

        int ways = 0;
        for (int i = 0; i <= m; i++) {
            ways += process(restBlood-i, m, restHit-1);
        }
        return ways;
    }

}
