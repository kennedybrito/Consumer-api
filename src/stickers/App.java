package stickers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws IOException, InterruptedException {

	String url = "https://alura-filmes.herokuapp.com/conteudos";
	URI uri = URI.create(url);
	
	HttpClient cliente = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
	
	HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
	String body = response.body();
	
	
	
	JsonParse parser = new JsonParse();
	List<Map<String, String>> listadeFilmes = parser.parse(body);
	
	System.out.println(listadeFilmes.get(1));
	
	for (Map<String, String> map : listadeFilmes) {
		System.out.println("O nome do filme e " + map.get("title"));
		System.out.println("foi lançado em  " + map.get("year"));
		System.out.println();
		
	}
	}
}
