package huffman_code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Gen_huffman_code {

	public static void gen_codes(Node n, String code){
		//System.out.println("Curr Node Freq: "+n.get_freq());
		if (n.get_msg() != -1){
			System.out.println(n.get_msg()+"==>"+code);
			return;
		}
		else{
			gen_codes(n.get_left(),code+"0");
			gen_codes(n.get_right(),code+"1");
		}
	}

	public static void build_tree_using_4way_heap(int[] freq_table) {

		/** Initially create a min binary heap with single node trees **/
		D_aryHeap min_4Wayheap = new D_aryHeap(freq_table.length,4);
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
	}

	public static void build_tree_using_pairing_heap(int[] freq_table) {

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
	}

	public static void build_tree_using_binary_heap(int[] freq_table) {

		/** Initially create a min binary heap with single node trees **/
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
		int num_trials = 1;

		/****************** Binary Heap ******************/
		{
			System.out.print("Building Huffman Tree using Binary Heap ... \nTrial:");
			startTime = System.currentTimeMillis();

			for (int i=0;i<num_trials;i++) {
				System.out.print((i+1)+".. ");
				build_tree_using_binary_heap(freq_table);
			}
			System.out.print(" Done\n");
			endTime   = System.currentTimeMillis();
			double totalTime_BH = (endTime - startTime)/num_trials;
			System.out.println("Average Time: "+totalTime_BH);
		}


		/*******************Pairing Heap********************/
		{
			System.out.print("Building Huffman Tree using Pairing Heap ... \nTrial:");
			startTime = System.currentTimeMillis();
			for (int i=0;i<num_trials;i++) {
				System.out.print((i+1)+".. ");
				build_tree_using_pairing_heap(freq_table);
			}
			System.out.print(" Done\n");
			endTime   = System.currentTimeMillis();
			double totalTime_BH = (endTime - startTime)/num_trials;
			System.out.println("Average Time: "+totalTime_BH);
		}


		/*******************4 Way Heap******************/
		{
			System.out.print("Building Huffman Tree using 4-ary Heap ... \nTrial:");
			startTime = System.currentTimeMillis();
			for (int i=0;i<num_trials;i++) {
				System.out.print((i+1)+".. ");
				build_tree_using_4way_heap(freq_table);
			}
			System.out.print(" Done\n");
			endTime   = System.currentTimeMillis();
			double totalTime_BH = (endTime - startTime)/num_trials;
			System.out.println("Average Time: "+totalTime_BH);
		}
	}
}
