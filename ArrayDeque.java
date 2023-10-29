package examples;

public class ArrayDeque<T> {
    private T[] item;
    private int size;

    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        this.size = other.size;
        System.arraycopy(other.item, 0, this.item, 0, other.size);
    }

    public void addFirst(T t) {
        if (size == item.length) {
            item = resizeBig();
        }

        for (int i = size; i < size - 1; i--) {
            item[i + 1] = item[i];
        }
        item[0] = t;
    }

    public void addLast(T t) {
        if (size == item.length) {
            item = resizeBig();
        }
        item[size] = t;
        size++;
    }

    public boolean isEmpty() {
        if (size == 0)
            return false;
        return true;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < item.length; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size < (item.length * 0.25)) {
            item = resizeSmall();
        }
        T rt = item[0];
        for (int i = 0; i < size - 1; i++) {
            item[i] = item[i + 1];
        }
        size--;
        return rt;
    }

    public T removeLast() {
        if (size < (item.length * 0.25)) {
            item = resizeSmall();
        }
        T rt = item[size - 1];
        size--;
        return rt;
    }

    public T get(int index) {
        return item[index];
    }

    public T[] resizeBig() {
        T[] a = (T[]) new Object[size * 2];
        System.arraycopy(item, 0, a, 0, size);
        return a;
    }

    public T[] resizeSmall() {
        T[] a = (T[]) new Object[size / 2];
        System.arraycopy(item, 0, a, 0, size);
        return a;
    }
}
