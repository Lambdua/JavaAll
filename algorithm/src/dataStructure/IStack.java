package dataStructure;

/**
 * @author liangtao
 * @Date 2020/5/24
 **/
public interface IStack<T>  extends Iterable<T> {
    boolean isEmpty();
    int size();
    void push(T item);
    T pop();
}
