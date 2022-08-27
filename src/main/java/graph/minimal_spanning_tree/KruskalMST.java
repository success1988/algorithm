package graph.minimal_spanning_tree;

import graph.Edge;
import graph.Graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树
 *    Kruskal算法（克鲁斯卡尔）求解最小生成树：直接选择权值最小的边
 *    1.将图中所有的边，都取出放入一个列表中。并按照边的权值由小到大的顺序排序
 *    2.初始化，查源数组（并查集），判断当前图是否存在环
 *    3.不构成环的边可以加入到结果中，同时合并集合
 * @author admin
 * @date 2022/8/27 22:09
 */
public class KruskalMST {


    public static Set<Edge> kruskalMST(Graph graph){
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((e1,e2) -> {
            return e1.weight - e2.weight;
        });

        for (Edge edge : graph.edges){
            priorityQueue.offer(edge);
        }

        UnionFindSet unionFindSet = new UnionFindSet(graph.nodes.values());
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(!unionFindSet.isSameSet(edge.from, edge.to)){//O(1)
                result.add(edge);
                unionFindSet.union(edge.from, edge.to);//O(1)
            }
        }

        return result;
    }

}
