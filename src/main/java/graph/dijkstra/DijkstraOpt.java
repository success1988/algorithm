package graph.dijkstra;

import graph.Edge;
import graph.Node;

import java.util.HashMap;

/**
 * 迪杰斯特拉的优化版本,属于单源最短路径算法,不允许有权重为负值的边
 * @author admin
 * @date 2022/9/17 10:04
 */
public class DijkstraOpt {

    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap{
        /**
         * 实际的堆结构
         */
        private Node[] nodes;
        /**
         * 某个node在数组中的索引位置
         */
        private HashMap<Node, Integer> headIndexMap;

        /**
         * 从源节点到某个node 目前最小距离
         */
        private HashMap<Node, Integer> distanceMap;

        /**
         * 堆上有多少个点
         */
        private int size;

        public NodeHeap(int size){
            this.nodes = new Node[size];
            this.headIndexMap = new HashMap<>();
            this.distanceMap = new HashMap<>();
        }


        public boolean isEmpty(){
            return size == 0;
        }


        private boolean isEntered(Node node){
            return headIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node){
            return isEntered(node) && headIndexMap.get(node) >= 0;
        }


        /**
         *
         * 添加：
         * 更新：
         * 忽略：
         * @param node
         */
        public void addOrUpdateOrIgnore(Node node, int distance){
            if(inHeap(node)){
                distanceMap.put(node, Math.min(distanceMap.get(node),  distance));
                insertHeapify(headIndexMap.get(node));
            }
            if(!isEntered(node)){
                nodes[size] = node;
                headIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            }
            //啥情况，会将headIndexMap中的value设置为-1呢？ 弹出的
            // 那又是什么情况下 要把节点 从 堆中弹出呢?

        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size-1);
            this.headIndexMap.put(nodes[size-1], -1);
            this.distanceMap.remove(nodes[size-1]);
            nodes[size-1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        //下沉
        private void heapify(int index, int size) {
            int left = index*2 + 1;
            while(left < size){
                //左右两个孩子中较小的那个节点的索引位置
                int smallestIndex = left+1 < size && distanceMap.get(nodes[left+1]) < distanceMap.get(nodes[left])
                        ? left+1 : left;
                //再跟孙子比
                smallestIndex = distanceMap.get(nodes[smallestIndex]) < distanceMap.get(nodes[index])
                        ? smallestIndex
                        : index;
                if(smallestIndex == index){
                    break;
                }
                swap(smallestIndex, index);
                index = smallestIndex;
                left = index*2 + 1;
            }
        }

        /**
         * 将堆中节点跟位置表同步进行交换
         * @param x
         * @param y
         */
        private void swap(int x, int y){
            headIndexMap.put(nodes[x], y);
            headIndexMap.put(nodes[y], x);

            Node tempNode = nodes[x];
            nodes[x] = nodes[y];
            nodes[y] = tempNode;
        }





        //上窜
        private void insertHeapify(int index) {
            while(distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index-1)/2])){
                swap(index, (index-1)/2);
                index = (index-1)/2;
            }
        }

    }

    /**
     * 改进后的迪杰斯特拉算法
     * @param head
     * @param size
     * @return 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
     */
    public static HashMap<Node, Integer> dijkstra(Node head, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);

        HashMap<Node, Integer> result = new HashMap<>();
        while(!nodeHeap.isEmpty()){
            NodeRecord record  = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for(Edge edge : cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight+distance);
            }
            result.put(cur, distance);
        }
        return result;
    }


}
