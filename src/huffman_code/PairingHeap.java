package huffman_code;

import java.util.ArrayList;

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
	
	public Node del_min(){
		if (is_empty())
			return null;
		else{
			Node x = new Node(0,-1);
			x = root.get_node();
			root = root.get_left();
			PairNode next = new PairNode(0, -1);
			next = root.get_next();
			root.set_next(null);
			while(next!=null){
				PairNode temp = new PairNode(0,-1);
				temp = next;
				next = next.get_next();
				temp.set_next(null);
				root = merge(root,temp);
			}
			return x;
		}
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
