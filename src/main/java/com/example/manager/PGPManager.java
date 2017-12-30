package com.example.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;

import org.bouncycastle.openpgp.PGPException;

public interface PGPManager {

	public void encryptFile(String originalFile, FileInputStream keyFile, FileOutputStream encryptFile,
			boolean asciiArmored, boolean integrityCheck) throws NoSuchProviderException, IOException, PGPException;

	public void decryptFile(FileInputStream encryptFile, FileOutputStream dencryptFile, FileInputStream keyFile, String passphrase)
			throws NoSuchProviderException, IOException, PGPException;

}
