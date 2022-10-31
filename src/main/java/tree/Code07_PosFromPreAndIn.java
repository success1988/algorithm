package tree;
 
/**
 *已知一颗二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历数组，返回后序遍历数组。
 *
 *
 * eg：int[] pre = {1,2,4,5,3,6,7}
 *
 *     int[] in = {4,2,5,1,6,3,7}
 *
 * 返回
 *     {4,5,2,6,7,3,1}
 */
public class Code07_PosFromPreAndIn {
 
    public int[] preAndInGetPos(int[] pre,int[] in){
        if(pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int N = pre.length;
        int[] pos = new int[N];
        set(pre,in,pos,0,N-1,0,N-1,0,N-1);
        return pos;
    }
 
    public void set(int[] pre,int[] in,int[] pos,
                    int prei,int prej,
                    int ini,int inj,
                    int posi,int posj){
        if(prei > prej){
            return;
        }
        if(prei == prej){ //只剩下最后一个元素
            pos[posi] = pre[prei];
            return;
        }
        pos[posj] = pre[prei];  //先序遍历的一个数的第一个 一定是 后序遍历的最后一个
        int find = ini;
        for (;find <= inj;find++){
            if(in[find] == pre[prei]){ //找到中序遍历的 中 这个节点所在的位置，确定左子树和右子树
                break;
            }
        }
        // ini...inj整棵树的范围
        // find-ini 确定左子树的个数
        set(pre,in,pos,prei+1,prei+(find-ini),ini,find-1,posi,posi+(find-ini)-1);//左子树
        set(pre,in,pos,prei+(find-ini)+1,prej,find+1,inj,posi+(find-ini),posj-1);//右子树
    }
}