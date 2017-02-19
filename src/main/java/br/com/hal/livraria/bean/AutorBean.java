package br.com.hal.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.hal.livraria.dao.DAO;
import br.com.hal.livraria.model.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public String gravar() {
		System.out.println("Gravando autor " + this.getAutor().getNome());

		if (getAutor().getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.getAutor());
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.getAutor());
		}
		autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
	
	public void remover(Autor autor) {
		new DAO<Autor>(Autor.class).remove(autor);
	}
	
	public void carregar(Autor autor) {
		this.autor = autor;
	}
}
