package without_categories;
 
import org.junit.Test;
 
import java.util.Arrays;
 
/**
 * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打包机上，放到每个机器上的这些物品数量有多有少，由于物品数量不同，需要工人将每个机器上的物品移动从而达到物品数量下相等才能打包。每个物品重量太大、每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动。请计算在搬动最小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同，返回-1。
 *
 * eg：[1,0,5]表示有三个机器，每个机器上分别有1，0，5个物品，经过这些轮后：
 *
 * 第一轮：1 0 <- 5 ==> 1 1 4
 *
 * 第二轮：1 <- 1 <- 4 ==> 2 1 3
 *
 * 第三轮：2 1 <- 3 ==> 2 2 2
 *
 * 移动了三轮，每个机器上的物品相等，所以返回3

 * eg：[2,2,3]表示有3个机器，每个机器上分别有2、2、3个物品，
 * 这些物品不管怎么移动，都不能使三个机器上物品数量相等，返回-1。
 */
public class Code01_AveragePackage {
 
    //arr中只有相邻的两个才可以搬运
    public int minTimesOfPackage(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int sum = 0;
        for(int i : arr){
            sum += i;
        }
        if(sum % arr.length != 0){
            return -1;
        }
        int avg = sum / arr.length;
        int maxTimes = Integer.MIN_VALUE;
        int leftSum = 0;
        int rightSum = sum - arr[0];
        for(int i = 0;i < arr.length;i++){
            int leftPoor = leftSum - avg * i;   //i位置左侧衣服总和的差值
            int rightPoor = rightSum - avg * (arr.length - 1 - i); //右侧衣服总和的差值
            int curTimes = 0;
            if(leftPoor < 0 && rightPoor < 0){  //左负右负  i位置每次只能拿一个
                curTimes = Math.abs(leftPoor) + Math.abs(rightPoor);
            }else if((leftPoor < 0 && rightPoor > 0)
                        || (leftPoor > 0 && rightPoor < 0)){  //左负右正 或者 左正右负
                curTimes = Math.max(Math.abs(leftPoor),Math.abs(rightPoor));
            }else if(leftPoor > 0 && rightPoor > 0){  //左正右正
                curTimes = Math.max(leftPoor,rightPoor);
            }else {  //左0 右0
                curTimes = 0;
            }
            maxTimes = Math.max(maxTimes,curTimes);  //取每次情况最差的
            leftSum += arr[i];  //向后移动
            rightSum -= arr[i];
        }
        return maxTimes;
    }
 
    public int[] getRandomArr(){
        int N = (int)(Math.random() * 20) + 1;  //长度1~20
        int[] arr = new int[N];
        for (int i = 0;i < N;i++){
            arr[i] = (int)(Math.random() * 101);  //个数为0~100
        }
        return arr;
    }
 
    @Test
    public void test(){
        int N = 20;
        for(int i = 0;i < N;i++){
            int[] arr = getRandomArr();
            int min = minTimesOfPackage(arr);
            System.out.println(Arrays.toString(arr));
            System.out.println(min);
        }
    }
}