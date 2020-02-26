<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Fornecedor</title>
</head>
<body>
 
    <form method="POST" action="fornecedorServlet">
		 <input type="hidden"  name="codigo" value="${fornecedor.codigo}"/> 
	     Codigo <input type="text" disabled name="codigo" value="${fornecedor.codigo}"/> <br>
	     Nome Fantasia <input type="text" name="nome" value="${fornecedor.nome}"/> <br>
	     Email <input type="text"  name="email" value="${fornecedor.email}"/> <br>
	     Razao Social <input type="text"  name="razaoSocial" value="${fornecedor.razaoSocial}"/> <br>
	     Cnpj<input type="text"  name="cnpj" value="${fornecedor.cnpj}"/> <br>
		 <input type="submit" value="Enviar" />
	</form>

	<h2>${mensagem}</h2>

	<h4>Fornecedores cadastrados</h4>
	<table border="1">
		<tr>
			<th>Codigo</th>
			<th>Nome Fantasia</th>			
			<th>Email</th>
			<th>Razão socialj</th>
			<th>Cnpj</th>
			<th>Editar</th>
			<th>Excluir</th>
			
		</tr>
		<c:forEach var="f" items="${fornecedores}">
		<tr>
			<td>${f.codigo}</td>		
			<td>${f.nome}</td>
			<td>${f.email}</td>
			<td>${f.razaoSocial}</td>
			<td>${f.cnpj}</td>
			<td><a href=fornecedorServlet?acao=editar&codigo=${f.codigo}>Editar</a>
			<td><a href=fornecedorServlet?acao=excluir&codigo=${f.codigo}>Excluir</a>
		</tr>	
		</c:forEach>	
	</table>
     
</body>
</html>