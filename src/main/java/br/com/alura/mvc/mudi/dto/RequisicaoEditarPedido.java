package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequisicaoEditarPedido extends RequisicaoNovoPedido{

	@NotNull
	private Long id;

	@Override
	public Pedido toPedido() {
		Pedido pedido = super.toPedido();
		pedido.setId(id);
		return pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
