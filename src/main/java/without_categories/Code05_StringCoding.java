package without_categories;


/**
 * 再数据加密和数据压缩中常需要对特殊的字符串进行编码。给定的字母表A由26个小写英文字母组成，即A={a,b,c..z}。该字母表产生的长序字符串是指定字符串中字母从左到右出现的次数与字母表中出现的次序相同，且每个字符最多出现一次。
 *
 * eg：a,b,ab,bc,xyz等字符串是升序字符串。对字母表A产生的所有长度不超过6的升序字符串按照字典排列编码如下：a(1),b(2),c(3)……,z(26),ab(27),ac(28)……对于任意长度不超过16的升序字符串，迅速计算出它在上述字典中的编码。
 *
 * 输入描述：
 *
 * 第一行是一个正整数N，表示接下来共有N行，在接下来的N行中，每行给出一个字符串。
 *
 * 输出描述：
 *
 * 输出N行，每行对应于一个字符串编码。
 */
public class Code05_StringCoding {
 
    //以i 字符开始的，总长度为len的子序列有多少个
    public int g(int i,int len){
        int sum = 0;
        if (len == 1){
            return 1;
        }
        for (int j = i + 1;j <= 26;j++){
            sum += g(j,len - 1);
        }
        return sum;
    }
    
    //长度为len的字符串有多少个
    public int f(int len){
        int sum = 0;
        for (int i = 1;i <= 26;i++){
            sum += g(i,len);
        }
        return sum;
    }
    
    public int kth(String str){
        char[] chars = str.toCharArray();
        int sum = 0;
        int len = chars.length;
        for (int i = 1;i < len;i++){  //长度len之前的总个数
            sum += f(i);
        }
        int first = chars[0] - 'a' + 1;  //第一个字符
        for(int i = 1;i < first;i++){
            sum += g(i,len);
        }
        int pre = first;
        for (int i = 1;i < len;i++){
            int cur = chars[i] - 'a' + 1;
            for(int j = pre + 1;j < cur;j++){
                sum += g(j,len - i);
            }
            pre = cur;
        }
        return sum + 1;
    }
}