package com.suntoon.swing;

/**
 * @ProjectionName desktop
 * @EnumName ItemStatus
 * @Description 代表当前的行，或者对象的编辑状态
 * @Author ylf
 * @Date 2019/4/15 0015下午 3:06
 * @Version 1.0
 */
public enum ItemStatus {

    //原始状态
    Original(0), New(1), Modified(2), Delete(3);

    //当前状态编码
    private int status = 0;

    //当前状态编码
    public int getStatus() {
        return status;
    }

    //当前的行状态
    private ItemStatus(int status) {
        this.status = status;
    }
}
