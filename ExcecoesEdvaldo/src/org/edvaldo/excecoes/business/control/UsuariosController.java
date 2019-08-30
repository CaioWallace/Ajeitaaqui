package org.edvaldo.excecoes.business.control;

import org.edvaldo.excecoes.business.model.Usuario;
import org.edvaldo.excecoes.business.model.execoes.NaoFoiPossivelInicializarArquivoException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioExistenteException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioInexistenteException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioInvalidoException;
import org.edvaldo.excecoes.infra.UsuarioDao;

public class UsuariosController {

	public void adicionarUsuario(Usuario novoUsuario) throws UsuarioInvalidoException,
		NaoFoiPossivelInicializarArquivoException, UsuarioExistenteException{
		
		validarUsuario(novoUsuario);
		
		UsuarioDao uDao = new UsuarioDao();
		uDao.addUsuario(novoUsuario);
	}
	
	private void validarUsuario(Usuario novoUsuario) throws UsuarioInvalidoException {
		
		//validando login
		if (novoUsuario.getLogin() == null || novoUsuario.getLogin().isEmpty()) {
			throw new UsuarioInvalidoException("Login não pode ser vazio.");
		}
		
		if (novoUsuario.getLogin().length() > 12) {
			throw new UsuarioInvalidoException("Login não pode ser maior que 12 caracteres.");
		}
		
		if (novoUsuario.getLogin().matches(".*\\d.*")) {
			throw new UsuarioInvalidoException("Login não pode conter número.");
		}
		
		//validando senha
		if (novoUsuario.getSenha() == null || novoUsuario.getSenha().isEmpty()) {
			throw new UsuarioInvalidoException("Senha não pode ser vazia.");
		}
		
		if (novoUsuario.getSenha().length() > 16 || novoUsuario.getSenha().length() < 8) {
			throw new UsuarioInvalidoException("Senha deve ter entre 8 e 16 caracteres.");
		}
		
		if (!novoUsuario.getSenha().matches("[a-zA-Z0-9]+")) {
			throw new UsuarioInvalidoException("Senha deve possuir apenas letras e números");
		}
		
		if (!novoUsuario.getSenha().matches(".*\\d.*\\d.*")) {
			throw new UsuarioInvalidoException("Senha deve possuir ao menos dois números.");
		}
	}

	public void removerUsuario(String login) throws NaoFoiPossivelInicializarArquivoException, UsuarioInexistenteException {
		UsuarioDao uDao = new UsuarioDao();
		Usuario aRemover = new Usuario(login, "");
		uDao.removerUsuario(aRemover);
	}
}
