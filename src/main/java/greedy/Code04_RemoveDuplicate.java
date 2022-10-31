package greedy;


/**
 * 给定一个全是小写字母的字符串str，删除多余字符，使得每种字符只保留一个，并让最终字符串的字典序最小
 *
 * eg：str=”acbc” ，删掉第一个’c’，得到”abc”，是所有结果字符串中字典序最小的
 *
 * str=”dbcacbca”，删掉第一个’b’,第一个’c’，第二个’c’，第二个’a’,得到”dabc” 是所有结果字符串中字典序最小的
 */
public class Code04_RemoveDuplicate {
    
    public String remove(String str){
        if(str == null || str.length() < 2){
            return str;
        }
        int[] map = new int[256];
        for (int i = 0; i < str.length();i++){
            map[str.charAt(i)]++;
        }
        int minASCIndex = 0;
        for (int i = 0;i < str.length();i++){
            if (--map[str.charAt(i)] == 0){
                break;
            }else {
                //获得最小的字符的第一个位置
                minASCIndex = str.charAt(minASCIndex) > str.charAt(i) ? i : minASCIndex;
            }
        }
        return String.valueOf(str.charAt(minASCIndex) + 
                remove(
                        str.substring(minASCIndex+1)
                                .replaceAll(String.valueOf(str.charAt(minASCIndex)),"")
                )
        );
    }
}