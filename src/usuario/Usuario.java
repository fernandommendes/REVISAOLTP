package usuario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

import dados.Produto;

public class Usuario {

	private static ArrayList<Produto> lista = new ArrayList<Produto>();
	public static void main(String[] args) {
		lerProdutos();
		ListarProdutos();
		gravarProdutos();
		System.out.println("\nPrograma Encerrado.");
		System.exit(0);

	}
	
	private static void gravarProdutos() {
		try {
			BufferedWriter gravProd = new BufferedWriter(new FileWriter("produtos2.txt"));
			for(Produto obj : lista){
				String codigo = new DecimalFormat("00000").format(obj.getCodigo()); // padronizando o codigo com 5 digitos
				String descricao = obj.getDescricao();
				int num = 60 - descricao.length();
				for (int i = 1; i <= num; i++ ) descricao = descricao + " "; // descrição com 60 caracter
				String preco = new DecimalFormat("0000000000").format(obj.getPreco()*100);
				String data = new SimpleDateFormat("ddMMyyyy").format(obj.getDataInclusao().getTime());
				gravProd.write(codigo+descricao+preco+data+"\n");				
			}
			gravProd.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void lerProdutos() {
		try{
			BufferedReader arqProduto = new BufferedReader(new FileReader("produtos.txt"));
			while (true) {
				String linha = arqProduto.readLine();
				if (linha==null) break;
				int codigo = Integer.parseInt(linha.substring(0, 5));
				String nome = linha.substring(5, 65);
				double preco = Double.parseDouble(linha.substring(65, 73)+"."+
												  linha.substring(73, 75));
				GregorianCalendar data = new GregorianCalendar(Integer.parseInt(linha.substring(79, 83)), 
															   Integer.parseInt(linha.substring(77, 79))-1,
															   Integer.parseInt(linha.substring(75, 77)));
				lista.add(new Produto(codigo, nome, preco, data));
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void listarProdutos() {
		System.out.println("\nLista Produto\n");
		for (Produto obj : lista) System.out.println(obj.toString());
	
		
	}

	//comparção e ordenação dos produtos pelo preço
	private static void ListarProdutos(){
		
		Collections.sort(lista, new Comparator<Produto>() {
			public int compare(Produto obj1, Produto obj2){
				if (obj1.getPreco() > obj2.getPreco()){
					return 1;
				}else{
					if(obj1.getPreco() < obj2.getPreco()){
						return -1;
					}else{
						return 0;
					}
				}
			}
		});

		System.out.println("\nRelação dos Produtos\n");
		
		for (Produto obj: lista) System.out.println(obj.toString());

		System.out.println("\nTOTAL DE PRODUTOS : " + lista.size() + "\n");
	}
}
