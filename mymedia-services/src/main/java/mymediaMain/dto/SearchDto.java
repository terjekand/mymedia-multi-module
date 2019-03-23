package mymediaMain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchDto implements Serializable {
    private String username;
    private String fullname;
    private String email;

}
