package fibonacci;

/**
 * 首先像斐波那契数列1,1,2,3,5,8...除了初始项，其他位置都有严格的推演公式，
 * F(N)=F(N-1)+F(N-2)，由于最小项为N-2，由N问题变成N-2，那么该公式可以变成一个二阶矩阵的问题
 * @author admin
 * @date 2022/10/31 22:58
 */
public class FiProblem {
    //斐波那契数列 O(logN) 求解
    public int fi(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int[][] base = {{1,1},
                {1,0}};
        int[][] res = matrixPower(base,n-2);
        return res[0][0] + res[1][0];
    }

    //求解一个矩阵的p次方
    public int[][] matrixPower(int[][] m,int p){
        int[][] res = new int[m.length][m[0].length];
        //先让res为单位矩阵，即对角线为1，其余都是0
        for (int i = 0;i < res.length;i++){
            res[i][i] = 1;
        }
        int[][] t = m;
        for (;p != 0;p >>= 1){  //每次二进制右移以为，看是不是等于1
            if((p & 1) != 0){
                res = multiMatrix(res,t);
            }
            t = multiMatrix(t,t);
        }
        return res;
    }

    //两个矩阵相乘
    public int[][] multiMatrix(int[][] m,int[][] t){
        int[][] res = new int[m.length][m[0].length];
        for(int i = 0;i < res.length;i++){
            for (int j = 0;j < t[0].length;j++){
                for (int k = 0;k < t.length;k++){
                    res[i][j] += m[i][k] * t[k][j];
                }
            }
        }
        return res;
    }


    /**
     * 字符串只由’0’和’1’两种字符构成
     *
     * 当字符串长度为1时，所有可能的字符串为”0”,”1”
     *
     * 当字符串长度为2时，所有可能的字符串为”00”、 ”01”、 ”10”、 ”11”
     *
     * 当字符串长度为3时，所有可能的字符串为”000”、 ”001”、 ”010”、 ”011”、 ”100”、
     *
     * ”101”、 ”110”、 ”111”
     *
     * .......
     *
     * 如果某一个字符串中，只要是出现’0’的位置，左边就靠着’1’，这样的字符串叫作达标字符串。
     *
     * 给定一个正数N，返回所有长度为N的字符串中，达标字符串的数量。
     *
     * eg:N=3，返回3，因为只有”101”、”110”、”111”达标
     *
     * 对于N长度的字符串，要求有0，左边必须有1，那么可以直到首位必须是1
     *
     * 那么对于i长度的字符串，其左边是1的话，则有F(i)=F(i-1)+F(i-2)，因此其就满足斐波那契数列，可直接根据斐波那契进行求解
     * @param N
     * @return
     */
    public int qualifiedString(int N){
        return fi(N+1);
    }


    /**
     * 在迷糊的大草原上，小红捡到了n根木棍，第i根木棍的长度为i，小红现在很开心。想选出其中的三根木棍组成美丽的三角形。但是小明想捉弄小红，想去掉一些木棍，使得小红任意选三根木棍都不能组成三角形。
     *
     * 请问小明最少去掉多少根木棍呢？给定N，请返回至少去掉多少根？
     *
     * 斐波那契额数列：1 1 2 3 5 8 13 21 ...刚好无法构成三角形的情况，这也是无法构成三角形的最多情况，就是题意去掉最少的木棍形成
     */
    //去掉最少的木棍，当前有N个数，从中去掉最少的
    public int minSticks(int N){
        //获得斐波那契额数列中小于N最大的一个数
        // 1 1 2 3 5 8 13 21 .....
        int num = 1;
        while (fi(num) < N){
            num++;
        }
        return N - (num - 1);//num - 1是其中保留的无法拼成三角形的数
    }
}
