package com.turnstrike.sessions.repository.model;
@Getter
@Setter
@Entity
@Table(name = "session")
public class SessionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique= true )
	private Long id;

	@Column(name = "owner_id", nullable = false, unique = true)
	private String ownerId;

	@Column(name = "player_count", nullable = false, unique = false)
	private Long playerCount;

	@Column(name = "max_player_count", nullable = false, unique = false)
	private Long maxPlayerCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
