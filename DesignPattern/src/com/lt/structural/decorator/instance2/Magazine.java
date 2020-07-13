package com.lt.structural.decorator.instance2;

/**
 * @author liangtao
 * @Date 2019/11/26
 **/
public class Magazine extends AbstarctMagazine {
    public Magazine(Gun gun) {
        super(gun);
    }

    @Override
    public void fire() {
        super.fire();
        System.out.println("装上弹夹");
    }
}
