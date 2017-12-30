package com.example.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "files")
public class FilesConfigProperties {

	@Value("${files.original}")
	private String originalFilePath;

	@Value("${files.encrypt}")
	private String encryptFilePath;

	@Value("${files.decrypt}")
	private String decryptFilePath;

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getEncryptFilePath() {
		return encryptFilePath;
	}

	public void setEncryptFilePath(String encryptFilePath) {
		this.encryptFilePath = encryptFilePath;
	}

	public String getDecryptFilePath() {
		return decryptFilePath;
	}

	public void setDecryptFilePath(String decryptFilePath) {
		this.decryptFilePath = decryptFilePath;
	}

	@Override
	public String toString() {
		return "FilesConfigProperties [originalFilePath=" + originalFilePath + ", encryptFilePath=" + encryptFilePath
				+ ", decryptFilePath=" + decryptFilePath + "]";
	}

}
