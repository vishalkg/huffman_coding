package huffman_code;

public class PairNode {

	private Node n = new Node(0,-1);
	private PairNode l;
	private PairNode next;
	private PairNode prev;
	
	public PairNode (int freq, int message) {
		this.n.set_freq(freq);
		this.n.set_msg(message);
		this.l = null;
		this.next = null;
		this.prev = null;
	}
	
	public int get_freq() {
		return this.n.get_freq();
	}
	
	public int get_msg() {
		return this.n.get_msg();
	}
	
	public void set_freq(int freq){
		this.n.set_freq(freq);
	}
	
	public void set_msg(int msg){
		this.n.set_msg(msg);
	}
	
	public PairNode get_left(){
		return this.l;
	}
	
	public Node get_node(){
		return this.n;
	}
	
	public void set_node(Node x){
		this.n = x;
	}
	
	public PairNode get_next(){
		return this.next;
	}
	
	public void set_left(PairNode x){
		this.l = x;
	}
	
	public void set_next(PairNode x){
		this.next = x;
	}
	
	public PairNode get_prev(){
		return this.prev;
	}
	
	public void set_prev(PairNode x){
		this.prev = x;
	}
}
