package graph.topsort;

import graph.Graph;
import graph.Node;

import java.util.*;

/**
 * 拓扑排序
 *    取入度为0的节点作为第一个节点
 *    然后消除该节点的影响，继续上述操作
 * @author admin
 * @date 2022/8/27 14:10
 */
public class TopologicalSort {

    public static List<Node> tuopuSort(Graph graph){
        //用来存放各个顶点的入度
        Map<Node,Integer> inMap = new HashMap<>();
        //用来存放入度为0的顶点
        Queue<Node> queue = new LinkedList<>();
        //对图的点集进行遍历，计算各个顶点的入度
        for(Node node : graph.nodes.values()){
            inMap.put(node, node.in);
            if(node.in == 0){
                queue.offer(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            //将入度为0的顶点依次加入到结果列表中，即可得到拓扑排序的结果
            result.add(cur);

            //消除当前顶点对其他顶点的入度影响
            for(Node next : cur.nexts){
                int nextIn = inMap.get(next)-1;
                inMap.put(next, nextIn);
                if(nextIn == 0){
                    queue.offer(next);
                }
            }
        }
        return result;
    }

}
