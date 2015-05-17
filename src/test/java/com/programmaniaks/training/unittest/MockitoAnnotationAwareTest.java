package com.programmaniaks.training.unittest;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class MockitoAnnotationAwareTest {
	
	@Before
	public void initMockito() {
		MockitoAnnotations.initMocks(this);
	}

}
