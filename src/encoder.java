import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;

public class encoder {

	private static void fillNwrite_code_table(Node huffman_tree, String code, String[] code_table, BufferedWriter output) throws IOException {
		if (huffman_tree.get_msg() != -1){
			code_table[huffman_tree.get_msg()] = code;
			output.write(huffman_tree.get_msg()+" "+code+"\n");
			return;
		}
		else{
			fillNwrite_code_table(huffman_tree.get_left(),code+"0",code_table,output);
			fillNwrite_code_table(huffman_tree.get_right(),code+"1",code_table,output);
		}
	}
	
	private static byte[] get_binary_data(ArrayList<Integer> freq_data, String[] code_table) {
		BitSet buff = new BitSet();
		int bitIndex = 0;
		for (int i=0;i<freq_data.size();i++){
			String symbol = code_table[freq_data.get(i)];
			for (int j=0;j<symbol.length();j++){
				char c = symbol.charAt(j);
				buff.set(bitIndex);
				if (c == '0') buff.clear(bitIndex);
				bitIndex++;
			}
		}
		return buff.toByteArray();
	}

	@SuppressWarnings("resource")
	public static void main (String[] args) throws InterruptedException, IOException {
		/**Read the input file and build the frequency table**/
		File input_file = null;
		Node huffman_tree = new Node(0,-1);

		if (args.length == 0){
			System.out.println("Input not provided ... terminating");
			return;
		}

		input_file = new File(args[0]);
		BufferedReader reader = new BufferedReader(new FileReader(input_file));
		System.out.println("Reading input file ... ");
		String line;
		ArrayList<Integer> freq_data = new ArrayList<>();
		while ((line = reader.readLine())!=null) {
			if (!line.equals("")) {
				freq_data.add(Integer.parseInt(line.toString()));
			}
		}

		int[] freq_table = Gen_huffman_code.build_freq_table(freq_data);

		System.out.print("\nBuilding huffman tree... ");
		huffman_tree = Gen_huffman_code.build_tree_using_4way_heap(freq_table);
		System.out.print("Done.\n");

		System.out.print("Generating code_table.txt .. ");
		String[] code_table = new String[Collections.max(freq_data)+1];
		Arrays.fill(code_table, "-1");
		File code_table_out = new File("code_table.txt");
		BufferedWriter output = new BufferedWriter(new FileWriter(code_table_out));
		fillNwrite_code_table(huffman_tree,"",code_table,output);
		output.close();
		System.out.print("Done.\n");

		/**
		for(int i=0;i<code_table.length;i++)
			if (code_table[i]!="-1")
				System.out.println(i+" ==> "+code_table[i]);
		 **/

		File encoded_out = new File("encoded.bin");
		FileOutputStream fos = new FileOutputStream(encoded_out); 
		System.out.print("Generating encoded.bin .. ");
		fos.write(get_binary_data(freq_data,code_table));
		System.out.print("Done.\n");
		fos.close();

	}
}