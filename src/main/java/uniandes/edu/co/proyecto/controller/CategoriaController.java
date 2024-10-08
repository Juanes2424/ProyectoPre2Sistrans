package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.repositorio.CategoriaRepository;

@RestController
@RequestMapping("")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/categoria/new/save")
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria) {
        try {
            categoriaRepository.insertarCategoria(
                    categoria.getCodigo(),
                    categoria.getNombre(),
                    categoria.getDescripcion(),
                    categoria.getCaracteristicas_de_almacenamiento());
            return new ResponseEntity<>("Categoría creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la categoría: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/{id}/get")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable int id) {
        try {
            Categoria categoria = categoriaRepository.obtenerCategoria(id);
            if (categoria != null) {
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("categoria/{id}/edit")
    public ResponseEntity<String> actualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria) {
        try {
            categoriaRepository.actualizaRCategoria(
                    id,
                    categoria.getCodigo(),
                    categoria.getNombre(),
                    categoria.getDescripcion(),
                    categoria.getCaracteristicas_de_almacenamiento());
            return new ResponseEntity<>("Categoría actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la categoría: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("categoria/{id}/delete")
    public ResponseEntity<String> eliminarCategoria(@PathVariable int id) {
        try {
            categoriaRepository.eliminarCategoria(id);
            return new ResponseEntity<>("Categoría eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la categoría: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
