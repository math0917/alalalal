import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int t,f, h;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        t = f = h = 0;
        for (int i = 0; i < n; i++) {
            switch (Integer.parseInt(st.nextToken())) {
                case 1:
                    t += 1;
                    break;
                case -1:
                    f +=1;
                    break;
                case 0:
                    h +=1;
            }
        }
        if (h >= (n + 1) / 2) {
            System.out.println("INVALID");
        } else {
            if (t > f) {
                System.out.println("APPROVED");
            } else{
                System.out.println("REJECTED");
            }
        }
    }
}