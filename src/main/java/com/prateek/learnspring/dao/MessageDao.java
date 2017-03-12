package com.prateek.learnspring.dao;

import java.util.List;

import com.prateek.learnspring.exceptions.DalException;
import com.prateek.learnspring.model.Message;
import com.prateek.learnspring.model.UserMessage;

public interface MessageDao {
	
	public void addMessage(Message message) throws DalException;
	
	public Message getMessage(String messageId) throws DalException;
	
	public List<UserMessage> getMessageFromId(String toId, String fromId, int limit) throws DalException;
	
	public List<UserMessage> getAllMessages(String toId, int from ,int to) throws DalException;
	
}
