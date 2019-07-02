import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int size;
    private boolean[][] grid;
    private int numberofsites;
    private final int top;
    private final int bottom;

    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int n) {
        if (n < 1)
            throw new IndexOutOfBoundsException("N must be greater than zero!");
        grid = new boolean[n][n];
        size = n;
        uf = new WeightedQuickUnionUF(n * n + 2); // N*N+2 sites
        numberofsites = 0;
        top = 0;
        bottom = n * n + 1;

        // Make all sites Blocked
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = false;
            }
        }

// Connect the top to the first (row,col) and the bottom the last (row,col) in maped one
        for (int col = 0; col < n; col++) {
            uf.union(top, encode(0, col));
        }
        for (int col = 0; col < n; col++) {
            uf.union(bottom, encode(n - 1, col));
        }
    }

// An integer ID (1...N) for site (row, col).
    private int encode(int row, int col) {
        return ((row * size) + col) + 1;
    }

    // Open site (i, j) if it is not open already.
    public void open(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (grid[row][col])
            return;
        if (checkIndexBound(row, col)) {
            grid[row][col] = true;
            numberofsites++; // increase the number of open sites
        }

        if (row - 1 >= 0 && isOpen(row - 1, col))
            uf.union(encode(row, col), topp(row, col)); // connect to top neighbour
        if (row + 1 < size && isOpen(row + 1, col))
            uf.union(encode(row, col), bottomm(row, col)); // connect to bottom neighbour
        if (col - 1 >= 0 && isOpen(row, col - 1))
            uf.union(encode(row, col), left(row, col)); // connect to left neighbour
        if (col + 1 < size && isOpen(row - 1, col))
            uf.union(encode(row, col), right(row, col)); // connect to right neighbour

    }

    private int topp(int row, int col) {
        return (encode(row, col));
    }

    private int bottomm(int row, int col) {
        return (encode(row, col));
    }

    private int left(int row, int col) {
        return (encode(row, col));
    }

    private int right(int row, int col) {
        return (encode(row, col));
    }

    // Is site (row, col) open?
    public boolean isOpen(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (checkIndexBound(row, col))
            return grid[row][col];
        throw new IndexOutOfBoundsException("Index out of range");
    }

    // Is site (row, col) full?
    public boolean isFull(int i, int j) {
        int row = i - 1;
        int col = j - 1;
        if (checkIndexBound(row, col))
            return (uf.connected(encode(row, col), top) && isOpen(row, col));
        throw new IndexOutOfBoundsException("Index out of range");
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return numberofsites;
    }

    // Does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);

    }

// Checks if the row and column are within the range of the grid
    // Is it within the range??
    private boolean checkIndexBound(int row, int col) {
        if (row < 0 || row > size || col < 0 || col > size)
            return false;
        return true;

    }
}
