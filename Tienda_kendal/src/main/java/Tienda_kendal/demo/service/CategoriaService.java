package Tienda_kendal.demo.service;

import Tienda_kendal.demo.domain.Categoria;
import Tienda_kendal.demo.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    // Obtener todas las categorías, filtrando por activo si se requiere
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        if (activo) { // Sólo activos
            return categoriaRepository.findByActivoTrue();
        }
        return categoriaRepository.findAll();
    }

    // Obtener una categoría por su ID
    @Transactional(readOnly = true)
    public Optional<Categoria> getCategoria(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    // Guardar o actualizar una categoría, subiendo imagen si se proporciona
    @Transactional
    public void save(Categoria categoria, MultipartFile imagenFile) {
        // Guardamos primero para asegurar que tenga ID
        categoria = categoriaRepository.save(categoria);

        if (imagenFile != null && !imagenFile.isEmpty()) { // Si hay archivo
            try {
                // Subimos la imagen a Firebase
                String rutaImagen = firebaseStorageService.uploadImage(
                        imagenFile,
                        "categoria",
                        categoria.getIdCategoria());

                // Actualizamos la ruta de la categoría y guardamos de nuevo
                categoria.setRutaImagen(rutaImagen);
                categoriaRepository.save(categoria);

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al subir la imagen de la categoría", e);
            }
        }
    }

    // Eliminar categoría por ID
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
}