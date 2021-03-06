package de.mediapool.core.exceptions;

public enum ExeptionErrorCode {

	// Databaseerror 1000 - 1999
	DB_READ(1000, "Konnte nicht auf Datenbank lesen."), DB_INSERT(1100, "Konnte neuen Datensatz nicht erzeugen."), DB_UPDATE(1200, "Konnte vorhandenen Datensatz nicht aktualisieren."), DB_DELETE(
			1300, "Konnte vorhandenen Datensatz nicht löschen."), DB_COMMIT(1300, "Konnte Commit nicht ausführen."),

	// Auto Instance Creator
	MANAGER_CREATE(2000, "Es konnte keine neue Instanz erstellt werden."),

	// Authfehler
	AUTH_LOGIN(3000, "Fehler beim Authentifizieren: Benutzer oder Passwort falsch"), AUTH_LOGIN_CACHE(3001, "Fehler beim Authentifizieren: Unzureichende Identifizierung."),

	// BOError
	BO_INIT(10000, "Fehler beim Initialisieren des BOs. Unzureichende Daten."), BO_VALIDATION(10001, "Die Validierung für die ausgefühere Funktion konnte nicht erfolgreich durchgeführt werden."),

	// EntityDefinitions
	ENTITY_TYPE_NO_TYPE_DEF(20000, "Der angegebene Entity-Type existiert nicht."),

	// Technical Errors
	T_NULL_REFERENCE(30000, "Der übergebene Parameter darf nicht NULL sein!"), T_ARRAY_LENGTH(30001, "Die länge des Arrays ist nicht korrekt.");

	private int errorCode = -1;

	private String message = "";

	private ExeptionErrorCode(int errorCode, String message) {
		this.setErrorCode(errorCode);
		this.setMessage(message);
	}

	private void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	private void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
