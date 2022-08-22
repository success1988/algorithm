package tree;

/**
 * 对折小纸条  凹凸折痕打印
 * @Author admin
 * @Date 2022/8/23
 */
public class PaperFolding {

    public static void printAllFolds(int N){
        printProcess(1, N, true);
    }

    /**
     *
     * @param i 节点的层数
     * @param n 一共的层数
     * @param downFlag true表示凹  ，false表示凸
     */
    private static void printProcess(int i, int n, boolean downFlag) {
        if(i > n){
            return;
        }
        printProcess(i+1, n, true);
        System.out.println(downFlag?"凹":"凸");
        printProcess(i+1, n, false);
    }

    public static void main(String[] args) {
        int n = 3;
        printAllFolds(n);
    }

}
