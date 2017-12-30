package com.example.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pgp")
public class PGPConfigProperties {

	@Value("${pgp.publicKeyFile}")
	private String publicKeyFilePath;

	@Value("${pgp.secretKeyFile}")
	private String secretKeyFilePath;

	@Value("${pgp.passphrase}")
	private String passphrase;

	private boolean asciiArmored;

	private boolean integrityCheck;

	public String getPublicKeyFilePath() {
		return publicKeyFilePath;
	}

	public void setPublicKeyFilePath(String publicKeyFilePath) {
		this.publicKeyFilePath = publicKeyFilePath;
	}

	public String getSecretKeyFilePath() {
		return secretKeyFilePath;
	}

	public void setSecretKeyFilePath(String secretKeyFilePath) {
		this.secretKeyFilePath = secretKeyFilePath;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public boolean isAsciiArmored() {
		return asciiArmored;
	}

	public void setAsciiArmored(boolean asciiArmored) {
		this.asciiArmored = asciiArmored;
	}

	public boolean isIntegrityCheck() {
		return integrityCheck;
	}

	public void setIntegrityCheck(boolean integrityCheck) {
		this.integrityCheck = integrityCheck;
	}

	@Override
	public String toString() {
		return "PGPConfigProperties [publicKeyFilePath=" + publicKeyFilePath + ", secretKeyFilePath="
				+ secretKeyFilePath + ", passphrase=" + passphrase + ", asciiArmored=" + asciiArmored
				+ ", integrityCheck=" + integrityCheck + "]";
	}

}
