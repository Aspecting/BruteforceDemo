package com.jclibo.libobrute;

import java.util.Date;

public class BruteStandard extends Bruteforce{

	private volatile int count = 0;
	private String password;
	private Date date;
	private long start;
	
	public static void main(String[] args){
		new BruteStandard("abcdefghijklmnopqrstuvwxyz".toCharArray(),"a","mike");
	} 
	
	public BruteStandard(char[] charSet, String start,String password) {
		super(charSet,start.toCharArray(),0);
		this.password = password;
		date = new Date();
		System.out.println(date);
		this.start = System.currentTimeMillis();
		super.initBrute();
	}

	protected synchronized boolean check(String n) {
		System.out.println(n);
		count++;
		return n.equals(password);
	}

	protected void found(String n) {
		System.out.println("FOUND! "+n);
		System.out.println(count+" tried.");
		System.out.println((System.currentTimeMillis()-start)/1000+"s elapsed");
		date = new Date();
		System.out.println(date);
		System.exit(0);
	}

}
