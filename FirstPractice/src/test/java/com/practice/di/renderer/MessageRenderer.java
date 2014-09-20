package com.practice.di.renderer;

import com.practice.di.provider.MessageProvider;

public interface MessageRenderer {
	public void render();
	public void setMessageProvider(MessageProvider messageProvider);
}