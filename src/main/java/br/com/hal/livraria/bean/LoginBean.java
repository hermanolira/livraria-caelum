package br.com.hal.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.hal.livraria.dao.UsuarioDAO;
import br.com.hal.livraria.model.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuarLogin() {
		
		System.out.println("Fazendo login do usuário " + usuario.getEmail());

		boolean existe = new UsuarioDAO().existe(usuario);
		
		if (existe) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			
			return "livro?faces-redirect=true";
		}
		
		return null;
	}
}
