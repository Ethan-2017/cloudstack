// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/02/2012
package com.cloud.async;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.cloud.utils.component.ComponentLocator;
import com.cloud.utils.testcase.ComponentSetup;
import com.cloud.utils.testcase.ComponentTestCase;

@ComponentSetup(managerName="management-server", setupXml="sync-queue-component.xml")
public class TestSyncQueueManager extends ComponentTestCase {
    public static final Logger s_logger = Logger.getLogger(TestSyncQueueManager.class.getName());
    
    private volatile int count = 0;
    private volatile long expectingCurrent = 1;

	public void leftOverItems() {
		SyncQueueManager mgr = ComponentLocator.getCurrentLocator().getManager(
			SyncQueueManager.class);

		List<SyncQueueItemVO> l = mgr.getActiveQueueItems(1L, false);
		if(l != null && l.size() > 0) {
			for(SyncQueueItemVO item : l) {
				s_logger.info("Left over item: " + item.toString());
				mgr.purgeItem(item.getId());
			}
		}
	}

	public void dequeueFromOneQueue() {
		final SyncQueueManager mgr = ComponentLocator.getCurrentLocator().getManager(
			SyncQueueManager.class);
		
		final int totalRuns = 5000;
		final SyncQueueVO queue = mgr.queue("vm_instance", 1L, "Async-job", 1);
		for(int i = 1; i < totalRuns; i++)
			mgr.queue("vm_instance", 1L, "Async-job", i+1);
		
		count = 0;
		expectingCurrent = 1;
		Thread thread1 = new Thread(new Runnable() {
				public void run() {
					while(count < totalRuns) {
						SyncQueueItemVO item = mgr.dequeueFromOne(queue.getId(), 1L);
						if(item != null) {
							s_logger.info("Thread 1 process item: " + item.toString());
							
							Assert.assertEquals(expectingCurrent, item.getContentId().longValue());
							expectingCurrent++;
							count++;
							
							mgr.purgeItem(item.getId());
						}
						try {
							Thread.sleep(getRandomMilliseconds(1, 10));
						} catch (InterruptedException e) {
						}
					}
				}
			}
		);
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
					while(count < totalRuns) {
						SyncQueueItemVO item = mgr.dequeueFromOne(queue.getId(), 1L);
						if(item != null) {
							s_logger.info("Thread 2 process item: " + item.toString());
							
							Assert.assertEquals(expectingCurrent, item.getContentId().longValue());
							expectingCurrent++;
							count++;
							mgr.purgeItem(item.getId());
						}
						
						try {
							Thread.sleep(getRandomMilliseconds(1, 10));
						} catch (InterruptedException e) {
						}
					}
				}
			}
		); 
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
		} catch (InterruptedException e) {
		}
		try {
			thread2.join();
		} catch (InterruptedException e) {
		}
		
		Assert.assertEquals(totalRuns, count);
	}
	
	public void dequeueFromAnyQueue() {
		final SyncQueueManager mgr = ComponentLocator.getCurrentLocator().getManager(
			SyncQueueManager.class);

		// simulate 30 queues
		final int queues = 30;
		final int totalRuns = 100;
		final int itemsPerRun = 20;
		for(int q = 1; q <= queues; q++)
			for(int i = 0; i < totalRuns; i++)
				mgr.queue("vm_instance", q, "Async-job", i+1);
		
		count = 0;
		Thread thread1 = new Thread(new Runnable() {
				public void run() {
					while(count < totalRuns*queues) {
						List<SyncQueueItemVO> l = mgr.dequeueFromAny(1L, itemsPerRun);
						if(l != null && l.size() > 0) {
							s_logger.info("Thread 1 get " + l.size() + " dequeued items");
							
							for(SyncQueueItemVO item : l) {
								s_logger.info("Thread 1 process item: " + item.toString());
								count++;
								
								mgr.purgeItem(item.getId());
							}
						}
						try {
							Thread.sleep(getRandomMilliseconds(1, 10));
						} catch (InterruptedException e) {
						}
					}
				}
			}
		);
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
					while(count < totalRuns*queues) {
						List<SyncQueueItemVO> l = mgr.dequeueFromAny(1L, itemsPerRun);
						if(l != null && l.size() > 0) {
							s_logger.info("Thread 2 get " + l.size() + " dequeued items");
							
							for(SyncQueueItemVO item : l) {
								s_logger.info("Thread 2 process item: " + item.toString());
								count++;
								mgr.purgeItem(item.getId());
							}
						}
						
						try {
							Thread.sleep(getRandomMilliseconds(1, 10));
						} catch (InterruptedException e) {
						}
					}
				}
			}
		); 
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
		} catch (InterruptedException e) {
		}
		try {
			thread2.join();
		} catch (InterruptedException e) {
		}
		Assert.assertEquals(queues*totalRuns, count);
	}
	
	public void testPopulateQueueData() {
		final int queues = 30000;
		final int totalRuns = 100;
		
		final SyncQueueManager mgr = ComponentLocator.getCurrentLocator().getManager(
				SyncQueueManager.class);
		for(int q = 1; q <= queues; q++)
			for(int i = 0; i < totalRuns; i++)
				mgr.queue("vm_instance", q, "Async-job", i+1);
	}
    
    public void testSyncQueue() {
        final SyncQueueManager mgr = ComponentLocator.getCurrentLocator().getManager(
                SyncQueueManager.class);

        mgr.queue("vm_instance", 1, "Async-job", 1);
        mgr.queue("vm_instance", 1, "Async-job", 2);
        mgr.queue("vm_instance", 1, "Async-job", 3);
        mgr.dequeueFromAny(100L, 1);
        
        List<SyncQueueItemVO> l = mgr.getBlockedQueueItems(100000, false);
        for(SyncQueueItemVO item : l) {
            System.out.println("Blocked item. " + item.getContentType() + "-" + item.getContentId());
            mgr.purgeItem(item.getId());
        }
    }
}
