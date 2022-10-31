package pretreatment_skill;
 
import org.junit.Test;
 
import java.util.Arrays;

/**
 * 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色，这个正方形 的颜色将会被覆盖。牛牛的目标是在完成染色之后，每个红色R都比每个绿色G距离最左侧近。牛牛想知道他最少需要涂染几个正方形。
 * eg：s=RGRGR
 *
 * 涂染之后变成 RRRGG就满足要求，涂染个数为2，没有比这更好的涂染方案。
 */
public class Code04_ColorRedGreen {
 
    //方法一：直接处理，时间复杂度大  
    public static int getMinPaint1(String s){   //时间复杂度 O(n^2)
        if(s == null || s.length() < 2){
            return 0;
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int minPoint = Integer.MAX_VALUE;
        for(int i = 0;i <= N;i++){  //0个R，1个R，．．．
            int count = 0;
            if (i == 0){  //全是G   R变成G
                for(char c : chars){
                    if(c == 'R'){
                        count++;
                    }
                }
            }else if (i == N){ //全是R   G变成R
                for(char c : chars){
                    if(c == 'G'){
                        count++;
                    }
                }
            }else {
                //一般情况下，左侧看看有多少个G变成R，右侧有多少个R变成G
                for (int j = 0; j < i; j++) {
                    if (chars[j] == 'G') {
                        count++;
                    }
                }
                for (int j = i; j < N; j++) {
                    if (chars[j] == 'R') {
                        count++;
                    }
                }
            }
            minPoint = Math.min(minPoint,count);
        }
        return minPoint;
    }
 
    //方法二：先申请数组记录 R 和 G 的个数，这样到达指定位置直接拿即可，不需要再查询
    public static int getMinPaint2(String s) {
        if(s == null || s.length() < 2){
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] arr_R = getNumi_N(chars,'R');  //i~N-1位置R的个数
        int[] arr_G = getNum0_i(chars,'G');  //0~i位置G个数
        int N = chars.length;
        int minPoint = Integer.MAX_VALUE;
        for(int i = 0;i <= N;i++){ //0个R，1个R，．．． 左侧的G变成R  右侧的R变成G  
            if(i == 0){  //都变为G   查询R的个数
                minPoint = Math.min(minPoint,arr_R[0]);
            }else if(i == N){   //都变为R   查询G的个数
                minPoint = Math.min(minPoint,arr_G[N-1]);
            }else {  //一般情况  i位置左侧查询G的个数 + i位置以及i右侧查询R的个数
                minPoint = Math.min(minPoint, arr_G[i-1] + arr_R[i]);
            }
        }
        return minPoint;
    }
 
    //返回chars上 0~每个位置之间(包含该位置) 的 c 的对应个数 数组
    public static int[] getNum0_i(char[] chars,char c){
        if(chars == null){
            return null;
        }
        int[] arr = new int[chars.length];
        arr[0] = chars[0] == c ? 1 : 0;
        for (int i = 1;i < chars.length;i++){
            arr[i] = arr[i - 1];
            arr[i] += chars[i] == c ? 1 : 0;
        }
        return arr;
    }
 
    //返回chars上 每个位置之间(包含该位置)~N-1 的 c 的对应个数 数组
    public static int[] getNumi_N(char[] chars,char c){
        if(chars == null){
            return null;
        }
        int N = chars.length;
        int[] arr = new int[N];
        arr[N - 1] = chars[N - 1] == c ? 1 : 0;
        for (int i = N - 2;i >= 0;i--){
            arr[i] = arr[i+1];
            arr[i] += chars[i] == c ? 1 : 0;
 
        }
        return arr;
    }
 
    //生成一个随机长度的RG数组
    public static String getRandom(){
        int N = (int)(Math.random() * 100 + 1);
        StringBuffer s = new StringBuffer(N);
        for(int i = 0;i < N;i++){
            if((int)(Math.random() * 2) == 0){
                s.append('R');
            }else {
                s.append('G');
            }
        }
        return s.toString();
    }
 
    @Test
    public void test(){
        for(int i = 1;i <= 100;i++){
            String s = getRandom();
            int minPoint1 = getMinPaint1(s);
            int minPoint2 = getMinPaint2(s);
            System.out.print(s + " : " + minPoint1 + " : " + minPoint2);
            if(minPoint1 != minPoint2){
                System.out.print("\t" + "false");
                break;
            }
            System.out.println();
        }
    }
}