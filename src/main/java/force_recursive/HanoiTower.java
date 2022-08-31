package force_recursive;

/**
 * 汉诺塔问题
 * @author admin
 * @date 2022/9/1 7:27
 */
public class HanoiTower {


    public static void main(String[] args) {
        process(3, "左", "右", "中");
    }

    private static void process(int i, String from, String to, String other){
        //base case
        if(i == 1){
            System.out.println(String.format("move %s from %s to %s", i, from ,to));
            return;
        }

        //将1~i-1从from移动到other
        process(i-1, from, other, to);
        //将i从from移动到to
        System.out.println(String.format("move %s from %s to %s", i, from ,to));
        //将1~i-1从other移动到to
        process(i-1, other, to, from);
    }

}