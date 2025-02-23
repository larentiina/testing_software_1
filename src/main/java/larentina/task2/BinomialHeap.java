package larentina.task2;



class BinomialHeapNode {

    int key, degree;
    BinomialHeapNode parent;
    BinomialHeapNode sibling;
    BinomialHeapNode child;

    public BinomialHeapNode(int k)
    {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }

    public BinomialHeapNode reverse(BinomialHeapNode sibl)
    {
        BinomialHeapNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }


    public BinomialHeapNode findMinNode()
    {
        BinomialHeapNode x = this, y = this;
        int min = x.key;

        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }

            x = x.sibling;
        }
        return y;
    }


    public int getSize()
    {
        return (
                1 + ((child == null) ? 0 : child.getSize())
                        + ((sibling == null) ? 0 : sibling.getSize()));
    }
}


public class BinomialHeap {


    private BinomialHeapNode Nodes;
    private int size;

    public BinomialHeap()
    {
        Nodes = null;
        size = 0;
    }

    public boolean isEmpty() { return Nodes == null; }


    public int getSize() { return size; }

    public void makeEmpty()
    {
        Nodes = null;
        size = 0;
    }


    public void insert(int value)
    {
        if (value > 0) {
            BinomialHeapNode temp
                    = new BinomialHeapNode(value);
            if (Nodes == null) {
                Nodes = temp;
                size = 1;
            }
            else {
                unionNodes(temp);
                size++;
            }
        }
    }


    private void merge(BinomialHeapNode binHeap)
    {
        BinomialHeapNode temp1 = Nodes, temp2 = binHeap;

        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.degree == temp2.degree) {
                BinomialHeapNode tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            }

            else {
                    BinomialHeapNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    Nodes = temp1;
            }
        }

        if (temp1 == null) {
            temp1 = Nodes;

            while (temp1.sibling != null) {
                temp1 = temp1.sibling;
            }
            temp1.sibling = temp2;
        }

    }


    private void unionNodes(BinomialHeapNode binHeap)
    {
        merge(binHeap);

        BinomialHeapNode temp = Nodes,
                nextTemp = Nodes.sibling;

        while (nextTemp != null) {

            if ((temp.degree != nextTemp.degree)
                    || ((nextTemp.sibling != null)
                    && (nextTemp.sibling.degree
                    == temp.degree))) {
                temp = nextTemp;
            }

            else {

                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                }

                else {
                    Nodes = nextTemp;
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }

    public int findMinimum()
    {
        return Nodes.findMinNode().key;
    }


    public int extractMin() {
        if (Nodes == null)
            return -1;

        BinomialHeapNode temp = Nodes, prevTemp = null;
        BinomialHeapNode minNode = Nodes.findMinNode();

        while (temp.key != minNode.key) {
            prevTemp = temp;
            temp = temp.sibling;
        }

        if (prevTemp == null) {
            Nodes = temp.sibling;
        } else {
            prevTemp.sibling = temp.sibling;
        }

        BinomialHeapNode childList = temp.child;
        while (childList != null) {
            childList.parent = null;
            childList = childList.sibling;
        }

        BinomialHeapNode mergedList = (temp.child != null) ? temp.child.reverse(null) : null;

        if (Nodes == null) {
            Nodes = mergedList;
        } else if (mergedList != null) {
            unionNodes(mergedList);
        }

        size = (Nodes != null) ? Nodes.getSize() : 0;
        return minNode.key;
    }


    public String displayHeap() {
        StringBuilder sb = new StringBuilder();
        sb.append("Heap: ");

        BinomialHeapNode current = Nodes;
        while (current != null) {
            sb.append("(");
            displayTree(sb, current);
            sb.append(") ");
            current = current.sibling;
        }

        return sb.toString();
    }

    private void displayTree(StringBuilder sb, BinomialHeapNode node) {
        sb.append(node.key);

        if (node.child != null) {
            sb.append(" -> [");
            BinomialHeapNode child = node.child;
            while (child != null) {
                displayTree(sb, child);
                if (child.sibling != null) sb.append(", ");
                child = child.sibling;
            }
            sb.append("]");
        }
    }


}


