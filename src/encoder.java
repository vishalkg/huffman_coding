import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class encoder {
	
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
		while ((line = reader.readLine())!=null)
			if (!line.equals(""))
				freq_data.add(Integer.parseInt(line.toString()));
		
		int[] freq_table = Gen_huffman_code.build_freq_table(freq_data);
		
		System.out.println("Building huffman tree... ");
		huffman_tree = Gen_huffman_code.build_tree_using_4way_heap(freq_table);
		System.out.print("Done.\n");
	}
}
