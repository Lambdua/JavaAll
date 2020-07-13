package Sort.elementary;

import Sort.Sort;
import Sort.SortCommon;

import static Sort.SortCommon.less;

/**
 * 插入排序：
 * 每次，从尚未排序的数组中选择一个，插入到已经排序的数组中。
 * 再给插入的数腾位置时，只需要将插入位置之后的数向后移动一个位置即可。
 * @author liangtao
 * @Date 2020/5/24
 **/
public class Insertion implements Sort {
    public static void main(String[] args) {
        SortCommon.showPerformanece(new Insertion(),20000);
    }

    @Override
    public void sort(Comparable[] arr){
        for (int i=0;i<arr.length;i++){
            Comparable temp=arr[i];
            for (int j=0;j<i;j++){
                if (!less(temp,arr[j])){
                    for(int k=i;k>j;k--){
                        arr[k]=arr[k-1];
                    }
                    arr[j]=temp;
                    break;
                }
            }
        }
    }
}
