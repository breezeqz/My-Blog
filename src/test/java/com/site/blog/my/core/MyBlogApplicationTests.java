package com.site.blog.my.core;

import com.site.blog.my.core.controller.blog.MyBlogController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

	MyBlogController myBlogController;
	private int count=0;
	@Test
	public void contextLoads() {
		Lock lock = new ReentrantLock();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(count<=30){
					lock.lock();
					if(count%3==0){
						System.out.print("1");
						count++;
					}
					lock.unlock();
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(count<=30){
					lock.lock();
					if(count%3==1){
						System.out.print("2");
						count++;
					}

					lock.unlock();
				}
			}
		});
		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(count<=30){
					lock.lock();
					if(count%3==2){
						System.out.print("3");
						count++;
					}

					lock.unlock();
				}
			}
		});
		thread1.start();
		thread2.start();
		thread3.start();
	}


}
