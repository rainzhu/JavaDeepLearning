package com.yujoo.jdk.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;




/**
 * 设计一个同步工具：该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的访问将被阻塞
 * @author eaves.zhu
 *
 */
public class TwinsLock implements Lock{
	//同一时刻最多允许两个人
	private final Sync sync=new Sync(2);
	
	private static class Sync extends AbstractQueuedSynchronizer{

		public Sync(int count) {
			if(count<=0){
				throw new IllegalArgumentException("count must large than zero.");

				
			}
			setState(count);
		}
	
		
		public int tryAcquireShared(int reduceCount){
			for(;;){
				int current=getState();
				int newCount=current-reduceCount;
				if(newCount<0|| compareAndSetState(current, newCount))
				{
					return newCount;
				}
				
			}
		}
		
		public boolean tryReleaseShared(int returnCount){
			for(;;)
			{
				int current=getState();
				int newCount=current+returnCount;
				if(compareAndSetState(current, newCount))
				{
					return true;
				}
			}

		}
		
	}
	@Override
	public void lock() {
		sync.acquireShared(1);
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
	sync.releaseShared(1);
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
