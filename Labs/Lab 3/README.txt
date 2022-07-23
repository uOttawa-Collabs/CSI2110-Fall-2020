Student name:   Jake Wang
Student number: *
Lab section:    02
Java:           11.0.8

Lab #:          3
Lab status:     Completed
Known bugs:     None

Analysis:

List of methods that does not met the expected performance:
prev
next
swapElements
set
addFirst
addLast
addBefore
addAfter
remove

Reason:
Since we are dealing with elements that are stored in nodes, rather than actual nodes, we need to use iterators to seek the element that we are looking for, which will take O(n) of time complexity.

I believe in a node list, all refereces to nodes are exposed to the outside, that is, all functions are accepting nodes as arguments and returning nodes, rather than elements. Operations such as prev() will only take O(1) using operations like node.getPrevious().
