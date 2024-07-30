# Sessions Service

This service manages sessions for turnstrike. 
It's needed so that there can be a session browser, session creation and session joining by providing a database of available sessions to join.
It uses Keycloak Robolock for authentication and authorization.

# Usage

Deploy this container with the following environment variables set:
- database_password={yourPassword}
- database_url=jdbc:mariadb://{YourDatabaseManagementSystem}:3306/{yourDatabase}
- database_username={username}
- domain_base={subdomain}.{domain}.{topleveldomain}/api
- domain_prefix=http(s)://
