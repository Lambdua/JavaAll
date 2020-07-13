//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
//
// 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
//
// 你可以假设 nums1 和 nums2 不会同时为空。
//
//
//
// 示例 1:
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
//
//
// 示例 2:
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
//
// Related Topics 数组 二分查找 分治算法
// 👍 2861 👎 0

package com.lt.hard;

/**
 * @author liangtao
 * @Date 2020/7/6
 * 思路：
 * 中位数有两种情况，一种是奇数，直接取中间值，一种是偶数，取中间两边的值/2。
 * 无论那种情况，都等同于每次从两个有序数组中找到第K小的数并返回。
 * 则此题转为在两个有序数组中找到第k小的数
 * 查找方法： 二分法：
 * A: 1 3 4 9
 * B: 1 2 3 4 5 6 7 8 9
 * 两个有序数组的长度分别是4和9，长度之和是13，中位数则为7，设k=7
 * 比较两个有序数组中，下标为 k/2-1=2的数， A[2]和B[2],
 * A: 1 3 4 9
 * ↑
 * B: 1 2 3 4 5 6 7 8 9
 * ↑
 * 由于A[2]>B[2] 因此排除B[0-2],即数组B的下标偏移（offset)3,同时更新k=k-k/2=4;
 * 继续进行下一步寻找， 此时比较k/2-1=1的数 即A[1]和B[1]，
 * A: 1 3 4 9
 * ↑
 * B: [1 2 3] 4 5 6 7 8 9
 * ↑
 * 由于A[1+offset]<B[1+offset]，因此排除A[0-1]，即数组A的下标偏移2，同时跟新k=k-k/2=2;
 * 同理，重复比较A[k/2-1+offset]和B[k/2-1+offset]，
 * A: [1 3] 4 9
 * ↑
 * B: [1 2 3] 4 5 6 7 8 9
 * ↑
 * 此时A[2]=B[2], 【排除A中的元素，即A的offset=3】,同时更新K值 k=k/2=1
 * <p>
 * 此时k==1,因此直接比较两个有序数组中的未排除下标范围内的第一个数即A[offset]和B[offset],此时A[3]>B[3],因此第k个数是B[3]=4
 * A: [1 3 4] 9
 * ↑
 * B: [1 2 3] 4 5 6 7 8 9
 * ↑
 **/
public class h4寻找两个正序数组的中位数 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = nums1.length + nums2.length;
        if (i % 2 == 1) {
            return getKminElement(nums1, nums2, i / 2 + 1);
        } else {
            int left = getKminElement(nums1, nums2, i / 2 - 1 + 1);
            int right = getKminElement(nums1, nums2, i / 2 + 1);
            return (left + right) / 2.0;
        }
    }

    public int getKminElement(int[] nums1, int[] nums2, int k) {
        int offset1 = 0, offset2 = 0;
        int n1length = nums1.length;
        int n2length = nums2.length;
        while (true) {
            //边界
            if (offset1 == n1length) return nums2[offset2 + k - 1];
            if (offset2 == n2length) return nums1[offset1 + k - 1];
            if (k == 1) return Math.min(nums2[offset2], nums1[offset1]);

            int half = k / 2;
            int index1 = Math.min(half + offset1, n1length) - 1;
            int index2 = Math.min(half + offset2, n2length) - 1;
            if (nums1[index1] > nums2[index2]) {
                k -= (index2 - offset2 + 1);
                offset2 = index2 + 1;
            } else {
                k -= (index1 - offset1 + 1);
                offset1 = index1 + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4,};

        h4寻找两个正序数组的中位数 entity = new h4寻找两个正序数组的中位数();
        System.out.println(entity.findMedianSortedArrays(nums1, nums2));
//        System.out.println(dfsd.findMedianSortedArrays(nums1, nums2));
    }
}
