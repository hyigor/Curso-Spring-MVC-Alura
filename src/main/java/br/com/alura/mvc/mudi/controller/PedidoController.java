package br.com.alura.mvc.mudi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import br.com.alura.mvc.mudi.dto.RequisicaoEditarPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("formulario") 
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}

	@PostMapping("formularioEditar")
	public String formularioEditar(Model model, Long id) {

		System.out.println(id);
		Optional<Pedido> pedidoOp = pedidoRepository.findById(Long.valueOf(id));
		Pedido pedido = pedidoOp.get();
		model.addAttribute("pedido", pedido);
		return "pedido/formularioEditar";
	}

	@PostMapping("editar")
	public String editar(@Valid RequisicaoEditarPedido requisicaoEditarPedido, BindingResult result, Model model){

		if(result.hasErrors()) {
			return formularioEditar(model, requisicaoEditarPedido.getId());
		}

		pedidoRepository.save(requisicaoEditarPedido.toPedido());

		return "redirect:/home";

//		String id = request.getParameter("id");
//		String nomeProduto = request.getParameter("nomeProduto");
//		String urlProduto = request.getParameter("urlProduto");
//		String urlImagem = request.getParameter("urlImagem");
//		String descricao = request.getParameter("descricao");
//
//		Optional<Pedido> pedidoOp = pedidoRepository.findById(Long.valueOf(id));
//		Pedido pedido = pedidoOp.get();
//
//		pedido.setNomeProduto(nomeProduto);
//		pedido.setUrlProduto(urlProduto);
//		pedido.setUrlImagem(urlImagem);
//		pedido.setDescricao(descricao);
//
//		pedidoRepository.save(pedido);


	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return "pedido/formulario";
		}
		
		Pedido pedido = requisicao.toPedido();
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
	}
	@PostMapping("deletar")
	public String deletar(Long id){
//		String id = request.getParameter("id");
		pedidoRepository.deleteById(Long.valueOf(id));
		return "redirect:/home";
	}
	
}
