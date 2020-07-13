package com.lt.Y19_9.week1.day6;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/21
 * <p>
 * day5_Solution2的拓展
 * 给出一个具有重复数字的列表，找出列表所有不同的排列。
 * <p>
 * 输入：[1,1]
 * 输出：
 * [
 * [1,1]
 * ]
 * <p>
 * 输入：[1,2,2]
 * 输出：
 * [
 * [1,2,2],
 * [2,1,2],
 * [2,2,1]
 * ]
 * <p>
 * <p>
 * 思考: 数组 count[],输入[1,2,2,1,2]
 * 插入[1],周围没有同样值
 * <p>
 * 插入2
 * [1,2] [2,1]周围没有相同值
 * <p>
 * <p>
 * 插入2
 * [1,2]:
 * [1,2,2]:插入最右方,此时设置 [1,2]的2值的索引位置count[1]=1
 * [2,1,2]:插入左方:没有相同
 * [1,2,2]:插入中间,右边相同,检测插入位置[1,2]的2值的索引count[1]==1 不插入
 * [2,1]:
 * [2,1,2]:插入最右方,没有相同
 * [2,2,1]:插入左方,相同,检测=1,则count[0]=1
 * [2,2,1]:插入中间,有相同,检测,不插入
 * 插入1
 * [1,2,2]:
 * [1,2,2,1]:无相同
 * [1,1,2,2]:count[0]=0,插入,设置count[0]=1
 * [1,1,2,2]:count[0]=1,放弃
 * [1,2,1,2]:无相同
 * [2,1,2]:
 * [1,2,1,2]
 * [2,1,2,1]
 * [2,1,1,2]
 * [2,1,1,2]:舍弃
 * ......
 * <p>
 * 插入2
 * [1,2,2,1]:
 * [2,1,2,2,1]:
 * [1,2,2,1,2]:
 * [1,2*,2,2,1]:查找count[1]=0,插入,设置count[1]=1
 * [1,2,2*,2,1]:查找count[1]=1,count[2]=0,放弃,设置count[2]=1,count[1]=2;
 * [1,2,2,2*,1]:cont[2]++,放弃
 * [1,1,2,2]:
 * [2,1,1,2,2]
 * [1,2*,1,2,2]
 * [1,1,2*,2,2]:count[2]=1,
 * [1,1,2,2*,2]:count[2]=2,count[3]=1;
 * ...
 * ....
 * <p>
 * 插入发现,如果在插入值的相邻位置有相同的元素,则只插入一次,设置一个数组,下标为相邻的同样的值在原数组的位置,当值>1时不在进行插入
 * <p>
 * 思考:
 * 同样的方法,添加检测重复逻辑,存在重复,根据条件筛选插入,返回.
 *
 *
 *  ★★缺陷 对称结构的相同排列无法检测出来
 **/
public class Solution1 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0 || nums == null) {
            return result;
        }

        for (int num : nums) {
            result = insert(result, num);
        }

        return result;
    }

    private List<List<Integer>> insert(List<List<Integer>> list, int insertNum) {

        List<List<Integer>> result = new ArrayList<>();

        //如果等于0,就是第一个插入,实例化一个对象
        if (list.size() == 0) {
            List<Integer> line = new ArrayList<>();
            line.add(insertNum);
            result.add(line);
            return result;
        }

        int count[] = new int[list.get(0).size()];
        for (int i = 0; i < list.size(); i++) {
            //遍历插入每一个集合
            List<Integer> line = list.get(i);

            for (int j = 0; j <= line.size(); j++) {
                //创建一个数组,判断重复元素
                Integer left = null;
                Integer right = null;
                boolean isrepeat = false;


                //在j位置插入
                boolean isInsert = false;
                List<Integer> temp = new ArrayList<>();
                int index = 0;
                while (index < line.size() || !isInsert) {
                    //要插入位置,还没有插入过
                    if (index == j && !isInsert) {
                        //重复判断
                        //左值为插入位置的值
                        if (j > 0) {//不在最左边
                            left = line.get(j - 1);
                        }
                        if (j < line.size()) {//不在最右边
                            right = line.get(j);
                        }
                        if (left != null && left == insertNum) {
                            if (count[j - 1] > 0) {
                                isrepeat = true;
                            }
                            count[j - 1]++;
                        }
                        if (right != null && right == insertNum) {
                            if (count[j] > 0) {
                                isrepeat = true;
                            }
                            count[j]++;
                        }

                        temp.add(insertNum);
                        //重复不重复都只进行一次插入判断,产生一个新line
                        isInsert = true;
                    } else {
                        temp.add(line.get(index++));
                    }
                }
                if (!isrepeat) {
                    result.add(temp);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int nums[] = new int[]{1, 2, 2};
        Solution1 solution1 = new Solution1();
        List<List<Integer>> lists = solution1.permuteUnique(nums);

    }
}

