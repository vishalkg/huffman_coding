import matplotlib.pyplot as plt
import numpy as np

data = np.zeros((5,4), dtype=np.int64)
data[:,0] = [1,2,3,4,5]
data[:,1] = [586,580,584,585,584]
data[:,2] = [1071,1055,1062,1051,1057]
data[:,3] = [527,532,526,525,527]

plt.xticks(data[:,0])
binH, = plt.plot(data[:,0],data[:,1],'r-o',label='Binary Heap')
pairH, = plt.plot(data[:,0],data[:,2],'b-o',label='Pairing Heap')
fourH, = plt.plot(data[:,0],data[:,3],'g-o',label='4-Way Heap')

plt.xlabel('Run Index')
plt.ylabel('Average Time')
plt.title('Average Time plot for different Priority Queues')
plt.legend(handles=[binH,pairH,fourH])
plt.show()
