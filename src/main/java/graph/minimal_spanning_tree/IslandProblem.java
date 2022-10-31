package graph.minimal_spanning_tree;

/**
 * 岛问题
 *     一个矩阵中只有０和１两种值，每个位置都可以和自己的上、下、左、右四个位置相连，如果有一片１连在一起，这个部分叫做一个岛，求矩阵中有多少个岛？
 * eg:
 * 001010
 * 111010
 * 100100
 * 000000
 *
 * 这个矩阵有三个岛
 *
 * 进阶：如何设计一个并行算法解决这个问题(使用并查集)
 * @author wangcg
 * @date 2022/10/31 12:16
 */
public class IslandProblem {
    //获得岛的个数
    public static int countIslands(int[][] arr){
        if(arr == null || arr[0] == null){
            return 0;
        }
        int N = arr.length; //行
        int M = arr[0].length;  //列
        int res = 0;
        for(int i = 0;i < N;i++){
            for(int j = 0;j < M;j++){
                if(arr[i][j] == 1){  //如果遇到1，就进行传染，岛的个数加一，使遇到的1都变为2
                    res++;
                    infect(arr,i,j,N,M);
                }
            }
        }
        return res;
    }

    //时间复杂度O(N*M)
    public static void infect(int[][] arr,int i,int j,int N,int M){
        if(i < 0 || i >= N || j < 0 || j >= M || arr[i][j] != 1){ //如果越界，或者遇到不是1就返回
            return;
        }
        //使岛元素变为2
        arr[i][j] = 2;
        //上下左右递归进行
        infect(arr, i + 1, j, N, M);
        infect(arr, i - 1, j, N, M);
        infect(arr, i, j + 1, N, M);
        infect(arr, i, j - 1, N, M);
    }

}
