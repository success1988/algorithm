package without_categories;
 
/**
 * 给定一个非负整数，代表二叉树的节点个数。返回能形成多少中不同的二叉树结构
 */
public class Code07_BSTNum {
 
    public static int process(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0){  //空树
            return 1;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int res = 0;
        for(int leftTreeNodeNum = 0;leftTreeNodeNum <= n - 1;leftTreeNodeNum++){
            int leftWays = process(leftTreeNodeNum);
            int rightWays = process(n - 1 - leftWays);
            res += leftWays * rightWays;
        }
        return res;
    }
 
    public static int numTrees(int n){
        if(n < 2){
            return 1;
        }
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 1;i < n + 1;i++){ //节点个数为 i时
            for (int j = 0;j <= i - 1;j++){  // 左侧节点个数为 j ，右侧节点个数为 i - j - 1
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}