package br.com.easyprice.controllers;

import br.com.easyprice.exception.ResourceNotFoundException;
import br.com.easyprice.model.Comentarios;
import br.com.easyprice.model.PostoCombustivel;
import br.com.easyprice.repositories.PostoCombustivelRepository;
import br.com.easyprice.services.ComentarioService;
import br.com.easyprice.services.PostoCombustivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;
    @Autowired
    private PostoCombustivelRepository postoCombustivelRepository;

    @Autowired
    private PostoCombustivelService postoCombustivelService;

    @GetMapping("/postos/{postoId}/comentarios")
    @CrossOrigin(origins = "*")
    public List<Comentarios> listarComentariosPorPosto(@PathVariable Long postoId) {
        Optional<PostoCombustivel> optionalPostoCombustivel = postoCombustivelRepository.findById(postoId);

        if (optionalPostoCombustivel.isPresent()) {
            PostoCombustivel postoCombustivel = optionalPostoCombustivel.get();
            return comentarioService.listarComentariosPorPostoCombustivel(postoCombustivel);
        } else {
            throw new ResourceNotFoundException("Posto de combustível não encontrado com o ID: " + postoId);
        }
    }

    @PostMapping("/comentarios")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Comentarios> cadastrarComentario(@RequestBody Comentarios comentario) {
        Long postoId = comentario.getPostoId(); // Pega o ID do posto do objeto de comentário

        if (postoId != null) {
            PostoCombustivel posto = postoCombustivelService.findById(postoId).orElse(null); // Encontra o posto pelo ID
            if (posto != null) {
                comentario.setPostoCombustivel(posto); // Associa o posto ao comentário
                Comentarios novoComentario = comentarioService.cadastrarComentario(comentario);
                return ResponseEntity.ok(novoComentario);
            } else {
                return ResponseEntity.notFound().build(); // Posto não encontrado
            }
        } else {
            return ResponseEntity.badRequest().build(); // ID do posto ausente
        }
    }
}
