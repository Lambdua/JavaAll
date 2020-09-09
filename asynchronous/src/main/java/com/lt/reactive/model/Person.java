package com.lt.reactive.model;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntFunction;

/**
 * @author 梁先生
 * @Date 2020/9/3
 **/
@Data
@AllArgsConstructor
public class Person {
    String name;
    int age;
    int height;


    public static List<Person> makePerson() {
        Class<Person> personClass = Person.class;
        return Arrays.asList(
                new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
                , new Person(RandomUtil.randomString(3), RandomUtil.randomInt(30), RandomUtil.randomInt(120, 200))
        );
    }

    public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr, T... a) {
        T[] mm = constr.apply(a.length);
        for (int i = 0; i < a.length; i++) {
            mm[i] = a[i];
        }
        return mm;
    }

    public static void main(String[] args) {
//        String[] minmax = Person.minmax(String[]::new, "2", "rw", "rer");
//        System.out.println(Arrays.toString(minmax));

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        HashMap<String,Integer> hashMap=new HashMap<>();
        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);
        for (int i = 0; i < 20; i++) {
            map.compute("1",(k,v)->{
                System.out.println("k = " + k);
                return ++v;
            });
        }
        System.out.println(map.get("1"));
    }
}

