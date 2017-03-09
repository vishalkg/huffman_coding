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
	
	public Node get_root(){
		if (is_empty())
			return null;
		else
			return data[0];
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
		Node temp = new Node(0,-1);
		int min_index;
		if (get_k_child_index(node_index, 1)<heap_size){
			min_index = get_min_child_index(node_index);
			if (data[node_index].get_freq() >= data[min_index].get_freq()){
				temp = data[min_index];
				data[min_index] = data[node_index];
				data[node_index] = temp;
				manage_heap_downwards(min_index);
			}
		}
	}

	private int get_parent_index(int node_index) {
		return (node_index-1)/d;
	}
	
	private int get_k_child_index(int node_index, int k) {
		return d*node_index+k;
	}
	
	private int get_min_child_index(int node_index) {
		int min_index = get_k_child_index(node_index, 1);
		int k = 2;
		int k_index = get_k_child_index(node_index, k);
		while (k<=d && k_index<heap_size) {
			if (data[min_index].get_freq()>data[k_index].get_freq()){
				min_index = k_index;
			}
			k++;
			k_index = get_k_child_index(node_index, k);
		}
		return min_index;
	}
}
