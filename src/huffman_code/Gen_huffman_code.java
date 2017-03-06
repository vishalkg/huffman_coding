package huffman_code;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Gen_huffman_code {

	public static void gen_codes(Node n, String code){
		//System.out.println("Curr Node Freq: "+n.get_freq());
		if (n.get_msg() != -1){
			//System.out.println(n.get_msg()+"==>"+code);
			return;
		}
		else{
			gen_codes(n.get_left(),code+"0");
			gen_codes(n.get_right(),code+"1");
		}
	}
	
	public static void build_tree_using_binary_heap(int[] freq_table) {
		
		/** Initially create a min heap with single node trees **/
		BinaryHeap min_heap = new BinaryHeap(freq_table.length);
		for (int i=0;i<freq_table.length;i++){
			if (freq_table[i]!=0){
				Node n = new Node(freq_table[i], i);
				min_heap.insert(n);
			}
		}
		
		/** Now we have a min heap of trees having single nodes only	**
		 ** Now we will combine all to build a single huffman tree		**/
		while (min_heap.heap_size != 1){
			Node internal = new Node(0,-1);
			internal.set_left(min_heap.del_min());internal.get_left().set_parent(internal);
			internal.set_right(min_heap.del_min());internal.get_right().set_parent(internal);
			internal.set_freq(internal.get_left().get_freq()+internal.get_right().get_freq());
			/**
			System.out.println(internal.get_left().get_freq()+
					":"+internal.get_left().get_msg()+
					"|"+internal.get_right().get_freq()+
					":"+internal.get_right().get_msg());
			**/
			min_heap.insert(internal);
		}
		String code = "";
		gen_codes(min_heap.get_root(),code);
	}
	
	public static void main (String[] args) throws InterruptedException, IOException {
		
		/*****************************************************/
		/**Read the input file and build the frequency table**/
		File freq_data = null;
		if (args.length == 0){
			System.out.println("Input not provided ... terminating");
			return;
		}
		freq_data = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(freq_data));
		int[] freq_table = new int[20000000];
		Arrays.fill(freq_table, 0);
		String line;
		System.out.print("Reading input file ... ");
		while ((line = reader.readLine())!=null) {
			if (!line.equals("")){
				int next_value = Integer.parseInt(line.toString());
				freq_table[next_value]++;
			}
		}
		System.out.print("Done.\n");
				
		long startTime,endTime;
		/****************** Binary Heap ******************/
		{
			System.out.print("Building Huffman Tree using Binary Heap ... \nTrial:");
			startTime = System.currentTimeMillis();
			for (int i=0;i<10;i++) {
				System.out.print((i+1)+".. ");
				build_tree_using_binary_heap(freq_table);
			}
			System.out.print(" Done\n");
			endTime   = System.currentTimeMillis();
			double totalTime_BH = (endTime - startTime)/10;
			System.out.println("Average Time: "+totalTime_BH);
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