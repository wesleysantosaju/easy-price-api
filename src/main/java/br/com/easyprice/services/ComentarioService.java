package br.com.easyprice.services;

import br.com.easyprice.model.Comentarios;
import br.com.easyprice.model.PostoCombustivel;
import br.com.easyprice.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {


    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentarios> listarComentariosPorPostoCombustivel(PostoCombustivel postoCombustivel) {
        return comentarioRepository.findByPostoCombustivel(postoCombustivel);
    }


    public Comentarios cadastrarComentario(Comentarios comentario) {
        return comentarioRepository.save(comentario);
    }
}
