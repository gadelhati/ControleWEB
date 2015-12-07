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

import br.eti.gadelha.ejb.controle.interfaces.local.DAOLocalFerramenta;
import br.eti.gadelha.ejb.controle.modelo.oque.Ferramenta;

/**
 * @autor Marcelo Ribeiro Gadelha
 * @since 05/12/2015
 * @see www.gadelha.eti.br
 **/

@ManagedBean(name="mbFerramenta")
@ViewScoped
public class MBFerramenta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB private DAOLocalFerramenta daoFerramenta;
	private Ferramenta ferramenta;
	private List<Ferramenta> lista;
	
	public MBFerramenta() {
		super();
	}
	@PostConstruct
	public void init() {
		this.ferramenta = new Ferramenta();
		this.lista = new ArrayList<>();
		listar();
	}
	
	//CRUD
	public void alterar() {
		if(consultar()){
			daoFerramenta.alterar(ferramenta);
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
			daoFerramenta.excluir(ferramenta);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exclu�do"));
		}
	}
	public void inserir() {
		if(!consultar()) {
			daoFerramenta.inserir(ferramenta);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inserido"));
		}
	}
	public void listar() {
		this.lista = daoFerramenta.listar();
	}
	
	public void limpar() {
		this.ferramenta = new Ferramenta();
	}
	public Boolean validar() {
		try {
			//(String.valueOf(ferramenta.getId()).matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))? true:false
			//"[a-z]{2,20}@[a-z]{2,20}.com(.|)[a-z]{0,2}"
			//"[a-zA-Z0-9]{2,20}"
			if(ferramenta!=null){
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
			if(daoFerramenta.consultar(ferramenta) != null) {
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
	public Ferramenta getFerramenta() {
		return ferramenta;
	}
	public DAOLocalFerramenta getDaoFerramenta() {
		return daoFerramenta;
	}
	public List<Ferramenta> getLista() {
		return lista;
	}
	public void setFerramenta(Ferramenta ferramenta) {
		this.ferramenta = ferramenta;
	}
	public void setDaoFerramenta(DAOLocalFerramenta daoFerramenta) {
		this.daoFerramenta = daoFerramenta;
	}
	public void setLista(List<Ferramenta> lista) {
		this.lista = lista;
	}
	@Override
	public String toString() {
		return "MBFerramenta [daoFerramenta=" + daoFerramenta + ", ferramenta=" + ferramenta + ", lista="
				+ lista + "]";
	}
}