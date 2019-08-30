package org.edvaldo.excecoes.infra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.edvaldo.excecoes.business.model.Usuario;
import org.edvaldo.excecoes.business.model.execoes.NaoFoiPossivelInicializarArquivoException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioExistenteException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioInexistenteException;

/**
 * Essa classe controla a persistência de usuários usando um arquivo binário.
 * 
 * @author Edvaldo
 * 
 */
public class UsuarioDao {

	private final String USUARIOS_NOME_DO_ARQUIVO = "usuarios.data";
	
	public void addUsuario(Usuario novoUsuario) throws NaoFoiPossivelInicializarArquivoException, UsuarioExistenteException {
		criarArquivoBaseCasoNaoExista();
		
		ArrayList<Usuario> usuarios = lerListaDeUsuarios();
		
		if (usuarios.contains(novoUsuario)) {
			throw new UsuarioExistenteException("O usuario " + novoUsuario.getLogin() + " já existe");
		}
		
		usuarios.add(novoUsuario);
		persistirLista(usuarios);
	}
	
	public void removerUsuario(Usuario aRemover) throws NaoFoiPossivelInicializarArquivoException, UsuarioInexistenteException{
		criarArquivoBaseCasoNaoExista();
		
		ArrayList<Usuario> usuarios = lerListaDeUsuarios();
		
		if (!usuarios.contains(aRemover)) {
			throw new UsuarioInexistenteException("O usuario " + aRemover.getLogin() + " não existe.");
		}
		
		usuarios.remove(aRemover);
		persistirLista(usuarios);
	}
	
	private void criarArquivoBaseCasoNaoExista() throws NaoFoiPossivelInicializarArquivoException {
		if (!new File(USUARIOS_NOME_DO_ARQUIVO).exists()) {
			persistirLista(new ArrayList<Usuario>());
		}
	}
	
	private ArrayList<Usuario> lerListaDeUsuarios() throws NaoFoiPossivelInicializarArquivoException {
		Object obj = null;
		
		try(FileInputStream f_in = new FileInputStream(USUARIOS_NOME_DO_ARQUIVO);
			ObjectInputStream obj_in = new ObjectInputStream(f_in)) {
			obj = obj_in.readObject();
		} catch (IOException e) {
			throw new NaoFoiPossivelInicializarArquivoException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new NaoFoiPossivelInicializarArquivoException(e.getMessage());
		}
		
		if (obj == null || !(obj instanceof ArrayList<?>)) {
			throw new NaoFoiPossivelInicializarArquivoException("Arquivo corronpido ou inválido.");
		}
		
		return (ArrayList<Usuario>) obj;
		 
	}
	
	private void persistirLista(ArrayList<Usuario> usuarios) throws NaoFoiPossivelInicializarArquivoException {
		try (FileOutputStream f_out = new FileOutputStream(USUARIOS_NOME_DO_ARQUIVO);
				ObjectOutputStream obj_out = new ObjectOutputStream(f_out)) {
			obj_out.writeObject(usuarios);
		} catch (IOException e) {
			throw new NaoFoiPossivelInicializarArquivoException(e.getMessage()); 
		}
	}
}
