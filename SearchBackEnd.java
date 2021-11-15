// --== CS400 Project Two File Header ==--
// Name: Julia Oghigian
// Email: oghigian@wisc.edu 
// Team: Red
// Group: BA 
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
/**
 * This is an interface for a Red Black Tree for a list of movies extends Comparable and Iterable
 * 
 * @author Julia Oghigian
 */
interface SearchBackEndInterface<T extends Comparable<T>> extends Iterable<T> {
    // Note that the provided iterators step through the data within this
    // collection in sorted order, as defined by their compareTo() method.
  //  public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    public int size();
    public boolean isEmpty();

	//public boolean insert(MovieData data) throws NullPointerException, IllegalArgumentException;
	/**
	 * Finds the director at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain director names
	 */
	List<String> findDirector(String movieRank);
	/**
	 * Finds the titles at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain movie titles
	 */
	List<String> findTitles(String movieRank);
	/**
	 * Determines if a movie has been added to the tree.
	 *
	 * @param movie The movie being searched for.
	 * @return true if movie is in tree else return false.
	 */
	boolean containsMovie(MovieDataInterface movie);
	/**
	 * Finds the years at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain movie titles
	 */

	List<String> findYear(String movieRank);
	/**
	 * Finds the genre at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain movie titles
	 */
	List<String> findGenre(String movieRank);
	

}
/**
 * This class generates a RedBlack Tree for movies  while implementing SearchFrontEndInterface.
 *
 * @author Julia Oghigian
 */
public class SearchBackEnd implements SearchBackEndInterface {

	/**
	 * This class represents a node holding a single value within a binary tree the
	 * parent, left, and right child references are always maintained.
	 */
	protected static class Node<MovieData> {
		public MovieDataInterface data;
		public Node<MovieData> parent; // null for root node
		public Node<MovieData> leftChild;
		public Node<MovieData> rightChild;
		public boolean isBlack;

		public Node(MovieDataInterface data) {
			this.data = data;
			this.isBlack = false;

		}

		/**
		 * @return true when this node has a parent and is the left child of that
		 *         parent, otherwise return false
		 */
		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

		/**
		 * This method performs a level order traversal of the tree rooted at the
		 * current node. The string representations of each data value within this tree
		 * are assembled into a comma separated string within brackets (similar to many
		 * implementations of java.util.Collection). Note that the Node's implementation
		 * of toString generates a level order traversal. The toString of the
		 * RedBlackTree class below produces an inorder traversal of the nodes / values
		 * of the tree. This method will be helpful as a helper for the debugging and
		 * testing of your rotation implementation.
		 * 
		 * @return string containing the values of this tree in level order
		 */
		@Override
		public String toString() {
			String output = "[";
			LinkedList<Node<MovieData>> q = new LinkedList<>();
			q.add(this);
			while (!q.isEmpty()) {
				Node<MovieData> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
			return output + "]";
		}
	}

	protected Node<MovieData> root; // reference to root node of tree, null when empty
	protected int size = 0; // the number of values in the tree

	/**
	 * Performs a naive insertion into a binary search tree: adding the input data
	 * value to a new node in a leaf position within the tree. After this insertion,
	 * no attempt is made to restructure or balance the tree. This tree will not
	 * hold null references, nor duplicate data values.
	 * 
	 * @param data to be added into this binary search tree
	 * @return true if the value was inserted, false if not
	 * @throws NullPointerException     when the provided data argument is null
	 * @throws IllegalArgumentException when the newNode and subtree contain equal
	 *                                  data references
	 */

	public boolean insert(MovieDataInterface data) throws NullPointerException, IllegalArgumentException {
		// null references cannot be stored within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		Node<MovieData> newNode = new Node<>(data);
		if (root == null) {
			root = newNode;
			size++;
			this.root.isBlack = true;
			return true;
		} // add first node to an empty tree
		else {
			boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
			if (returnValue)
				size++;
			else
				throw new IllegalArgumentException("This RedBlackTree already contains that value.");
			this.root.isBlack = true;
			return returnValue;
		}

	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newNode should be inserted, and then extend this tree by
	 * the newNode in that position.
	 * 
	 * @param newNode is the new node that is being added to this tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descenedent beneath
	 * @return true is the value was inserted in subtree, false if not
	 */
	private boolean insertHelper(Node<MovieData> newNode, Node<MovieData> subtree) {
		int compare = (Integer.toString(newNode.data.getRank())).compareTo(Integer.toString(subtree.data.getRank()));
		// do not allow duplicate values to be stored within this tree
		if (compare == 0)
			return false;

		// store newNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.leftChild);
		}

		// store newNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newNode;
				newNode.parent = subtree;
				enforceRBTreePropertiesAfterInsert(newNode);
				return true;
				// otherwise continue recursive search for location to insert
			} else
				return insertHelper(newNode, subtree.rightChild);
		}
	}

	/**
	 * Performs the rotation operation on the provided nodes within this tree. When
	 * the provided child is a leftChild of the provided parent, this method will
	 * perform a right rotation. When the provided child is a rightChild of the
	 * provided parent, this method will perform a left rotation. When the provided
	 * nodes are not related in one of these ways, this method will throw an
	 * IllegalArgumentException.
	 * 
	 * @param child  is the node being rotated from child to parent position
	 *               (between these two node arguments)
	 * @param parent is the node being rotated from parent to child position
	 *               (between these two node arguments)
	 * @throws IllegalArgumentException when the provided child and parent node
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	private void rotate(Node<MovieData> child, Node<MovieData> parent) throws IllegalArgumentException {
		if (child == null || parent == null) {
			throw new IllegalArgumentException();
		}

		if (child.isLeftChild()) {// child.equals(parent.leftChild)) {
			if (parent.equals(this.root)) {
				child.parent = null;
				parent.leftChild = child.rightChild;
				parent.parent = child;
				child.rightChild = parent;
				this.root = child;

			} else {

				if (parent.isLeftChild()) {// parent.parent.leftChild.equals(parent)) {
					child.parent = parent.parent;
					parent.leftChild = child.rightChild;
					parent.parent = child;
					child.rightChild = parent;
					child.parent.leftChild = child;
				} else {
					child.parent = parent.parent;
					parent.leftChild = child.rightChild;
					parent.parent = child;
					child.rightChild = parent;
					child.parent.rightChild = child;
				}

			}

		} else if (child.equals(parent.rightChild)) {
			if (parent.equals(this.root)) {
				child.parent = null;

				parent.rightChild = child.leftChild;
				child.leftChild = parent;
				parent.parent = child;
				this.root = child;
			} else {
				if (parent.parent.leftChild.equals(parent)) {
					child.parent = parent.parent;
					parent.rightChild = child.leftChild;
					child.leftChild = parent;
					parent.parent = child;
					child.parent.leftChild = child;
				} else {
					child.parent = parent.parent;
					parent.rightChild = child.leftChild;
					child.leftChild = parent;
					parent.parent = child;
					child.parent.rightChild = child;
				}

			}

		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Determines if a movie has been added to the tree.
	 *
	 * @param movie The movie being searched for.
	 * @return true if movie is in tree else return false.
	 */

	@Override
	public boolean containsMovie(MovieDataInterface movie) {

		if (contains(movie) == true) {
			return true;
		}

		return false;
	}

	/**
	 * Finds the titles at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain movie titles
	 */
	@Override
	public List<String> findTitles(String movieRank) {
		List<String> titles = new LinkedList<>();
		lookupHelperTitles(movieRank, root, titles);

		if (size() == 0) {
			throw new NoSuchElementException("No Movie");

		}

		else {
			return titles;
		}
	}

	/**
	 * Helper to finds the titles at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @param current   node that is used to determine the node for the rank
	 * @param titles    a list
	 * @returns List contain movie titles
	 */
	protected void lookupHelperTitles(String movieRank, Node<MovieData> current, List<String> titles) {

		if (current == null) {
			return;
		}
		if (current != null) {
			if (current.data.getRank() == Integer.parseInt(movieRank)) {
				titles.add(current.data.getTitle());

			}
			lookupHelperTitles(movieRank, current.leftChild, titles);
			lookupHelperTitles(movieRank, current.rightChild, titles);

		}
	}

	/**
	 * Finds the genre at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain movie genre
	 */
	@Override
	public List<String> findGenre(String movieRank) {
		List<String> genre = new LinkedList<>();
		lookupHelperGenre(movieRank, root, genre);

		if (size() == 0) {
			throw new NoSuchElementException("No Movie");

		}

		else {
			return genre;
		}
	}
/**
	 * Helper to finds the titles at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @param current   node that is used to determine the node for the rank
	 * @param genre     a list
	 * @returns List contain movie genre
	 */
	protected void lookupHelperGenre(String movieRank, Node<MovieData> current, List<String> genre) {

		if (current == null) {
			return;
		}
		if (current != null) {
			if (current.data.getRank() == Integer.parseInt(movieRank)) {
				genre.add(current.data.getGenre());

			}
			lookupHelperGenre(movieRank, current.leftChild, genre);
			lookupHelperGenre(movieRank, current.rightChild, genre);

		}
	}

	/**
	 * Finds the years at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain movie year
	 */
	@Override
	public List<String> findYear(String movieRank) {
		List<String> years = new LinkedList<>();
		lookupHelperYear(movieRank, root, years);

		if (size() == 0) {
			throw new NoSuchElementException("No Movie");

		}

		else {
			return years;
		}
	}

	/**
	 * Helper to finds the year at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @param current   node that is used to determine the node for the rank
	 * @param year      a list
	 * @returns List contain movie year
	 */
	protected void lookupHelperYear(String movieRank, Node<MovieData> current, List<String> year) {

		if (current == null) {
			return;
		}
		if (current != null) {
			if (current.data.getRank() == Integer.parseInt(movieRank)) {
				year.add(Integer.toString(current.data.getYearPublished()));
				return;
			}
			lookupHelperYear(movieRank, current.leftChild, year);
			lookupHelperYear(movieRank, current.rightChild, year);

		}
	}

	/**
	 * Finds the director at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @returns List contain director names
	 */
	@Override
	public List<String> findDirector(String movieRank) {

		List<String> directors = new LinkedList<>();
		lookupHelperDirector(movieRank, root, directors);

		if (size() == 0) {
			throw new NoSuchElementException("No Movie");

		}

		else {
			return directors;
		}
	}

	/**
	 * Helper to finds the directors at the specific node using movieRank as a key.
	 *
	 * @param movieRank key
	 * @param current   node that is used to determine the node for the rank
	 * @param directors a list
	 * @returns List contain movie director name
	 */
	protected void lookupHelperDirector(String movieRank, Node<MovieData> current, List<String> directors) {

		if (current == null) {
			return;
		}
		if (current != null) {
			if (current.data.getRank() == Integer.parseInt(movieRank)) {
				directors.add(current.data.getDirector());
				return;
			}
			lookupHelperDirector(movieRank, current.leftChild, directors);
			lookupHelperDirector(movieRank, current.rightChild, directors);

		}
	}

	/**
	 * Finds the size of the tree.
	 *
	 * @return size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Determines if RedBlack tree is empty or not. *
	 * 
	 * @return true if tree is empty else returns false.
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Checks whether the tree contains the value *data*.
	 * 
	 * @param movie the data value to test for
	 * @return true if *data* is in the tree, false if it is not in the tree
	 */

	public boolean contains(MovieDataInterface movie) {
		// null references will not be stored within this tree
		if (movie == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");
		return this.containsHelper(movie, root);
	}

	/**
	 * Recursive helper method that recurses through the tree and looks for the
	 * value *data*.
	 * 
	 * @param movie   the data value to look for
	 * @param subtree the subtree to search through
	 * @return true of the value is in the subtree, false if not
	 */
	private boolean containsHelper(MovieDataInterface movie, Node<MovieData> subtree) {
		if (subtree == null) {
			// we are at a null child, value is not in tree
			return false;
		} else {
			int compare = Integer.toString(movie.getRank()).compareTo(Integer.toString(subtree.data.getRank()));
			if (compare < 0) {
				// go left in the tree
				return containsHelper(movie, subtree.leftChild);
			} else if (compare > 0) {
				// go right in the tree
				return containsHelper(movie, subtree.rightChild);
			} else {
				// we found it :)
				return true;
			}
		}
	}

	/**
	 * Returns an iterator over the values in in-order (sorted) order.
	 * 
	 * @return iterator object that traverses the tree in in-order sequence
	 */
	@Override
	public Iterator<MovieData> iterator() {
		// use an anonymous class here that implements the Iterator interface
		// we create a new on-off object of this class everytime the iterator
		// method is called
		return new Iterator<MovieData>() {
			// a stack and current reference store the progress of the traversal
			// so that we can return one value at a time with the Iterator
			Stack<Node<MovieData>> stack = null;
			Node<MovieData> current = root;

			/**
			 * The next method is called for each value in the traversal sequence. It
			 * returns one value at a time.
			 * 
			 * @return next value in the sequence of the traversal
			 * @throws NoSuchElementException if there is no more elements in the sequence
			 */
			public MovieData next() {
				// if stack == null, we need to initialize the stack and current element
				if (stack == null) {
					stack = new Stack<Node<MovieData>>();
					current = root;
				}
				// go left as far as possible in the sub tree we are in until we hit a null
				// leaf (current is null), pushing all the nodes we fund on our way onto the
				// stack to process later
				while (current != null) {
					stack.push(current);
					current = current.leftChild;
				}
				// as long as the stack is not empty, we haven't finished the traversal yet;
				// take the next element from the stack and return it, then start to step down
				// its right subtree (set its right sub tree to current)
				if (!stack.isEmpty()) {
					Node<MovieData> processedNode = stack.pop();
					current = processedNode.rightChild;
					return (MovieData) processedNode.data;
				} else {
					// if the stack is empty, we are done with our traversal
					throw new NoSuchElementException("There are no more elements in the tree");
				}

			}

			/**
			 * Returns a boolean that indicates if the iterator has more elements (true), or
			 * if the traversal has finished (false)
			 * 
			 * @return boolean indicating whether there are more elements / steps for the
			 *         traversal
			 */
			public boolean hasNext() {
				// return true if we either still have a current reference, or the stack
				// is not empty yet
				return !(current == null && (stack == null || stack.isEmpty()));
			}

		};
	}

	/**
	 * Changes isBlack on nodes after new inserting and rotates nodes in RedBlack
	 * Tree if needed. This is done in order to keep a balance RedBlack Tree. The
	 * swapping of node colors and reordering is dependent on whether the uncle node
	 * of the new Node is red or black.
	 * 
	 * @param newNode the new Node that has just been inserted.
	 */
	private void enforceRBTreePropertiesAfterInsert(Node<MovieData> newNode) {
		if (newNode.isBlack == true) {
			return;
		}
		if (newNode.parent == null) {
			return;
		}

		Node<MovieData> parentNode = newNode.parent;
		Node<MovieData> grandparentNode = newNode.parent.parent;
		Node<MovieData> uncleNode = null;
		if (parentNode.isBlack == false) { // parent node of new node must be red to reorder

			if (parentNode.isLeftChild()) { // to determine the location of uncle node
				uncleNode = grandparentNode.rightChild;
			} else if (parentNode.isLeftChild() == false) {
				uncleNode = grandparentNode.leftChild;
			}
			if (uncleNode == null) {
				rotate(parentNode, grandparentNode);
				enforceRBTreePropertiesAfterInsert(parentNode);

			} else if (uncleNode.isBlack == false) { // if uncle node is red
				parentNode.isBlack = true;
				uncleNode.isBlack = true;
				grandparentNode.isBlack = false;
				root.isBlack = true;
				enforceRBTreePropertiesAfterInsert(grandparentNode);
			} else if (uncleNode.isBlack == true) { // if uncle node is black
				// Left Left Case
				if (parentNode.isLeftChild() && newNode.isLeftChild()) {
					parentNode.isBlack = true;
					grandparentNode.isBlack = false;
					rotate(parentNode, grandparentNode);
					// Left Right Case
				} else if (parentNode.isLeftChild() && newNode.isLeftChild() == false) {
					newNode.isBlack = true;
					grandparentNode.isBlack = false;
					rotate(newNode, parentNode);
					rotate(newNode, grandparentNode);
					// Right Right Case
				} else if (parentNode.isLeftChild() == false && newNode.isLeftChild() == false) {
					parentNode.isBlack = true;
					grandparentNode.isBlack = false;
					rotate(parentNode, grandparentNode);
					// Right Left Case
				} else if (parentNode.isLeftChild() == false && newNode.isLeftChild()) {
					newNode.isBlack = true;
					grandparentNode.isBlack = false;
					rotate(newNode, parentNode);
					rotate(newNode, grandparentNode);
				}
			}
		}

	}

	/**
	 * This method performs an inorder traversal of the tree. The string
	 * representations of each data value within this tree are assembled into a
	 * comma separated string within brackets (similar to many implementations of
	 * java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note that
	 * this RedBlackTree class implementation of toString generates an inorder
	 * traversal. The toString of the Node class class above produces a level order
	 * traversal of the nodes / values of the tree.
	 * 
	 * @return string containing the ordered values of this tree (in-order
	 *         traversal)
	 */
	@Override
	public String toString() {
		// use the inorder Iterator that we get by calling the iterator method above
		// to generate a string of all values of the tree in (ordered) in-order
		// traversal sequence
		Iterator<MovieData> treeNodeIterator = this.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		if (treeNodeIterator.hasNext())
			sb.append(treeNodeIterator.next());
		while (treeNodeIterator.hasNext()) {
			MovieData data = treeNodeIterator.next();
			sb.append(", ");
			sb.append(data.toString());
		}
		sb.append(" ]");
		return sb.toString();
	}

}

class SearchBackEndPlaceholder implements SearchBackEndInterface {
	private MovieDataInterface onlyMovie;

	public boolean containsMovie(MovieDataInterface movie) {
		return onlyMovie.equals(movie);
	}

	public List<String> findTitles(String movieRank) {
		List<String> titles = new LinkedList<>();
		if (onlyMovie.getRank() == (Integer.parseInt(movieRank)))
			titles.add(onlyMovie.getTitle());
		return titles;
	}

	public List<String> findDirector(String movieRank) {
		List<String> directors = new LinkedList<>();
		if (onlyMovie.getRank() == (Integer.parseInt(movieRank)))
			directors.add(onlyMovie.getDirector());
		return directors;
	}

	public Iterator iterator() {

		return null;
	}

	public int size() {

		return 0;
	}

	public boolean isEmpty() {

		return false;
	}

	public List<String> findYear(String movieRank) {
		List<String> years = new LinkedList<>();
		if (onlyMovie.getRank() == (Integer.parseInt(movieRank)))
			years.add(Integer.toString(onlyMovie.getYearPublished()));
		return years;
	}

	public List<String> findGenre(String movieRank) {
		List<String> genre = new LinkedList<>();
		if (onlyMovie.getRank() == (Integer.parseInt(movieRank)))
			genre.add(onlyMovie.getGenre());
		return genre;
	}

}

