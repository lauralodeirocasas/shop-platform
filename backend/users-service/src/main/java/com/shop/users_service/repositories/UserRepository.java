package com.shop.users_service.repositories;

import com.shop.users_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
/*Crea automáticamente un repositorio que gestione objetos User con identificadores de tipo UUID*/
public interface UserRepository extends JpaRepository<User, UUID> {


    /*Pregunta si existe un usuario con ese email, pero no devuelve el usuario, solo un true o false.*/
    boolean existsByEmail(String email);


    /*Busca el usuario completo (el registro entero) por su email y devuelve un Optional<User>*/
    Optional<User> findByEmail(String email);

}
