package lab5;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;

/*
This is the file that will generate the keys, publish the public key encrypt and decrypt the message. Notice the package statement!
NOTE: You must use try/catch to handle all of the exceptions possible. You may not use catch(Exxception e) or throws Exception on the method headers for the final version.
My suggestion is to get the class to work by putting throws Exception on the method headers, and then come back later and handle the exceptions.

NOTE: You will need the following imports java.io.*, java.security.*, java.util.*, and javax.crypto.
 */

public class CSCD437Crypto {

    private KeyPairGenerator keyPairGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private Signature sign;

    public CSCD437Crypto(final String signatureAglo, final String keyPairAlgo, final int keySize) {
        this.generateKeys(signatureAglo, keyPairAlgo, keySize);
    }


    public void generateKeys(final String signatureAlgo, final String keyPairAlgo, final int keySize) {
        try {
            this.sign = Signature.getInstance(signatureAlgo);
            this.keyPairGen = KeyPairGenerator.getInstance(keyPairAlgo);
            this.keyPairGen.initialize(keySize);
            this.pair = this.keyPairGen.genKeyPair();
            this.privateKey = this.pair.getPrivate();
            this.publicKey = this.pair.getPublic();
        } catch (final NoSuchAlgorithmException error) {
            error.printStackTrace();// TODO: hand exception
        }
    }

    public void publishPublicKey(final String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.publicKey);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (final IOException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static PublicKey getPublicKey(final String filename) {
        PublicKey publicKey = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            publicKey = (PublicKey) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (final ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return publicKey;
    }

    public void encrypt(final PublicKey publicKey, final String transformation,
                        final String message, final String encryptedFilename) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = message.getBytes();
            byte[] cipherBytes = cipher.doFinal(bytes);
            FileOutputStream fileOutputStream = new FileOutputStream(encryptedFilename);
            fileOutputStream.write(cipherBytes);
            fileOutputStream.close();
        } catch (final NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException | IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void encrypt(final PublicKey publicKey, final String transformation,
                        final File messageFile, final String encryptedFilename) {
        try {
            Scanner messageFileScanner = new Scanner(messageFile);
            StringBuilder stringBuilder = new StringBuilder();
            while (messageFileScanner.hasNext()) {
                stringBuilder.append(messageFileScanner.nextLine());
                stringBuilder.append("\n");
            }
            this.encrypt(publicKey, transformation, stringBuilder.toString(), encryptedFilename);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void decrypt(final String filename, final String transformation) {
        File file = new File(filename);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
            byte[] decryptBytes = cipher.doFinal(bytes);
            System.out.println(new String(decryptBytes));
        } catch (final IOException | NoSuchPaddingException | NoSuchAlgorithmException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

}
