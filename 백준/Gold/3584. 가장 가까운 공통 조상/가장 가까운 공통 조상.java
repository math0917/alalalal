import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int testCase;
    static int n;
    static Node[] tree;
    static int aim1, aim2;
    static int[] depth;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        testCase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testCase; t++) {
            n = Integer.parseInt(br.readLine());
            tree = new Node[n + 1];
            for (int i = 1; i < n + 1; i++) {
                tree[i] = new Node(i);
            }
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());

                Node par = tree[Integer.parseInt(st.nextToken())];
                Node child = tree[Integer.parseInt(st.nextToken())];
                par.child.add(child);
                child.par = par;
            }
            st = new StringTokenizer(br.readLine());
            aim1 = Integer.parseInt(st.nextToken());
            aim2 = Integer.parseInt(st.nextToken());

            Node value = tree[n];
            depth = new int[n + 1];
            while (value.par != null) {
                value = value.par;
            }
            Node root = value;
            depth[root.i] = 0;
            dfs(root, 0);
            find(aim1, aim2);

        }
        System.out.println(sb);
    }

    private static void find(int aim1, int aim2) {
        if (depth[aim1] < depth[aim2]) {
            find(aim1, tree[aim2].par.i);
        } else if (depth[aim1] > depth[aim2]) {
            find(tree[aim1].par.i, aim2);
        } else {
            if (aim1 != aim2) {
                find(tree[aim1].par.i, tree[aim2].par.i);
            } else {
                sb.append(aim1).append('\n');
                return;
            }
        }
    }

    private static void dfs(Node node, int i) {
        for (Node child : node.child) {
            depth[child.i] = i + 1;
            dfs(child, i + 1);
        }
    }
}

class Node{
    Node par;


    ArrayList<Node> child;
    int i;

    public Node(int i) {
        this.i = i;
        this.par = null;
        this.child = new ArrayList<>();
    }
}