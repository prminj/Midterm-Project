import java.util.Random;
import java.util.Scanner;

public class MagicMachine {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        input.nextLine();
        String str = input.nextLine();

        int[][] a = new int[num][num];
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = r.nextInt(5) + 1;
            }
        }
        System.out.println(magicMachine(num, a, str));
        input.close();

    }


    /**
     * implement this method for creating a magic machine
     * @param n     size of machine
     * @param array   an array in size n * n
     * @param input the input string
     * @return the output string of machine
     */
    public static String magicMachine(int n, int[][] array, String input) {
        String[][][] inputs = new String[n][n][2];
        inputs[0][0][0] = input;

        //greens
        for (int i = 0; i < n-1; i++) {
            inputs[i+1][0][0] = black(array[i][0], inputs[i][0][0]);
            inputs[i][1][1] = black(array[i][0], inputs[i][0][0]);
        }
        for (int i = 1; i < n-1; i++) {
            inputs[0][i+1][1] = black(array[0][i], inputs[0][i][1]);
            inputs[1][i][0] = black(array[0][i], inputs[0][i][1]);
        }
        //yellows
        inputs[n-1][1][1] = black(array[n-1][0], inputs[n-1][0][0]);
        inputs[1][n-1][0] = black(array[0][n-1], inputs[0][n-1][1]);
        //blues
        for (int i = 1; i < n-1; i++) {
            for (int j = 1; j < n-1; j++) {
                inputs[i+1][j][0] = black(array[i][j], inputs[i][j][0]);
                inputs[i][j+1][1] = black(array[i][j], inputs[i][j][1]);
            }
        }
        //pinks
        for (int i = 1; i < n-1; i++) {
            inputs[n-1][i+1][1] = white(array[n-1][i], inputs[n-1][i][1], inputs[n-1][i][0]);
            inputs[i+1][n-1][0] = white(array[i][n-1], inputs[i][n-1][1], inputs[i][n-1][0]);
        }
        return white(array[n-1][n-1], inputs[n-1][n-1][1], inputs[n-1][n-1][0]);
    }

    public static String black(int i, String input) {
        switch (i) {
            case 1:
                return black1(input);
            case 2:
                return black2(input);
            case 3:
                return black3(input);
            case 4:
                return black4(input);
            case 5:
                return black5(input);
            default:
                return null;
        }
    }

    public static String white(int i, String input1, String input2) {
        switch (i) {
            case 1:
                return white1(input1, input2);
            case 2:
                return white2(input1, input2);
            case 3:
                return white3(input1, input2);
            case 4:
                return white4(input1, input2);
            case 5:
                return white5(input1, input2);
            default:
                return null;
        }
    }


    public static String black1(String str) {
        char[] charArray = str.toCharArray();

        int left = 0;
        int right = charArray.length - 1;
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        return new String(charArray);
    }

    public static String black2(String str) {
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            output = output + str.charAt(i) + str.charAt(i);
        }
        return output;
    }

    public static String black3(String str) {
        return str + str;
    }

    public static String black4(String str) {
        return str.charAt(str.length()-1) + str.substring(0, str.length()-1);
    }

    public static String black5(String str) {
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            output = output + (char) ('z' - (str.charAt(i) - 'a'));
        }
        return output;
    }

    public static String white1(String str1, String str2) {
        String output = "";
        for (int i = 0; i < str1.length(); i++) {
            if (i < str2.length()) {
                output = output + str1.charAt(i) + str2.charAt(i);
            } else {
                output = output + str1.charAt(i);
            }
        }
        for (int i = str1.length(); i < str2.length(); i++) {
            output = output + str2.charAt(i);
        }
        return output;
    }

    public static String white2(String str1, String str2) {
        return str1 + black1(str2);
    }

    public static String white3(String str1, String str2) {
        return white1(str1, black1(str2));
    }

    public static String white4(String str1, String str2) {
        if (str1.length() % 2 == 0)
            return str1;
        return str2;
    }

    public static String white5(String input1, String input2) {
        String output = "";
        for (int i = 0; i < input1.length(); i++) {
            if (i < input2.length()) {
                output = output + (char) ('a'+((input1.charAt(i) - 'a' + input2.charAt(i) - 'a') % 26));
            } else {
                output = output + input1.charAt(i);
            }
        }
        for (int i = input1.length(); i < input2.length(); i++) {
            output = output + input2.charAt(i);
        }
        return output;
    }
}