package com.dohatec.esigner.util;

import com.dohatec.esigner.model.CertificateInfo;

import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import sun.security.x509.X500Name;

/**
 * @author Sadman
 */
public class Util {
    public static String convertKeyToString(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static Key convertStringToKey(String keyString, String algorithm){
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);
        return key;
    }

    public static String readApplicationProperty(String name) throws IOException {
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream("application.properties")) {
            properties.load(is);
        }
        return properties.getProperty(name);
    }

    public static String getPemPublicKey(String filename, String algorithm) throws Exception {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();

        String temp = new String(keyBytes);
        String publicKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----", "");
        publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
        publicKeyPEM = publicKeyPEM.replace("\n", "");

        return publicKeyPEM;
    }

    public static PrivateKey getPrivateKey(String alias, String password) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException {
        JavaKeyStore javaKeyStore = new JavaKeyStore("PKCS12", "123456", "JavaKeyStore.jks");
        javaKeyStore.loadKeyStore();
        return javaKeyStore.getPrivateKey(alias,password);
    }

    public static List<CertificateInfo> getCertificates() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
        JavaKeyStore javaKeyStore = new JavaKeyStore("PKCS12", "123456", "JavaKeyStore.jks");
        javaKeyStore.loadKeyStore();

        List<CertificateInfo> certificates = new ArrayList<>();
        try {
            Map<String, Certificate> certificateMap = javaKeyStore.getCertificateMap();
            certificateMap.forEach((k, v) -> {
                X509Certificate x509Certificate = (X509Certificate) v;
                String cn = null;
                String on = null;
                try {
                    cn = ((X500Name) x509Certificate.getSubjectDN()).getCommonName();
                    on = ((X500Name) x509Certificate.getSubjectDN()).getOrganization();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String date = x509Certificate.getNotAfter().toString();
                System.out.println(cn + "     " + on + "     " + date);
                List<String> x = new ArrayList<String>();
                CertificateInfo certificateInfo = new CertificateInfo();
                certificateInfo.setAliasName(k);
                certificateInfo.setUserName(cn);
                certificateInfo.setCompanyName(on);
                certificateInfo.setValidDate(date);
                certificates.add(certificateInfo);
            });

        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return certificates;
    }
}
