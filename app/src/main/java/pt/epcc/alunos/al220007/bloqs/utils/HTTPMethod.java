package pt.epcc.alunos.al220007.bloqs.utils;

public enum HTTPMethod {
	GET("GET"),
	;

private final String verb;

HTTPMethod(String method) {
	this.verb = method;
}


public String getVerb() {
	return verb;
}
}
