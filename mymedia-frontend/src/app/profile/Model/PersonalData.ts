import {MymediaResponse} from '../../shared/model/Shared';

export interface PersonalData {
  bio: string;
  email: string;
  fullName: string;
  phoneNumber: string;
  username: string;
}

export interface PersonalResponse extends MymediaResponse {
  personalData: PersonalData;
}
