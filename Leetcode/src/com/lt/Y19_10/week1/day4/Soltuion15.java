package com.lt.Y19_10.week1.day4;

import java.util.*;

/**
 * @author liangtao
 * @Date 2019/10/11
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 思考:
 * 可以先对数组进行排序:
 * [-4,-1,-1,0,1,2]
 * 取-4+-1 此时从右边寻找 5,最右边小于五 不存在
 * 取-4+-1 同理
 * ...
 * 取-1+-1 最右寻找2
 * 取-1 +0 最右寻找1
 * 取-1+1=0 0<右边1 结束.
 * <p>
 * eg:
 * [-4,-2,-1,0,1,2,3,4,5]
 * -4+-2 寻6,无
 * -4+-1 寻5 有
 * -4+0 寻4 有
 * -4+1 寻3 有
 * -4 +2 寻2 ,2索引右边无2 结束
 **/
public class Soltuion15 {
    public List<List<Integer>> threeSum(int[] nums) {
//        boolean found=false;
        List<List<Integer>> result = new ArrayList<>();

        if (nums.length==0){
            return result;
        }

        //判断重复
        Set<Integer> firstContain=new LinkedHashSet<>();
        Set<Integer> secondContain=new LinkedHashSet<>();
        //数组进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            //去重复
            if (!firstContain.contains(first)) {
                firstContain.add(first);
                for (int j = i + 1; j < nums.length; j++) {

                    if (secondContain.contains(nums[j])) {
                       continue;
                    }else {
                        secondContain.add(nums[j]);
                    }


                    int targetNeed = -(first + nums[j]);
                    if (targetNeed < nums[j]) {
                        break;
                    }

                    for (int k = nums.length - 1; k > j; k--) {

                        //找到了,存入,结束遍历
                        if (nums[k] == targetNeed) {

                            result.add(Arrays.asList(first, nums[j], nums[k]));

                            break;
                        }
                        //如果右边小于目标数,后面不用在遍历
                        if (nums[k] < targetNeed) {
                            break;
                        }
                    }
                }
            }

            secondContain.clear();
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        int[] nums2 = new int[]{0,0,0,0};
        int[] nums3 = new int[]{-1,0,0,1};
        int[] nums4 = new int[]{1,1,-2};
        Soltuion15 soltuion15 = new Soltuion15();
        List<List<Integer>> lists = soltuion15.threeSum(nums);

        List<List<Integer>> lists1 = soltuion15.threeSum(nums2);

    }

}
