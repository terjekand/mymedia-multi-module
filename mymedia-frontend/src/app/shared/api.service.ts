import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {LoginViewModel, LoginResponse, RegistrationViewModel} from '../login/model/login';
import {CreatePostDto, CreatePostResponse} from '../new-post/model/createPost';
import {MymediaResponse} from './model/Shared';
import {PostListResponse} from '../news-feed/model/PostListResponse';
import {PersonalResponse} from '../profile/Model/PersonalData';
import {UpdateProfile} from '../profile-edit/model/UpdateProfile';
import {FollowWithTokenDto, SearchData, SearchDto, SearchResponse} from '../search/model/SearchResponse';
import {SearchComponent} from '../search/search.component';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private BASE_URL = 'http://localhost:8080/mymedia-rest/rest/';
  private AUTH_URL = this.BASE_URL + 'auth/';
  private POST_URL = this.BASE_URL + 'post/';
  private PERSONAL_URL = this.BASE_URL + 'personal/';
  private NEWSFEED_URL = this.BASE_URL + 'newsfeed/';
  private SEARCH_URL = this.BASE_URL + 'search/';
  private FOLLOW_URL = this.BASE_URL + 'follow/';

  private LOGIN_URL = this.AUTH_URL + 'login';
  private REGISTER_URL = this.AUTH_URL + 'registration';
  private LOGOUT_URL = this.AUTH_URL + 'logout/';

  private GET_PERSONAL_DATA_BY_ID_URL = this.PERSONAL_URL + 'getdata/';
  private GET_PERSONAL_DATA_BY_TOKEN_URL = this.PERSONAL_URL + 'getdatabytoken/';
  private UPDATE_PERSONAL_DATA = this.PERSONAL_URL + 'update';

  private CREATE_POST_URL = this.POST_URL + 'createpostwithtoken';

  private GET_NEWS_FEED_BY_TOKEN_URL = this.NEWSFEED_URL + 'getbytoken/';

  private SEARCH_USER_BY_TOKEN = this.SEARCH_URL + 'bytoken/';


  private FOLLOW_BY_TOKEN = this.FOLLOW_URL + 'bytoken/';


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

  req = '';

  searchResponse: SearchResponse = {
    errorCode: -1,
    errorMessage: '',
    searchData: []
  };

  searcDto: SearchDto = {
    token: '',
    req: ''
  };

  findings: SearchData[] = [];




  constructor(private http: HttpClient, private router: Router) {

  }

  doSearch() {
    if (this.req !== '') {
      this.searcDto.token = this.getCookie('token');
      this.searcDto.req = this.req;
      this.postSearchUserByToken(this.searcDto).subscribe(
        res => {
          this.searchResponse = res;
          this.findings = this.searchResponse.searchData;
          this.router.navigateByUrl('/search');
        },
        err => {
          alert('An error occurred while search');
          this.router.navigateByUrl('/newsfeed');
        }
      );
    } else {
      alert('Type something to the search bar...');
    }
    this.req = '';
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

  getPersonalDataByToken(token: string): Observable<PersonalResponse> {
    return this.http.get<PersonalResponse>(this.GET_PERSONAL_DATA_BY_TOKEN_URL + token);
  }

  postUpdatePersonalData(updateProfile: UpdateProfile): Observable<MymediaResponse> {
    return this.http.post<MymediaResponse>(this.UPDATE_PERSONAL_DATA, updateProfile);
  }

  postSearchUserByToken(searchDto: SearchDto): Observable<SearchResponse> {
    return this.http.post<SearchResponse>(this.SEARCH_USER_BY_TOKEN, searchDto);
  }

  postFollowUserByToken(followWithTokenDto: FollowWithTokenDto): Observable<MymediaResponse> {
    return this.http.post<MymediaResponse>(this.FOLLOW_BY_TOKEN, followWithTokenDto);
  }

}
