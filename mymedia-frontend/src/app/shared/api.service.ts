import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {LoginViewModel, LoginResponse, RegistrationViewModel} from '../login/model/login';
import {CreatePostDto, CreatePostResponse} from '../new-post/model/createPost';
import {MymediaResponse} from './model/Shared';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private BASE_URL = 'http://localhost:8080/mymedia-rest/rest/';
  private AUTH_URL = this.BASE_URL + 'auth/';
  private POST_URL = this.BASE_URL + 'post/';

  private LOGIN_URL = this.AUTH_URL + 'login';
  private REGISTER_URL = this.AUTH_URL + 'registration';
  private LOGOUT_URL = this.AUTH_URL + 'logout/';

  private CREATE_POST_URL = this.POST_URL + 'createpostwithtoken';


  constructor(private http: HttpClient) {

  }


  // postFeedback(feedback: FeedbackViewModel): Observable<any> {
  //   return this.http.post(this.SEND_FEEDBACK_URL, feedback);
  // }
  //
  // getFavoritesOfUser(username: number): Observable<Favorite[]> {
  //   return this.http.get<Favorite[]>(this.GET_FAVORITES_OF_USER_URL  + username);
  // }
  //
  // postDeleteFavoriteById(favoriteId: number): Observable<any> {
  //   return this.http.post(this.DELETE_FROM_FAVORITES_URL, favoriteId);
  // }
  //
  // postAddToFavorites(fav: FavoriteViewModel): Observable<any> {
  //   return this.http.post(this.ADD_TO_FAVORITES_URL, fav);
  // }
  //
  // getCartByUser(id: number): Observable<Cart[]> {
  //   return this.http.get<Cart[]>(this.GET_CART_BY_USER + id);
  // }
  //
  // postDeleteCartById(cartId: number): Observable<any> {
  //   return this.http.post(this.DELETE_CART, cartId);
  // }
  //
  // postAddToCart(cart: CartViewModel): Observable<any>{
  //   return this.http.post(this.ADD_TO_CART, cart);
  // }

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

}
