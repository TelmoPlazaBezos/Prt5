package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test void saveTest() {
        // Given ...
        AppUser user = new AppUser("Telmo","hola@gmail.com","aaaaaaaA1", Role.USER);
        Token token = new Token(user);
        token.setAppUser(user);

        // When
        appUserRepository.save(user);
        tokenRepository.save(token);

        // Then 
        AppUser savedUser = appUserRepository.findByEmail("hola@gmail.com");
        Token savedToken = tokenRepository.findByAppUser(savedUser);


        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedToken);
        Assertions.assertEquals(user, savedToken.getAppUser());
        
        
        

    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test void deleteCascadeTest() {
        // Given
        AppUser user = new AppUser("Telmo","hola@gmail.com","aaaaaaaA1", Role.USER);
        Token token = new Token(user);
        appUserRepository.save(user);
        tokenRepository.save(token);

        // When
        tokenRepository.deleteByAppUser(user);

        // Then ...

        Assertions.assertEquals(0,tokenRepository.count());
    }
}