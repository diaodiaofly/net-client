package com.seejoke.net;

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
     * @param info
     */
    void statusCall(String info);

    /**
     * 事件回调
     *
     * @param info
     */
    void eventCall(String info);

    /**
     * 流量统计
     *
     * @param traffic
     */
    void trafficCall(long traffic);


    /**
     * speedCall
     *
     * @param speed
     */
    void speedCall(long speed);

    /**
     * 关闭
     */
    void onClose();


    /**
     * ping
     *
     * @param ms
     */
    void ping(long ms);
}