package com.iscas.common.tools.threadpool;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author ISCAS
 * @date 2016/12/8
 */
@SuppressWarnings("unused")
public abstract class BaseConsumer implements Runnable {
	private ConcurrentLinkedQueue<Object> taskQueue;

	public ConcurrentLinkedQueue<Object> getTaskQueue() {
		return taskQueue;
	}

	public void setTaskQueue(ConcurrentLinkedQueue<Object> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
	@Override
    public void run() {
		while (true) {
			if (taskQueue.isEmpty()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				Object task = taskQueue.poll();
				if (task != null) {
					consume(task);
				}
			}
		}
	}

	/**
	 * consume
	 * @since jdk11
	 * @date 2022/4/22
	 * @param task task
	 */
	public abstract void consume(Object task);
}
