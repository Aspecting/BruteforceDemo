package com.jclibo.libobrute;

public abstract class Bruteforce{
	private char[] charSet;
	protected boolean silent = false;
	protected boolean log = false;
	public Bruteforce(char[] charSet){
		this.charSet = new char[charSet.length];
		for(int i = 0;i<charSet.length;i++)
			this.charSet[i] = charSet[i];
	}
	public void brute(char[] n){
		println(new String(n));
		if(check(new String(n))){
			found(new String(n));
			return;
		}
		/*
		 * The password does not match.
		 */
		// if(last character in the array is the last character of the charset)
		// then(all characters will be shifted up)
		if(globalIndex(n[n.length-1])==charSet.length-1){
			for(int i = n.length-1;i>=0;i--){
				if(globalIndex(n[i])==charSet.length-1)
					n[i]=charSet[0];
				else{
					n[i] = charSet[globalIndex(n[i])+1];
					break;
				}
			}
			char[] returnThis = new char[n.length+1];
			for(int i = 0;i<n.length;i++)
				returnThis[i] = n[i];
			returnThis[returnThis.length-1] = charSet[0];
			brute(returnThis);
			return;
		}
		// if NOT (last character in the array is the last character of the charset)
		// then(last character will shift up)
		else{
			n[n.length-1] = (char)(charSet[(globalIndex(n[n.length-1]))+1]);
			brute(n);
			return;
		}
					
	}
	
	/**
	 * 
	 * @param c A character found in the charSet
	 *
	 * @return <p>The index of [c] in the charSet</p> <p>-1 if the character is not found in the charSet</p>
	 */
	private int globalIndex(char c){
		for(int i = 0;i<charSet.length;i++)
			if(c==charSet[i])
				return i;
		return -1;
	}
	private void println(String n){
		if(!silent)
			System.out.println(n);
	}
	protected abstract boolean check(String n);
	protected abstract void found(String n);
}
