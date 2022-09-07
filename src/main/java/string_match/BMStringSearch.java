package string_match;

/**
 * @author admin
 * @date 2022/9/7 7:47
 */
public class BMStringSearch {

    private static final int SIZE = 256; // 全局变量或成员变量
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; ++i) {
            bc[i] = -1; // 初始化 bc
        }
        for (int i = 0; i < m; ++i) {
            int ascii = (int)b[i]; // 计算 b[i] 的 ASCII 值
            bc[ascii] = i;
        }
    }

    // b 表示模式串，m 表示长度，suffix，prefix 数组事先申请好了
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; ++i) { // b[0, i]
            int j = i;
            int k = 0; // 公共后缀子串长度
            //让j向后移动k-1个位置，然后向前移动，与此同时，让另一个指针从末尾也向前移动
            while (j >= 0 && b[j] == b[m-1-k]) { // 与 b[0, m-1] 求公共后缀子串
                --j;
                ++k;
                suffix[k] = j+1; //j+1 表示公共后缀子串在 b[0, i] 中的起始下标
            }
            if (j == -1){
                prefix[k] = true; // 如果公共后缀子串也是模式串的前缀子串
            }
        }
    }

    // a,b 表示主串和模式串；n，m 表示主串和模式串的长度。
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0; // j 表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是 j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            int x = j - bc[(int)a[i+j]];
            int y = 0;
            if (j < m-1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    // j 表示坏字符对应的模式串中的字符下标 ; m 表示模式串长度
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        if (suffix[k] != -1) return j - suffix[k] +1;
        for (int r = j+2; r <= m-1; ++r) {
            if (prefix[m-r] == true) {
                return r;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        String masterStr = "abcacabcbcbacabcdbadfw";
        String patternStr = "cbacabc";

        BMStringSearch bmStringSearch = new BMStringSearch();
        int index = bmStringSearch.bm(masterStr.toCharArray(), masterStr.length(), patternStr.toCharArray(), patternStr.length());
        System.out.println("检索结果为:"+index);
    }
}
