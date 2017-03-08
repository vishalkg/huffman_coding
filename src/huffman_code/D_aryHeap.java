package huffman_code;

class D_aryHeap {
	
	public void stub(){
		System.out.println("Reached stub func");
	}
	
	private Node[] data;
	private int d;
	public int heap_size;
	
	/** Constructor **/
	public D_aryHeap(int num_nodes, int d){
		this.data = new Node[num_nodes];
		this.d = d;
		this.heap_size = 0;
	}
	
	public boolean is_empty() {
		return (heap_size == 0);
	}
	
	public void insert(Node node){
		if (heap_size==data.length)
			System.out.println("Size Overflow");
		else{
			heap_size++;
			data[heap_size-1] = node;
			manage_heap_upwards(heap_size-1);
		}
	}
	
	public Node del_min(){	
		if (is_empty())
			return null;
		else{
			Node min_node = new Node(0,-1);
			min_node = data[0];
			data[0] = data[heap_size-1];
			heap_size--;
			if (heap_size>0)
				manage_heap_downwards(0);
			return min_node;
		}
	}
	
	private void manage_heap_upwards(int node_index) {
		int parent_index;
		Node temp = new Node(0,-1);
		if (node_index!=0){
			parent_index = get_parent_index(node_index);
			if (data[parent_index].get_freq() >= data[node_index].get_freq()){
				temp = data[parent_index];
				data[parent_index] = data[node_index];
				data[node_index] = temp;
				manage_heap_upwards(parent_index);
			}
		}
	}
	
	private void manage_heap_downwards(int node_index){
		int l_index,r_index,min_index;
		Node temp = new Node(0,-1);
		l_index = get_left_child_index(node_index);
		r_index = get_right_child_index(node_index);
		if (r_index>=heap_size){
			if (l_index>=heap_size)
				return;
			else
				min_index = l_index;
		}
		else {
			if (data[l_index].get_freq() <= data[r_index].get_freq())
				min_index = l_index;
			else
				min_index = r_index;
		}
		if (data[node_index].get_freq() > data[min_index].get_freq()){
			temp = data[min_index];
			data[min_index] = data[node_index];
			data[node_index] = temp;
			manage_heap_downwards(min_index);
		}
	}
	
	private int get_parent_index(int node_index) {
		return (node_index-1)/d;
	}
	
	private int get_k_child_index(int node_index, int k) {
		return d*node_index+k;
	}
}
