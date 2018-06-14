package com.seejoke.net;

import io.socket.emitter.Emitter.Listener;

/**
 * @author yangzhongying
 */
public abstract class AbstractSocketListener implements Listener {

    private String eventName;

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public void call(Object... args) {
        this.eventCall(this.getEventName(), this.getMessage(), args);
    }


    /**
     * 回调事件
     *
     * @param eventName
     * @param message
     * @param args
     */
    public abstract void eventCall(String eventName, String message, Object... args);

}
