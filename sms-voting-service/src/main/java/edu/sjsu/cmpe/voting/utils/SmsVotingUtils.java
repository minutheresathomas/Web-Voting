package edu.sjsu.cmpe.voting.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class SmsVotingUtils {
	private static AtomicInteger atomicId = new AtomicInteger();
	
	public static long incrementCounter()
	{
		long count = atomicId.incrementAndGet();
		return(count);
	}
}
