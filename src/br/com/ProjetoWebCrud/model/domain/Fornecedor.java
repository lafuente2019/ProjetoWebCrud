package br.com.ProjetoWebCrud.model.domain;

import util.ValidacaoException;

public class Fornecedor extends Pessoa {
	
	private String razaoSocial;
	private String cnpj;
	
	
	public Fornecedor(Integer codigo, String nome, String email, String razaoSocial, String cnpj) {
		super(codigo, nome, email);
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
	}
	public Fornecedor() {
		super();
	}
	public void valida() throws ValidacaoException {
		super.valida();
		if(razaoSocial == null || razaoSocial.equals("")){
			throw new ValidacaoException(" o campo Razao Social é obrigatorio");
		}
		if(cnpj == null || cnpj.equals("")){
			throw new ValidacaoException(" o campo cnpj é obrigatorio");
		}
	}
		public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
