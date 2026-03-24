package com.example.bibliotecaduoc.services;

import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerLibros() {
        return libroRepository.obtenerLibros();
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.guardarLibro(libro);
    }

    public Optional<Libro> buscarLibroPorId(int id) {
        return Optional.ofNullable(libroRepository.buscarLibroPorId(id));
    }

    public Optional<Libro> buscarLibroPorisbn(String isbn) {
        return Optional.ofNullable(libroRepository.buscarLibroPorisbn(isbn));
    }

    public Libro actualizarLibro(Libro libro) {
        return libroRepository.actualizarLibro(libro);
    }

    public void eliminarLibro(int id) {
        libroRepository.eliminarLibro(id);
    }

    public Integer totalLibrosV1() {
        return libroRepository.obtenerLibros().size();
    }

    public int totalLibrosV2() {
        return libroRepository.totalLibros();
    }

}
