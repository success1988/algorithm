package bit_compute;

public class BitMapUtil {


    /**
     * 加上某一个位上的值
     * @param value
     * @param bitValue
     * @return
     */
    public static int addFlag(int value, int bitValue){
        if(checkFlag(value, bitValue)){
            return value;
        }
        return value | bitValue;
    }

    /**
     * 移除某一位上的值
     * @param value
     * @param bitValue
     * @return
     */
    public static int removeFlag(int value, int bitValue){
        if(!checkFlag(value, bitValue)){
            return value;
        }
        return value ^ bitValue;
    }

    /**
     * 校验某一个位上是否有值
     * @param value
     * @param bitValue
     * @return
     */
    public static boolean checkFlag(int value, int bitValue){
        return (value & bitValue) > 0 ;
        //return (value & bitValue) == bitValue ;
    }
}