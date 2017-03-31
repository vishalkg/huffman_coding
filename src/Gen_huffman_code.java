import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Gen_huffman_code {

	public static int count;
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

	public static Node build_tree_using_4way_heap(int[] freq_table) {

		/** Initially create a min binary heap with single node trees **/
		D_aryHeap min_4Wayheap = new D_aryHeap(count,4);
		for (int i=0;i<freq_table.length;i++){
			if (freq_table[i]!=0){
				Node n = new Node(freq_table[i], i);
				min_4Wayheap.insert(n);
			}
		}

		/** Now we have a min heap of trees having single nodes only	**
		 ** Now we will combine all to build a single huffman tree		**/
		while (min_4Wayheap.heap_size != 1){
			Node internal = new Node(0,-1);
			internal.set_left(min_4Wayheap.del_min());internal.get_left().set_parent(internal);
			internal.set_right(min_4Wayheap.del_min());internal.get_right().set_parent(internal);
			internal.set_freq(internal.get_left().get_freq()+internal.get_right().get_freq());
			/**
			System.out.println(internal.get_left().get_freq()+
					":"+internal.get_left().get_msg()+
					"|"+internal.get_right().get_freq()+
					":"+internal.get_right().get_msg());
			 **/
			min_4Wayheap.insert(internal);
		}
		String code = "";
		gen_codes(min_4Wayheap.get_root(),code);
		return min_4Wayheap.get_root();
	}

	public static Node build_tree_using_pairing_heap(int[] freq_table) {

		/** Initially create a min pairing heap with single node trees **/
		PairingHeap min_pairingheap = new PairingHeap();
		for (int i=0;i<freq_table.length;i++){
			if (freq_table[i]!=0){
				Node n = new Node(freq_table[i], i);
				min_pairingheap.insert(n);
			}
		}

		/** Now we have a min pairing heap of trees having single nodes only	**
		 ** Now we will combine all to build a single huffman tree				**/
		while(min_pairingheap.get_root().get_left()!=null){
			Node internal = new Node(0,-1);
			internal.set_left(min_pairingheap.del_min());internal.get_left().set_parent(internal);
			internal.set_right(min_pairingheap.del_min());internal.get_right().set_parent(internal);
			internal.set_freq(internal.get_left().get_freq()+internal.get_right().get_freq());
			/**
			System.out.println(internal.get_left().get_freq()+
					":"+internal.get_left().get_msg()+
					"|"+internal.get_right().get_freq()+
					":"+internal.get_right().get_msg());
			 **/
			min_pairingheap.insert(internal);
		}
		String code = "";
		gen_codes(min_pairingheap.get_root().get_node(),code);
		return min_pairingheap.get_root().get_node();
	}

	public static Node build_tree_using_binary_heap(int[] freq_table) {

		/** Initially create a min binary heap with single node trees **/
		BinaryHeap min_heap = new BinaryHeap(count);
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
		return min_heap.get_root();
	}

	public static int[] build_freq_table(ArrayList<Integer> freq_data){
		int[] freq_table = new int[Collections.max(freq_data)+1];
		Arrays.fill(freq_table, 0);	
		System.out.print("Building Freq Table ... ");
		for (int i=0;i<freq_data.size();i++){
			freq_table[freq_data.get(i)]++;
			if (freq_table[freq_data.get(i)]==1) count++;
		}
		return freq_table;
	}
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main (String[] args) throws InterruptedException, IOException {

		/**Read the input file and build the frequency table**/
		File input_file = null;
		if (args.length == 0){
			System.out.println("Input not provided ... terminating");
			return;
		}
		input_file = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(input_file));
		System.out.print("Reading input file ... ");
		String line;
		ArrayList<Integer> freq_data = new ArrayList<>();
		while ((line = reader.readLine())!=null)
			if (!line.equals(""))
				freq_data.add(Integer.parseInt(line.toString()));
		
		int[] freq_table = build_freq_table(freq_data);
		System.out.print("Done.\n");
		long startTime,endTime;
		int num_runs = 10;
		Node huffman_tree = new Node(0,-1);
		/****************** Binary Heap ******************/
		{
			System.out.print("Building Huffman Tree using Binary Heap ... \nRun:");
			startTime = System.currentTimeMillis();

			for (int i=0;i<num_runs;i++) {
				System.out.print((i+1)+".. ");
				huffman_tree = build_tree_using_binary_heap(freq_table);
			}
			System.out.print(" Done\n");
			endTime   = System.currentTimeMillis();
			double totalTime_BH = (endTime - startTime)/num_runs;
			System.out.println("Average Time: "+totalTime_BH);
		}


		/*******************Pairing Heap********************/
		{
			System.out.print("Building Huffman Tree using Pairing Heap ... \nRun:");
			startTime = System.currentTimeMillis();
			for (int i=0;i<num_runs;i++) {
				System.out.print((i+1)+".. ");
				huffman_tree = build_tree_using_pairing_heap(freq_table);
			}
			System.out.print(" Done\n");
			endTime   = System.currentTimeMillis();
			double totalTime_PH = (endTime - startTime)/num_runs;
			System.out.println("Average Time: "+totalTime_PH);
		}


		/*******************4 Way Heap******************/
		{
			System.out.print("Building Huffman Tree using 4-ary Heap ... \nRun:");
			startTime = System.currentTimeMillis();
			for (int i=0;i<num_runs;i++) {
				System.out.print((i+1)+".. ");
				huffman_tree = build_tree_using_4way_heap(freq_table);
			}
			System.out.print(" Done\n");
			endTime   = System.currentTimeMillis();
			double totalTime_4WH = (endTime - startTime)/num_runs;
			System.out.println("Average Time: "+totalTime_4WH);
		}
	}
}
