package mapeadores;

import java.sql.SQLException;
import java.util.Collection;

import excecoes.CampoObrigatorioException;

public interface Mapper<T> {
	/** Retorna um objeto da base de dados.
	 * @throws SQLException
	 * @throws CampoObrigatorioException*/
	public T encontrarPorId(Long id) throws SQLException;
	
	/** Persiste os dados de um objeto na base de dados. 
	 * @throws SQLException*/
	public void adicionar(T domainObject) throws SQLException;
	
	/** Atualiza os dados previamente armazenados na base de dados do objeto dado como parâmetro. 
	 * @throws SQLException*/
	public void atualizar(T domainObject) throws SQLException;
	
	/** Exclui os dados de um objecto da base de dados. */
	public void deletar(T domainObject) throws SQLException;
	
	/** Retorna uma lista de objetos da base de dados.
	 * @throws SQLException*/
	public Collection<T> encontrarTodos() throws SQLException;
}