package Sort.elementary;

import Sort.Sort;
import Sort.SortCommon;

/**
 * 初级排序：选择排序
 * 说明：
 *      每一次对数组中尚未进行排序的部份进行遍历,从中按照升序（最小的元素）或者降序（最大的元素）
 *      选取元素，插入到已经排序的部份的末尾。直至结束
 * @author liangtao
 * @Date 2020/5/24
 **/
public class Selection implements Sort {
    public static void main(String[] args) {
        SortCommon.showPerformanece(new Selection(),20000);
    }



    /**
     * @param arr 参数使用实现了Comparable接口的类，方便比较
     */
    @Override
    public  void sort(Comparable[] arr){
        Comparable max;
        for (int i = 0; i < arr.length; i++) {
            max=arr[i];
            for (int j=i+1;j<arr.length;j++){
                int compare = arr[j].compareTo(max);
                if (compare>0){
                    max=arr[j];
                    SortCommon.exch(i,j,arr);
                }
            }
        }
    }

}
