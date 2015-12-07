package br.eti.gadelha.web.controle.controlador.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.eti.gadelha.ejb.controle.interfaces.local.DAOLocalCargo;
import br.eti.gadelha.ejb.controle.modelo.oque.Cargo;

/**
 * @autor Marcelo Ribeiro Gadelha
 * @since 05/12/2015
 * @see www.gadelha.eti.br
 **/

@ManagedBean(name="mbCargo")
@SessionScoped
public class MBCargo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB private DAOLocalCargo daoCargo;
	private Cargo cargo;
	private List<Cargo> lista;
	
	public MBCargo() {
		super();
	}
	@PostConstruct
	public void init() {
		this.cargo = new Cargo();
		this.lista = new ArrayList<>();
		listar();
	}
	
	//CRUD
	public void alterar() {
		if(consultar()){
			daoCargo.alterar(cargo);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alterado"));
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manutenção", "Alterado"));
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manutenção", "Alterado"));
		}
	}
	public Boolean consultar() {
		if(validar()) {
			if(autenticar()) {
				listar();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Encontrado"));
				return true;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não encontrado"));
				return false;
			}
		}else {
			return null;
		}
	}
	public void excluir() {
		if(consultar()) {
			daoCargo.excluir(cargo);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluído"));
		}
	}
	public void inserir() {
		if(!consultar()) {
			daoCargo.inserir(cargo);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inserido"));
		}
	}
	public void listar() {
		this.lista = daoCargo.listar();
	}
	
	public void limpar() {
		this.cargo = new Cargo();
	}
	public Boolean validar() {
		try {
			//(String.valueOf(cargo.getId()).matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))? true:false
			//"[a-z]{2,20}@[a-z]{2,20}.com(.|)[a-z]{0,2}"
			//"[a-zA-Z0-9]{2,20}"
			if(cargo!=null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Válido"));
				return true;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não válido"));
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Boolean autenticar() {
		try {
			if(daoCargo.consultar(cargo) != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Autenticado"));
				return true;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não autenticado"));
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
		 * apache shiro	PROPRIETÁRIO
		 */
		return null;
	}
	
	//GET & SET
	public Cargo getCargo() {
		return cargo;
	}
	public DAOLocalCargo getDaoCargo() {
		return daoCargo;
	}
	public List<Cargo> getLista() {
		return lista;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public void setDaoCargo(DAOLocalCargo daoCargo) {
		this.daoCargo = daoCargo;
	}
	public void setLista(List<Cargo> lista) {
		this.lista = lista;
	}
	@Override
	public String toString() {
		return "MBCargo [daoCargo=" + daoCargo + ", cargo=" + cargo + ", lista="
				+ lista + "]";
	}
}