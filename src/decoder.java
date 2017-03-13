import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;

@SuppressWarnings("unused")
public class decoder {

	public static void add_node(Node huff, int msg, String code){
		if (code.length()==1){
			Node leaf = new Node(msg,0);
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
		
		System.out.print("Reading code_table.txt and building huffman tree .. ");
		File input = new File("code_table.txt");
		BufferedReader br = new BufferedReader(new FileReader(input));
		Node huffman_tree = build_huffTree_using_codeTable_txt(br);
		System.out.print("Done.\n");
	}
}
