package graphs3;

public class LinkedList 
{
    private Node head;

    public LinkedList() 
    {
        head = null;
    }

    public void addFirst(int data) 
    {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    public int getFirst() 
    {
        if (head != null) 
        {
            return head.data;
        }
        return -1;
    }

    public Node getHead() 
    {
        return head;
    }

    public int size() 
    {
        int count = 0;
        Node current = head;
        while (current != null) 
        {
            count++;
            current = current.next;
        }
        return count;
    }
}
