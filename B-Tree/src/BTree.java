import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BTree<E>
{
	private Node<E> root;
	private Comparator<E> comp;
	private int order;
	public BTree(int order, Comparator<E> comp)
	{
		this.comp = comp;
		this.order = order;
		root = new Node<E>(order, comp);
	}
	public void add(E item)
	{
		Node<E> curr = findLeaf(root, item);
		curr.leafAdd(item);
		while(curr.hasOverflow()) {
			curr.split();
			curr = curr.parent;
		}
		if(root.parent != null) {
			root = root.parent;
		}
	}
	public Node<E> findLeaf(Node<E> curr, E item)
	{
		while(!curr.isLeaf()) {
			curr = curr.childToFollow(item);
		}
		return curr;	
	}
	public boolean contains(E item)
	{
		return findNode(root, item) != null;
	}
	public Node<E> findNode(Node<E> curr, E item)
	{
		if(curr.contains(item)) {
			return curr;
		}
		else if(curr.isLeaf()) { 
			return null;
		}
		return findNode(curr.childToFollow(item), item);				
	}
	public void inorder(Visitor<E> visitor)
	{
		inorder(visitor, root);
	}
	public void inorder(Visitor<E> visitor, Node<E> curr)
	{	
		if(curr == null) {
			return;
		}	
		int i;
		for(i = 0; i < curr.data.size(); i++) {
			if(!curr.children.isEmpty()) {
				inorder(visitor, curr.children.get(i));
			}
			visitor.visit(curr.data.get(i));
		}
		if(!curr.children.isEmpty()) {
			inorder(visitor, curr.children.get(i));
		}
	}
	public String toStringSorted()
	{
		String str = "";
		StringVisitor<E> strVisit = new StringVisitor<E>();
		inorder(strVisit);
		str += strVisit.getValue();
		str = "[" + str.trim() + "]";
		return str;
	}
	public String toString()
	{
		Queue<Node<E>> queue = new LinkedList<Node<E>>();		
		String str = "";
		queue.add(root);
		while(!queue.isEmpty()) {
			Node<E> store = queue.poll();
			str += store.data + " ";
			for(Node<E> child : store.children) {
				queue.add(child);
			}
		}
		str = "[" + str.trim() + "]";
		str = str.replaceAll(",", "");
		return str;
	}
}