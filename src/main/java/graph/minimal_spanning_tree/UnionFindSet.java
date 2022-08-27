package graph.minimal_spanning_tree;

import graph.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 并查集简易版本(用于最小生成树的K算法)
 * @author admin
 * @date 2022/8/27 15:21
 */
public class UnionFindSet {

    public HashMap<Node, List<Node>> setMap;

    public UnionFindSet(Collection<Node> nodes){
        for(Node node : nodes){
            List<Node> list = new ArrayList<>();
            list.add(node);
            setMap.put(node, list);
        }
     }


     public boolean isSameSet(Node node1, Node node2){
        return setMap.get(node1) == setMap.get(node2);
     }

     public void union(Node node1, Node node2){
         List<Node> list1 = setMap.get(node1);
         List<Node> list2 = setMap.get(node2);

         for(Node node : list2){
             list1.add(node);
             setMap.put(node, list1);
         }
     }

}
