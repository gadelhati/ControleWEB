package br.eti.gadelha.web.controle.controlador.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.eti.gadelha.ejb.controle.interfaces.local.DAOLocalPessoaFisica;
import br.eti.gadelha.ejb.controle.modelo.oque.quem.PessoaFisica;

/**
 * @autor Marcelo Ribeiro Gadelha
 * @since 27/08/2015
 * @see www.gadelha.eti.br
 **/

@ManagedBean(name="mbPessoaFisica")
@ViewScoped
public class MBPessoaFisica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB private DAOLocalPessoaFisica daoPessoaFisica;
	private PessoaFisica pessoaFisica;
	private List<PessoaFisica> lista;
	
	public MBPessoaFisica() {
		super();
	}
	@PostConstruct
	public void init() {
		this.pessoaFisica = new PessoaFisica();
		this.lista = new ArrayList<>();
		listar();
	}
	
	//CRUD
	public void alterar() {
		if(consultar()){
			daoPessoaFisica.alterar(pessoaFisica);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alterado"));
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuten��o", "Alterado"));
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manuten��o", "Alterado"));
		}
	}
	public Boolean consultar() {
		if(validar()) {
			if(autenticar()) {
				listar();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Encontrado"));
				return true;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N�o encontrado"));
				return false;
			}
		}else {
			return null;
		}
	}
	public void excluir() {
		if(consultar()) {
			daoPessoaFisica.excluir(pessoaFisica);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exclu�do"));
		}
	}
	public void inserir() {
		if(!consultar()) {
			daoPessoaFisica.inserir(pessoaFisica);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inserido"));
		}
	}
	public void listar() {
		this.lista = daoPessoaFisica.listar();
	}
	
	public void limpar() {
		this.pessoaFisica = new PessoaFisica();
	}
	public Boolean validar() {
		try {
			//(String.valueOf(pessoaFisica.getId()).matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))? true:false
			//"[a-z]{2,20}@[a-z]{2,20}.com(.|)[a-z]{0,2}"
			//"[a-zA-Z0-9]{2,20}"
			if(pessoaFisica!=null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("V�lido"));
				return true;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N�o v�lido"));
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Boolean autenticar() {
		try {
			if(daoPessoaFisica.consultar(pessoaFisica) != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Autenticado"));
				return true;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N�o autenticado"));
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Boolean autorizar() {
		/*
		 * servlet filter
		 * phase listener
		 * container managed security
		 * jguard
		 * spring security
		 * apache shiro	PROPRIET�RIO
		 */
		return null;
	}
	
	//GET & SET
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}
	public DAOLocalPessoaFisica getDaoPessoaFisica() {
		return daoPessoaFisica;
	}
	public List<PessoaFisica> getLista() {
		return lista;
	}
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	public void setDaoPessoaFisica(DAOLocalPessoaFisica daoPessoaFisica) {
		this.daoPessoaFisica = daoPessoaFisica;
	}
	public void setLista(List<PessoaFisica> lista) {
		this.lista = lista;
	}
	@Override
	public String toString() {
		return "MBPessoaFisica [daoPessoaFisica=" + daoPessoaFisica + ", pessoaFisica=" + pessoaFisica + ", lista="
				+ lista + "]";
	}
}