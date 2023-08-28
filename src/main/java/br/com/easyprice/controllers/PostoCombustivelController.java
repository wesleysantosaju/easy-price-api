package br.com.easyprice.controllers;

import br.com.easyprice.model.PostoCombustivel;
import br.com.easyprice.services.PostoCombustivelService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PostoCombustivelController {

    private static final String UPLOAD_DIR = "uploads/";
    @Autowired
    private PostoCombustivelService postoService;

    @GetMapping("/{imageName}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Path.of("src/main/resources/uploads/" + imageName); // Path to your image

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type as needed
            headers.setContentLength(imageBytes.length);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar-postos")
    @CrossOrigin(origins = "*")
    public List<PostoCombustivel> listarPostos() {
        return postoService.listarPostos();
    }

    @GetMapping("/postos-por-endereco/{endereco}")
    @CrossOrigin(origins = "*")
    public List<PostoCombustivel> listarPostosPorEndereco(@PathVariable String endereco) {
        return postoService.listarPostosPorEndereco(endereco);
    }

    @GetMapping("/imagem-posto/uploads/{imageName:.+}")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<Resource> getImagemPosto(@PathVariable String imageName) throws IOException {
        String fullImagePath = Paths.get(UPLOAD_DIR, imageName).toString(); // Caminho completo da imagem
        Path imagePath = Paths.get(fullImagePath);

        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new ByteArrayResource(Files.readAllBytes(imagePath));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Ajuste o tipo de mídia conforme necessário
        headers.setContentLength(Files.size(imagePath));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }


    @PostMapping("cadastrar")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> cadastrarPosto(@RequestParam("nome") String nome,
                                                 @RequestParam("nomePosto") String nomePosto,
                                                 @RequestParam("tipoCombustivel") String tipoCombustivel,
                                                 @RequestParam("valorCombustivel") Double valorCombustivel,
                                                 @RequestParam("endereco") String endereco,
                                                 @RequestParam("formaPagamento") String formaPagamento,
                                                 @RequestParam("image") MultipartFile imageFile) {
        try {
            PostoCombustivel novoPosto = postoService.cadastrarPosto(
                    nome, nomePosto, tipoCombustivel, valorCombustivel, endereco, formaPagamento, imageFile
            );

            return new ResponseEntity<>("Posto cadastrado com sucesso. ID: " + novoPosto.getId(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Erro ao cadastrar posto.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
