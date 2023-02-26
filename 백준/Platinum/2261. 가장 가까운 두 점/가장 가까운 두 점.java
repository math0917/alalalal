import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Dot[] dot;
    static int minLength = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        dot = new Dot[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            dot[i] = new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(dot, Comparator.comparingInt(a -> a.x));
        System.out.println(findMinLength(0, n - 1));


    }

    private static int findMinLength(int start, int finish) {
        if (finish - start <= 2) {
            return brute(start, finish);
        }
        List<Dot> compareDot = new ArrayList<>();
        int mid = (finish + start) / 2;
        int leftLength = findMinLength(start, mid);
        int rightLength = findMinLength(mid + 1, finish);
        int standard = Math.min(leftLength, rightLength);

//        compareDot.add(dot[mid]);
//        System.out.println(standard);
        for (int i = start; i <= finish; i++) {
            if (standard > (dot[i].x - dot[mid].x) * (dot[i].x - dot[mid].x)){
                compareDot.add(dot[i]);
            }
        }


        compareDot.sort(comparator);
//        for (int i = 0; i < compareDot.size(); i++) {
//            System.out.println(compareDot.get(i).x + " "+ compareDot.get(i).y);
//        }

        for (int i = 0; i < compareDot.size() - 1; i++) {
            for (int j = i + 1; j < compareDot.size(); j++) {
                if (standard > (compareDot.get(j).y - compareDot.get(i).y) * (compareDot.get(j).y - compareDot.get(i).y)) {
                    standard = Math.min(compareDot.get(i).length(compareDot.get(j)), standard);
                } else {
                    break;
                }
            }


        }
        return standard;

    }

    private static int brute(int start, int finish) {
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= finish - 1; i++) {
            min = Math.min(min, dot[i].length(dot[i + 1]));
        }
        return min;
    }

    static Comparator<Dot> comparator = new Comparator<Dot>() {
        @Override
        public int compare(Dot o1, Dot o2) {
            return Integer.compare(o1.y, o2.y);
        }
    };
}

class Dot{
    int x;
    int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int length(Dot dot) {
        return (x - dot.x) * (x - dot.x) + (y - dot.y) * (y - dot.y);
    }

}