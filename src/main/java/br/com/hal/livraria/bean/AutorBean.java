package br.com.hal.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.hal.livraria.dao.DAO;
import br.com.hal.livraria.model.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.getAutor().getNome());

		new DAO<Autor>(Autor.class).adiciona(this.getAutor());
		
		autor = new Autor();
	}
}
