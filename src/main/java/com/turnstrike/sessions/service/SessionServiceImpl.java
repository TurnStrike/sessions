package com.turnstrike.sessions.service;

import java.util.List;
import java.util.Optional;

import com.turnstrike.sessions.repository.SessionRepository;
import com.turnstrike.sessions.repository.model.SessionEntity;
import com.turnstrike.sessions.rest.model.Session;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

	private final SessionFactory sessionFactory;

	private final SessionRepository sessionRepository;

	private final ModelMapper modelMapper;

	public Session createSession(String userId, Session session) {
		if (sessionRepository.existsByOwnerId(session.getOwnerId())) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(403));
		}
		SessionEntity sessionEntity = sessionFactory.createSessionEntity(userId, session);
		SessionEntity savedSessionEntity = sessionRepository.save(sessionEntity);
		return modelMapper.map(savedSessionEntity, Session.class);
	}

	public List<Session> getSessions() {
		List<SessionEntity> sessionEntities = sessionRepository.findAll();
		return sessionEntities.stream().map(session -> modelMapper.map(savedSessionEntity, Session.class)).toList();
	}

	public Session updateSession(String userId, Session sessionUpdate) {
		SessionEntity sessionEntity = findSessionByOwnerAndSessionID(userId, sessionUpdate.getSessionId());
		SessionEntity sessionEntityUpdate = sessionFactory.createSessionEntity(sessionUpdate.getOwnerId(), sessionUpdate);
		SessionEntity mergedSessionEntity = mergeIntoSessionEntity(sessionEntity, sessionEntityUpdate);
		SessionEntity updatedSessionentity = sessionRepository.save(mergedSessionEntity);
		return modelMapper.map(updatedSessionentity, Session.class);
	}

	public Session deleteSession(String userId, Long sessionId) {
		SessionEntity sessionEntity = findSessionByOwnerAndSessionID(userId, sessionId);
		SessionEntity deletedSession = sessionRepository.delete(sessionEntity);
		return modelMapper.map(deletedSession, Session.class);
	}

	private SessionEntity findSessionByOwnerAndSessionID(String userId, Long sessionId) throws ResponseStatusException {
		Optional<SessionEntity> sessionEntityOptional = sessionRepository.findById(sessionId);
		if (sessionEntityOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(404));
		}

		SessionEntity sessionEntity = sessionEntityOptional.get();
		if (!sessionEntity.getOwnerId().equals(userId)) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(403));
		}
		return sessionEntity;
	}

	private SessionEntity mergeIntoSessionEntity(SessionEntity sessionEntity, SessionEntity session) {
		SessionEntity mergedSessionEntity = new SessionEntity();
		mergedSessionEntity.setId(sessionEntity.getId());
		mergedSessionEntity.setOwnerId(session.getOwnerId());
		mergedSessionEntity.setPlayerCount(session.getPlayerCount());
		mergedSessionEntity.setMaxPlayerCount(session.getMaxPlayerCount());
		return mergedSessionEntity;
	}
}
