import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class Main {
    static boolean flag = true;
    static Stack<Long> stack;
    static List<int[]> proc;
    static StringBuilder sb = new StringBuilder();
    static final long MAX_VAL = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st;

        while (flag){

            boolean isProc = true;
            proc = new LinkedList<>();
            while (true){
                if (isProc) {
                    String line = br.readLine();
//                    System.out.println(line);
                    if (line.equals("QUIT")) {
                        flag = false;
                        break;
                    }
                    st = new StringTokenizer(line);
                    String procSt = st.nextToken();
                    switch (procSt) {
                        case "NUM":
                            proc.add(new int[]{0, Integer.parseInt(st.nextToken())});
                            break;
                        case "POP":
                            proc.add(new int[]{1});
                            break;
                        case "INV":
                            proc.add(new int[]{2});
                            break;
                        case "DUP":
                            proc.add(new int[]{3});
                            break;
                        case "SWP":
                            proc.add(new int[]{4});
                            break;
                        case "ADD":
                            proc.add(new int[]{5});
                            break;
                        case "SUB":
                            proc.add(new int[]{6});
                            break;
                        case "MUL":
                            proc.add(new int[]{7});
                            break;
                        case "DIV":
                            proc.add(new int[]{8});
                            break;
                        case "MOD":
                            proc.add(new int[]{9});
                            break;
                        case "END":
                            isProc = false;
                            break;
                    }
                }
                else{
                    int numCount = Integer.parseInt(br.readLine());
                    boolean errorFlag;

                    for (int t = 0; t < numCount; t++) {
                        int num = Integer.parseInt(br.readLine());
                        errorFlag = false;
                        stack = new Stack<>();
                        stack.add((long)num);
                        for (int[] pro : proc) {
                            switch (pro[0]) {
                                case 0:
                                    stack.add((long) pro[1]);
                                    break;
                                case 1:
                                    if (stack.isEmpty()) {
                                        errorFlag = true;
                                    } else {
                                        stack.pop();
                                    }
                                    break;
                                case 2:
                                    if (stack.isEmpty()) {
                                        errorFlag = true;
                                    } else {
                                        stack.add(stack.pop() * -1);
                                    }
                                    break;
                                case 3:
                                    if (stack.isEmpty()) {
                                        errorFlag = true;
                                    } else {
                                        stack.add(stack.peek());
                                    }
                                    break;
                                case 4:
                                    if (stack.size() < 2) {
                                        errorFlag = true;
                                    } else {
                                        Long top = stack.pop();
                                        Long bot = stack.pop();
                                        stack.add(top);
                                        stack.add(bot);
                                    }
                                    break;
                                case 5:
                                    if (stack.size() < 2) {
                                        errorFlag = true;
                                    } else {
                                        Long top = stack.pop();
                                        Long bot = stack.pop();
                                        if (Math.abs(top + bot) > MAX_VAL) {

                                            errorFlag = true;
                                        } else {
                                            stack.add(top + bot);
                                        }
                                    }
                                    break;
                                case 6:
                                    if (stack.size() < 2) {
                                        errorFlag = true;
                                    } else {
                                        Long top = stack.pop();
                                        Long bot = stack.pop();
                                        if (Math.abs(bot - top) > MAX_VAL) {
                                            errorFlag = true;
                                        } else {
                                            stack.add(bot - top);
                                        }
                                    }
                                    break;
                                case 7:
                                    if (stack.size() <= 1) {
                                        errorFlag = true;
                                    } else {
                                        long val = stack.pop() * stack.pop();
                                        if (Math.abs(val) > MAX_VAL) {
                                            errorFlag = true;

                                        } else {
                                            stack.add(val);
                                        }
                                    }
                                    break;
                                case 8: case 9:
                                    if (stack.size() <= 1) {
                                        errorFlag = true;
                                    } else {
                                        Long top = stack.pop();
                                        Long bot = stack.pop();
                                        if (top == 0) {
                                            errorFlag = true;
                                            break;
                                        }
                                        long q = (bot) / (top);
                                        long r = (bot) % (top);

                                        stack.add(pro[0] == 8 ? q : r);
                                    }
                                    break;
                            }
                            if (errorFlag) {
                                break;
                            }
                        }
                        if (errorFlag || stack.size() != 1) {
                            sb.append("ERROR").append('\n');
                        } else {

                            sb.append(stack.pop()).append('\n');
                        }
                    }

                    br.readLine();
                    sb.append('\n');
                    break;
                }



            }


        }

        System.out.print(sb);
    }
}