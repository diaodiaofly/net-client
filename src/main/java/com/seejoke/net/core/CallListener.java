package com.seejoke.net.core;

/**
 * CallListener
 *
 * @author yangzhongying
 * @version 1.0
 * @date 2018-06-01 16:43
 **/
public interface CallListener {

    /**
     * 状态改变
     *
     * @param info info
     */
    void statusCall(String info);

    /**
     * 事件回调
     *
     * @param info 参数
     */
    void eventCall(String info);

    /**
     * 流量统计
     *
     * @param traffic 参数
     */
    void trafficCall(long traffic);


    /**
     * speedCall
     *
     * @param speed 参数
     */
    void speedCall(long speed);

    /**
     * 关闭
     */
    void onClose();


    /**
     * ping
     *
     * @param ms 毫秒
     */
    void ping(long ms);
}