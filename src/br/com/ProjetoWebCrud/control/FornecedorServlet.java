package br.com.ProjetoWebCrud.control;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ProjetoWebCrud.model.dao.FornecedorDao;
import br.com.ProjetoWebCrud.model.domain.Fornecedor;
import util.ValidacaoException;

/**
 * Servlet implementation class FornecedorServlet
 */
@WebServlet("/fornecedorServlet")
public class FornecedorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private FornecedorDao  fornecedorDao = new FornecedorDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FornecedorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String codigo = request.getParameter("codigo");
		try {
			if ( acao !=null && acao.equals("excluir")){
				Integer codFornecedor = Integer.parseInt(codigo);
				fornecedorDao.excluir(codFornecedor);
				request.setAttribute("mensagem", "Fornecedor Excluido");
			}else if ( acao !=null && acao.equals("editar")){
				Integer codFornecedor = Integer.parseInt(codigo);
				Fornecedor fornecedor = fornecedorDao.getFornecedorId(codFornecedor);
				request.setAttribute("fornecedor", fornecedor);
			}
			request.setAttribute("fornecedores", fornecedorDao.getFornecedores());
		} catch (SQLException e) {
			request.setAttribute("mensagem", "Erro de banco de dados" + e.getMessage());	
		} catch (ClassNotFoundException e) {
			request.setAttribute("mensagem", "Erro de Driver" + e.getMessage());
		}catch (ValidacaoException e) {
			request.setAttribute("mensagem", "Erro de Driver" + e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/fornecedores.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String razaoSocial = request.getParameter("razaoSocial");
		String cnpj = request.getParameter("cnpj");
		String codigo = request.getParameter("codigo");
		Fornecedor fornecedor = new Fornecedor(null,nome,email,razaoSocial,cnpj);
		if( codigo != null && !codigo.contentEquals("")) {
			fornecedor.setCodigo(Integer.parseInt(codigo));
		}
		try {
			fornecedor.valida();
			if(fornecedor.getCodigo() != null) {
				fornecedorDao.atualizar(fornecedor);
				request.setAttribute("mensagem", "Fornecedor atualizado com sucesso!");
			}else {
				fornecedorDao.salvar(fornecedor);
				request.setAttribute("mensagem", "Fornecedor salvo com sucesso!");
			}
		} catch (ValidacaoException e) {
			request.setAttribute("mensagem", " Erro de validação dos campos" + e.getMessage());
			request.setAttribute("fornecedor", fornecedor);
		} catch (SQLException e) {
			request.setAttribute("mensagem", "Erro de banco de dados" + e.getMessage());	
			request.setAttribute("fornecedor", fornecedor);
		} catch (ClassNotFoundException e) {
			request.setAttribute("mensagem", "Erro de Driver" + e.getMessage());
			request.setAttribute("fornecedor", fornecedor);
		} 
		try {
			request.setAttribute("fornecedores", fornecedorDao.getFornecedores());
		} catch (SQLException e) {
			request.setAttribute("mensagem", "Erro de banco de dados" + e.getMessage());	
			request.setAttribute("fornecedor", fornecedor);
		} catch (ClassNotFoundException e) {
			request.setAttribute("mensagem", "Erro de Driver" + e.getMessage());
			request.setAttribute("fornecedor", fornecedor);
		} 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/fornecedores.jsp");
		dispatcher.forward(request, response);
       	
	}

}

