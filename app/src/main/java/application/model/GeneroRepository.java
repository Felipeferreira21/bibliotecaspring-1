package application.model;

import org.springframework.data.repository.CrudRepository;

public interface GeneroRepository extends CrudRepository<Livro, Integer> {

    void save(Genero genero);
    
}
