import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {LoginResponse, LoginViewModel, RegistrationViewModel, RegResponse} from './model/login';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginModel: LoginViewModel = {
    username: '',
    password: ''
  };
  regModel: RegistrationViewModel = {
    username: '',
    password: '',
    email: '',
    fullname: '',
    password_re: '',
    isAcceptAgreement: false
  };

  public actualUser: LoginResponse = {
    errorCode: -1,
    errorMessage: '',
    token: ''
  };

  public regResponse: RegResponse = {
    errorCode: -1,
    errorMessage: '',
    userId: ''
  };

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
  }


  public setCookie(name: string, val: string) {
    const date = new Date();
    const value = val;

    // Set it expire in 7 days
    date.setTime(date.getTime() + (7 * 24 * 60 * 60 * 1000));

    // Set it
    document.cookie = name + '=' + value + '; expires=' + date.toUTCString() + '; path=/';
  }


  doLogin(): void {
    this.apiService.postLoginUser(this.loginModel).subscribe(
      res => {
        this.actualUser = res;
        this.setCookie('token' , this.actualUser.token);
        if (this.actualUser.errorCode > 0) {
          this.router.navigateByUrl('/newsfeed');
        } else {
          location.reload();
        }
      },
      err => {
        alert('failed to login');
      }
    );
  }

  doRegistration(): void {
    this.apiService.postRegisterUser(this.regModel).subscribe(
      res => {
        this.regResponse = res;
        if (this.regResponse.errorCode < 0) {
          alert('MSG: ' + this.regResponse.errorMessage + ' CODE: ' + this.regResponse.errorCode);
        }
        location.reload();
      },
      err => {
        alert('Failed to register');
      }
    );
  }
}


