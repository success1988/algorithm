package string_match;

/**
 * Trie树适用场景：查找前缀匹配的字符串,并且字符串中包含的字符集不能太大、要求字符串的前缀重合比较多、
 * Trie树不适合精确匹配查找，这种问题更适合用散列表或者红黑树来解决
 * Trie 树就是一种多模式串匹配算法
 * @author admin
 * @date 2022/8/29 7:26
 */
public class TrieTree {

    public static class TrieNode{
        public int pass;
        public int end;
        public TrieNode[] nexts;

        public TrieNode(){
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie{

        private TrieNode root;

        public Trie(){
            this.root = new TrieNode();
        }

        public void insert(String str){

            if(str == null){
                return;
            }

            TrieNode node = root;
            node.pass++;
            char[] chars = str.toCharArray();

            for(int i=0 ; i<chars.length; i++){
                char curChar = chars[i];
                int index = curChar = 'a';
                if(node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }

                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word){
            if(search(word) == 0){
                return;
            }

            char[] chars = word.toCharArray();
            TrieNode node = root;
            node.pass--;

            // a-b-c "abc"
            for (int i = 0; i < chars.length; i++) {
                char currentChar = chars[i];
                int currentIndex = currentChar - 'a';

                if(--node.nexts[currentIndex].pass == 0){
                    //正常情况，只有最后一个字符才可能会走到这里
                    node.nexts[currentIndex] = null;
                    return;
                }
                node = node.nexts[currentIndex];
            }
            node.end--;
        }


        //单词word在之前加入过几次  关注end值
        public int search(String word){
            if(word == null){
                return 0;
            }

            TrieNode node = root;
            char[] chars = word.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                char curChar = chars[i];
                int index = curChar = 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        //以pre作为前缀的单词加入了几次  关注pass值
        public int prefixNum(String pre){
            if(pre == null){
                return 0;
            }

            TrieNode node = root;
            char[] chars = pre.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                char curChar = chars[i];
                int index = curChar = 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }




}
