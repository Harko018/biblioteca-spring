package com.example.bibliotecaduoc.controller;

import com.example.bibliotecaduoc.model.Prestamo;
import com.example.bibliotecaduoc.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        return ResponseEntity.ok(prestamoService.obtenerPrestamos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamoId(@PathVariable int id) {
        return prestamoService.obtenerPrestamoId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> agregarPrestamo(@RequestBody Prestamo prestamo, UriComponentsBuilder ucb) {
        Prestamo guardarPrestamo = prestamoService.guardarPrestamo(prestamo);
        if(guardarPrestamo == null){
            return ResponseEntity.notFound().build();
        }
        URI locationOfNewPrestamo = ucb
                .path("api/v1/prestamos/{id}")
                .buildAndExpand(guardarPrestamo.getIdPrestamo())
                .toUri();
        return ResponseEntity.created(locationOfNewPrestamo).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarPrestamo(@PathVariable int id, @RequestBody Prestamo prestamo) {
        Optional<Prestamo> existente = prestamoService.obtenerPrestamoId(id);
        if(existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        prestamo.setIdPrestamo(id);
        prestamoService.actualizarPrestamo(prestamo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable int id) {
        Optional<Prestamo> existente = prestamoService.obtenerPrestamoId(id);
        if(existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }

}
