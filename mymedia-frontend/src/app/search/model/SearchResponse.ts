import {MymediaResponse} from '../../shared/model/Shared';

export interface SearchResponse extends MymediaResponse {
  searchData: SearchData [];
}

export interface SearchDto {
  token: string;
  req: string;
}

export interface SearchData {
  userId: string;
  fullName: string;
  userName: string;
  value: string;
}

export interface IsFollowResponse {
  userId: string;
  fullName: string;
  userName: string;
  value: string;
}

export interface FollowWithTokenDto {
  token: string;
  destUserId: string;
}
