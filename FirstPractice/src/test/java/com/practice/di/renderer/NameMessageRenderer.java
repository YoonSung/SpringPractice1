package com.practice.di.renderer;

import com.practice.di.provider.MessageProvider;

public class NameMessageRenderer implements MessageRenderer{
	
	private MessageProvider messageProvider;
	private String name;
	
	public NameMessageRenderer(String name) {
		this.name = name;
	}
	
	@Override
	public void render() {
		System.out.println(name + " "+ messageProvider.getMessage());
	}

	@Override
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider; 
	}

}
