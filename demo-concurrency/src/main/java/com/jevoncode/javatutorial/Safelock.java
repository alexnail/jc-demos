package com.jevoncode.javatutorial;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * the usage of lock
 * 
 */
public class Safelock {
	static class Friend {
		private final String name;
		private final Lock lock = new ReentrantLock();

		public Friend(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public boolean impendingBow(Friend bower) {
			Boolean myLock = false;
			Boolean yourLock = false;
			try {
				myLock = lock.tryLock(); //尝试获取自己的锁
				yourLock = bower.lock.tryLock(); //尝试获取对方的锁
			} finally {
				if (!(myLock && yourLock)) { //只要有一方的锁没拿到
					if (myLock) {  //如果是自己的锁拿到了,对方的锁没拿到，则释放自己的锁
						lock.unlock();
					}
					if (yourLock) { //如果是对方的锁拿到了，自己的锁没拿到，则释放对方的锁
						bower.lock.unlock();
					}
				}
			}
			return myLock && yourLock; 
		}

		public void bow(Friend bower) {
			if (impendingBow(bower)) { //只有拿到双方的锁才开始向对方鞠躬
				try {
					System.out.format("%s: %s has" + " bowed to me!%n",
							this.name, bower.getName());
					bower.bowBack(this);
				} finally {
					lock.unlock();
					bower.lock.unlock();
				}
			} else { //否则让对方鞠躬
				System.out.format("%s: %s started"
						+ " to bow to me, but saw that"
						+ " I was already bowing to" + " him.%n", this.name,
						bower.getName());
			}
		}

		public void bowBack(Friend bower) {
			System.out.format("%s: %s has" + " bowed back to me!%n", this.name,
					bower.getName());
		}
	}

	static class BowLoop implements Runnable {
		private Friend bower;
		private Friend bowee;

		public BowLoop(Friend bower, Friend bowee) {
			this.bower = bower;
			this.bowee = bowee;
		}

		public void run() {
			Random random = new Random();
			for (;;) {
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
				}
				//我所猜测的是，有这种情况：两者都会可能获取自己锁，对方的锁没拿到，于是双方释放自己的锁。然后再重新尝试获取自己的锁和对方的锁。
				bowee.bow(bower);
			}
		}
	}

	public static void main(String[] args) {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		new Thread(new BowLoop(alphonse, gaston)).start();
		new Thread(new BowLoop(gaston, alphonse)).start();
	}
}
