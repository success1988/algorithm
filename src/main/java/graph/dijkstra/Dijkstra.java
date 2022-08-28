package graph.dijkstra;

import graph.Edge;
import graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 迪杰斯特拉,属于单源最短路径算法,不允许有权重为负值的边
 *    有一个堆的优化
 * @author admin
 * @date 2022/8/28 20:53
 */
public class Dijkstra {

    public static HashMap<Node, Integer> dijkstra(Node head){
        //从head到各顶点的路径长度，不存在的键值对表示路径长度为正无穷大
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);

        //被锁定的顶点
        HashSet<Node> selectedNodes = new HashSet<>();
        //从distanceMap中找到距离最小的顶点，要求该顶点尚未被锁定
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);

        //考量minNode的每一条边，是否可以让distanceMap中的某些值变得更小
        while(minNode != null){
            //head到minNode的距离
            Integer distance = distanceMap.get(minNode);
            for(Edge edge : minNode.edges){
                Node toNode = edge.to;
                int currentDistance = distance + edge.weight;
                //head到toNode的距离为无穷大或比现在大，则需要清洗数据，采用现在的距离
                // 让之前没路的节点有路了，就增加； 让之前有路的节点距离变得更短了，那就覆盖
                if(!selectedNodes.contains(toNode) || currentDistance<distanceMap.get(toNode)){
                    distanceMap.put(toNode, currentDistance);
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 该方法可以使用优化后的堆来降低时间复杂度
     * @param distanceMap
     * @param selectedNodes
     * @return
     */
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        int distance = Integer.MAX_VALUE;
        Node targetNode = null;
        for(Map.Entry<Node,Integer> entry : distanceMap.entrySet()){
            if(selectedNodes.contains(entry.getKey())){
                continue;
            }
            if(entry.getValue() < distance){
                distance = entry.getValue();
                targetNode = entry.getKey();
            }
        }
        return targetNode;
    }


}
