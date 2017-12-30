package com.example.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.stream.Collectors;

import org.bouncycastle.openpgp.PGPException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.configurations.FilesConfigProperties;
import com.example.configurations.PGPConfigProperties;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class PGPManagerTest {

	private static Logger logger = LoggerFactory.getLogger(PGPManagerTest.class);

	@Autowired
	PGPManager pgpManager;

	@Autowired
	PGPConfigProperties pgpConfigProperties;

	@Autowired
	FilesConfigProperties filesConfigProperties;

	@Test
	public void testA_encryptTest()
			throws NoSuchProviderException, FileNotFoundException, IOException, PGPException, InterruptedException {

		logger.info("encryptTest()");

		pgpManager.encryptFile(filesConfigProperties.getOriginalFilePath(),
				new FileInputStream(pgpConfigProperties.getPublicKeyFilePath()),
				new FileOutputStream(filesConfigProperties.getEncryptFilePath()), pgpConfigProperties.isAsciiArmored(),
				pgpConfigProperties.isIntegrityCheck());

		assertEquals((new File(filesConfigProperties.getEncryptFilePath())).exists(), true);

		List<String> encryptFileContent = Files.readAllLines(Paths.get(filesConfigProperties.getEncryptFilePath()),
				StandardCharsets.ISO_8859_1);
		assertFalse(encryptFileContent.containsAll(
				Files.lines(Paths.get(filesConfigProperties.getOriginalFilePath())).collect(Collectors.toList())));
	}

	@Test
	public void testB_decryptTest()
			throws NoSuchProviderException, FileNotFoundException, IOException, PGPException, InterruptedException {

		logger.info("decryptTest()");

		pgpManager.decryptFile(new FileInputStream(filesConfigProperties.getEncryptFilePath()),
				new FileOutputStream(filesConfigProperties.getDecryptFilePath()),
				new FileInputStream(pgpConfigProperties.getSecretKeyFilePath()), pgpConfigProperties.getPassphrase());

		assertEquals((new File(filesConfigProperties.getDecryptFilePath())).exists(), true);

		List<String> fileContent = Files.readAllLines(Paths.get(filesConfigProperties.getDecryptFilePath()));
		assertTrue(fileContent.containsAll(Files.readAllLines(Paths.get(filesConfigProperties.getDecryptFilePath()))));

	}

}
