package greedy;

/**
 * 小Q正在给一条长度为n的道路设计安置方案。
 *
 * 为了让问题更简单，小Q把道路视为n个方格，需要照亮的地方用’.’表示，不需要照亮的障碍物格子用’X’表示。
 * 小Q现在要在道路上安置一些路灯，对于安置在pos位置的路灯，这栈路灯可以照亮pos-1，pos，pos+1这三个位置。
 * 小Q希望能安置尽量少的路灯就照亮所有’.’区域。希望你能帮他计算一下最少需要多少盏路灯。
 */
public class Code06_Light {
 
    //s除了'.'就是'X'
    //路灯可以影响左中右三个位置
    //贪心
    public int minLight(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        int index = 0;
        int light = 0;
        while (index < chars.length){
            if(chars[index] == 'X'){
                index++;
            }else { //当前位置为'.'
                light++;
                if(index + 1 == chars.length){  //最后一个位置跳出
                    break;
                }else {
                    if (chars[index + 1] == 'X'){ // i+1位置是障碍物，跳到i+2位置
                        index = index + 2;
                    }else {  //i+1位置是'.'，灯直接放在i+1位置，可以照亮i，i+1，i+2，因此之直接跳到i+3位置
                        index = index + 3;
                    }
                }
            }
        }
        return light;
    }
}