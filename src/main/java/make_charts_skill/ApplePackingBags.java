package make_charts_skill;
 
import org.junit.Test;
 
/**
 * 小虎区附近的商店买水果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装包袋不可拆分。
 * 可是现在小虎只想购买恰好n个苹果，小虎想购买尽量少的袋数方便携带。
 * 如果不能购买恰好n个苹果，小虎将不会购买。
 * 输入一个整数n，表示小虎想购买的苹果，返回最小使用多少袋子。如果无论如何都不能正好装下，返回-1。
 */
public class ApplePackingBags {
 
    //方法一：尽量使用8类型的袋子，然后减
    public static int getPackingBags(int apple){
        if(apple < 0){
            return -1;
        }
        int bag_6 = -1;
        int bag_8 = apple / 8;   //全用8类型的袋子，最多用多少个
        int rest = apple - 8 * bag_8;
        //6和8 的最小公倍数为 24，因此大于24过后，数字可变成 24 + x ，则会重复之前的操作
        while (bag_8 >= 0 && rest < 24){
            if(rest % 6 == 0){
                bag_6 = rest / 6;
                break;
            }
            rest = apple - 8 * (--bag_8);
        }
        return bag_6 == -1 ? -1 : bag_6 + bag_8;
    }
 
    //方法二：对于输入是整型，输出是整型，根据已有的方法，找出输出整型的规律
    //找到他们的规律，不需要关心为什么产生
    public static int getPackingBags2(int apple){
        if( apple < 0 || (apple & 1) != 0){  //奇数就输出-1
            return -1;
        }
 
        if(apple < 18){
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 :
                    (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }
 
    @Test
    public void test(){
        for(int i = 0;i <= 100;i++){
            int res = getPackingBags(i);
            System.out.println(i + " : " + res);
        }
//        int N = 20;
//        int packingBags = getPackingBags(N);
//        System.out.println(packingBags);
    }
}