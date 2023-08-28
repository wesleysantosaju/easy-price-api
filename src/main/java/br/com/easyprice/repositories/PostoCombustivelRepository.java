package br.com.easyprice.repositories;

import br.com.easyprice.model.PostoCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostoCombustivelRepository extends JpaRepository<PostoCombustivel, Long> {

    List<PostoCombustivel> findAllByOrderByIdDesc();

    List<PostoCombustivel> findByEndereco(String endereco);

}
