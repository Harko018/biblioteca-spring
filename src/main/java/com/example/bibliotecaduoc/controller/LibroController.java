package com.example.bibliotecaduoc.controller;

import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroService.obtenerLibros();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable int id) {
        return libroService.buscarLibroPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Libro> buscarPorIsbn(@PathVariable String isbn) {
        return libroService.buscarLibroPorisbn(isbn)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> agregarLibro(@RequestBody Libro libro, UriComponentsBuilder ucb) {
        Libro guardarLibro = libroService.guardarLibro(libro);
        URI locationOfNewLibro = ucb
                .path("/api/v1/libros/{id}")
                .buildAndExpand(guardarLibro.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewLibro).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarLibro(@PathVariable int id, @RequestBody Libro libro) {
        Optional<Libro> existente  = libroService.buscarLibroPorId(id);
        if(existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        libroService.actualizarLibro(libro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id) {
        Optional<Libro> existente = libroService.buscarLibroPorId(id);
        if(existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> totalLibrosV2(){
        int total = libroService.totalLibrosV2();
        return ResponseEntity.ok(total);
    }


}
