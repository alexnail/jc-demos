package com.jc.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperWatcher implements Watcher {

	public CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private ZooKeeper zooKeeper ;
	

	public static void main(String[] args) throws IOException {
		ZooKeeperWatcher watcher = new ZooKeeperWatcher();
		ZooKeeper zookeeperInit = new ZooKeeper("192.168.56.101:2181,192.168.56.102:2181,192.168.56.103:2181", 5000,
				watcher);
		watcher.setZooKeeper(zookeeperInit);
		System.out.println(zookeeperInit.getState());
		synchronized (watcher.connectedSemaphore) {
			try {
				watcher.connectedSemaphore.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("ZooKeeper session established.");
	}

	public ZooKeeper getZooKeeper() {
		return zooKeeper;
	}

	public void setZooKeeper(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event: " + event);
		if (KeeperState.SyncConnected == event.getState()) {
			System.out.println("syncConnected");
			System.out.println("sessionId="+getZooKeeper().getSessionId());
			System.out.println("sessionPasswd="+getZooKeeper().getSessionPasswd());
			connectedSemaphore.countDown();
		}
	}

}
