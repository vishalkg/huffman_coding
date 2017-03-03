package huffman_code;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Gen_huffman_code {
	
	public static void build_tree_using_binary_heap(int[][] freq_table) {
		
		/** Initially create a list of binary heaps with single **
		 ** node in the order of increasing frequencies			**/
		ArrayList<BinaryHeap> x = new ArrayList<BinaryHeap>();
		for (int i=0;i<freq_table.length;i++){
			BinaryHeap bh = new BinaryHeap();
			if (i==0)
				x.add(bh);
			else {
				for (int j=0;j<x.size();j++){
					if ((bh.key)<=x.get(j).key) {
						x.add(j,bh);
						break;
					}
				}
			}
		}
		
		/** Now we have a list of binary heaps having single nodes only	**
		 ** Now we will combine all to build a single binary heap		**/
		while (x.size() != 1){
			
		}
	}
	
	public static void main (String[] args) throws InterruptedException, IOException {
		
		/*****************************************************/
		/**Read the input file and build the frequency table**/
		File freq_data = null;
		if (args.length == 0)
			System.out.println("Input not provided .. terminating");
		else
			freq_data = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(freq_data));
		ArrayList<Integer> freq = new ArrayList<Integer>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		String line;
		while ((line = reader.readLine())!=null) {
			if (!line.equals("")){
				int next_value = Integer.parseInt(line.toString());
				if (values.contains(next_value)) {
					int index = values.indexOf(next_value);
					freq.set(index, freq.get(index)+1);
				}
				else {
					values.add(next_value);
					freq.add(1);
				}
			}
		}
		int freq_table[][] = new int[freq.size()][2];
		for (int i=0;i<freq.size();i++) {
			freq_table[i][0] = freq.get(i).intValue();
			freq_table[i][1] = values.get(i).intValue();
		}
		
		/*
		Arrays.sort(freq_table, (a,b)->Double.compare(a[0],b[0]));
		for (int i=0;i<freq_table.length;i++)
			System.out.println(freq_table[i][1]+"=>"+freq_table[i][0]);
		*/
		
		long startTime,endTime;
		/****************** Binary Heap ******************/
		{
			startTime = System.currentTimeMillis();
			for (int i=0;i<10;i++) {
				build_tree_using_binary_heap(freq_table);
			}
			endTime   = System.currentTimeMillis();
			double totalTime_BH = (endTime - startTime)/10;
			System.out.println(totalTime_BH);
		}
		
		final boolean DEBUG = false;
		if (DEBUG) {
		/*******************4 Way Heap********************/
		{
			startTime = System.currentTimeMillis();
			
			FourWayHeap nWH_4 = new FourWayHeap();
			for (int i=0;i<10;i++) {
				//System.out.println("Stub Function\n");
				Thread.sleep(50);
				nWH_4.stub();
			}
			endTime   = System.currentTimeMillis();
			double totalTime_4WH = (endTime - startTime)/10;
			System.out.println(totalTime_4WH);
		}
		
		/*******************Pairing Heap******************/
		{
			startTime = System.currentTimeMillis();
			PairingHeap ph = new PairingHeap();
			for (int i=0;i<10;i++) {
				//System.out.println("Stub Function\n");
				Thread.sleep(50);
				ph.stub();
			}
			endTime   = System.currentTimeMillis();
			double totalTime_PH = (endTime - startTime)/10;
			System.out.println(totalTime_PH);
		}
		}
	}
}
