import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import java.security.spec.KeySpec
import java.util.Base64

private const val SALT = "your_salt_here"

private fun generateKey(password: String): SecretKeySpec {
    val salt = SALT.toByteArray()
    val keySpec: KeySpec = PBEKeySpec(password.toCharArray(), salt, 65536, 256)
    val secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val keyBytes: ByteArray = secretKeyFactory.generateSecret(keySpec).encoded
    return SecretKeySpec(keyBytes, "AES")
}

private fun decryptQrCode(encryptedDataBase64: String, password: String): String? {
    return try {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        val key = generateKey(password)
        val iv = "FY;m2:~jRDQx5eQH".toByteArray() // Use a unique IV for each encryption operation

        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))

        val encryptedBytes = Base64.getDecoder().decode(encryptedDataBase64)
        String(cipher.doFinal(encryptedBytes))
    } catch (e: Exception) {
        null
    }
}

// Usage
val encryptedDataBase64 = "your_encrypted_data_here"
val password = "your_password_here"
val decryptedData = decryptQrCode(encryptedDataBase64, password)
println(decryptedData)
