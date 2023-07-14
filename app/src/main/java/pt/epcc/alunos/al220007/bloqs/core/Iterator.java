package pt.epcc.alunos.al220007.bloqs.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

abstract public class Iterator<Product, Raw, Collection> implements java.util.Iterator<Product>, Iterable<Product> {
private final Collection array;
private final Iterator.Instanciator<Product, Raw, Collection> init;
private int counter;

public Iterator(Collection array, Iterator.Instanciator<Product, Raw, Collection> init) {
	this.array = array;
	this.init = init;
	this.counter = 0;
}

abstract public int size(@NonNull Collection array);

@Override
public boolean hasNext() {
	return this.size(this.array) > this.counter;
}

@Override
public Product next() {
	Product o;
	do {
		o = this.init.fromJson(this.init.next(this.array, this.counter++), this.init.init());
	} while (o == null);
	return o;
}

abstract public Iterator<Product, Raw, Collection> createIterator(Collection array);

@NonNull
@Override
public java.util.Iterator<Product> iterator() {
	return this.createIterator(this.array);
}

@Override
public void remove() {
	java.util.Iterator.super.remove();
}

public interface Instanciator<Product, Raw, Collection> {
	@NonNull
	Product init();

	@Nullable
	Product fromJson(Raw json, @NonNull Product o);

	@Nullable
	Raw next(Collection array, int counter);
}
}
