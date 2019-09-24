package mymediaMain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeletePostWithTokenDto implements Serializable {
    private String postId;
    private String token;
}
