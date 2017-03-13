# Huffman Coding
Given a frequency data, produces huffman codes for the unique messages.

* Node.java: DS for storing huffman tree
* PairNode.java: DS used in PairingHeap.java
* BinaryHeap.java: To implement binary heap
* PairingHeap.java: To implement pairing heap
* D_aryHeap.java: To implement D-ary heap. For eg. 4-way heap.
* Gen_huffman_code.java: To comapre the performance of above mentioned priority queues for huffman coding.
Usage:
```java
~:src$ java Gen_huffman_code ../sample_input_large.txt 
Reading input file ... Building Freq Table ... Done.
Building Huffman Tree using Binary Heap ... 
Trial:1.. 2.. 3.. 4.. 5.. 6.. 7.. 8.. 9.. 10..  Done
Average Time: 1663.0
Building Huffman Tree using Pairing Heap ... 
Trial:1.. 2.. 3.. 4.. 5.. 6.. 7.. 8.. 9.. 10..  Done
Average Time: 3417.0
Building Huffman Tree using 4-ary Heap ... 
Trial:1.. 2.. 3.. 4.. 5.. 6.. 7.. 8.. 9.. 10..  Done
Average Time: 1291.0
```

It is found that 4-ary heap is the fastest. So later encoding is done using 4-ary heap.

* encoder.java: Produces code_table.txt containing codes for all the unique messages; and encoded.bin, which is the binary file for the codes corresponding to the input file.
Usage:
```java
~:src$ java encoder ../sample_input_large.txt 
Reading input file ... 
Building Freq Table ... 
Building huffman tree... Done.
Generating code_table.txt .. Done.
Generating encoded.bin .. Done.
```

As a proof of concept, we will reconstruct our input using code_table.txt and encoded.bin

* decoder.java: Takes in input encoded.bin and code_table.txt and produces decoded.txt which is exactly the same as sample_input_large.txt as used in encoder.
Usage:
```java
~:src$ java decoder encoded.bin code_table.txt 
Building huffman tree from code_table.txt .. 
Reading code_table.txt .. Done.
Reading encoded.bin .. Done.
Generating decoded.txt .. Done.
```

Do `diff decoded.txt ../sample_input_large.txt` to check correctness.

Clone repository and make:
```
~:$ git clone https://github.com/vishalkg/huffman_coding.git
~:$ cd huffman_coding/src
~:src$ make
javac -d . -classpath . Node.java
javac -d . -classpath . PairNode.java
javac -d . -classpath . BinaryHeap.java
javac -d . -classpath . PairingHeap.java
javac -d . -classpath . D_aryHeap.java
javac -d . -classpath . Gen_huffman_code.java
javac -d . -classpath . encoder.java
javac -d . -classpath . decoder.java
```