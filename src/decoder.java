import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class decoder {

	public static void add_node(Node huff, int msg, String code){
		if (code.length()==1){
			Node leaf = new Node(0,msg);
			if (code.charAt(0)=='0')
				huff.set_left(leaf);
			else
				huff.set_right(leaf);
			return;
		}
		else {
			if (code.charAt(0)=='0') {
				if (huff.get_left()==null)
					huff.set_left(new Node(0,-1));
				add_node(huff.get_left(),msg,code.substring(1));
			}
			else {
				if (huff.get_right()==null)
					huff.set_right(new Node(0,-1));
				add_node(huff.get_right(), msg, code.substring(1));
			}
		}

	}

	public static Node build_huffTree_using_codeTable_txt(BufferedReader br) throws IOException {
		Node huff = new Node(0,-1);
		String line;
		while ((line = br.readLine())!=null){
			int msg = Integer.parseInt(line.split(" ")[0]);
			String code = line.split(" ")[1];
			add_node(huff,msg,code);
		}

		return huff;
	}

	public static void main(String[] args) throws IOException {

		if (args.length < 2){
			System.out.println("Please check .. not all inputs provided");
			System.out.println("Inputs:");
			System.out.println("   -args[0]: encoded.bin");
			System.out.println("   -args[1]: code_table.txt");
			return;
		}
		System.out.print("Reading code_table.txt\n");
		System.out.print("Building huffman tree .. ");
		File input = new File(args[1]);
		BufferedReader br = new BufferedReader(new FileReader(input));
		Node huffman_tree = build_huffTree_using_codeTable_txt(br);
		System.out.print("Done.\n");

		System.out.print("Reading encoded.bin .. \n");
		FileInputStream fis = new FileInputStream(new File(args[0]));
		int next_byte;
		String code_string="";
		while (true){
			next_byte = fis.read();
			if (next_byte==-1) break;
			String reverse = new StringBuffer(String.format("%8s",Integer.toBinaryString(next_byte)).replace(' ', '0')).reverse().toString();
			code_string = code_string+reverse;
		}
		fis.close();
		
		System.out.print("Decoding encoded.bin .. \n");
		System.out.print("Generating decoded.txt .. ");
		File decoded = new File("decoded.txt");
		BufferedWriter output = new BufferedWriter(new FileWriter(decoded));
		Node curr = huffman_tree;
		int i = 0;
		while(i<=code_string.length()){
			if (curr.get_msg()!=-1){
				output.write(curr.get_msg()+"\n");
				curr = huffman_tree;	
				if (i==code_string.length()) break;
			}
			else {
				if (code_string.charAt(i)=='0')
					curr = curr.get_left();
				else
					curr = curr.get_right();
				i++;
			}
		}
		output.close();
		System.out.print("Done.\n");
	}
}
