package graph.minimal_spanning_tree;

import graph.Edge;
import graph.Graph;
import graph.Node;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树：prim算法
 * 第一步：随意选取起点
 * 第二步：在前一步的基础上寻找最小权值
 * 第三步：继续寻找最小权值，之后以此类推，直到遍历完所有的节点。
 * @author admin
 * @date 2022/8/27 22:28
 */
public class PrimMST {


    public static Set<Edge> primMST(Graph graph){

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((e1, e2) -> {
            return e1.weight - e2.weight;
        });

        Set<Edge> result = new HashSet<>();
        Set<Node> viewedNodes = new HashSet<>();

        for(Node node :  graph.nodes.values()){
            if(!viewedNodes.contains(node)){
                viewedNodes.add(node);
                //获取所有的边
                for(Edge e : node.edges){
                    priorityQueue.offer(e);
                }

                while(!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if(!viewedNodes.contains(toNode)){
                        viewedNodes.add(toNode);
                        result.add(edge);
                        for(Edge ed : toNode.edges){
                            priorityQueue.offer(ed);
                        }
                    }
                }
            }
        }

        return result;
    }
}
