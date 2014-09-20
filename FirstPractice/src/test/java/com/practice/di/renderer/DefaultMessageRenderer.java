package com.practice.di.renderer;

import com.practice.di.provider.MessageProvider;

public class DefaultMessageRenderer implements MessageRenderer{
	
	private MessageProvider messageProvider;
	
	@Override
	public void render() {
		System.out.println(messageProvider.getMessage());
	}

	@Override
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider; 
	}

}
