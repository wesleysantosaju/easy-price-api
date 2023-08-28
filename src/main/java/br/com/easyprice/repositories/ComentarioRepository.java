package br.com.easyprice.repositories;

import br.com.easyprice.model.Comentarios;
import br.com.easyprice.model.PostoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentarios, Long> {

    List<Comentarios> findByPostoCombustivel(PostoCombustivel postoCombustivel);
}
