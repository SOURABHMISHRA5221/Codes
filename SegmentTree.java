import java.util.Random;

public class SegmentedTree {
    int max_size ;
    long segment[];
    long maxSegment[];

    public SegmentedTree(int s){
        max_size = s;
        segment = new long[max_size];
        maxSegment = new long[max_size];
    }

    public void build(int[] array,int v,int l,int r){
        if ( l == r){
            segment[v] = array[l];
            maxSegment[v] = array[l];
        }
        else{
            int mid = l + (r-l)/2;
            build(array,2*v+1,l,mid);
            build(array,2*v+2,mid+1,r);
            segment[v] = segment[2*v+1]+segment[2*v+2];
            maxSegment[v] = max(maxSegment[2*v+1],maxSegment[2*v+2]);
        }
    }

    public void update(int v,int l,int r,int x,int y){
        if ( l <=x && x <= r) {
            if (l == r) {
                segment[v] += y;
            }
            else {
                int mid = l +(r-l)/2;
                update(2*v+1,l,mid,x,y);
                update(2*v+2,mid+1,r,x,y);
                segment[v] = segment[2*v+1]+segment[2*v+2];
            }
        }
    }

    public long sum(int v,int l,int r,int x,int y){

        if ( x <= l && r <= y){
            return segment[v];
        }
        else if ( r < x || y < l){
            return 0;
        }
        else{
            int mid = l + (r-l)/2;
            return sum(2*v+1,l,mid,x,y)+sum(2*v+2,mid+1,r,x,y);
        }
    }
    
    public long findMax(int v,int l,int r,int x,int y){
        if ( x <= l && r <= y){
            return maxSegment[v];
        }
        else if ( r < x || y < l){
            return -1;
        }
        else{
            int mid = l + (r-l)/2;
            return max(findMax(2*v+1,l,mid,x,y),findMax(2*v+2,mid+1,r,x,y));
        }
    }

    private long max(long a,long b){
        return a > b ? a : b;
    }

    public static void  main(String[] args){
        int array [] = {1,2,3,4,5};
        SegmentedTree segmentedTree = new SegmentedTree(1000000);
        segmentedTree.build(array,0,0,4);
    }
}
