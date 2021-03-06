package season1.week5.백준_작업_1번;

// 위상정렬

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] edgeCount = new int[n+1];
        int[] time = new int[n+1];
        int[] cache = new int[n+1];
        List[] graph = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            String[] splitedInput = input.split(" ");
            time[i] = Integer.parseInt(splitedInput[0]);
            int cnt = Integer.parseInt(splitedInput[1]);
            for (int j = 0; j < cnt; j++) {
                int before = Integer.parseInt(splitedInput[j+2]);
                graph[before].add(i);
                edgeCount[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if(edgeCount[i] ==0){
                q.add(i);
            }
            cache[i] = time[i];
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for (int i = 0; i < graph[cur].size(); i++) {
                int nxt = (int) graph[cur].get(i);
                if(--edgeCount[nxt] == 0) q.add(nxt);
                cache[nxt] = Math.max(cache[nxt], cache[cur] + time[nxt]);
            }
        }

        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer,cache[i]);
        }

        System.out.println(answer);
        br.close();
        bw.close();
    }
}
