package pt.epcc.alunos.al220007.bloqs.models;

import androidx.annotation.NonNull;

import java.util.Map;

public class User implements Model {

private String user;
private String pass;
private long id;

@Override
public void fromMap(@NonNull Map<String, ?> map) {

}

@NonNull
@Override
public Map<String, Object> getImmutable() {
	return null;
}

@NonNull
@Override
public Map<String, Object> getMutable() {
	return null;
}

@Override
public long getId() {
	return this.id;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}
}
