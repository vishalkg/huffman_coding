all: Node.class PairNode.class BinaryHeap.class PairingHeap.class D_aryHeap.class Gen_huffman_code.class encoder.class decoder.class

Node.class: Node.java
	javac -d . -classpath . Node.java

PairNode.class: PairNode.java
	javac -d . -classpath . PairNode.java

BinaryHeap.class: BinaryHeap.java
	javac -d . -classpath . BinaryHeap.java

PairingHeap.class: PairingHeap.java
	javac -d . -classpath . PairingHeap.java

D_aryHeap.class: D_aryHeap.java
	javac -d . -classpath . D_aryHeap.java

Gen_huffman_code.class: Gen_huffman_code.java
	javac -d . -classpath . Gen_huffman_code.java

encoder.class: encoder.java
	javac -d . -classpath . encoder.java

decoder.class: decoder.java
	javac -d . -classpath . decoder.java

clean:
	rm -rf ./*.class
	rm encoded.bin decoded.txt code_table.txt
