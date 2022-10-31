package without_categories;
/**
 * 给定一个只由0（假）、1（真）、&（逻辑与）、|（逻辑或）和^（异或）五种字符组成的字符串express，再给定一个布尔值desired。返回express能有多少种组合方式，可以达到desired的结果。
 *
 * eg:express = ”1^0|0|1”,desired=false
 *
 * 只有1^((0|0)|1)和1^(0|(0|1))的组合可以得到false，返回2。
 *
 * express = “1”,desired = false
 *
 * 无组合可以得到false，返回0
 */
public class Code01_Expression {
 
    //判断输入的expression是不是有效的字符
    public boolean isVaild(char[] exp){
        if((exp.length & 1) == 0){
            return false;
        }
        for (int i = 0;i < exp.length;i = i + 2){
            if ((exp[i] != '1') && (exp[i] != '0')){
                return false;
            }
        }
        for (int i = 1;i < exp.length;i = i + 2){
            if((exp[i] != '|') && (exp[i] != '&') && (exp[i] != '^')){
                return false;
            }
        }
        return true;
    }
 
    public int num1(String express,boolean desired){
        if (express == null || express.equals("")){
            return 0;
        }
        char[] chars = express.toCharArray();
        if(!isVaild(chars)){
            return 0;
        }
        return p(chars,desired,0,chars.length - 1);
    }
 
    //L R 不要压中逻辑符号
    public int p(char[] exp,boolean desired,int L,int R){
        if (L == R){
            if(exp[L] == '1'){
                return desired ? 1 : 0;
            }else {
                return desired ? 0 : 1;
            }
        }
 
        int res = 0;
        if (desired){ //结果为true
            //i 位置上尝试 L ... R 范围上的每一个逻辑符号，都是最后结合的
            for (int i = L + 1;i < R;i += 2){
                switch (exp[i]){
                    case '&':
                        res += p(exp,true,L,i - 1) * p(exp,true,i + 1,R);
                        break;
                    case '|':
                        res += p(exp,true,L,i - 1) * p(exp,false,i + 1,R);
                        res += p(exp,false,L,i - 1) * p(exp,true,i + 1,R);
                        res += p(exp,true,L,i - 1) * p(exp,true,i + 1,R);
                        break;
                    case '^':
                        res += p(exp,true,L,i - 1) * p(exp,false,i + 1,R);
                        res += p(exp,false,L,i - 1) * p(exp,true,i + 1,R);
                        break;
                }
            }
        }else { //结果为false
            for (int i = L + 1;i < R;i += 2){
                switch (exp[i]){
                    case '&':
                        res += p(exp,true,L,i - 1) * p(exp,false,i + 1,R);
                        res += p(exp,false,L,i - 1) * p(exp,true,i + 1,R);
                        res += p(exp,false,L,i - 1) * p(exp,false,i + 1,R);
                        break;
                    case '|':
                        res += p(exp,false,L,i - 1) * p(exp,false,i + 1,R);
                        break;
                    case '^':
                        res += p(exp,true,L,i - 1) * p(exp,true,i + 1,R);
                        res += p(exp,false,L,i - 1) * p(exp,false,i + 1,R);
                        break;
                }
            }
        }
        return res;
    }
}