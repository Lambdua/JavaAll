package Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 排序的公共方法
 *
 * @author liangtao
 * @Date 2020/5/24
 **/
public class SortCommon<T extends Comparable<T>> {
    public static void exch(int i, int j, Comparable[] arr) {
        Comparable iCo = arr[i];
        arr[i] = arr[j];
        arr[j] = iCo;
    }


    public static void show(Comparable[] arr) {
        for (Comparable item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /**
     * 检测是否为降序配列
     *
     * @param arr
     * @return
     */
    public static boolean isDescendingSort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否为升序排序
     *
     * @param arr
     * @return
     */
    public static boolean isAscendingSort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) return false;
        }
        return true;
    }

    /**
     * 判断a是否小于b
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }


    /**
     * 展示运算性能
     * @param sort
     */
    public static void showPerformanece(Sort sort,int size){
        Comparable[] comArray = createComArray(size);
        long start = System.currentTimeMillis();
        sort.sort(comArray);
        long end=System.currentTimeMillis();
        System.out.print("排序结果为：");
        for (Comparable comparable : comArray) {
            System.out.print(comparable+" ");
        }
        System.out.println();
        if (!isDescendingSort(comArray)){
            System.out.println("排序算法错误,不是降序");
        }
        System.out.println("用时： "+(end-start));
    }


    public static Comparable[] createComArray(int size){
        Random random=new Random(24);
        List<Comparable> list=new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(size*2));
        }
        Integer[] ints = list.toArray(new Integer[list.size()]);
        System.out.print("生成的序列串为：");
        for (Integer anInt : ints) {
            System.out.print(anInt+" ");
        }
        System.out.println();
        return ints;
    }

}
