package com.turnstrike.sessions.rest.model;

@Data
public class Session {

	private Long sessionId;

	private String ownerId;

	private Long playerCount;

	private Long maxPlayerCount;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Long getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(Long playerCount) {
		this.playerCount = playerCount;
	}

	public Long getMaxPlayerCount() {
		return maxPlayerCount;
	}

	public void setMaxPlayerCount(Long maxPlayerCount) {
		this.maxPlayerCount = maxPlayerCount;
	}
}
