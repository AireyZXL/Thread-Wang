package com.soft.chatper7;

/**
 * 线程状态监控接口
 *
 * @author zxlei1
 * @date 2018/9/6 15:02
 */
public interface ThreadListener {


    public Object threadStart(Object[] args);

    public Object threadRunning(Object[] args);

    public Object threadFinish(Object[] args);

    public Object threadException(Object[] args);
}
