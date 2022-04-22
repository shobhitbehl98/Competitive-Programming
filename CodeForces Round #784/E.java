/* Author _trevorphillips_ */

import java.io.*;
import java.util.*;

public class E {

    public static void main(String[] args) {
        FastReader f = new FastReader();
        StringBuilder sb = new StringBuilder();
        int t = f.nextInt();
        while (t-- > 0) {
            int n=f.nextInt();
            String[] arr=new String[n];
            for(int i=0;i<n;i++){
            arr[i]=f.nextLine();
            }
            HashMap<Character,HashMap<Character,Integer>> hm=new HashMap<>();
            HashMap<Character,HashMap<Character,Integer>> hb=new HashMap<>();
            for(int i=0;i<n;i++){
                if(!hm.containsKey(arr[i].charAt(0))){
                   HashMap<Character,Integer> l=new HashMap<>();
                   l.put(arr[i].charAt(1),l.getOrDefault(arr[i].charAt(1), 0)+1);
                   hm.put(arr[i].charAt(0),l);
                }else{
                    HashMap<Character,Integer> y=hm.get(arr[i].charAt(0));
                    y.put(arr[i].charAt(1),y.getOrDefault(arr[i].charAt(1), 0)+1);
                }

                if(!hb.containsKey(arr[i].charAt(1))){
                    HashMap<Character,Integer> l=new HashMap<>();
                    l.put(arr[i].charAt(0),l.getOrDefault(arr[i].charAt(0), 0)+1);
                    hb.put(arr[i].charAt(1),l);
                 }else{
                     HashMap<Character,Integer> y=hb.get(arr[i].charAt(1));
                     y.put(arr[i].charAt(0),y.getOrDefault(arr[i].charAt(0), 0)+1);
                 }
                
            }
            long ans=0;
            for(char p:hm.keySet()){
              HashMap<Character,Integer> l=hm.get(p);
              long m=0;
              if(l.size()<2){
                  continue;
              }
              long s=0;
              for(char x:l.keySet()){
                s+=l.get(x);
              }
              for(char x:l.keySet()){
                 s-=l.get(x);
                 m+=(l.get(x)*s);
                }
                ans+=m;
            }
            for(char p:hb.keySet()){
                HashMap<Character,Integer> l=hb.get(p);
                long m=0;
                if(l.size()<2){
                    continue;
                }
                long s=0;
                for(char x:l.keySet()){
                    s+=l.get(x);
                }
                for(char x:l.keySet()){
                    s-=l.get(x);
                   m+=(l.get(x)*s);
                }
              ans+=m;
            }
            
            sb.append(ans+"\n");
        }
        System.out.println(sb);

    }

    static final Random random = new Random();
    static final int mod = 1_000_000_007;

    static void ruffleSort(int[] a) {
        int n = a.length;// shuffle, then sort
        for (int i = 0; i < n; i++) {
            int oi = random.nextInt(n), temp = a[oi];
            a[oi] = a[i];
            a[i] = temp;
        }
        Arrays.sort(a);
    }

    static long add(long a, long b) {
        return (a + b) % mod;
    }

    static long sub(long a, long b) {
        return ((a - b) % mod + mod) % mod;
    }

    static long mul(long a, long b) {
        return (a * b) % mod;
    }

    static long exp(long base, long exp) {
        if (exp == 0)
            return 1;
        long half = exp(base, exp / 2);
        if (exp % 2 == 0)
            return mul(half, half);
        return mul(half, mul(half, base));
    }

    static long[] factorials = new long[2_000_001];
    static long[] invFactorials = new long[2_000_001];

    static void precompFacts() {
        factorials[0] = invFactorials[0] = 1;
        for (int i = 1; i < factorials.length; i++)
            factorials[i] = mul(factorials[i - 1], i);
        invFactorials[factorials.length - 1] = exp(factorials[factorials.length - 1], mod - 2);
        for (int i = invFactorials.length - 2; i >= 0; i--)
            invFactorials[i] = mul(invFactorials[i + 1], i + 1);
    }

    static long nCk(int n, int k) {
        return mul(factorials[n], mul(invFactorials[k], invFactorials[n - k]));
    }

    static void sort(int[] a) {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i : a)
            l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++)
            a[i] = l.get(i);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}