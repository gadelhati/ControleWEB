package br.eti.gadelha.web.controle.controlador.mb;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.eti.gadelha.ejb.controle.interfaces.local.DAOLocalPessoa;
import br.eti.gadelha.ejb.controle.modelo.oque.quem.Pessoa;

/**
 * @autor Marcelo Ribeiro Gadelha
 * @since 27/08/2015
 * @see www.gadelha.eti.br
 **/

@ManagedBean(name="mbPessoa")
@ViewScoped
public class MBPessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB private DAOLocalPessoa daoPessoa;
	private Pessoa pessoa;
	private List<Pessoa> lista;
	private List<Pessoa> selecionados;
    
	public MBPessoa() {
		super();
	}
	@PostConstruct
	public void init() {
		this.pessoa = new Pessoa();
		this.lista = new ArrayList<>();
		this.selecionados = new ArrayList<>();
		listar();
	}
	
	//VALIDANDO A SESS�O
	public String logIn() {
		try {
			if(consultar()){//!=null
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("atributo");
				if(daoPessoa.consultar(pessoa).getId() == pessoa.getId()) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pessoa", pessoa);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lista", daoPessoa.listar());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se��o iniciada"));
					System.out.println("SO: "+System.getProperty("os.name"));
					System.out.println("Arquitetura do SO: "+System.getProperty("os.arch"));
					System.out.println("Usu�rio do SO: "+System.getProperty("user.name"));
					System.out.println("Vers�o do JRE: "+System.getProperty("java.version"));
					System.out.println("Vers�o do JVM: "+System.getProperty("java.vm.specification.version"));
					SimpleDateFormat sdf = new SimpleDateFormat("hh:mm dd/MM/yyyy ");  
					System.out.println("Hora do Sistema: "+ sdf.format(new Date()));
					return "/Filtro/paginas/crud/pessoa.xhtml";
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se��o n�o iniciada"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	//INVALIDANDO A SESS�O
  	public String logOut() throws IOException{
  		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
  		return "/index.xhtml";
  	}
  	/*
  	public String in() throws IOException{
  		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pessoa", pessoa);
  		return "/Filtro/paginas/crud/inserir.xhtml";
  	}
	*/
	//CRUD
	public void alterar() {
		if(consultar()){
			daoPessoa.alterar(pessoa);
			listar();
			//limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alterado"));
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Manuten��o", "Alterado"));
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Manuten��o", "Alterado"));
		}
	}
	public Boolean consultar() {
		/*
		URL url = getClass().getResource("/Filtro/sons/cartoon001.wav");
		AudioClip audio = Applet.newAudioClip(url);
		audio.play();
		*/
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
			daoPessoa.excluir(pessoa);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exclu�do"));
		}
	}
	public void inserir() {
		if(!consultar()) {
			daoPessoa.inserir(pessoa);
			listar();
			limpar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Inserido"));
		}
	}
	public void listar() {
		this.lista = daoPessoa.listar();
	}
	
	public void limpar() {
		this.pessoa = new Pessoa();
	}
	public Boolean validar() {
		try {
			//(String.valueOf(pessoa.getId()).matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))? true:false
			//"[a-z]{2,20}@[a-z]{2,20}.com(.|)[a-z]{0,2}"
			//"[a-zA-Z0-9]{2,20}"
			if(pessoa!=null){
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
			if(daoPessoa.consultar(pessoa) != null) {
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
	public Pessoa getPessoa() {
		return pessoa;
	}
	public DAOLocalPessoa getDaoPessoa() {
		return daoPessoa;
	}
	public List<Pessoa> getLista() {
		return lista;
	}
	public List<Pessoa> getSelecionados() {
		return selecionados;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public void setDaoPessoa(DAOLocalPessoa daoPessoa) {
		this.daoPessoa = daoPessoa;
	}
	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}
	public void setSelecionados(List<Pessoa> selecionados) {
		this.selecionados = selecionados;
	}
	@Override
	public String toString() {
		return "MBPessoa [daoPessoa=" + daoPessoa + ", pessoa=" + pessoa + ", lista=" + lista + ",selecionados=" + selecionados + "]";
	}
	/*
	public void onRowSelect(SelectEvent event) {}
	public void onRowUnselect(UnselectEvent event) {}
	public void onRowDblClckSelect(final SelectEvent event) {}
	
	public void viewCars() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("viewCars", options, null);
    }
    */
}