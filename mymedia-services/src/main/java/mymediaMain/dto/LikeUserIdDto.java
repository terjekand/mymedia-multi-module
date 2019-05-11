package mymediaMain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikeUserIdDto implements Serializable {
    private String userId;
    private String postId;
}
