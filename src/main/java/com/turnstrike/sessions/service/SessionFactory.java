package com.turnstrike.sessions.service;

import com.turnstrike.sessions.repository.model.SessionEntity;
import com.turnstrike.sessions.rest.model.Session;

public interface SessionFactory {

	SessionEntity createSessionEntity(String ownerId, Session session);
}
