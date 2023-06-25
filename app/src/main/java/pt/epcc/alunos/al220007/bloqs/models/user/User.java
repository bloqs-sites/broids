package pt.epcc.alunos.al220007.bloqs.models.user;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jvmdbhelper.model.Model;

public class User implements Model {

private String user;
private String pass;
private byte[] salt;
private long id;

@Override
public void fromMap(@NonNull Map<String, ?> map) {
	Object id = map.get(UserManager.COL_ID);
	if (id != null) {
		this.id = Long.parseLong(String.valueOf(id));
	}
	Object user = map.get(UserManager.COL_USER);
	if (user instanceof CharSequence) {
		this.user = user.toString();
	}
	Object pass = map.get(UserManager.COL_PASS);
	if (pass instanceof CharSequence) {
		this.pass = pass.toString();
	}
}

@NonNull
@Override
public Map<String, Object> getImmutable() {
	Map<String, Object> immutable = new HashMap<>();
	immutable.put(UserManager.COL_ID, this.id);
	return immutable;
}

@NonNull
@Override
public Map<String, Object> getMutable() {
	Map<String, Object> mut = new HashMap<>();
	mut.put(UserManager.COL_USER, this.user);
	mut.put(UserManager.COL_PASS, this.pass);
	return mut;
}

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

@NonNull
@Override
public String toString() {
	return "User{" +
		"user='" + user + '\'' +
		", pass='" + pass + '\'' +
		", id=" + id +
		'}';
}

public byte[] getSalt() {
	return salt;
}

public void setSalt(byte[] salt) {
	this.salt = salt;
}
}
