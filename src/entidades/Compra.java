package entidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import excecoes.ConsumidorNaoIdentificadoException;
import excecoes.TicketNaoSelecionadoException;
import excecoes.TicketVendidoException;
import excecoes.VendaInconsistenteException;

public class Compra {
	
	private Collection<Ticket> carrinho;
	
	public Compra() {
		carrinho = new ArrayList<Ticket>();
	}
	
	public void adicionarTicket(Ticket ticket) {
		carrinho.add(ticket);
	}
	
	public void removerTicket(Ticket ticket) {
		carrinho.remove(ticket);
	}
	
	public void esvaziarCarrinho() {
		carrinho.clear();
	}
	
	public void vincularConsumidor(Consumidor consumidor) {
		if(consumidor instanceof Aluno) {
			for(Ticket t : carrinho) t.vincularTipoConsumidor(TipoConsumidor.ALUNO);
		} else if(consumidor instanceof Funcionario) {
			for(Ticket t : carrinho) t.vincularTipoConsumidor(TipoConsumidor.FUNCIONARIO);
		}
	}
	
	public BigDecimal calcularTotal() {
		BigDecimal soma = new BigDecimal("0");		
		for(Ticket t : carrinho) {
			soma = soma.add(t.calcularPreco());
		}		
		return soma;
	}
	
	public void cancelarCompra() {
		
	}
		
	public void finalizarCompra(Consumidor consumidor) throws VendaInconsistenteException, 
		TicketVendidoException, ConsumidorNaoIdentificadoException, TicketNaoSelecionadoException {
		if(consumidor==null) {
			throw new ConsumidorNaoIdentificadoException();
		} else if(carrinho==null || carrinho.isEmpty()) {
			throw new TicketNaoSelecionadoException();
		} else {
			for(Ticket t : carrinho) {
				t.vender(consumidor);
			}
		}
	}
	
	public Collection<Ticket> getCarrinho() {
		return carrinho;
	}
	
	public int getTotalTickets() {
		return carrinho.size();
	}
}