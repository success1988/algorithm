package dynamic_programming;

/**
 * 字符串str中，最长回文子串的长度如何求解？如何做到时间复杂度O(N)
 *
 *  回文半径：以一个中心向左右两边扩，扩出来的整个区域大小为回文直径，一半的长度为回文半径。
 */
public class Manacher {
 
    public static int getMaxPalindromeSize(String str){
        if(str == null || str.length() == 0){
            return 0;
        }
        //先对str进行字符处理，加入虚拟字符
        char[] chars = manacher(str);
        //新建一个回文半径的数组，保存每一个字符的回文半径
        int[] pArr = new int[chars.length];
        //如果有以C为中心，R为回文半径的回文字符串，遍历到i位置(一般在C后面)，i如果在C的回文半径里面，那么肯定有以C为对称轴的i'
        //只需要判断i‘的回文半径，在根据其回文半径大小分小点讨论即可。
        int C = -1;  //定义回文字符的中心
        int R = -1;  //定义回文右边界再往右一个位置， 最右的有效区是 R-1 位置
        int maxSize = Integer.MIN_VALUE;  //所有回文字串的最大长度
        for(int i = 0;i < chars.length;i++){  //每个位置都要求回文半径
            //i 至少的回文区域  2*c-i表示的是i’ 即i关于C的对称点
            pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
            while (i + pArr[i] < chars.length && i - pArr[i] > -1){
                if(chars[i + pArr[i]] == chars[i - pArr[i]]){ //在至少的回文区域往外扩，有相同的就加一
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if(i + pArr[i] > R){ //有边界扩大，中心点变化
                R = i + pArr[i];
                C = i;
            }
            maxSize = Math.max(maxSize,pArr[i]);
        }
        //处理串的回文半径代表的 是 原始串的回文半径+1
        return maxSize - 1;
    }
 
    //给一个字符串，进行字符处理，在每个字符两端加上虚拟字符
    public static char[] manacher(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for(int i = 0;i != res.length;i++){
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
}