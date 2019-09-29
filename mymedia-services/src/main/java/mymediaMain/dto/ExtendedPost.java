package mymediaMain.dto;

import database.entities.Post;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExtendedPost extends Post  implements Serializable {
    private int numberOfLikes;


}
