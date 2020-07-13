package dataStructure;

/**
 * @author liangtao
 * @Date 2020/5/24
 **/
public interface IQueue<T> extends Iterable<T> {
    boolean isEmpty();
    int size();

    /**
     * 向表尾添加元素
     * @param item
     */
    void enqueue(T item);

    /**
     * 从表头删除元素
     * @return
     */
    T dequeue();
}
