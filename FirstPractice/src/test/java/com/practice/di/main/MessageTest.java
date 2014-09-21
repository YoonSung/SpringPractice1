package com.practice.di.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.practice.di.renderer.MessageRenderer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/di.xml")
public class MessageTest {
	
	@Autowired
	MessageRenderer messageRenderer;
	
	@Test
	public void di() {
		messageRenderer.render();
	}
}
