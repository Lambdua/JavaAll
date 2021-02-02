package com.lt.base.pqueue;

/**
 * @author liangtao
 * @description 基于堆的优先队列
 * @date 2021年02月01 09:46
 **/
public class MaxPQ<key extends Comparable<key>> implements PrimaryQueueBase<key> {
    private key[] pq;
    private int N = 0;

    public MaxPQ(int size) {
        //这里取size+1，堆排序中不使用0索引
        this.pq = (key[]) new Comparable[size + 1];
    }

    public MaxPQ() {
        this(20);
    }


    @Override
    public void insert(key key) {
        checkCapacity();
        pq[++N] = key;
        //新增的节点在最底下，进行上浮操作
        swim(N);
    }


    @Override
    public key max() {
        return pq[1];
    }

    @Override
    public key delMax() {
        key max = pq[1];
        //将1放入到最后的位置
        exch(1, N);
        //这里要先置空，否则下沉操作会使max重新回到1索引避免对象游离
        pq[N--] = null;
        //下沉操作,恢复堆的有序性
        sink(1);
        if (N > 0 && N == pq.length >> 2) resize(pq.length >> 1);
        return max;
    }

    @Override
    public key min() {
        return null;
    }

    @Override
    public key delMin() {
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * 判断i 是否小于j j
     *
     * @return boolean
     * @author liangtao
     * @date 2021/2/1
     **/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 元素位置互换
     *
     * @author liangtao
     * @date 2021/2/1
     **/
    private void exch(int i, int j) {
        key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 由下至上的堆的有序化(上浮）
     * 如果堆的有序状态因为某个结点变得比它的父结点更大而被打破，那么我们就需要通过交换它和它的
     * 父结点来修复堆。交换后，这个结点比它的两个子结点都大（一个是曾经的父结点，另一个比它更小，因
     * 为它是曾经父结点的子结点），但这个结点仍然可能比它现在的父结点更大。我们可以一遍遍地用同样的
     * 办法恢复秩序，将这个结点不断向上移动直到我们遇到了一个更大的父结点。只要记住位置 k 的结点的父
     * 结点的位置是 k/2，这个过程实现起来很简单。
     *
     * @author liangtao
     * @date 2021/2/1
     **/
    private void swim(int k) {
        if (k <= 1) return;
        if (less(k >> 1, k)) exch(k, k >> 1);
        swim(k >> 1);
    }


    /**
     * 由上至下的堆有序化（下沉）
     * 如果堆的有序状态因为某个结点变得比它的两个子结点或是其中之一更小了而被打破了，那么
     * 我们可以通过将它和它的两个子结点中的较大者交换来恢复堆。交换可能会在子结点处继续打破堆
     * 的有序状态，因此我们需要不断地用相同的方式将其修复，将结点向下移动直到它的子结点都比它
     * 更小或是到达了堆的底部。由位置为 k 的结点的子结点位于 2k 和 2k+1 可以直接得到对应的代码。
     *
     * @author liangtao
     * @date 2021/2/1
     **/
    private void sink(int k) {
        int j = k << 1;
        if (j > N) return;
        //和子节点中较大的一个进行互换，确保互换后父节点比子节点都大
        //这里j+1可能越界,所以要确保J+1不越界，即j<N
        if (j < N && less(j, j + 1)) j++;
        //递归出口
        if (!less(k, j)) return;
        exch(j, k);
        sink(j);
    }

    private void checkCapacity() {
        if (N == pq.length) {
            resize(2 * N);
        }
    }

    private void resize(int size) {
        key[] newArray = (key[]) new Comparable[size];
        System.arraycopy(pq, 0, newArray, 0, N);
        pq = newArray;
    }

}
