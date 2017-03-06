package huffman_code;

public class PairNode {

	private int freq = 0;
	private int message = -1;
	private PairNode l;
	private PairNode next;
	
	public PairNode (int freq, int message) {
		this.freq = freq;
		this.message = message;
		this.l = null;
		this.next = null;
	}
	
	public int get_freq(PairNode x) {
		return x.freq;
	}
	
	public int get_msg(PairNode x) {
		return x.message;
	}
	
	public void set_freq(int freq){
		this.freq = freq;
	}
	
	public void set_msg(int msg){
		this.message = msg;
	}
	
	public PairNode get_left(){
		return this.l;
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
}
