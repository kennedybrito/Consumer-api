package stickers;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	
	public static void main(String[] args) throws Exception {

	String url = "https://alura-filmes.herokuapp.com/conteudos";
	URI uri = URI.create(url);
	
	HttpClient cliente = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
	
	HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
	String body = response.body();
	
	
	
	JsonParse parser = new JsonParse();
	List<Map<String, String>> listadeFilmes = parser.parse(body);
	

	
	for (int i = 0; i<5 ; i++) {
		Map<String, String> map = listadeFilmes.get(i);
		
		// pegando a imagem na url 
		String urlImagem = map.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
		String titulo = map.get("title");
		
		// nomeando a imagen nova 
		InputStream inputStream = new URL(urlImagem).openStream();
		String nomeArquivo = "novapasta/" + titulo + ".png";
		
		// gerando a imagem
		GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
		geradora.cria(inputStream, nomeArquivo);
		
		
		
		
		
		System.out.println( " O nome do filme e  " + map.get("title"));
		System.out.println("foi lançado em  " + map.get("year"));
		System.out.println();
		
	}
	}
}
