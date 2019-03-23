package mymediaMain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikeWithTokenDto implements Serializable {
    private String token;
    private Long postId;
}
