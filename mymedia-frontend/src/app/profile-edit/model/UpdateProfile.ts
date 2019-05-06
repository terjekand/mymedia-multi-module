import {MymediaResponse} from '../../shared/model/Shared';
import {PersonalData} from '../../profile/Model/PersonalData';

export interface UpdateProfile {
  userId: string;
  token: string;
  fullName: string;
  bio: string;
  phoneNumber: string;
}

export interface UpdateProfileResponse extends MymediaResponse {
  personalData: PersonalData;
}
