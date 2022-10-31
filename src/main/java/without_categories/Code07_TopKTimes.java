package without_categories;
 
import java.util.HashMap;
 
/**
 * 实时的显示最多K系统
 */
public class Code07_TopKTimes {
 
    //存放字符串和字符串出现次数的节点 Node
    class Node{
        public String str;
        public int times;
 
        public Node(String str,int times){
            this.str = str;
            this.times = times;
        }
    }
    
    class TopKRecord{
        private  Node[] heap;  //堆
        private int heapSize; //堆的大小
        private HashMap<String,Node> strNodeMap;    // 字符串表  相当于词频表
        private HashMap<Node,Integer> nodeIndexMap; // 节点位置表
 
        public TopKRecord(int size){
            heap = new Node[size];
            heapSize = 0;
            strNodeMap = new HashMap<String,Node>();
            nodeIndexMap = new HashMap<Node,Integer>();
        }
 
        //添加字符串
        public void add(String str){
            Node curNode = null;  //当前str对应的节点对象
            int preIndex = -1;  //当前str节点对象是否在堆上
            if(!strNodeMap.containsKey(str)){//先看词频表里面有没有，没有词频表添加，且数为1;并且在节点的位置表中，初始化位置为-1
                curNode = new Node(str,1);
                strNodeMap.put(str,curNode);
                nodeIndexMap.put(curNode,-1);
            }else {  //词频表已经有，修改词频表对应字符串节点的次数，并获得该字符串节点之前在节点位置表的位置
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }
            //判断是否在堆上
            if(preIndex == -1){ //不在堆上，词频增加，是否要上堆
                if(heapSize == heap.length){ //堆满
                    if(heap[0].times < curNode.times){ //干掉堆顶,替换，并把位置表中对应节点位置改变
                        nodeIndexMap.put(heap[0],-1);
                        nodeIndexMap.put(curNode,0);
                        heap[0] = curNode;
                        heapify(0,heapSize);  //堆中以小根堆方式变化
                    }
                }else { //堆没有满，直接添加,堆的大小增加
                    nodeIndexMap.put(curNode,heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            }else {  //在堆上
                heapify(preIndex,heapSize);
            }
        }
 
        //把堆按照词频次数从小到大，节点位置表也需要对应修改
        public void heapify(int start,int end){
            for(int i = start;i < end;i++) {
                if (heap[i].times > heap[i+1].times){
                    Node temp = heap[i];
                    heap[i] = heap[i + 1]; //交换两个位置的节点
                    heap[i + 1] = temp;
                    nodeIndexMap.put(heap[i],i+1);  //对应节点的位置也需要改变
                    nodeIndexMap.put(heap[i+1],i);
                }
            }
        }
 
        //当前堆中已有节点的数加一
        public void heapInsert(int index){
            this.heapSize = heapSize;
        }
    }
}