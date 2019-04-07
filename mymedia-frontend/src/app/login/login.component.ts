import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';

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
  constructor(private http: HttpClient) { }

  ngOnInit() {
  }


  doLogin(): void {
    const URL = 'http://localhost:8080/mymedia-rest/rest/auth/login';
    this.http.post(URL, this.loginModel).subscribe(
      res => {
        location.reload();
      },
      error1 => {
        alert('An error has occurred!');
      }
    );
  }

  doRegistration(): void {
    const URL = 'http://localhost:8080/mymedia-rest/rest/auth/registration';
    alert(this.regModel.username);
    alert(this.regModel.password);
    alert(this.regModel.password_re);
    alert(this.regModel.fullname);
    alert(this.regModel.email);
    alert(this.regModel.isAcceptAgreement);
}
}

export interface LoginViewModel {
  username: string;
  password: string;
}

export interface RegistrationViewModel extends  LoginViewModel{
  fullname: string;
  email: string;
  password_re: string;
  isAcceptAgreement: boolean;
}
