package graph.traversal;

import graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 宽度优先遍历
 * @Author admin
 * @Date 2022/8/27
 */
public class GraphBFS {


    /**
     * 从node出发，进行宽度优先遍历
     * @param node
     */
    public static void bfs(Node node){
        if(node == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Node> nodeSetProcessed = new HashSet<>();
        queue.offer(node);
        nodeSetProcessed.add(node);

        while(!queue.isEmpty()){
            Node pollNode = queue.poll();
            //这里的打印行为代表着对该节点的处理
            System.out.println(pollNode.value);
            for(Node n : pollNode.nexts){
                if(!nodeSetProcessed.contains(n)){
                    nodeSetProcessed.add(n);
                    queue.offer(n);
                }
            }
        }
    }
}
