package com.lt.structural.adapter.instance2;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class USBAdapter implements USBInsert {
   private NetInsert netInsert;

    public USBAdapter(NetInsert netInsert) {
        this.netInsert = netInsert;
    }

    @Override
    public void insertUse() {
       netInsert.useNetInsert();
    }
}
