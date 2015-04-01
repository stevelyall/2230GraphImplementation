import exceptions.EmptyCollectionException;import java.util.ConcurrentModificationException;import java.util.Iterator;/** * LinkedQueue.java * * COMP 2230 Assignment 4 * Steven Lyall (T00023594) * * Linked implementation of a queue. *  * @author Java Foundations except where noted * @version 4.0 */public class LinkedQueue<T> implements QueueADT<T>, Iterable{    private int count;    private int modCount = 0;    private LinearNode<T> head, tail;    private LinkedQueueIterator iterator;    /**     * Creates an empty queue.     */    public LinkedQueue()    {        count = 0;        head = tail = null;    }    /**     * Adds the specified element to the tail of this queue.     * @param element the element to be added to the tail of the queue     */    public void enqueue(T element)    {        LinearNode<T> node = new LinearNode<T>(element);        if (isEmpty())            head = node;        else            tail.setNext(node);        tail = node;        count++;        modCount++;    }    /**     * Removes the element at the head of this queue and returns a     * reference to it.      * @return the element at the head of this queue     * @throws exceptions.EmptyCollectionException if the queue is empty     */    public T dequeue() throws EmptyCollectionException    {        if (isEmpty())            throw new EmptyCollectionException("queue");        T result = head.getElement();        head = head.getNext();        count--;        modCount++;        if (isEmpty())            tail = null;        return result;    }       /**     * Returns a reference to the element at the head of this queue.     * The element is not removed from the queue.     *  Complexity: O(1)     *  Precondiion: The queue is not empty.     *  Postcondition: The queue remains unchanged.     * @author stevelyall     * @return a reference to the first element in this queue     * @throws exceptions.EmptyCollectionException if the queue is empty     */    public T first() throws EmptyCollectionException {        // defensively check for null pointer        if (head==null) {            throw new EmptyCollectionException("queue");        }        else {            return head.getElement();        }    }    /**     * Returns true if this queue is empty and false otherwise.     *  Complexity: O(1)     *  Precondition: The LinkedQueue object has been instantiated.     *  Postcondition: The queue remains unchanged.	 * @author stevelyall     * @return true if count equals zero, false otherwise     */    public boolean isEmpty() {       return count == 0;     }     /**     * Returns the number of elements currently in this queue.     *  Complexity: O(n)     *  Precondition: The LinkedQueue object has been instantiated.     *  Postcondition: The queue remains unchanged.	 * @author stevelyall     * @return the number of elements in the queue     */    public int size() {        LinearNode<T> current = head;		int numNodes = 0;		while (current!=null) {			numNodes++;			current = current.getNext();		}		return numNodes;    }    /**     * Returns a string representation of this queue.     *  Compexity: O(n)     *  Precondition: The LinkedQueue object has been instantiated.     *  Postconidtion: The queue remains unchanged.	 * @author stevelyall     * @return the string representation of the queue     */    public String toString() {		String queueString = "<FRONT OF QUEUE>";		LinearNode currentNode = head;        iterator = new LinkedQueueIterator();		while (iterator.hasNext()) {			queueString += "\n" + iterator.next();			currentNode = currentNode.getNext();		}		return queueString + "\n<BACK OF QUEUE>";    }    @Override    public Iterator iterator() {        return iterator();    }    class LinkedQueueIterator implements Iterator<T> {        private int iteratorModCount;        private LinearNode<T> current;        /**         * Sets up the iterator.         */        public LinkedQueueIterator() {            current = head;            iteratorModCount = modCount;        }        /**         * Check whether another element exists.         *         * @author stevelyall         * @throws java.util.ConcurrentModificationException if the collection is modified while the iterator is being used         * @return true if there is another element in the collection, false otherwise         */        @Override        public boolean hasNext() {            if (iteratorModCount != modCount) {                throw new ConcurrentModificationException();            }            return (current !=null);        }        /**         * Moves iterator to next element in the structure.         *         * @author stevelyall         * @throws exceptions.EmptyCollectionException if there is no next element         * @return the next element in the structure         */        @Override        public T next() {            if (!hasNext()){                throw new EmptyCollectionException("LinkedStack");            }            T toGet = current.getElement();            current = current.getNext();            return toGet;        }        /**         * Remove is not supported.         */        @Override        public void remove() {            throw new UnsupportedOperationException();        }    }}