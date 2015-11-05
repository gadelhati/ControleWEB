package br.eti.gadelha.web.controle.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.Form;

import br.eti.gadelha.ejb.controle.interfaces.local.DAOLocalPessoaFisica;
import br.eti.gadelha.ejb.controle.modelo.oque.quem.PessoaFisica;

/**
 * @autor Marcelo Ribeiro Gadelha
 * @since 08/10/2015
 * @see www.gadelha.eti.br
 * */

@WebService
@Path("/WSPessoaFisica")
public class WSPessoaFisica {
	
	private static final Logger log = Logger.getLogger(WSPessoaFisica.class.getName());
	
	@EJB DAOLocalPessoaFisica dao;
	public WSPessoaFisica() {
		// TODO Auto-generated constructor stub
	}
	//CRUD
	@PUT
	@Path(value = "/alterar")
	public void alterar(@Form PessoaFisica objeto) {
		log.info("web: altetando"+objeto.toString());
		dao.alterar(objeto);
	}
	@GET
	@Path(value = "/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public PessoaFisica consultar(@Form PessoaFisica objeto) {
		log.info("web: consultando"+objeto.toString());
		return dao.consultar(objeto);
	}
	@DELETE
	@Path(value = "/excluir")
	public void excluir(@Form PessoaFisica objeto) {
		log.info("web: excluindo"+objeto.toString());
		dao.excluir(objeto);
	}
	@POST
	@Path(value = "/inserir")
	@Consumes("application/vnd.com.demo.user-management.user+xml;charset=UTF-8;version=1")
	public void inserir(PessoaFisica objeto) {
		log.info("web: inserindo"+objeto.toString());
		dao.inserir(objeto);
		//return Response.status(200);
	}
	@GET
	@Path(value = "/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PessoaFisica> listar() {
		log.info("web: listando");
		return dao.listar();
	}
}