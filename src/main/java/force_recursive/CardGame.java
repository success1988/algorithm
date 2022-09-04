package force_recursive;

/**
 * 绝顶聪明的人的纸牌游戏
 * @author admin
 * @date 2022/9/4 19:14
 */
public class CardGame {


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,100,4};
        System.out.println(winnerScore(arr, 0, 3));
    }

    public static int winnerScore(int[] arr, int L, int R){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(firstHand(arr,L,R), secondHand(arr,L,R));
    }

    private static int firstHand(int[] arr, int L, int R){
        if(L == R){
            return arr[L];
        }
        int selfChooseLeft = arr[L] + secondHand(arr, L+1, R);
        int selfChooseRight = arr[R] + secondHand(arr, L, R-1);
        return Math.max(selfChooseLeft, selfChooseRight);
    }


    private static int secondHand(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        int otherChooseLeft = firstHand(arr, L+1, R);
        int otherChooseRight = firstHand(arr, L, R-1);
        return Math.min(otherChooseLeft, otherChooseRight);
    }

}
