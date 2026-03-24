package com.example.bibliotecaduoc.services;

import com.example.bibliotecaduoc.model.Libro;
import com.example.bibliotecaduoc.model.Prestamo;
import com.example.bibliotecaduoc.repository.LibroRepository;
import com.example.bibliotecaduoc.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {
    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private LibroService libroService;

    public List<Prestamo> obtenerPrestamos() {
        return prestamoRepository.obtenerPrestamos();
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        Optional<Libro> existe = libroService.buscarLibroPorId(prestamo.getIdLibro());
        if(existe.isEmpty()) {
            return null;
        }
        prestamo.setFechaSolicitud(LocalDate.now());
        prestamo.setFechaEntrega(prestamo.getFechaSolicitud().plusDays(prestamo.getCantidadDias()));
        prestamoRepository.guardarPrestamo(prestamo);
        return prestamo;
    }

    public Optional<Prestamo> obtenerPrestamoId(int id) {
        return Optional.ofNullable(prestamoRepository.obtenerPrestamoId(id));
    }

    public Prestamo actualizarPrestamo(Prestamo pres) {
        pres.setFechaSolicitud(LocalDate.now());
        pres.setFechaEntrega(pres.getFechaSolicitud().plusDays(pres.getCantidadDias()));
        return prestamoRepository.actualizarPrestamo(pres);
    }

    public void eliminarPrestamo(int id) {
        prestamoRepository.eliminarPrestamo(id);
    }
}
