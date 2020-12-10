package problems_pg2.p59;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class P59 {

	/*
	 * Each character on a computer is assigned a unique code and the preferred
	 * standard is ASCII (American Standard Code for Information Interchange).
	 * For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
	 * 
	 * A modern encryption method is to take a text file, convert the bytes to
	 * ASCII, then XOR each byte with a given value, taken from a secret key.
	 * The advantage with the XOR function is that using the same encryption key
	 * on the cipher text, restores the plain text; for example, 65 XOR 42 =
	 * 107, then 107 XOR 42 = 65.
	 * 
	 * For unbreakable encryption, the key is the same length as the plain text
	 * message, and the key is made up of random bytes. The user would keep the
	 * encrypted message and the encryption key in different locations, and
	 * without both "halves", it is impossible to decrypt the message.
	 * 
	 * Unfortunately, this method is impractical for most users, so the modified
	 * method is to use a password as a key. If the password is shorter than the
	 * message, which is likely, the key is repeated cyclically throughout the
	 * message. The balance for this method is using a sufficiently long
	 * password key for security, but short enough to be memorable.
	 * 
	 * Your task has been made easy, as the encryption key consists of three
	 * lower case characters. Using cipher.txt, a file containing the encrypted
	 * ASCII codes, and the knowledge that the plain text must contain common
	 * English words, decrypt the message and find the sum of the ASCII values
	 * in the original text.
	 */
	public static void main(String[] args) throws IOException {
		// [103, 111, 100]
		byte[] cipherData = readCipherBytes();
		byte[] keyGuess = {103, 111, 100};
		System.out.println(Arrays.toString(cipherData));
		System.out.println();
		
//		Map<Byte, Integer> histogram = createHistogram(cipherData, 3, 0);
//		printHistogram(histogram);
		
		String decrypted = cipher(cipherData, keyGuess);
		System.out.println(decrypted);
		
		int sum = 0;
		for(char c : decrypted.toCharArray()) {
			sum += c;
		}
		
		System.out.println(sum);
		
//		byte[] cyclicalData = getCyclicalBytes(cipherData, 1, 3);
//		for(byte b = 'a'; b <= 'z'; b++) {
//			System.out.println((char)b + ": " + cipher(cyclicalData, new byte[]{b}));
//		}
	}
	
	private static byte[] getCyclicalBytes(byte[] input, int start, int interval) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(input.length/interval + 1);
		for(int i = start; i < input.length; i += interval) {
			baos.write(input[i]);
		}
		return baos.toByteArray();
	}
	
	private static Map<Byte, Integer> createHistogram(byte[] cipherData, int keyLength, int keyIndex) {
		Map<Byte, Integer> result = new TreeMap<Byte, Integer>();
		for(int i = keyIndex; i < cipherData.length; i += keyLength) {
			Integer count = result.get(cipherData[i]);
			if (count == null) count = 0;
			result.put(cipherData[i], count+1);
		}
		return result;
	}
	
	private static void printHistogram(Map<Byte, Integer> histogram) {
		Byte last = null;
		for(Entry<Byte, Integer> entry : histogram.entrySet()) {
			byte b = entry.getKey().byteValue();
			if (last != null && b != (last + 1)) {
				System.out.println();
			}
			last = b;
				
			StringBuilder sb = new StringBuilder();
			sb.append(b);
			sb.append("\t| ");
			for(int i = entry.getValue(); i > 0; i--) {
				sb.append('*');
			}
			System.out.println(sb.toString());
		}
		System.out.println();
	}
	
	private static String cipher(byte[] cipherData, byte[] key) {
		StringBuilder sb = new StringBuilder();
		
		int j = 0;
		for(int i = 0; i < cipherData.length; i++) {
			int a = cipherData[i];
			if (a < 0) a += 256;
			int b = key[j];
			if (b < 0) b += 256;
			
			int c = a ^ b;
			if (c > Byte.MAX_VALUE) c -= 256;

			sb.append((char)c);
			
			if (++j == key.length) j = 0;
		}
		
		return sb.toString();
	}

	private static byte[] readCipherBytes() throws IOException {
		InputStream cipherStream = P59.class.getResourceAsStream("p059_cipher.txt");
		try {
			return readBytesFrom(cipherStream);
		} finally {
			cipherStream.close();
		}
	}
	
	private static byte[] readBytesFrom(InputStream in) {
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		Scanner s = new Scanner(br);
		try {
			s.useDelimiter("[\\s,]");
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			while (s.hasNext()) {
				baos.write(s.nextByte());
			}
			
			return baos.toByteArray();
		} finally {
			s.close();
		}
	}
}
