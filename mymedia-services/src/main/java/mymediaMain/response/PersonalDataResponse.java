package mymediaMain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mymediaMain.model.PersonalData;

@Getter
@Setter
@NoArgsConstructor
public class PersonalDataResponse extends Response{
    private PersonalData personalData;

    public PersonalDataResponse(String errorMessages, int errorCodes, PersonalData personalData) {
        super(errorMessages, errorCodes);
        this.personalData = personalData;
    }
}
