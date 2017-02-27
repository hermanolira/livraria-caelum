package br.com.hal.livraria.bean;

import javax.faces.application.FacesMessage;
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
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			
			return "livro?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado!"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}
}
