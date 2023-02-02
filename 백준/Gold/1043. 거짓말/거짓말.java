

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static Set<Integer> set;
    static int knowCount;
    static Set<Integer> partySet;
    static int[][] party;
    static ArrayList<Integer>[] visits;
    static Stack<Integer> stack;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        set = new HashSet<>();
        visits = new ArrayList[n+ 1];
        partySet = new HashSet<>();
        for (int i = 0; i < n + 1; i++) {
            visits[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        knowCount = Integer.parseInt(st.nextToken());

        stack = new Stack<>();
        for (int i = 0; i < knowCount; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        party = new int[m][];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            party[i] = new int[count];

            for (int j = 0; j < count; j++) {
                int visitor = Integer.parseInt(st.nextToken());
                visits[visitor].add(i);
                if (set.contains(visitor)) {
                    partySet.add(i);
                    stack.add(i);
                }
                party[i][j] = visitor;
            }
        }

        while (!stack.isEmpty()) {
            Integer thisTurn = stack.pop();
//            System.out.println("party" + thisTurn+ " ");
            for (int next : party[thisTurn]) {
//                간적이 없음
                if (!set.contains(next)) {
                    set.add(next);
                    for (int goParty : visits[next]) {
                        if (!partySet.contains(goParty)) {
                            partySet.add(goParty);
                            stack.add(goParty);
                        }
                    }
                }
            }
        }
        System.out.println(m - partySet.size());

    }
}
