package br.ufu;

//import java.util.Random;

public class MutexFischers extends Thread
{
	   public static int sharedData  = -1;
	   public static int sharedData2 = -1;
	   /* Any real java object or array would suit for synchronization          */
	   /* We invent one here since we have two unique data items to synchronize */
	   /* An in this simple example, they're not in an object                   */
	   public class theLock extends Object {
	   }
	   public theLock lockObject = new theLock();
	 
	   public class theThread extends Thread {
	      public void run() {
	         System.out.print("Thread " + getName() + ": Entered\n");
	         synchronized (lockObject) {
	            /********** Critical Section *******************/
	            System.out.print("Thread " + getName() +
	                             ": Start critical section, in synchronized block\n");
	            ++sharedData; --sharedData2;
	            System.out.print("Thread " + getName() +
	                             ": End critical section, leave synchronized block\n");
	            /********** Critical Section *******************/
	         }
	      }
	   }
}
