package max;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

 public class max
{
    private int[] id; // parent link (site indexed)
    private int[] size; // size of component i.e number of nodes
    private int component; // number of components
    private int[] Max;

    public max(int N)
    {
    	component = N;
        id = new int[N];
        size = new int[N];
        Max=new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            size[i] = 1;
            Max[i]=i;
        }
    }

    public int component()
    {
        return component;
    }

    public boolean connected(int p, int q)
    {
        return parent(p) == parent(q);
    }

    public int find(int p)
    {
        return Max[parent(p)];
    }

    private int parent(int i){
        while (i!=id[i]){
            i=id[i];
        }
        return i;
    }

    public void union(int p, int q)
    {
        int pID= parent(p);
        int qID= parent(q);
        int Maxi=Max[pID];
        int Maxj=Max[qID];
        if (pID== qID) return;
        if (size[pID] < size[qID]) {
            id[pID] = qID;
            size[qID] += size[qID];
            if(Maxi>Maxj){
                Max[qID]=Maxi;
            }

        }
        else {
            id[qID] = pID;
            size[pID] += size[qID];
            if(Maxj>Maxi){
                Max[pID]=Maxj;
            }
        }
        component--;
    }
    public static void main(String[] args)throws FileNotFoundException {
        String filename=new String(args[0]);
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        int N = Integer.parseInt(scan.nextLine());
       max Max = new max(N); 
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (line != null ) {
                String[] lineArray = line.split(" ");
                if (lineArray.length == 2) {
                    int p = Integer.parseInt(lineArray[0]);
                    int q = Integer.parseInt(lineArray[1]);
                    if (Max.connected(p, q))
                        continue; // Ignore if the are already connected.
                    Max.union(p, q); // Combine components.
                }
                else if(lineArray.length == 1){
                    int p = Integer.parseInt(lineArray[0]);
                    System.out.println(Max.find(p));
                }
            }
        }
    }
}