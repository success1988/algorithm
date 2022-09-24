package dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * 能做严格位置依赖的就做
 * 不能做严格位置依赖的就用傻缓存
 * @author admin
 * @date 2022/9/24 15:30
 */
public class StrikersToSpellWord {

    public static void main(String[] args) {
        String[] strikers = {"abc", "abacfd", "dagg", "bafs", "whais"};
        String word = "bagshahacgfgiadbhg";
        int count = computeStrikerNum(strikers, word);
        System.out.println("【暴力递归】需要的纸条数目为:"+(count == Integer.MAX_VALUE ? -1 : count));

        int count2 = computeStrikerNum2(strikers, word);
        System.out.println("【暴力递归+剪枝优化】需要的纸条数目为:"+(count2 == Integer.MAX_VALUE ? -1 : count2));

        int count3 = computeStrikerNum3(strikers, word);
        System.out.println("【暴力递归+剪枝优化+傻缓存】需要的纸条数目为:"+(count3 == Integer.MAX_VALUE ? -1 : count3));
    }




    //1.将target转换为int[26] a~z的计数数组 targetArr
    //2.将first转换为int[26] a~z的计数数组 firstArr
    //3.在targetArr的每一个元素值上做扣减，扣减幅度不超过firstArr对应元素上的值
    //4.将扣减后的结果还原未为结果字符串
    private static String minus(String target, String first) {

        int[] targetArr = parseStr(target);
        int[] firstArr = parseStr(first);

        return minusCharArr(targetArr, firstArr);
    }

    private static String minusCharArr(int[] targetArr, int[] strikerArr){
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            if(targetArr[i] == 0){
                continue;
            }
            //最多将该字符出现的次数扣减为零
            int minusNum = Math.max(0, targetArr[i]-strikerArr[i]);
            result.append(repeatChar((char)('a'+i), minusNum));
        }
        return result.toString();
    }

    private static String repeatChar(char ch, int num){
        if(num == 0){
            return "";
        }
        StringBuffer repeatResult = new StringBuffer();
        for (int i = 0; i < num; i++) {
            repeatResult.append(ch);
        }
        return repeatResult.toString();
    }

    private static int[] parseStr(String str){
        int[] lowerCaseNums = new int[26];
        char[] chars = str.toCharArray();
        for(char c : chars){
           lowerCaseNums[c - 'a']++;
        }
        return lowerCaseNums;
    }

    //=============================================暴力递归==============================================

    //每使用一个有效的纸条，就可以将原始的单词变短（能让target变短的字条可以称之为有效字条）
    private static int computeStrikerNum(String[] strikers, String target) {
        //当原始的单词变短到0时，表示已经拼到位了, 不需要再继续使用纸条来拼了
        if(target.length() == 0){
            return 0;
        }

        int minRestCount = Integer.MAX_VALUE;
        for(String first : strikers){
            String newTarget = minus(target, first);
            //遇到有效的字条，才会作为第一张，继续找后续
            if(newTarget.length() != target.length()){
                int currentRestCount = computeStrikerNum(strikers, newTarget);
                //如果返回值不为系统最大值，则表示拼通了，可以参与打擂台了
                if(currentRestCount != Integer.MAX_VALUE){
                    minRestCount = Math.min(minRestCount, currentRestCount);
                }
            }
        }
        //没有拼通有两种可能性：一是 压根就没有遇到过一张有效的字条， 二是，虽然遇到了开头，但没有可以走通的后续
        if(minRestCount == Integer.MAX_VALUE){
            return minRestCount;
        }else{
            return minRestCount+1;
        }

    }

    //=============================================暴力递归+剪枝优化==============================================

    private static int computeStrikerNum2(String[] strikers, String word) {

        //对所有纸条进行词频统计
        int[][] strikerArr = new int[strikers.length][26];
        for (int i = 0; i < strikers.length; i++) {
            strikerArr[i] = parseStr(strikers[i]);
        }

        return process2(strikerArr, word);
    }

    private static int process2(int[][] strikerArr, String target) {
        if(target.length() == 0){
            return 0;
        }

        int restMinCount = Integer.MAX_VALUE;
        int[] targetArr = parseStr(target);
        for (int i = 0; i < strikerArr.length; i++) {
            //剪枝优化
            if(strikerArr[i][target.charAt(0) - 'a'] == 0){
                continue;
            }

            String currTarget = minusCharArr(targetArr, strikerArr[i]);
            int restCount = process2(strikerArr, currTarget);
            if(restCount != Integer.MAX_VALUE){
                restMinCount = Math.min(restMinCount, restCount);
            }
        }

        if(restMinCount != Integer.MAX_VALUE){
            return restMinCount+1;
        }else{
            return restMinCount;
        }
    }

    //=============================================暴力递归+剪枝优化+傻缓存==============================================

    private static int computeStrikerNum3(String[] strikers, String word) {

        //对所有纸条进行词频统计
        int[][] strikerArr = new int[strikers.length][26];
        for (int i = 0; i < strikers.length; i++) {
            strikerArr[i] = parseStr(strikers[i]);
        }
        Map<String,Integer> countOfTarget = new HashMap<>();
        countOfTarget.put("", 0);
        return process3(strikerArr, word, countOfTarget);
    }

    private static int process3(int[][] strikerArr, String target, Map<String,Integer> countOfTarget) {
        if(countOfTarget.containsKey(target)){
            return countOfTarget.get(target);
        }

        int restMinCount = Integer.MAX_VALUE;
        int[] targetArr = parseStr(target);
        for (int i = 0; i < strikerArr.length; i++) {
            //剪枝优化
            if(strikerArr[i][target.charAt(0) - 'a'] == 0){
                continue;
            }

            String currTarget = minusCharArr(targetArr, strikerArr[i]);
            int restCount = process2(strikerArr, currTarget);
            if(restCount != Integer.MAX_VALUE){
                restMinCount = Math.min(restMinCount, restCount);
            }
        }

        if(restMinCount != Integer.MAX_VALUE){
            countOfTarget.put(target, restMinCount+1);
        }else{
            countOfTarget.put(target, restMinCount);
        }
        return countOfTarget.get(target);
    }

}
