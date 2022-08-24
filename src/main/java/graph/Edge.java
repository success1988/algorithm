package graph;

/**
 * 边：
 *    from
 *    to
 *    weight
 * @Author admin
 * @Date 2022/8/25
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }


}
