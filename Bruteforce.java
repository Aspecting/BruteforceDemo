package com.aspecting.bruteforcedemo;

/**
 * @author Micheal Myers
 */
public abstract class Bruteforce{
	private char[] charSet;
	private boolean bruteStatus;
	private long sleep;
	private volatile char[] n;
	
	/**
	 * @param charSet The characters in cirrculation being used for the search.
	 * @param start Characters to start search at.
	 * @param sleep How many seconds the Thread will sleep after each iteration.
	 */
	public Bruteforce(char[] charSet, char[] start, long sleep){
		bruteStatus = false;
		this.sleep = sleep;
		this.charSet = charSet;
		n = start;
	}
	
	/**
	 * 
	 * Initializes the bruteforce.
	 * 
	 * @return <p><code>true</code> if the bruteforce was successfully initialized.</p>
	 * 		   <p><code>false</code> if the bruteforce initialization failed.</p>
	 */
	public boolean initBrute(){
		if(bruteStatus)
			return false;
		else{
			bruteStatus = true;
			bruteLoop();
			return true;
		}
	}
	
	private synchronized void bruteLoop(){
		while(true){
			brute();
			try {
				Thread.sleep(this.sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void brute(){
		if(check(new String(n))){
			found(new String(n));
		}
		else{
			/*
			 * The password does not match. All applicible
			 * characters will be shifted up.
			 */
			for(int i = n.length-1;i>=0;i--){
				if(globalIndex(n[i])==charSet.length-1){
					/* If the first index is the last character
					 * of the charSet then all characters will
					 * be shifted up to the first character of
					 * the charSet and the password length will
					 * increase by 1.
					 */
					if(i == 0){
						char[] addedNewIndex = new char[n.length+1];
						for(int k = 0;k<addedNewIndex.length;k++)
							addedNewIndex[k] = charSet[0];
						n = addedNewIndex;
						return;
					}
					else
						n[i]=charSet[0];
				}
				else{
					n[i] = charSet[globalIndex(n[i])+1];
					return;
				}
			}
		}			
	}
	
	/**
	 * @param c A character found in the charSet
	 * @return <p>The index of [c] in the charSet<p>
	 * 		   <p>-1 if the character is not found in the charSet</p>
	 */
	private int globalIndex(char c){
		for(int i = 0;i<charSet.length;i++)
			if(c==charSet[i])
				return i;
		return -1;
	}
	
	/**
	 * <code>check()</code> will be called each iteration to check
	 * if the password matches the designated objective.
	 * 
	 * @param n The current string being iterated by brute()
	 * @return true if <code>String n</code> matches the designated password/objective.
	 */
	protected abstract boolean check(String n);
	
	/**
	 * This method will be called once check() returns
	 * true.
	 * 
	 * @param n The password that reached the objective.
	 */
	protected abstract void found(String n);
}
