package dados;

import java.util.GregorianCalendar;

import utilitarios.LtpUtil;

public class Produto {

	//private static int seq = 0;
	
	private int codigo; 
	private String descricao;
	private double preco;
	private GregorianCalendar dataInclusao;
	
	public Produto(int codigo, String descricao, double preco,
			GregorianCalendar dataInclusao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.dataInclusao = dataInclusao;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public GregorianCalendar getDataInclusao() {
		return dataInclusao;
	}

	public double getPreco() {
		return preco;
	}

	public String toString(){
		return
				"Código: " + codigo + "\n" +
				"Nome:   " + descricao + "\n" +
				"Preço:  " + LtpUtil.formatarValor(preco, "#,##0.00") + "\n"+
				"Data:   " + LtpUtil.formatarData(dataInclusao, "dd/MM/yyyy") + "\n";
	}
	
	
}
