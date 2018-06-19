package com.dfire.soa.activity.thread;

import com.quspacedragon.workflow.thread.ActivityThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum ThreadPoolManager {

    INSTANCE;

    // 线程池维护线程的最少数量
    private final int SIZE_CORE_POOL = 16;

    // 线程池维护线程的最大数量
    private final int SIZE_MAX_POOL = 20;

    // 线程池维护线程所允许的空闲时间
    private final int TIME_KEEP_ALIVE = 60;

    // 线程池所使用的缓冲队列大小
    private final int SIZE_WORK_QUEUE = 100;

    private ThreadPoolExecutor treadPool;

    private ThreadPoolExecutor jobTreadPool;


    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);

    ThreadPoolManager() {

        treadPool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, TIME_KEEP_ALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(SIZE_WORK_QUEUE),
                new ActivityThreadFactory("activity_treadPool"), new DiscardPolicy());

        jobTreadPool = new ThreadPoolExecutor(8, 16, TIME_KEEP_ALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(30),
                new ActivityThreadFactory("job_treadPool"), new DiscardPolicy());

    }

    /**
     * 拒绝策略添加日志
     */
    public class DiscardPolicy implements RejectedExecutionHandler {

        public DiscardPolicy() {
        }

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            logger.error("DiscardPolicy {} {}", r.toString(), e.getActiveCount());
        }
    }

    /**
     * 定时任务线程
     */
    public void addJobExecuteTask(Runnable r) {
        jobTreadPool.execute(r);
    }

    /**
     * 向线程池中添加任务方法
     */
    public void addExecuteTask(Runnable r) {
        treadPool.execute(r);
    }

}
