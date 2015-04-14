Report
  * Elements:
    * Problem definition
    * Solution
    * Experiments and Discussion
    * Manual (installation and usage instruction for your code deliverable)
  * Approximately 8 pages
  * Be concise!


### Problem Definition ###
The problem consists in implementing the Tree Edit Distance and the Traversal String Filter, which is lower bound of the Tree Edit Distance. The Tree Edit Distance is an algorithm used to compute the difference between two trees. A tree, like a XML document, can be very large and therefore the computational cost may be too high. In order to have an efficient computation cost, we have to apply inexpensive filters before executing the Tree Edit Distance algorithm. In our case, we implement a lower bound filter. A lower bound filter extracts all  instances that have a edit distance greater or equal to a constant. Then, the costly Tree Edit Distance has to be computed only on the instances that pass the filter.



### Solution ###
The implementation of the lower bound filter is as follows: firstly, we do a preorder traversal of the trees, returning a string that consists of the tree labels in preorder  ordering. Then, we a postorder traversal in the same way as with the preorder traversal. After that, we apply the String Edit Distance algorithm on the two preorder strings, followed by doing the same for the postorder strings. Than, the lowerbound is the maximum between the edit distances that we already computed.

The idea behind that is the following:
If there is a difference between the preorder or postorder traversal of the two trees, then there has to be a difference between the trees. Although this procedure may not recognize every differences (there may exist two different trees with the same traversal strings), this is not a problem, since we are only interested on a lower bound filter. In fact, if the trees are at edit distance k, then the maximum edit distance between their preorder or postorder traversals is at most k. This two propositions lead to the following theorem:
Let T1, T2, be ordered labeled trees. Then max(ed(pre(T1), pre(T2), ed(post(T1), post(T2)) <= TDist(T1, T2)
with T1, T2 being the two trees, pre and post being the described preorder and postorder traversal, ed the String Edit Distance and TDist the actual Tree Edit Distance.

Example:

![http://treeeditdistance.googlecode.com/svn/trunk/treeeditdistance/images/trees.png](http://treeeditdistance.googlecode.com/svn/trunk/treeeditdistance/images/trees.png)

Given the two trees in the image above, the results of the pre- and postorder traversals with respect to each tree are the following:
  * preorder of tree one: _fdacbe_
  * postorder of tree one: _abcdef_
  * preorder of tree two: _fcdabe_
  * postorder of tree two: _abdcef_
Then, the String Edit Distances are:
  * preorder: 2
  * postorder: 2
Getting the maximum of 2 and 2 which is 2, we can say that the real Tree Edit Distance is greater or equal than 2. In fact, it is exactly 2.



### Manual ###
The delivery consists in a single jar file. For running it, the following command with 3 parameters as Integers is executed:
```
java -jar treeeditdistance.jar <fanout> <height> <nLabels>
```
The arguments stand for:
  * fanout: the maximum fanout of a node of a tree
  * height: the height of a tree
  * nLabels: the number of different labels for the nodes

The program works in the following way:
Given the three arguments, firstly it generates two random trees with random labels and it computes the lower bound, respectively, storing also the execution time. Afterwards, it computes the real tree edit distance, storing again the execution time. All the relative information such as the execution time or the distance is printed out.