package solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {
        Queue<Integer> cookies = new PriorityQueue<>();

        for (Integer sweetness : cookiesSweetness) {
            cookies.add(sweetness);
        }
        int currentMinSweetness = cookies.peek();
        int steps=0;
        while (currentMinSweetness < requiredSweetness && cookies.size() > 1) {

            int leastSweetCookie = cookies.poll();
            int secondLeastSweetCookie = cookies.poll();

            int combineSweetness = leastSweetCookie + (2 * secondLeastSweetCookie);

            cookies.add(combineSweetness);
            currentMinSweetness=cookies.peek();
            steps++;

        }

        return currentMinSweetness>requiredSweetness ? steps : -1;
    }
}
