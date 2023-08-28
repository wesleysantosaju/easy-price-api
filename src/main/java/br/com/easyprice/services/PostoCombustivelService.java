package br.com.easyprice.services;

import br.com.easyprice.model.Comentarios;
import br.com.easyprice.model.PostoCombustivel;
import br.com.easyprice.repositories.ComentarioRepository;
import br.com.easyprice.repositories.PostoCombustivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.StandardCopyOption;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class PostoCombustivelService {
    @Autowired
    private PostoCombustivelRepository postoRepository;



    private final String UPLOAD_DIR = "uploads/";

    public List<PostoCombustivel> listarPostos() {
        return postoRepository.findAllByOrderByIdDesc();
    }

    public List<PostoCombustivel> listarPostosPorEndereco(String endereco) {
        return postoRepository.findByEndereco(endereco);
    }

    public Optional<PostoCombustivel> findById(Long id) {
        return postoRepository.findById(id);
    }

    public PostoCombustivel cadastrarPosto(String nome,
                                           String nomePosto,
                                           String tipoCombustivel,
                                           Double valorCombustivel,
                                           String endereco,
                                           String formaPagamento,
                                           MultipartFile imageFile) throws IOException {
        PostoCombustivel novoPosto = new PostoCombustivel();
        novoPosto.setNome(nome);
        novoPosto.setNomePosto(nomePosto);
        novoPosto.setTipoCombustivel(tipoCombustivel);
        novoPosto.setValorCombustivel(valorCombustivel);
        novoPosto.setEndereco(endereco);
        novoPosto.setFormaPagamento(formaPagamento);

        // Salvar a imagem no servidor
        if (imageFile != null && !imageFile.isEmpty()) {
            Path imagePath = Path.of(UPLOAD_DIR + imageFile.getOriginalFilename());
            Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            novoPosto.setImagemPath(imagePath.toString());
        }

        return postoRepository.save(novoPosto);
    }
}
