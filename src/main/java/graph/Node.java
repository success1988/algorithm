package graph;


import java.util.ArrayList;

/**
 * 顶点
 * @Author admin
 * @Date 2022/8/25
 */
public class Node {
    /**
     * 值
     */
    public int value;
    /**
     * 入度：指向当前顶点的顶点个数
     */
    public int in;
    /**
     * 出度：从当前顶点出发的边的个数
     */
    public int out;
    /**
     * 出度对应的直接邻居
     */
    public ArrayList<Node> nexts;
    /**
     * 出度对应的边
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
