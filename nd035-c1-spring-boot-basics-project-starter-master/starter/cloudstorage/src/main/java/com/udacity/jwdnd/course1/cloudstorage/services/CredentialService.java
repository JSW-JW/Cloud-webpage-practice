package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.auth.EncryptionService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void insert(Credential credential, Integer userId) {
        String password = credential.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encodedPassword = encryptionService.encryptValue(password, encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encodedPassword);
        credential.setUserId(userId);
        credentialMapper.insert(credential);
    }

    public void update(Credential credential) {
        String password = credential.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encodedPassword = encryptionService.encryptValue(password, encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encodedPassword);
        credentialMapper.update(credential);
    }

    public void delete(Integer id) {
        credentialMapper.delete(id);
    }

    public List<Credential> getCredentials(Integer userId) {
        return credentialMapper.findAll(userId);
    }
}
