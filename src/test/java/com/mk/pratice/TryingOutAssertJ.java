package com.mk.pratice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

public class TryingOutAssertJ {

	@Test
	public void tryingAssertJ() {
	
	LocalDateTime n = LocalDateTime.now();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss");
	String currentTime = n.format(dtf);
		System.out.println(currentTime);
	
	}
	
}
