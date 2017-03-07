package huffman_code;

class PairingHeap {
	
	public void stub(){
		System.out.println("Reached stub func");
	}
	
	private PairNode root;
	
	public PairingHeap(){
		root = null;
	}
	
	public boolean is_empty(){
		return root==null;
	}
	
	public PairNode get_root(){
		if (!is_empty())
			return root;
		else
			return null;
	}
	
	public void insert(Node x){
		PairNode new_node = new PairNode(x.get_freq(), x.get_msg());
		if (root==null)
			root = new_node;
		else 
			root = merge(root,new_node);
	}
	
	private PairNode merge(PairNode old_node, PairNode new_node){
		if (old_node.get_freq()>new_node.get_freq()){
			new_node.set_left(old_node);
			old_node.set_prev(new_node);
			old_node.set_next(new_node.get_left());
			return new_node;
		}
		else {
			new_node.set_next(old_node.get_left());
			old_node.set_left(new_node);
			new_node.set_prev(old_node);
			return new_node;
		}
	}
}
