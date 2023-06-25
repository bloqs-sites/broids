package pt.epcc.alunos.al220007.bloqs.core;

import androidx.annotation.NonNull;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import pt.epcc.alunos.al220007.bloqs.models.user.User;

public class Password {
private final static int ITERATIONS = 65536;
private final static int KEY_LEN = 128;
private final static int SALT_LEN = 72;
private final static String ALGO = "PBKDF2WithHmacSHA1";

public static boolean verify(@NonNull String raw, @NonNull User user) {
	byte[] salt = user.getSalt();
	if (salt == null) {
		return raw.equals(user.getPass());
	}
	return Arrays.equals(generateHash(raw, salt), user.getPass().getBytes());
}

public static User generate(@NonNull String raw, @NonNull User user) {
	SecureRandom random = new SecureRandom();
	byte[] salt = new byte[SALT_LEN];
	random.nextBytes(salt);
	byte[] hash = generateHash(raw, salt);
	user.setPass(Arrays.toString(hash));
	user.setSalt(salt);
	return user;
}

private static byte[] generateHash(String raw, byte[] salt) {
	KeySpec spec = new PBEKeySpec(raw.toCharArray(), salt, ITERATIONS, KEY_LEN);
	SecretKeyFactory factory;

	try {
		factory = SecretKeyFactory.getInstance(ALGO);
	} catch (NoSuchAlgorithmException e) {
		throw new RuntimeException(e);
	}
	try {
		return factory.generateSecret(spec).getEncoded();
	} catch (InvalidKeySpecException e) {
		throw new RuntimeException(e);
	}
}
}
