package com.lt.structural.decorator.instance2;

/**
 * @author liangtao
 * @Date 2019/11/26
 **/
public abstract class AbstarctMagazine implements Gun{
    private Gun gun;

    public AbstarctMagazine(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void fire() {
        gun.fire();
    }
}

