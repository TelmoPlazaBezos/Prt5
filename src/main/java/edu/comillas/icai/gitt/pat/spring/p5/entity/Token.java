package edu.comillas.icai.gitt.pat.spring.p5.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.CascadeType.ALL;

/**
 * TODO#3
 * Completa la entidad Token (cuya tabla en BD se llamará TOKEN)
 * para que, además de la clave primaria ya indicada (cadena autogenerada aleatoria representando la sesión),
 * tenga un campo appUser, que represente la asociación uno a uno con la entidad AppUser (el usuario asociado a la sesión).
 * Este campo deberá configurarse para que en caso de que se borre el usuario, también se borre su sesión asociada.
 */

@Entity
@Table(name="TOKEN")
public class Token {
    @Id @GeneratedValue(strategy = GenerationType.UUID) public String id;
    @OneToOne // relacion "uno a uno" entre las clases token y appuser-> un token pertenece a un usuario
    @JoinColumn(name="token_id", referencedColumnName ="id") //clave foránea que referencia a id de la tabla app_user
    @OnDelete(action = OnDeleteAction.CASCADE) //se borra el usuario se elemina tmb su token asociado

    private AppUser appUser;

    public Token(AppUser appUser){
        this.appUser = appUser;
    }

    public String getId() {
        return id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
