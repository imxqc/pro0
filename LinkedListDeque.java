package examples;

public class LinkedListDeque<T> {
    private int size;
    private Tnode sentinel;
    private Tnode last;

    private class Tnode {
        public Tnode pre;
        public T item;
        public Tnode next;

        public Tnode(Tnode pre, T item, Tnode next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new Tnode(null, null, null);
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        this.size = other.size;
        sentinel = new Tnode(null, null, null);//先将next设为null，后loop在创建next的tnode
        Tnode p = sentinel;
        Tnode q = other.sentinel;
        for (int i = 0; i < size; i++, p = p.next, q = q.next) {
            p.next = new Tnode(p, q.next.item, null);
        }
    }


    public void addFirst(T item) {
        if (sentinel.next == null) {
            last = new Tnode(sentinel, item, null);
            sentinel.next = last;
            size++;
        } else {
            sentinel.next = new Tnode(sentinel, item, sentinel.next);
            size++;
        }
    }

    public void addLast(T item) {
        //因为第一个添加的节点就是last,如果last是null说明是空表
        if (last == null) {
            sentinel.next = new Tnode(sentinel, item, null);
            last = sentinel.next;
            size++;
        } else {
            last.next = new Tnode(last, item, null);
            last = last.next;
            size++;
        }
    }

    public boolean isEmpty() {
        if (sentinel.next == null)
            return true;
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Tnode p = sentinel.next;
        if (p != null) {
            while (p != null) {
                System.out.print(p.item + " ");
                p = p.next;
            }
        } else {
            throw new RuntimeException("链表为空");
        }

    }

    public T removeFirst() {
        Tnode p = sentinel.next;
        if (p != null) {
            sentinel.next = p.next;
            if ((p.next) != null)
                p.next.pre = sentinel;//nullpointer
            size--;
            return p.item;
        } else {
            throw new RuntimeException("链表为空");
        }
    }

    public T removeLast() {
        T p = last.item;
        if (last != null) {
            last.pre.next = null;
            last = last.pre;
            size--;
            return p;
        } else {
            throw new RuntimeException("链表为空");
        }
    }

    public T get(int index) {
        Tnode p = sentinel.next;

        for (int i = 0; i < index; i++) {
            if (p.next == null)
                return null;
            p = p.next;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        Tnode p = sentinel;
        for (int i = 0; i < index; i++, p = p.next) {
            if (p == null)
                return null;
        }
        return p.item;
    }
}
