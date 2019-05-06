import { Component, OnInit } from '@angular/core';
import {PersonalResponse} from './Model/PersonalData';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

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

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
    this.apiService.getPersonalDataByToken(this.apiService.getCookie('token')).subscribe(
      res => {
          this.personalResponse = res;
          alert('MSG: ' + this.personalResponse.errorMessage + ' CODE: ' + this.personalResponse.errorCode);
      },
      err => {
        alert('An error occurred while get personal data');
        this.router.navigateByUrl('/');
      }
    );
  }

}
