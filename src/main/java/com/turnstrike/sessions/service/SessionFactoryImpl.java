package com.turnstrike.sessions.service;

import com.turnstrike.sessions.repository.model.SessionEntity;
import com.turnstrike.sessions.rest.model.Session;

@Service
public class SessionFactoryImpl implements SessionFactory {

	private final static Long DEFAULT_MAX_PLAYER_COUNT = 10L;
	private final static Long MAX_MAX_PLAYER_COUNT = 32L;

	public SessionEntity createSessionEntity(String ownerId, Session session){
		SessionEntity sessionEntity = new SessionEntity();
		sessionEntity.setOwnerId(ownerId);
		sessionEntity.setPlayerCount(0L);

		if (session.getMaxPlayerCount() == null){
			sessionEntity.setMaxPlayerCount(DEFAULT_MAX_PLAYER_COUNT);
		} else if (session.getMaxPlayerCount() > MAX_MAX_PLAYER_COUNT) {
			sessionEntity.setMaxPlayerCount(MAX_MAX_PLAYER_COUNT);
		}else if (session.getMaxPlayerCount() <= 1){
			sessionEntity.setMaxPlayerCount(DEFAULT_MAX_PLAYER_COUNT);
		}else {
			sessionEntity.setMaxPlayerCount(session.getMaxPlayerCount());
		}
		return sessionEntity;
	}
}
