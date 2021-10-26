package org.pfsilga.blogengine;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.Random;

@Entity
@Schema(description = "Author entity")
public class Author extends PanacheEntity {

    @Schema(required = true)
    @NotEmpty
    @Column(length = 60)
    public String firstname;

    @Schema(required = true)
    @NotEmpty
    @Column(length = 60)
    public String lastname;

    @Schema(required = true)
    @NotEmpty
    public String picture;

    @Schema(required = true)
    @NotEmpty
    @Column(length = 60)
    public String username;

    public static Author findByUsername(String username){
        return find("username", username).firstResult();
    }

    public static Author findRandomAuthor(){
        Author randomAuthor = null;
        do {
            long countVAuthor = count();
            Random random = new Random();
            int randomId = random.nextInt((int) countVAuthor);
            randomAuthor =  findAll().page(randomId, 1).firstResult();
        }while(randomAuthor == null);
        return randomAuthor;
    }
}
