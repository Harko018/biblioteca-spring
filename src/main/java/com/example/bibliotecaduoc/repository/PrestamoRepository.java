package com.example.bibliotecaduoc.repository;

import com.example.bibliotecaduoc.model.Prestamo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PrestamoRepository {

    private List<Prestamo> listaPrestamos = new ArrayList<>();

    public List<Prestamo> obtenerPrestamos() {
        return listaPrestamos;
    }

    public Prestamo guardarPrestamo(Prestamo prestamo) {
        listaPrestamos.add(prestamo);
        return prestamo;
    }

    public Prestamo obtenerPrestamoId(int id) {
        for(Prestamo pres : listaPrestamos) {
            if(pres.getIdPrestamo() == id) {
                return pres;
            }
        }
        return null;
    }

    public Prestamo actualizarPrestamo(Prestamo pres) {
        int id = 0;
        int idPosicion = -1;

        for(int i = 0; i < listaPrestamos.size(); i++) {
            if(listaPrestamos.get(i).getIdPrestamo() == pres.getIdPrestamo()) {
                id = pres.getIdPrestamo();
                idPosicion = i;
            }
        }
        if(idPosicion == -1) {
            return null;
        }
        pres.setIdPrestamo(id);
        listaPrestamos.set(idPosicion, pres);
        return pres;
    }

    public void eliminarPrestamo(int id) {
        Prestamo prestamo = obtenerPrestamoId(id);
        if(prestamo != null) {
            listaPrestamos.remove(prestamo);
        }
    }

}
