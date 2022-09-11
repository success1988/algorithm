package string_match;

/**
 * next[j+1]能取到的最大值为next[j]+1
 * 若最大值确定取不到，那么next[j+1]能取到的次大值为next[next[j]]+1
 * @author admin
 * @date 2022/9/10 11:57
 */
public class KMPstringSearch {

    // a, b 分别是主串和模式串；n, m 分别是主串和模式串的长度。
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts2(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到 a[i] 和 b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }

    // b 表示模式串，m 表示模式串的长度
    //数组的下标是每个前缀结尾字符下标，数组的值是这个前缀的最长可以匹配前缀子串的结尾字符下标。
    //想让模式字符串快速向后移动，但要确保不会错过
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }


    private static int[] getNexts2(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        int i = 1;
        while(i < m){
            if(k == -1 || b[k + 1] == b[i]){
                next[i++] = ++k;
            }else{
                k = next[k];
            }
        }
        return next;
    }


    public static void main(String[] args) {
        String masterStr = "abcacabcababaeabacbcbacababacfcdbadfw";
        String patternStr = "ababacd";

        KMPstringSearch kmpStringSearch = new KMPstringSearch();
        int index = kmpStringSearch.kmp(masterStr.toCharArray(), masterStr.length(), patternStr.toCharArray(), patternStr.length());
        System.out.println("检索结果为:"+index);
    }
}
