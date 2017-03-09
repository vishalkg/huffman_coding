all: Node.class PairNode.class BinaryHeap.class PairingHeap.class D_aryHeap.class Gen_huffman_code.class Encoder.class
Node.class: src/huffman_code/Node.java
	javac -d ./bin -classpath ./bin src/huffman_code/Node.java

PairNode.class: src/huffman_code/PairNode.java
	javac -d ./bin -classpath ./bin src/huffman_code/PairNode.java

BinaryHeap.class: src/huffman_code/BinaryHeap.java
	javac -d ./bin -classpath ./bin src/huffman_code/BinaryHeap.java

PairingHeap.class: src/huffman_code/PairingHeap.java
	javac -d ./bin -classpath ./bin src/huffman_code/PairingHeap.java

D_aryHeap.class: src/huffman_code/D_aryHeap.java
	javac -d ./bin -classpath ./bin src/huffman_code/D_aryHeap.java

Gen_huffman_code.class: src/huffman_code/Gen_huffman_code.java
	javac -d ./bin -classpath ./bin src/huffman_code/Gen_huffman_code.java

Encoder.class: src/huffman_code/Encoder.java
	javac -d ./bin -classpath ./bin src/huffman_code/Encoder.java
clean:
	rm -f ./bin/huffman_code/*.class
