package without_categories;
 
import org.junit.Test;
 
/**
 * 用中文表述数字
 * 12001  输出：1W2QL1
 * 11 1S1
 */
public class Code01_ChineseRepresentationNumber {
 
    //只接收范围1~9
    public String num1To9(int num){
        if(num < 1 || num > 9){
            return "";
        }
        String[] names = {"一","二","三","四","五","六","七","八","九"};
        return names[num - 1];
    }
 
    //只接受范围1~99
    public String num1To99(int num,boolean hasBai){
        if (num < 1 || num > 99){
            return "";
        }
        if (num < 10){
            return num1To9(num);
        }
        //有十位
        int shi = num / 10;
        if(shi == 1 && (!hasBai)){
            return "十" + num1To9(num % 10);
        }else {
            return num1To9(shi) + "十" + num1To9(num % 10);
        }
    }
 
    public  String num1To999(int num){
        if (num < 1 || num > 999){
            return "";
        }
        if(num < 100){
            return num1To99(num,false);
        }
        String res = num1To9(num / 100) + "百";
        int rest = num % 100;
        if (rest == 0){
            return res;
        }else if(rest >= 10) {
            res += num1To99(rest,true);
        }else {
            res += "零" + num1To9(rest);
        }
        return res;
    }
 
    public String num1To9999(int num){
        if(num < 1 || num > 9999){
            return "";
        }
        if (num < 1000){
            return num1To999(num);
        }
        String res = num1To9(num / 1000) + "千";
        int rest = num % 1000;
        if(rest == 0){
            return res;
        }else if(rest >= 100){
            res += num1To999(rest);
        }else {
            res += "零" + num1To99(rest,false);
        }
        return res;
    }
 
    public String num1To99999999(int num){
        if(num < 1 || num > 99999999){
            return "";
        }
        if (num < 10000){
            return num1To9999(num);
        }
        String res = num1To9999(num / 10000) + "万";
        int rest = num % 10000;
        if(rest == 0){
            return res;
        }else if(rest >= 1000){
            res += num1To9999(num);
        }else {
            res += num1To999(num);
        }
        return res;
    }
}