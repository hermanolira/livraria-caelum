package br.com.hal.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.hal.livraria.dao.DAO;
import br.com.hal.livraria.model.Livro;

@ManagedBean
public class LivroBean {

	private Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {

		System.out.println("Gravando Livro " + getLivro().getTitulo());

		if (getLivro().getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		new DAO<Livro>(Livro.class).adiciona(this.getLivro());
	}
}