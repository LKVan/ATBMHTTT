package support;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class CKDT {

	public static String[] createSignature(String data)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
		// Thêm Bouncy Castle Provider
		Security.addProvider(new BouncyCastleProvider());

		String[] result = new String[3];
		// Khởi tạo khóa công khai và khóa bí mật bạn cần phải lấy từ cặp khóa RSA
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
		keyGen.initialize(1024);
		KeyPair keyPair = keyGen.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		// Ký băm dữ liệu bằng khóa bí mật
		Signature signature = Signature.getInstance("SHA256withRSA", "BC");
		signature.initSign(privateKey);
		signature.update(data.getBytes());
		byte[] digitalSignature = signature.sign();

		result[0] = Base64.getEncoder().encodeToString(digitalSignature);
		result[1] = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		result[2] = Base64.getEncoder().encodeToString(privateKey.getEncoded());

		return result;
	}

	public static boolean verifySignature(String signatureString, String publicKeyString, String signatureData) {
		// Thêm Bouncy Castle Provider
		Security.addProvider(new BouncyCastleProvider());
		try {
			byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
			byte[] signatureBytes = Base64.getDecoder().decode(signatureString);
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
			PublicKey publicKey = keyFactory.generatePublic(keySpec);

			Signature signature = Signature.getInstance("SHA256withRSA");
			signature.initVerify(publicKey);
			signature.update(signatureData.getBytes());

			return signature.verify(signatureBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static PrivateKey encodePVKey(String input) throws InvalidKeySpecException, NoSuchAlgorithmException {
		byte[] privateKeyBytes = Base64.getDecoder().decode(input);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(keySpec);
	}

	public static void main(String[] args)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeySpecException {
		// TODO Auto-generated method stub
//		CKDT test = new CKDT();
//		String[] a = test.createSignature("Le Khanh Van");
//		System.out.println(test.verifySignature(a[0], a[1], "Le Khanh Van"));
		//System.out.println(test.encodePVKey(a[2]));
		

	}

}
