package br.eti.gadelha.web.controle.controlador.mb;

public class MBLog {
	/*
	//VALIDANDO A SESS�O
	public String logIn() {
		try {
			if(consultar()){//!=null
				//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("atributo");
				if(daoPessoa.consultar(pessoa).getId() == pessoa.getId()) {
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pessoa", pessoa);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lista", daoPessoa.listar());
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se��o iniciada"));
					return "/Filtro/paginas/crud/pessoa.xhtml";
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se��o n�o iniciada"));
				//ou inserir();
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
	*/
}