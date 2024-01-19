package support;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashOrder {
	// mã hóa SHA (sử dụng SHA-256)
		public static String SHA(String data) throws NoSuchAlgorithmException {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] output = md.digest(data.getBytes());
			BigInteger num = new BigInteger(1, output);
			String result = num.toString(16);
			while (result.length() < 64) {
				result = "0" + result;
			}
			return result;
		}
		public static boolean confirm(String data,String hash) throws NoSuchAlgorithmException {
			return SHA(data).equals(hash);
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
