import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

interface Calc {
    Long calculation(Long a, Long b);
}

public class Main {
    static int N;
    static Long a, b;
    static Long[] nums;
    static ArrayList<String> program;
    static ArrayList<String> num_x;
    static Deque<Long> stack;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputString = br.readLine().split(" ");


        while (!inputString[0].equals("QUIT")) {
            // input 받기
            program = new ArrayList<>();
            num_x = new ArrayList<>();

            while (!inputString[0].equals("END")) {
                program.add(inputString[0]);
                if (inputString.length > 1 && inputString[0].equals("NUM")) {
                    num_x.add(inputString[1]);
                }
                inputString = br.readLine().split(" ");
            }

            N = Integer.parseInt(br.readLine());
            nums = new Long[N];
            for(int i = 0; i < N; i++) {
                nums[i] = Long.parseLong(br.readLine());
            }

            for(Long num: nums) {
                run(num);
            }

            System.out.println();
            br.readLine();
            inputString = br.readLine().split(" ");
        }
    }

    private static void run(Long num) {
        // 각 숫자별로 프로그램 실행하기
        stack = new ArrayDeque<>();
        stack.push(num);

        int num_idx = 0;
        for(String p:program) {
            //System.out.printf("- %d %s %s\n", num, p, stack.toString());
            switch (p) {
                case "NUM":
                    stack.push(Long.parseLong(num_x.get(num_idx)));
                    num_idx += 1;
                    break;

                case "POP":
                    if(validate(1)) {
                        stack.pop();
                        break;
                    }
                    else {
                        System.out.println("ERROR");
                        return;
                    }

                case "INV":
                    if(validate(1)) {
                        Long a = stack.pop();
                        a = -a;
                        stack.push(a);
                        break;
                    }
                    else {
                        System.out.println("ERROR");
                        return;
                    }


                case "DUP":
                    if(validate(1)) {
                        stack.push(stack.peek());
                        break;
                    }
                    else {
                        System.out.println("ERROR");
                        return;
                    }

                case "SWP":
                    if(validate(1)) {
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(a);
                        stack.push(b);
                        break;
                    }
                    else {
                        System.out.println("ERROR");
                        return;
                    }

                case "ADD":
                    if(operate(stack, (num1, num2) -> num1 + num2))
                        break;
                    else {
                        System.out.println("ERROR");
                        return;
                    }

                case "SUB":
                    if(operate(stack, (num1, num2) -> num1 - num2))
                        break;
                    else {
                        System.out.println("ERROR");
                        return;
                    }

                case "MUL":
                    if(operate(stack, (num1, num2) -> num1 * num2))
                        break;
                    else {
                        System.out.println("ERROR");
                        return;
                    }

                case "DIV":
                    if(operate(stack, (num1, num2) -> num1 / num2, "DIV"))
                        break;
                    else {
                        System.out.println("ERROR");
                        return;
                    }

                case "MOD":
                    if(operate(stack, (num1, num2) -> num1 % num2, "MOD"))
                        break;
                    else {
                        System.out.println("ERROR");
                        return;
                    }
            }
        }
        if (stack.size() == 1) {
            System.out.println(stack.pop());
        }
        else System.out.println("ERROR");
    }

    private static boolean validate(int count) {
        return stack.size() >= count;
    }

    private static boolean operate(Deque<Long> stack, Calc func) {
        if (validate(2)) {
            b = stack.pop();
            a = stack.pop();

//			System.out.printf("%d %d\n", a, b);
            Long result = func.calculation(a, b);
            if (Math.abs(result) <= 1000000000) {
                stack.push(result);
                return true;
            }
            return false;
        }
        return false;
    }

    private static boolean operate(Deque<Long> stack, Calc func, String opName) {
        if (validate(2)) {
            b = stack.pop();
            a = stack.pop();
            if (b == 0)
                return false;
//			System.out.printf("%d %d\n", a, b);
            Long result = func.calculation(Math.abs(a), b);
            if (opName.equals("DIV") && (a > 0 ^ b > 0)){
                stack.push(-Math.abs(result));
                return true;
            }

            if (opName.equals("MOD") && (a < 0)) {
                stack.push(-Math.abs(result));
                return true;
            }

            stack.push(Math.abs(result));
            return true;
        }
        return false;
    }
}