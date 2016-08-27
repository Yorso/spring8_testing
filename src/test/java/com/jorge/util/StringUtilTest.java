package com.jorge.util;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;

public class StringUtilTest {

	/**
	 * Simulating dependencies with mocks using Mockito
	 * 
	 * With the mock, we don't need the actual StringUtil class to execute our test method. Mockito 
	 * creates and uses, behind the scenes, a proxy class.
	 * Again, in the real world, creating a mock for this class would be overkill. Mocks are useful to
	 * simulate complicated dependencies, such as an SMTP method, the SMTP server behind it, or a REST
	 * service.
	 * 
	 * For more Mockito's features:
	 *		http://mockito.github.io/mockito/docs/current/org/mockito/Mockito.html
	 *
	 */
	@Test
	public void testContact() {
		StringUtil stringUtilMock = Mockito.mock(StringUtil.class); // Creating a mock instance of StringUtil
		
		// Programming the mock
		Mockito.when(stringUtilMock.concat("a", "b")).thenReturn("ab");
		Mockito.when(stringUtilMock.concat("aa", "bb")).thenReturn("aabb");
		
		// Checking how the mock actually works
		assertEquals(stringUtilMock.concat("a", "b"), "ab");
		assertEquals(stringUtilMock.concat("aa", "bb"), "aabb");
	}
}
