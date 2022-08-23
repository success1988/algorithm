/**
 * 树型DP问题：
 *     可以通过向左右子树要信息来计算整棵树的结果
 *     从左、右两棵子树要到的信息要能成为计算整棵树结果时的充分必要条件
 * @Author admin
 * @Date 2022/8/23
 */
package tree.dynamic_plan;

/**
   二叉树的递归套路
 1.假设以X节点为头，假设可以向X左树和X右树要任何信息
 2.在上一步的假设下，讨论以X为头节点的树，得到答案的所有可能性（最重要）
 3.列出所有可能性后，确定到底需要向左树和右树要什么样的信息
 4.把左树信息和右树信息求全集， 就是任何一棵子树都要返回的信息S
 5.递归函数都返回S，每一棵子树都这么要求
 6.写代码，在代码中考虑如何把左树的信息和右树的信息整合出整棵树的信息

 **/