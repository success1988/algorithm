package graph.traversal;

import graph.Node;

import java.util.*;

/***
 * 深度优先遍历
 */
public class GraphDFS {


    /**
     * 从node出发，进行深度优先遍历
     * @param node
     */
    public static void dfs(Node node){
        if(node == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        Set<Node> nodeSetProcessed = new HashSet<>();
        stack.push(node);
        System.out.println(node.value);
        nodeSetProcessed.add(node);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for(Node next : cur.nexts){
                if(!nodeSetProcessed.contains(next)){
                    stack.push(cur);
                    //后放的这个节点会先弹出，这样就可以继续探究next节点的后续节点了
                    stack.push(next);
                    nodeSetProcessed.add(next);
                    //这里的打印行为代表着对该节点的处理
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }



}
