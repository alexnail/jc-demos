package com.jc.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperCreateAPI implements Watcher {
	private ZooKeeper zooKeeper;
	private CountDownLatch connectedSemaphore = new CountDownLatch(1);

	public ZooKeeperCreateAPI() {
		try {
			zooKeeper = new ZooKeeper("192.168.56.101:2181,192.168.56.102:2181,192.168.56.103:2181", 5000, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		ZooKeeperCreateAPI zooKeeperCreateAPI = new ZooKeeperCreateAPI();
		zooKeeperCreateAPI.goToWait();
	}

	private void create() {
		try {
			String path1 = zooKeeper.create("/zk-test-ephemeral-", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL);
			System.out.println("Success create znode:" + path1);
		} catch (KeeperException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void goToWait() {
		synchronized (connectedSemaphore) {
			try {
				connectedSemaphore.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event: " + event);
		if (KeeperState.SyncConnected == event.getState()) {
			System.out.println("syncConnected");
			System.out.println("sessionId=" + getZooKeeper().getSessionId());
			System.out.println("sessionPasswd=" + getZooKeeper().getSessionPasswd());
			connectedSemaphore.countDown();
			create();
		}
	}

	public ZooKeeper getZooKeeper() {
		return zooKeeper;
	}

	public void setZooKeeper(ZooKeeper zooKeeper) {
		this.zooKeeper = zooKeeper;
	}

}
