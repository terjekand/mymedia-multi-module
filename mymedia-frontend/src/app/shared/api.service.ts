import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {LoginViewModel, LoginResponse, RegistrationViewModel} from '../login/model/login';
import {CreatePostDto, CreatePostResponse} from '../new-post/model/createPost';
import {MymediaResponse} from './model/Shared';
import {PostListResponse} from '../news-feed/model/PostListResponse';
import {PersonalResponse} from '../profile/Model/PersonalData';
import {UpdateProfile} from '../profile-edit/model/UpdateProfile';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private BASE_URL = 'http://localhost:8080/mymedia-rest/rest/';
  private AUTH_URL = this.BASE_URL + 'auth/';
  private POST_URL = this.BASE_URL + 'post/';
  private PERSONAL_URL = this.BASE_URL + 'personal/';
  private NEWSFEED_URL = this.BASE_URL + 'newsfeed/';

  private LOGIN_URL = this.AUTH_URL + 'login';
  private REGISTER_URL = this.AUTH_URL + 'registration';
  private LOGOUT_URL = this.AUTH_URL + 'logout/';

  private GET_PERSONAL_DATA_BY_ID_URL = this.PERSONAL_URL + 'getdata/';
  private GET_PERSONAL_DATA_BY_TOKEN_URL = this.PERSONAL_URL + 'getdatabytoken/';
  private UPDATE_PERSONAL_DATA = this.PERSONAL_URL + 'update';

  private CREATE_POST_URL = this.POST_URL + 'createpostwithtoken';

  private GET_NEWS_FEED_BY_TOKEN_URL = this.NEWSFEED_URL + 'getbytoken/';

  personalResponse: PersonalResponse = {
    errorCode: -1,
    errorMessage: '',
    personalData: {
      bio: '',
      fullName: '',
      phoneNumber: '',
      email: '',
      username: ''
    }
  };

  constructor(private http: HttpClient) {

  }

  public getCookie(name: string) {
    const value = '; ' + document.cookie;
    const parts = value.split('; ' + name + '=');

    if (parts.length === 2) {
      return parts.pop().split(';').shift();
    }
  }

  public deleteCookie(name: string) {
    const date = new Date();

    // Set it expire in -1 days
    date.setTime(date.getTime() + (-1 * 24 * 60 * 60 * 1000));

    // Set it
    document.cookie = name + '=; expires=' + date.toUTCString() + '; path=/';
  }

  postRegisterUser(regData: RegistrationViewModel ): Observable<any> {
    return this.http.post(this.REGISTER_URL, regData);
  }

  postLoginUser(loginData: LoginViewModel): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.LOGIN_URL, loginData);
  }
  postCreatePost(createPostDto: CreatePostDto): Observable<CreatePostResponse> {
    return this.http.post<CreatePostResponse>(this.CREATE_POST_URL, createPostDto);
  }

  getLogout(token: string): Observable<MymediaResponse> {
    return this.http.get<MymediaResponse>(this.LOGOUT_URL + token);
  }

  getNewsfeedByToken(token: string): Observable<PostListResponse> {
    return this.http.get<PostListResponse>(this.GET_NEWS_FEED_BY_TOKEN_URL + token);
  }

  getPersonalDataByUserId(userId: string): Observable<PersonalResponse> {
    return this.http.get<PersonalResponse>(this.GET_PERSONAL_DATA_BY_ID_URL + userId);
  }

  getUserNameByUserid(userId: string): string {
    this.getPersonalDataByUserId(userId).subscribe(
      res => {
        this.personalResponse = res;
      },
      err => {
        alert('An error occurred while get userName from userId: ' + userId);
      }
    );
    return this.personalResponse.personalData.username;
  }

  getPersonalDataByToken(token: string): Observable<PersonalResponse> {
    return this.http.get<PersonalResponse>(this.GET_PERSONAL_DATA_BY_TOKEN_URL + token);
  }

  postUpdatePersonalData(updateProfile: UpdateProfile): Observable<MymediaResponse> {
    return this.http.post<MymediaResponse>(this.UPDATE_PERSONAL_DATA, updateProfile);
  }

}
