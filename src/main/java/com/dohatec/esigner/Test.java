package com.dohatec.esigner;


import com.dohatec.esigner.util.CertificateGenerator;
import com.dohatec.esigner.util.JavaKeyStore;
//import sun.security.tools.keytool.CertAndKeyGen;
//import sun.security.x509.X500Name;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Test {
    private static final String commonName = "Md Amin";
    private static final String organizationalUnit = "IT";
    private static final String organization = "Dohatec";
    private static final String city = "Dhaka";
    private static final String state = "Dhaka";
    private static final String country = "BD";
    private static final long validity = 720; // 2 years
    private static final String alias = "entry1";
    private static final String keyPass = "123456";

    public static void main(String[] args) throws Exception {
        JavaKeyStore javaKeyStore = new JavaKeyStore("PKCS12", "123456", "JavaKeyStore.jks");
//        javaKeyStore.createEmptyKeyStore();
        javaKeyStore.loadKeyStore();

//        X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);
//
//        CertificateGenerator certificateGenerator = new CertificateGenerator();
//        CertAndKeyGen keypair = certificateGenerator.generateCertAndKeyGen();
//        X509Certificate certificate = certificateGenerator.generateCertificate(keypair, x500Name, validity);
//        PrivateKey privateKey = certificateGenerator.generatePrivateKey(keypair);
//        javaKeyStore.setKeyEntry(alias,privateKey,keyPass, new Certificate[]{certificate});
//
//        X500Name x500Name1 = new X500Name("Sadman Sobhan", "Engineering", "Dohatec", "Dhaka", "Dhaka", "BD");
//        CertAndKeyGen keypair1 = certificateGenerator.generateCertAndKeyGen();
//        X509Certificate certificate1 = certificateGenerator.generateCertificate(keypair1, x500Name1, validity);
//        PrivateKey privateKey1 = certificateGenerator.generatePrivateKey(keypair);
//
//        javaKeyStore.setKeyEntry("entry2",privateKey1,keyPass, new Certificate[]{certificate1});
//
//        javaKeyStore.saveKeyStore();

//        for (Certificate cert : javaKeyStore.getCertificateList()) {
//            X509Certificate x509Certificate = (X509Certificate) cert;
//            System.out.println(cert);
//            String cn = ((X500Name) x509Certificate.getSubjectDN()).getCommonName();
//            String on = ((X500Name) x509Certificate.getSubjectDN()).getOrganization();
//            String date = x509Certificate.getNotAfter().toString();
//            System.out.println(cn + "     " + on + "     " + date);
//        }
//
//        System.out.println(javaKeyStore.getCertificateList().size());
//        System.out.println(javaKeyStore.getPrivateKeyList("123456").size());
//
//        for (PrivateKey key : javaKeyStore.getPrivateKeyList("123456")) {
//            String cn = key.toString();
//            System.out.println(cn);
//        }
//        System.out.println(javaKeyStore.getCertificate("entry1"));
    }
}
