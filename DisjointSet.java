

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DisjointSet {
    int leader [];
    int size;

    DisjointSet(int s){
        size = s;
        leader = new int[size];
    }

    public void  build(int size){
        int i  = 0;
        while ( i < size){
            leader[i] = i;
            i++;
        }
    }

    public int leader(int a){
        int original = a;
        while (leader[a] != a){
            a = leader[a];
        }
        leader[original] = a;
        return leader[original];
    }

    public void get(int a,int b){
        if ( leader(a) == leader(b)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }

    public void union(int a,int b){
        a = leader(a);
        b = leader(b);
        leader[a] = b;
    }

    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        int n = Reader.nextInt();
        int q = Reader.nextInt();
        DisjointSet set = new DisjointSet(n+1);
        set.build(set.size);
        while ( q > 0){
            String s = Reader.next();
            if ( s.equals("get")){
                set.get(Reader.nextInt(),Reader.nextInt());
            }
            else{
                set.union(Reader.nextInt(),Reader.nextInt());
            }
            q--;
        }


    }
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }

    static long nextLong() throws IOException{
        return Long.parseLong(next());
    }
}