package string_match;
 
import org.junit.Test;
 
import java.util.TreeMap;
 
/**
 * 给你一个字符串类型的数组arr，譬如：
 * String[] arr = {“b\\cst”,”d\\”,”a\\d\\e”,”a\\b\\c”};
 * 你把这些路径中蕴含的目标结构给画出来，于目录直接列在父目录下面，并比父目录向右前进两格，就像这样：
 */
public class DirectoryListPrint {
 
    class Node{  //把字符串中每一级目录用节点表示，节点之间的路径表示目录名称
        String name;
        TreeMap<String,Node> nextMap; //有序表，保证按顺序打印
 
        public Node(String name){
            this.name = name;
            nextMap = new TreeMap<>();
        }
    }
 
    //打印目录
    public void printDirectoryString(String[] strings){
        if (strings == null){
            System.out.println();
        }
        Node head = generateFolderTree(strings);
        printFolderTree(head,0);
    }
 
    //根据所给的字符目录数组，生成一个前缀树
    private Node generateFolderTree(String[] folderPath){
        //先生成一个空节点，表示前缀树的头节点
        Node head = new Node("");
        for(String str : folderPath){
            String[] paths = str.split("\\\\"); //因为有转义和正则匹配，所以要用四个\
            Node cur = head;
            for (int i = 0;i < paths.length;i++){
                if (!cur.nextMap.containsKey(paths[i])){
                    cur.nextMap.put(paths[i],new Node(paths[i]));
                }
                cur = cur.nextMap.get(paths[i]);
            }
        }
        return head;
    }
 
    //打印一个前缀数  深度优先遍历
    private void printFolderTree(Node head,int level){
        if(level != 0){
            System.out.println(get2nSpace(level) + head.name);
        }
        for(Node next : head.nextMap.values()){
            printFolderTree(next,level + 1);
        }
    }
 
    //打印每级目录的前面空格
    private String get2nSpace(int level){
        String res = "";
        for (int i = 1;i < level;i++){
            res += "  ";
        }
        return res;
    }
 
    @Test
    public void test(){
        String[] strings = {"b\\cst","d\\","a\\d\\e","a\\b\\c"};
        printDirectoryString(strings);
    }
}