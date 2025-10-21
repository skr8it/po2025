import com.sun.tools.javac.Main;

import java.util.Arrays;

public class CodingBat {
    public String lastChars(String a, String b) {
        char[] charArray = a.toCharArray();
        char[] charBrray = b.toCharArray();
        char[] result = new char[]{'@','@'};
        if (charArray.length != 0){
            result[0] = charArray[0];
        }
        if (charBrray.length != 0){
            result[1] = charBrray[charBrray.length - 1];
        }
        String str = new String(result);
        return str;
    }
    //Warmup1
    public int intMax(int a, int b, int c) {
        int [] nums = new int []{a,b,c};
        if (a<b){
            nums[0]=b;
            nums[1]=a;
        }
        if (nums[0]<c){
            nums[2] = nums[0];
            nums[0] = c;
        }
        return nums[0];
    }
    public int diff21(int n) {
        if (n>21){
            return ((n-21)*2);
        }
        else {
            return (21-n);
        }
    }
    //Arrays 2
    public boolean sum28(int[] nums) {
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==2){
                sum = sum +2;
            }
            if (sum>8){
                return false;
            }
        }
        if (sum == 8){
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args) {
        CodingBat codingBat = new CodingBat();
        System.out.println("sum28 function with parameters: {2,2,5,2,2,6,8,8})");
        System.out.println(codingBat.sum28(new int[]{2,2,5,2,2,6,8,8}));
        System.out.println("diff21 function with parameter: 50");
        System.out.println(codingBat.diff21(50));
        System.out.println("intMax function with parameters: {6,4,35}");
        System.out.println(codingBat.intMax(6,4,35));
        System.out.println("lastChars function with parameters: sigma,boy");
        System.out.println(codingBat.lastChars("sigma","boy"));

    }


}

