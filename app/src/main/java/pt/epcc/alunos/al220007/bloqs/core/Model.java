package pt.epcc.alunos.al220007.bloqs.core;

import androidx.annotation.NonNull;

public interface Model extends jvmdbhelper.model.Model {
long getId();

@NonNull
String toString();
}