import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MagicMachine {

    public static void main(String[] args) {
        // test();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str = scanner.next();
        int[][] array = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = rand.nextInt(5)  + 1;
            }
        }
        System.out.println(magicMachineFunction(n, array, str));

        scanner.close();
    }

    private static void test() {
        int n = 8;
        String str = "qmiqwnhwnrckeirepjgv";
        int[][] array = {
                {2, 5, 5, 4, 2, 1, 5, 5 },
                {2, 1, 2, 4, 4, 1, 5, 4 },
                {4, 4, 1, 1, 1, 5, 1, 4 },
                {4, 1, 4, 4, 1, 4, 5, 1 },
                {1, 1, 1, 5, 1, 4, 4, 5 },
                {4, 4, 5, 4, 5, 1, 5, 5 },
                {1, 4, 4, 4, 1, 1, 1, 4 },
                {4, 5, 4, 5, 5, 1, 4, 4 },
        };
        System.out.println(magicMachineFunction(n, array, str));
    }

    /**
     * implement this method for creating a magic machine
     * @param n     size of machine
     * @param array   an array in size n * n
     * @param input the input string
     * @return the output string of machine
     */
    public static String magicMachineFunction(int n, int[][] array, String input) {
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

    private static String black(int i, String input) {
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

    private static String white(int i, String input1, String input2) {
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


    private static String black1(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = input.charAt(i) + output;
        }
        return output;
    }

    private static String black2(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = output + input.charAt(i) + input.charAt(i);
        }
        return output;
    }

    private static String black3(String input) {
        return input + input;
    }

    private static String black4(String input) {
        return input.charAt(input.length()-1) + input.substring(0, input.length()-1);
    }

    private static String black5(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = output + (char) ('z' - (input.charAt(i) - 'a'));
        }
        return output;
    }

    private static String white1(String input1, String input2) {
        String output = "";
        for (int i = 0; i < input1.length(); i++) {
            if (i < input2.length()) {
                output = output + input1.charAt(i) + input2.charAt(i);
            } else {
                output = output + input1.charAt(i);
            }
        }
        for (int i = input1.length(); i < input2.length(); i++) {
            output = output + input2.charAt(i);
        }
        return output;
    }

    private static String white2(String input1, String input2) {
        return input1 + black1(input2);
    }

    private static String white3(String input1, String input2) {
        return white1(input1, black1(input2));
    }

    private static String white4(String input1, String input2) {
        if (input1.length() % 2 == 0)
            return input1;
        return input2;
    }

    private static String white5(String input1, String input2) {
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