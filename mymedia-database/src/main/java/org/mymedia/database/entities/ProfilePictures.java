package org.mymedia.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Picture.getPictureOfUser", query = "SELECT picture FROM ProfilePictures picture WHERE picture.userId =:uid"),
})
@Getter
@Setter
public class ProfilePictures {
    @Id
    private String id;

    private String userId;

    private String picture;

    public ProfilePictures() {
    }

    public ProfilePictures(String userId, String picture) {
        id = UUID.randomUUID().toString();
        this.userId = userId;
        this.picture = picture;
    }
}
