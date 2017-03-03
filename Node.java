package huffman_code;

public class Node{

	private Node p;
	private Node l=null;
	private Node r=null;
	private int freq = 0;
	private int message = -1;
	
	public Node(int freq, int message){
		this.freq = freq;
		this.message = message;
	}
	
	public void set_parent(Node p){
		this.p = p;
	}
	
	public Node get_parent(){
		return p;
	}
	
	public void set_left(Node l){
		this.l = l;
	}
	
	public Node get_left(){
		return this.l;
	}
	
	public void set_right(Node r){
		this.r = r;
	}
	
	public Node get_right(){
		return this.r;
	}
	
	public void set_freq(int freq){
		this.freq = freq;
	}
	
	public int get_freq(){
		return this.freq;
	}
	
	public void set_msg(int msg){
		this.message = msg;
	}
	
	public int get_msg(){
		return this.message;
	}
}
