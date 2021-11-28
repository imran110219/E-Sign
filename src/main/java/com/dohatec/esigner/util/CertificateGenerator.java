package com.dohatec.esigner.util;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * @author Sadman
 */
public class CertificateGenerator {

    private static final String keyStoreType = "PKCS12";
    private static final String keyAlgorithm = "RSA";
    private static final String signatureAlgorithm = "SHA256withRSA";
    private static final int keysize = 1024;

    public CertAndKeyGen generateCertAndKeyGen() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        CertAndKeyGen keypair = new CertAndKeyGen(keyAlgorithm, signatureAlgorithm, null);
        keypair.generate(keysize);
        return keypair;
    }

    public X509Certificate generateCertificate(CertAndKeyGen keypair, X500Name x500Name, long validity) throws IOException, InvalidKeyException, NoSuchAlgorithmException, CertificateException, SignatureException, NoSuchProviderException {
        return keypair.getSelfCertificate(x500Name, new Date(), validity * 24 * 60 * 60);
    }

    public PrivateKey generatePrivateKey(CertAndKeyGen keypair) {
        return keypair.getPrivateKey();
    }
}
