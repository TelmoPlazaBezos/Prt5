package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {

    private final AppUserRepository appUserRepository;
    private final TokenRepository tokenRepository;
    private final Hashing hashing;
    public UserService(AppUserRepository appUserRepository, TokenRepository tokenRepository, Hashing hashing) {
        this.appUserRepository = appUserRepository;
        this.tokenRepository = tokenRepository;
        this.hashing = hashing;
    }



    public Token login(String email, String password) {

        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null || !hashing.compare(appUser.getPassword(), password)) {
            return null; // No coincidencia las contraseñas
        }

        Token token = new Token(appUser);
        return tokenRepository.save(token);
    }

    public AppUser authentication(String tokenId) {
        if (tokenId == null || tokenId.isEmpty()) return null;
        // Si el token existe, devuelve el usuario asociado; si no, devuelve null
        Optional<Token> token = tokenRepository.findById(tokenId);
        return token.map(Token::getAppUser).orElse(null);
    }


    public ProfileResponse profile(AppUser appUser) {
        if (appUser==null){
            return null;
        }
        ProfileResponse profileResponse= new ProfileResponse(
                appUser.getName(),
                appUser.getEmail(),
                appUser.getRole()
        );
        return profileResponse;
    }
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {

        if (appUser == null || profile == null) {
            return null;
        }

        appUser.setName(profile.name());
        appUser.setRole(profile.role());
        appUser = appUserRepository.save(appUser);

        ProfileResponse updatedProfileResponse = new ProfileResponse(
                appUser.getName(),
                appUser.getEmail(),
                appUser.getRole()
        );

        return updatedProfileResponse;
    }
    public ProfileResponse profile(RegisterRequest register) {

        String hashedPassword = hashing.hash(register.password());

        AppUser newUser = new AppUser(register.name(), register.email(), hashedPassword, register.role());
        newUser = appUserRepository.save(newUser);

        return new ProfileResponse(newUser.getName(), newUser.getEmail(), newUser.getRole());
    }




    public void logout(String tokenId) {
        if (tokenId != null && !tokenId.isEmpty()) {
            tokenRepository.deleteById(tokenId);
        }
    }

    public void delete(AppUser appUser) {
        if (appUser != null) {
            tokenRepository.deleteByAppUser(appUser); // borrar su sesión
            appUserRepository.delete(appUser); // borrar el usuario
        }
    }

}
