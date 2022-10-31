package greedy;
 
import org.junit.Test;
 
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
 
/**
 * 给定一个Job类型的数组jobarr，表示所有的工作。
 * 给定一个int类型的数组arr，表示所有小伙伴的能力。
 * 返回int类型的数组，表示每一个小伙伴按照牛牛的标准选择工作所能获得的报酬。
 */
public class Code04_JobChoose {
 
    class Job{
        public int money;
        public int hard;
 
        public Job(int hard,int money){
            this.hard = hard;
            this.money = money;
        }
    }
 
    class JobComparator implements Comparator<Job>{
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard == o2.hard ? (o2.money - o1.money) : (o1.hard - o2.hard);
        }
    }
 
    //把所有工作按难度由小到大，先删掉同样难度的低报酬，只保留同样难度的最高报酬；
    // 然后根据难度上升，报酬也要上升的原则进行删除难度大报酬低的职位。
    public int[] getJobMoney(Job[] jobarr,int[] peopleAbility){
        Arrays.sort(jobarr,new JobComparator());  //先对工作排序，难度由小到大，同难度的钱由大到小
        System.out.print("jobMoney=> ");
        for(Job j : jobarr){
            System.out.print(j.hard + ":" + j.money + "\t");
        }
        System.out.println();
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(jobarr[0].hard,jobarr[0].money);
        int preMoney = jobarr[0].money;
        for (int i = 1;i < jobarr.length;i++){
            //jobArr已经按照要求排好序，只保留相同工作难度，获得工作薪资最多的
            // 并且随难度升高，工作薪资也升高的工作
            if(!treeMap.containsKey(jobarr[i].hard) && jobarr[i].money > preMoney){
                treeMap.put(jobarr[i].hard,jobarr[i].money);
                preMoney = jobarr[i].money;
            }
        }
        System.out.print("treeMap=> ");
        for (Object o : treeMap.keySet()) {
            System.out.print(o + ":" + treeMap.get(o) + "\t");
        }
        System.out.println();
        int[] salary = new int[jobarr.length];
        for (int i = 0;i < salary.length;i++){
            Integer maxhard = treeMap.floorKey(peopleAbility[i]); //返回小于等于key的最大Key的key，没有返回null
            salary[i] = maxhard == null ? 0 : treeMap.get(maxhard);
        }
        return salary;
    }
 
    public Job[] jobRandom(){
        int N = (int)(Math.random() * 20 + 1);
        Job[] jobArr = new Job[N];
        int hard = 0;
        int money;
        for (int i = 0;i < N;i++){ //工作难度在1~10
            hard = (int)(Math.random() * 10) + 1;
            money = (int)(Math.random() * 20) + 1;
            jobArr[i] = new Job(hard,money);
        }
        return jobArr;
    }
 
    public int[] ability(int n){
        int[] ability = new int[n];
        for (int i = 0;i < n;i++){  //工作能力在0~15
            ability[i] = (int)(Math.random() * 15);
        }
        return ability;
    }
 
    @Test
    public void test(){
        int N = 100;
        for(int i = 0;i < 100;i++){
            Job[] jobArr = jobRandom();
            int[] ability = ability(jobArr.length);
            int[] pmoney = getJobMoney(jobArr,ability);
            System.out.print("ability=> ");
            System.out.println(Arrays.toString(ability));
            System.out.print("peopleMoney=> ");
            System.out.println(Arrays.toString(pmoney));
            System.out.println();
        }
 
    }
}