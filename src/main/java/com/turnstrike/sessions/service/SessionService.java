package com.turnstrike.sessions.service;

import java.util.List;

import com.turnstrike.sessions.rest.model.Session;

public interface SessionService {

	Session createSession(String userId, Session session);

	List<Session> getSessions();

	Session updateSession(String userId, Session session);

	Session deleteSession(String userId, Long sessionId);
}
