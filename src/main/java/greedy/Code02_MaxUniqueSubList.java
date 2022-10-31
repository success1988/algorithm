package greedy;

/**
 * 在一个字符串中找到没有重复字符字串中最长的长度
 *
 * eg：abcabcbb没有重复字符的最长字串是abc，长度为3
 *
 * bbbbb 答案是b，长度为1
 *
 * pwwkew，答案是wke，长度为3
 *
 * 要求：答案必须是字串，”pwke”是一个子字符序列但不是一个子字符串
 */
public class Code02_MaxUniqueSubList {
    
    public int maxUnique(String s){
        if (s == null || s.equals("")){
            return 0;
        }
        char[] chars = s.toCharArray();
        //map 替代哈希表，假设字符的码是 0~255
        int[] map = new int[256];  //记录字符出现的位置
        for (int i = 0;i < 256;i++){
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0;i != chars.length;i++){
            pre = Math.max(pre,map[chars[i]]);
            cur = i - pre;
            len = Math.max(len,cur);
            map[chars[i]] = i;
        }
        return len;
    }
}