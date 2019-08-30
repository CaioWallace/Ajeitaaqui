package org.edvaldo.excecoes.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.edvaldo.excecoes.business.control.UsuariosController;
import org.edvaldo.excecoes.business.model.Usuario;
import org.edvaldo.excecoes.business.model.execoes.NaoFoiPossivelInicializarArquivoException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioExistenteException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioInexistenteException;
import org.edvaldo.excecoes.business.model.execoes.UsuarioInvalidoException;

/**
 * Re
 * @author Edvaldo
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Bem-Vindo ao Sistema de Usuários");
		System.out.println("");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		String entrada = "";
		String login = "";
		String senha = "";
		
		int entradaInteira = 0;
		
		UsuariosController controller = new UsuariosController();
		
		while (true) {
		
			System.out.println("");
			System.out.println("Digite 1 para adicionar usuário");
			System.out.println("Digite 2 para remover usuário");
			System.out.println("Digite 3 para sair");
			
			try {
				entrada = reader.readLine();
				
				entradaInteira = Integer.parseInt(entrada);
			} catch (IOException e) {
				System.out.println("Falha ao ler entrada de dados.");
				break;
			} catch (NumberFormatException e) {
				System.out.println("Opção inválida.");
			}
			
			if (entradaInteira == 1) {
				System.out.print("Digite o login: ");
				
				try {
					login = reader.readLine();
				} catch (IOException e) {
					System.out.println("Falha ao ler entrada de dados.");
					break;
				}
				
				System.out.print("Digite a senha: ");
				
				try {
					senha = reader.readLine();
				} catch (IOException e) {
					System.out.println("Falha ao ler entrada de dados.");
					break;
				}
				
				Usuario novoUsuario = new Usuario(login, senha);
				
				try {
					controller.adicionarUsuario(novoUsuario);
					System.out.println("Usuário adicionado com sucesso");
				} catch (UsuarioInvalidoException | NaoFoiPossivelInicializarArquivoException
						| UsuarioExistenteException e) {
					System.out.println(e.getMessage());
				}
				
			} else if (entradaInteira == 2) {
				
				System.out.print("Digite o login: ");
				
				try {
					login = reader.readLine();
				} catch (IOException e) {
					System.out.println("Falha ao ler entrada de dados.");
					break;
				}
				
				try {
					controller.removerUsuario(login);
					System.out.println("Usuário removido com sucesso");
				} catch (NaoFoiPossivelInicializarArquivoException | UsuarioInexistenteException e) {
					System.out.println(e.getMessage());
				}
				
			} else if (entradaInteira == 3) {
				System.out.println("Até breve.");
				break;
			}
		}
	}
}
