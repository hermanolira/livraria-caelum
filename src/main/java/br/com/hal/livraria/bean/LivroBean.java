package br.com.hal.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.hal.livraria.dao.DAO;
import br.com.hal.livraria.model.Autor;
import br.com.hal.livraria.model.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();

	private Integer autorId;
	
	public Livro getLivro() {
		return livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void gravar() {
		
		System.out.println("Gravando Livro " + getLivro().getTitulo());

		if (getLivro().getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		} else {
			new DAO<Livro>(Livro.class).adiciona(this.getLivro());
			livro = new Livro();	
		}
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		getLivro().adicionaAutor(autor);
		System.out.println("Associando Autor " + autor.getNome());
	}
	
	public List<Autor> getAutoresDoLivro() {
		return getLivro().getAutores();
	}
	
	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
	}
}