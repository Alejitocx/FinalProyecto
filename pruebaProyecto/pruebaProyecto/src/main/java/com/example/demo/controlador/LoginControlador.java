package com.example.demo.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.AdminDto;
import com.example.demo.modelo.Administrador;
import com.example.demo.repositorio.RepositorioAdministrador;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class LoginControlador {

    @Autowired
    RepositorioAdministrador repositorioAdministrador;

    @PostMapping
    public ResponseEntity<Boolean> login(@RequestBody AdminDto admin){

        Optional<Administrador> administrador = repositorioAdministrador.findByUser(admin.getUsername());
        if (administrador.isPresent()) {
            if(admin.getPassword().equals(administrador.get().getPass())) {
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(false);
    }
}
