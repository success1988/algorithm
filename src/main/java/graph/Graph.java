package graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 基本概念： 顶点、边、度（入度、出度）、有向图、无向图、带权图
 * 表示方法： 邻接矩阵法（查找块，但当图为稀疏图时浪费内存空间）、邻接表法（节省内存空间，但查找慢）
 *
 * @Author admin
 * @Date 2022/8/25
 */
public class Graph {
    /**
     * 点集
     */
    public HashMap<Integer,Node>  nodes;
    /**
     * 边集
     */
    public HashSet<Edge> edges;


    public Graph(){
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
