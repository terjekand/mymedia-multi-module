package mymediaMain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchDto implements Serializable {
    private String destUserId;
    private String req;

}
