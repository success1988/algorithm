package string_match;
 
/**
 * 如果一个字符串为str，把字符串str前面任意的部分挪到后面形成的字符串叫作str的旋转词。
 * 比如str=”12345”，str的旋转词有”12345”、”23451”、”34512”、”45123”和”51234”。
 * 给定两个字符串a和b，请判断a和b是否互为旋转词。
 * eg:a=”cdab”,b=”abcd” ，返回true
 * a=”1ab2”,b=”ab12” ，返回false
 * a=”2ab1”,b=”ab12” ，返回true
 */
public class RotatingWord {
 
    public boolean isRotatingWord(String str1,String str2){
        if(str1 == null && str2 == null){
            return true;
        }
        if((str1 == null && str2 != null) || (str1 != null && str2 == null)
                || str1.length() != str2.length()){ //长度不同，肯定不互为旋转词
            return false;
        }
        //生成str1+str1，判断str2是不是str1+str1的字串(通过KMP算法加速)
        //是字串，那么就互为旋转词，不是字串，就不互为旋转词
        String str = str1 + str1;
        return isSubString(str,str2);
    }
 
    //str1和str2均不是null
    //判断str2是不是str1的子串
    public boolean isSubString(String str1,String str2){
        int[] next = getStr2Next(str2);  //获得str2的next数组
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int i1 = 0;
        int i2 = 0;
        while(i1 < char1.length && i2 < char2.length){
            if(char1[i1] == char2[i2]){
                i1++;
                i2++;
            }else if(i2 == 0){ //next[i2] == -1; //判断子字符串的判断是否回到了第一个元素，无法再往前跳了
                //当子字符串回到第一个元素，说明当前str1中判断的元素已经不满足，要后移再继续判断
                i1++;
            }else {
                i2 = next[i2];
            }
        }
        return i2 == str2.length() ? true : false;
    }
 
    //获得最大相同字符串下标位置的数组
    public int[] getStr2Next(String str){
        char[] chars = str.toCharArray();
        int N = chars.length;
        int[] next = new int[N];
        //前两个位置认为规定，0位置前面没有为-1，1位置值是0，字符相同也是0，不相同也回到起始位置0
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int j = next[i - 1];
        for (;i < chars.length;i++){
            if (chars[i - 1] == chars[j]){
                next[i] = ++j;
            }else if(j > 0) {
                j = next[j];
            }else {
                next[i] = 0;
            }
        }
        return next;
    }
}