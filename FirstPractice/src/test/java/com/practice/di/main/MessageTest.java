package com.practice.di.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.practice.di.provider.HiWorldMessageProvider;
import com.practice.di.provider.MessageProvider;
import com.practice.di.renderer.DefaultMessageRenderer;
import com.practice.di.renderer.MessageRenderer;

public class MessageTest {

	public static void main(String[] args) {
		//codeLevelDI();
		springXMLLevelDI();
	}

	public static void springXMLLevelDI() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/di.xml");
		MessageRenderer messageRenderer = context.getBean("messageRenderer", MessageRenderer.class);
		messageRenderer.render();
		
		context.close();
	}

	public static void codeLevelDI() {
		MessageProvider messageProvider = new HiWorldMessageProvider();

		MessageRenderer messageRenderer = new DefaultMessageRenderer();
		messageRenderer.setMessageProvider(messageProvider);

		messageRenderer.render();
	}
}
