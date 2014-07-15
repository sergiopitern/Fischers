package br.ufu;

/*
 *
 * Programa principal para testar os problemas de secoes criticas com exclusao mutua.
 *
 */

public class Principal {
	
	  public final static int NUM_THREADS   = 10;
	   public static int sharedData  = 0;
	   public static int sharedData2 = 0;
	   
	   static class theLock extends Object {
	   }
	   
	   static public theLock lockObject = new theLock();
	 
	   static class theThread extends Thread {
	      public void run() {
	         System.out.print(getName() + ": Criada.\n");
	         
	         synchronized (lockObject) {
	            /********** Critical Section *******************/
	            System.out.print("Thread " + getName() +
	                             ": Entrando na secao critica.\n");
	            ++sharedData; 
	            --sharedData2;
	            System.out.print("Thread " + getName() +
	                             ": Saindo da secao critica.\n");
	            System.out.print("\n");
	            /********** Critical Section *******************/
	         }
	      }
	   }
	 
	   public static void main(String argv[]) {
	      theThread threads[] = new theThread[NUM_THREADS];
	      
	      System.out.print("Iniciando os processos...\n");
	      synchronized (lockObject) {
	 
	         System.out.print("Criando as threads \n");
	         for (int i=0; i<NUM_THREADS; ++i) {
	            threads[i] = new theThread();
	            threads[i].start();
	         }
	 
	         //System.out.print("Aguardando as Threads compartilhadas \n");
	         try {
	            Thread.sleep(5000);
	         }
	         catch (InterruptedException e) {
	            System.out.print("processo interrompido\n");
	         }
	         //System.out.print("Saindo do processo compartilhado\n");
	      }
	 
	      System.out.print("\n");
	      
	      for(int i=0; i <NUM_THREADS; ++i) {
	            try {
					threads[i].join();
				} catch (InterruptedException e) {
					System.out.print("Juncao interrompida\n");
				}
	       
	        }
		         
		      System.out.print("Finalizado.\n");
		      System.exit(0);
	      }
	 

	   }
	 
//}
