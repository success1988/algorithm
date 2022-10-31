package without_categories;
 
public class Code04_Mol3 {
 
    //数列：1，12，123，... 12345678910,1234567891011 ...
    // 1 <= left <= right <=1e9
    public int getMol_3(int left,int right){
        if(right < left){
            return 0;
        }
        int res = 0;
        while (left <= right){
            long sum = (1 + (left + 1)) * (left + 1) / 2;
            if (sum % 3 == 0){
                res++;
            }
            left++;
        }
        return res;
    }
}