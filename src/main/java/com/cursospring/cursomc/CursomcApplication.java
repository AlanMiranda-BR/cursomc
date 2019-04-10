package com.cursospring.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cursospring.cursomc.domain.Categoria;
import com.cursospring.cursomc.domain.Cidade;
import com.cursospring.cursomc.domain.Cliente;
import com.cursospring.cursomc.domain.Endereco;
import com.cursospring.cursomc.domain.Estado;
import com.cursospring.cursomc.domain.ItemPedido;
import com.cursospring.cursomc.domain.Pagamento;
import com.cursospring.cursomc.domain.PagamentoComBoleto;
import com.cursospring.cursomc.domain.PagamentoComCartao;
import com.cursospring.cursomc.domain.Pedido;
import com.cursospring.cursomc.domain.Produto;
import com.cursospring.cursomc.domain.enums.EstadoPagamento;
import com.cursospring.cursomc.domain.enums.TipoCliente;
import com.cursospring.cursomc.repositories.CategoriaRepository;
import com.cursospring.cursomc.repositories.CidadeRepository;
import com.cursospring.cursomc.repositories.ClienteRepository;
import com.cursospring.cursomc.repositories.EnderecoRepository;
import com.cursospring.cursomc.repositories.EstadoRepository;
import com.cursospring.cursomc.repositories.ItemPedidoRepository;
import com.cursospring.cursomc.repositories.PagamentoRepository;
import com.cursospring.cursomc.repositories.PedidoRepository;
import com.cursospring.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	//Instancia dos Repositories
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	//Criação de registros automáticos nas tabelas
	@Override
	public void run(String... args) throws Exception {
		//Criação de registros de Categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Armas");
		Categoria cat9 = new Categoria(null, "Caça e Pesca");
		
		
		//Criação de registros de Produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha de Retalhos", 200.00);
		Produto p7 = new Produto(null, "TV Tubo Atômico", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		
		
		//Associação de categoria_produto
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		//Associação de produto_categoria
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		//Criação de registros de Estados
		Estado est1 = new Estado(null, "Santa Catarina");
		Estado est2 = new Estado(null, "Paraná");
		
		//Criação de registros de Cidades com associação ManyToOne
		Cidade c1 = new Cidade(null, "Camboriú", est1);
		Cidade c2 = new Cidade(null, "Guaratuba", est2);
		Cidade c3 = new Cidade(null, "Blumenal", est1);
		
		//Associação de estado_cidade
		est1.getCidades().addAll(Arrays.asList(c1,c3));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		//Criação de registros do cliente
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123"));
			//Criação dos telefones do cliente
			cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
			//Criação dos endereços do cliente
			Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 203", "Jardim", "38220834", cli1, c1);
			Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "88330590", cli1, c2);
			cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
			
		//Criação de registros dos pedidos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //Mascara de formato para instanciar Data/Hora 
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		//Criação de registros dos pagamentos
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
			ped1.setPagamento(pagto1); //Associa o pagamento com o pedido
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
			ped2.setPagamento(pagto2); //Associa o pagamento com o pedido
			//Associa o cliente com o pedido
			cli1.getPedidos().addAll(Arrays.asList(ped1,ped2)); 
		
		//Criação de registros dos itens_pedidos (Entidade Associativa)
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
			//Associa os pedidos com o Item_Pedido
			ped1.getItens().addAll(Arrays.asList(ip1, ip2)); 
			ped2.getItens().addAll(Arrays.asList(ip3));
			//Associa os produtos com o Item_Pedido
			p1.getItens().addAll(Arrays.asList(ip1));
			p2.getItens().addAll(Arrays.asList(ip3));
			p3.getItens().addAll(Arrays.asList(ip2));
		
		//Chama o métodos dos repositories para salvar os registros
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
			
	}
}
