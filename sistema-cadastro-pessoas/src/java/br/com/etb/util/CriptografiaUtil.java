package br.com.etb.util;

import org.mindrot.bcrypt.BCrypt;

/**
 *
 * @author Aline
 */
public class CriptografiaUtil {
    // Gerar hashda senha
    public static String gerarHash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
    // Verificar senha
    public static boolean verificarSenha(String senha, String hash) {
        return BCrypt.checkpw(senha, hash);
    }
}
