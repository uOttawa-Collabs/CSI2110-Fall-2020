Student name:   Jake Wang
Student number: *
Lab section:    02
Java:           11.0.8

Lab #:          6
Lab status:     Completed
Known bugs:     None

Additional information:

I found that class TreeMap only provides interfaces to behave as a Map, hiding its inner tree structure that implements the essential methods of node operations, which blocks the tree traversal, so the height calculation.

Therefore, with the original code of the package net.datastructure not being modified, I took advantage of the reflection mechanism of Java and bypassed protection of the inner LinkedBinaryTree instance, which was described in the method called getInnerTree().

With the method above, I eventually got a similar result as the sample output.
