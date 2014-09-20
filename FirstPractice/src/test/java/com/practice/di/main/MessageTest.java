package com.practice.di.main;

import com.practice.di.provider.HiWorldMessageProvider;
import com.practice.di.provider.MessageProvider;
import com.practice.di.renderer.DefaultMessageRenderer;
import com.practice.di.renderer.MessageRenderer;

public class MessageTest {

	public static void main(String[] args) {
		MessageProvider messageProvider = new HiWorldMessageProvider();

		MessageRenderer messageRenderer = new DefaultMessageRenderer();
		messageRenderer.setMessageProvider(messageProvider);

		messageRenderer.render();
	}
}
