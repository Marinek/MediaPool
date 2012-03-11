package de.mediapool.core.business.authentication.shiro;

import java.util.UUID;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;

public class UniqueUsernameAndPasswortAuth implements AuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	
	private final String currentUser;
	private final String currentPasswort;
	
	private final UUID currentPrincipal;

	public UniqueUsernameAndPasswortAuth(String pUser, String pPasswort) {
		this.currentUser = pUser;
		this.currentPasswort = pPasswort;
		
		currentPrincipal = UUID.randomUUID();
	}
	
	public Object getCredentials() {
		return new UsernamePasswordToken(this.currentUser, currentPasswort).getCredentials();
	}

	public Object getPrincipal() {
		return currentPrincipal;
	}

}
