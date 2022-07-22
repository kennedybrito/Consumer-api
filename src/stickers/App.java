package stickers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

	
	public static void main(String[] args) throws Exception {

	String url = "https://alura-filmes.herokuapp.com/conteudos";
//    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";

//    ExtratoraDeConteudoDaNasa extrator = new ExtratoraDeConteudoDaNasa();
		
    ExtratorDeConteudoDoIMDB extrator = new ExtratorDeConteudoDoIMDB();
    		
    		
    ClienteHttp http = new ClienteHttp();
    String json = http.buscaDados(url);
    
    List<Conteudo> conteudos = extrator.extraiConteudos(json);
	
	
//	JsonParse parser = new JsonParse();
//	List<Map<String, String>> listadeFilmes = parser.parse(json);
	

	
	for (int i = 0; i<3 ; i++) {
		Conteudo map = conteudos.get(i);
		
		// nomeando a imagen nova 
		InputStream inputStream = new URL(map.getUrlImagem()).openStream();
		String nomeArquivo = "novapasta/" + map.getTitulo() + ".png";
		
		// gerando a imagem
		GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
		geradora.cria(inputStream, nomeArquivo);
		
		
		
		
		
		System.out.println( " O nome: " + map.getTitulo());
				System.out.println();
		
	}
	}
		
		 
		
}
