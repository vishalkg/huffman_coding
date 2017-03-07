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
		PairNode new_node = new PairNode(x);
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
			if (root.get_left()==null) {
				root = null;
				return x;
			}
			root = root.get_left();
			PairNode next = new PairNode(new Node(0,-1));
			next = root.get_next();
			root.set_next(null);
			root.set_prev(null);
			while(next!=null){
				PairNode temp = new PairNode(new Node(0,-1));
				temp = next;
				next = next.get_next();
				temp.set_next(null);
				temp.set_prev(null);
				root = merge(root,temp);
			}
			return x;
		}
	}
	
	private PairNode merge(PairNode old_node, PairNode new_node){
		if (old_node.get_freq()>=new_node.get_freq()){
			if (new_node.get_left()!=null)
				old_node.set_next(new_node.get_left());
			new_node.set_left(old_node);
			old_node.set_prev(new_node);
			return new_node;
		}
		else {
			if (old_node.get_left()!=null)
				new_node.set_next(old_node.get_left());
			if (new_node.get_next()!=null)
				new_node.get_next().set_prev(new_node);
			old_node.set_left(new_node);
			new_node.set_prev(old_node);
			return old_node;
		}
	}
}
