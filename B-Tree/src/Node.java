import java.util.Comparator;
import java.util.LinkedList;
public class Node<E>
{
	int order;
	private Comparator<E> comp;
	Node<E> parent;
	LinkedList<E> data;
	LinkedList<Node<E>> children;
	public Node(int theOrder, Comparator<E> theComp)
	{	
		this.order = theOrder;
		this.comp = theComp;
		parent = null;
		data = new LinkedList<E>();
		children = new LinkedList<Node<E>>();
	}
	public Node(int theOrder, Comparator<E> theComp, Node<E> left, E item, Node<E> right)
	{
		data = new LinkedList<E>();
		children = new LinkedList<Node<E>>();
		this.order = theOrder;
		this.comp = theComp;
		data.add(item);	
		children.add(left);
		left.parent = this;
		children.add(right);
		right.parent = this;
	}
	public Node(int theOrder, Comparator<E> theComp, Node<E> theParent, LinkedList<E> theData, LinkedList<Node<E>> theChildren)
	{
		this.order = theOrder;
		this.comp = theComp;
		this.parent = theParent;	
		this.data = theData;
		this.children = theChildren;
		for(Node<E> child : children) {
			child.parent = this;
		}
	}
	public boolean hasOverflow()
	{
		return data.size() > order; 
	}
	public boolean isLeaf()
	{
		return children.isEmpty();
	}
	public Node<E> childToFollow(E item)
	{
		int indexOfChild;
		for(indexOfChild = 0; indexOfChild < data.size(); indexOfChild++) {
			if(comp.compare(data.get(indexOfChild), item) > 0) {
				break;
			}	
		}	
		return children.get(indexOfChild);	
	}
	public void leafAdd(E item)
	{
		int index;
		for(index = 0; index < data.size(); index++) {
			if(comp.compare(data.get(index), item) > 0) {
				break;
			}	
		}		
		data.add(index, item);
	}
	public void split()
	{
		int midIndex = data.size()/2;
		int midChildIndex = children.size()/2;
		LinkedList<E> halfData = new LinkedList<E>(data.subList(midIndex + 1, data.size()));		
		LinkedList<Node<E>> halfChildren = new LinkedList<Node<E>>();
		if(!children.isEmpty()) {
			halfChildren = new LinkedList<Node<E>>(children.subList(midChildIndex, children.size()));
			children.subList(midChildIndex, data.size()).clear();
		}
		E middleVal = data.get(midIndex);			
		Node<E> rightSibling = new Node<E>(order, comp, parent, halfData, halfChildren);
		if(parent != null) {
			halfData = new LinkedList<E>(data.subList(midIndex + 1, data.size()));
			data.subList(midIndex, data.size()).clear();
			int newMidIndex = parent.children.indexOf(this);
			parent.data.add(newMidIndex, middleVal);
			parent.children.add(newMidIndex + 1, rightSibling);
		}
		else {
			data.subList(midIndex, data.size()).clear();
			parent = new Node<E>(order,comp, this, middleVal, rightSibling);
		}
	}
	public boolean contains(E item)
	{
		return data.contains(item);	
	}
}
