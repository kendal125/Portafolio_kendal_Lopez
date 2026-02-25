package Tienda_kendal.demo.service;

import Tienda_kendal.demo.domain.Categoria;
import Tienda_kendal.demo.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        if (activo) { // Sólo activos...
            return categoriaRepository.findByActivoTrue();
        }
        return categoriaRepository.findAll();
    }

    /* ================== CÓDIGO AGREGADO (COMENTADO) ==================

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Transactional(readOnly = true)
    public Optional<Categoria> getCategoria(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    @Transactional
    public void save(Categoria categoria, MultipartFile imagenFile) {
        categoria = categoriaRepository.save(categoria);

        if (!imagenFile.isEmpty()) { // Si no está vacío...
            try {
                String rutaImagen = firebaseStorageService.uploadImage(
                        imagenFile,
                        "categoria",
                        categoria.getIdCategoria());

                categoria.setRutaImagen(rutaImagen);
                categoriaRepository.save(categoria);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    public void delete(Integer idCategoria) {

        if (!categoriaRepository.existsById(idCategoria)) {
            throw new IllegalArgumentException(
                "La categoría con ID " + idCategoria + " no existe.");
        }

        try {
            categoriaRepository.deleteById(idCategoria);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException(
                "No se puede eliminar la categoría. Tiene datos asociados.");
        }
    }

    =================================================================== */

}