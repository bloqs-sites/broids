package pt.epcc.alunos.al220007.bloqs.async;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import pt.epcc.alunos.al220007.bloqs.utils.HTTPMethod;

public class HTTPGETJSON implements Callable<String> {
private final URL resource;

public HTTPGETJSON(URL resource) {
	this.resource = resource;
}

@NonNull
@Override
public String call() throws Exception {
	HttpURLConnection con = (HttpURLConnection) this.resource.openConnection();
	con.setRequestMethod(HTTPMethod.GET.getVerb());
	con.setRequestProperty("Accept", "application/json; charset=UTF-8");
	con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

	if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
		return this.readAll(con.getInputStream());
	}

	con.disconnect();

	return "";
}

private String readAll(InputStream in) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	StringBuilder buf = new StringBuilder();
	String line;
	while (true) {
		line = reader.readLine();
		if (line == null) {
			break;
		}
		buf.append(line);
	}

	return String.valueOf(buf);
}
}
