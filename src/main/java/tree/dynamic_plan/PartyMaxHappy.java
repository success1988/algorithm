package tree.dynamic_plan;

import java.util.List;

/**
 * 派对的最大快乐值
 *    如果某个员工来了，那么他的直接下级不能来
 *
 *    X来： X快乐值 + 直接子节点不来的最大快乐值之和
 *    X不来： Math.max(直接子节点来 ,  直接子节点不来)
 * @Author admin
 * @Date 2022/8/24
 */
public class PartyMaxHappy {

    public static class Employee{
        //该员工可以带来的快乐值
        public int happy;
        //员工的直接下级
        public List<Employee> nexts;
    }


    public static class Info{
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }


    public static Info process(Employee x){
        if(x.nexts.isEmpty()){
            return new Info(x.happy, 0);
        }
        int yes = x.happy;
        int no = 0;
        for (Employee next: x.nexts) {
            Info info = process(next);
            yes += info.no;
            no += Math.max(info.yes, info.no);
        }
        return new Info(yes, no);
    }


    public static void main(String[] args) {
        //取x来和不来的最大值
    }

}
