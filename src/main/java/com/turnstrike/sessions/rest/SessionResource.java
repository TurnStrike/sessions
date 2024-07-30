package com.turnstrike.sessions.rest;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frameboter.rest.AbstractResource;
import com.turnstrike.sessions.rest.model.Ping;
import com.turnstrike.sessions.rest.model.Session;
import com.turnstrike.sessions.service.SessionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
@Log4j2
public class SessionResource extends AbstractResource {

	private final SessionService sessionService;

	// @formatter:off
    @Operation(summary = "Creates a Session ", description = "Creates a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session created successfully"),
            @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content)
    })
    @PostMapping()
    @ResponseBody
    Session createSession(@AuthenticationPrincipal Jwt jwt, @RequestBody Session session) {
        // @formatter:on
		String userId = getUserId(jwt);
		log.info("Started createSession for user={} with session={}", userId, session);
		Session result = sessionService.createSession(userId, session);
		log.info("Finished createSession for user={} with result={}", userId, result);
		return result;
	}

	// @formatter:off
    @Operation(summary = "Retrieves all Sessions", description = "Retrieves all sessions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sessions retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content)
    })
    @GetMapping()
    @ResponseBody
    List<Session> getSessions(@AuthenticationPrincipal Jwt jwt) {
        // @formatter:on
		String userId = getUserId(jwt);
		log.info("Started getSessions for user={}", userId);
		List<Session> result = sessionService.getSessions();
		log.info("Finished getSessions for user={} with result={}", userId, result);
		return result;
	}

	// @formatter:off
    @Operation(summary = "Updates a Session", description = "Updates a session if the logged in user is the owner")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Session updated successfully"),
        @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content),
        @ApiResponse(responseCode = "403", description = "User is not the owner of the session", content = @Content),
        @ApiResponse(responseCode = "404", description = "Session not found", content = @Content)
    })
    @PutMapping()
    @ResponseBody
    Session updateSession(@AuthenticationPrincipal Jwt jwt, @RequestBody Session session) {
        // @formatter:on
		String userId = getUserId(jwt);
		log.info("Started updateSession for user={}", userId);
		Session result = sessionService.updateSession(userId, session);
		log.info("Finished updateSession for user={} with result={}", userId, result);
		return result;
	}

	// @formatter:off
	@Operation(summary = "Deletes a Session", description = "Deletes a session")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Session updated successfully"),
		@ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content),
		@ApiResponse(responseCode = "403", description = "User is not the owner of the session", content = @Content),
		@ApiResponse(responseCode = "404", description = "Session not found", content = @Content)
	})
	@DeleteMapping("/{sessionId}")
	@ResponseBody
	Session deleteSession(@AuthenticationPrincipal Jwt jwt, @PathVariable("sessionId") Long sessionId) {
		// @formatter:on
		String userId = getUserId(jwt);
		log.info("Started deleteSession for user={} with sessionId={}", userId, sessionId);
		Session result = sessionService.deleteSession(userId, sessionId);
		log.info("Finished deleteSession for user={} with result={}", userId, result);
		return result;
	}
}
