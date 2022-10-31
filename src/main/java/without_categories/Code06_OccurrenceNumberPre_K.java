package without_categories;
 
import java.util.*;
 
/**
 * 给定一个字符串类型的数组arr，求其中出现次数最多的前k个
 */
public class Code06_OccurrenceNumberPre_K {
 
    //通过建立词频表，放在大根堆，根据出现次数排列的大根堆
    public String[] pre_KOfMaxTimes1(String[] arr,int K){
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(String s : arr){
            if(!hashMap.containsKey(s)){
                hashMap.put(s,1);
            }else {
                hashMap.put(s,hashMap.get(s) + 1);
            }
        }
        //根据词出现的个数建立对应的大根堆
        PriorityQueue<Map.Entry<String,Integer>> priorityQueue
                = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        //存入大根堆
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            priorityQueue.add(entry);
        }
        //取出前K个
        String[] str = new String[K];
        for(int i = 0;i < K;i++){
            String key = priorityQueue.poll().getKey();
            str[i] = key;
        }
        return str;
    }
 
    //也可以建立只具有K个容量的小根堆，这样存放词频表，能进入门槛的放入(多了就开始删门槛放入)
    public String[] pre_KOfMaxTimes2(String[] arr,int K){
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(String s : arr){
            if(!hashMap.containsKey(s)){
                hashMap.put(s,1);
            }else {
                hashMap.put(s,hashMap.get(s) + 1);
            }
        }
        //建立小根堆
        PriorityQueue<Map.Entry<String,Integer>> priorityQueue
                = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()){
            if(priorityQueue.size() <= 7){
                priorityQueue.add(entry);
            }else if (priorityQueue.size() > K
                    && priorityQueue.peek().getValue() < entry.getValue()){ //小根堆顶部词数小于后序添加的
                priorityQueue.poll();
                priorityQueue.add(entry);
            }
        }
        //取出前K个
        String[] str = new String[K];
        for(int i = 0;i < K;i++){
            String key = priorityQueue.poll().getKey();
            str[i] = key;
        }
        return str;
    }
}