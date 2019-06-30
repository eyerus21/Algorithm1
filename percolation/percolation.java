import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opened;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private int len;
   private int  numberofsites ;

    public Percolation(int n){

        if (N <= 0) {
            throw new IllegalArgumentException("Given N <= 0");
        }
        opened = new boolean[N][N];
        uf     = new WeightedQuickUnionUF(N*N + 2);
        uf2    = new WeightedQuickUnionUF(N*N + 1);
        top    = 0;
        bottom = N*N + 1;
        len    = N;
        numberofsites = 0;
        // Make all sites Blocked
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                opened[row][col] = false;
            }
    }
    public void open(int row, int col){
        if (checkIndex(i, j)) {
            opened[i-1][j-1] = true;

            // Connect the top to the first (row,col) and the bottom the last (row,col) in maped one
            if (i == 1) {
                uf.union(j-1, top);
                uf2.union(j-1, top);
            }
            if (i == len) uf.union((i-1)*len+j-1, bottom);
            if (i > 1   && isOpen(i-1, j)) {
                uf.union((i-1)*len+j-1, (i-2)*len+j-1);// connect to top neighbour
                uf2.union((i-1)*len+j-1, (i-2)*len+j-1);
            }
            if (i < len && isOpen(i+1, j)) {
                uf.union((i-1)*len+j-1, i*len+j-1);// connect to bottom neighbour
                uf2.union((i-1)*len+j-1, i*len+j-1);
            }
            if (j > 1   && isOpen(i, j-1)) {
                uf.union((i-1)*len+j-1, (i-1)*len+j-2);// connect to right neighbour
                uf2.union((i-1)*len+j-1, (i-1)*len+j-2);
            }
            if (j < len && isOpen(i, j+1)) {
                uf.union((i-1)*len+j-1, (i-1)*len+j);// connect to left neighbour
                uf2.union((i-1)*len+j-1, (i-1)*len+j);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public boolean isOpen(row,  col) {
        if (checkIndex(i, j)) {
            return opened[i-1][j-1];
                numberofsites ++; // increase the number of open sites


        }
        throw new IndexOutOfBoundsException();
    }

    public boolean isFull( row,col) {
        if (checkIndex(i, j)) {
            return uf2.connected((i-1)*len+j-1, top);
        }
        throw new IndexOutOfBoundsException();
    }
    public int numberOfOpenSites()
         {
            return numberofsites ;    // Number of open sites.
         }

        public boolean percolates()
       {

            return uf.connected(top, bottom);

        }







}