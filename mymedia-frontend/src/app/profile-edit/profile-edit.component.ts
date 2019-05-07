import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {PersonalResponse} from '../profile/Model/PersonalData';
import {UpdateProfile} from './model/UpdateProfile';
import {MymediaResponse} from '../shared/model/Shared';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {

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

  updateProfile: UpdateProfile = {
    userId: undefined,
    token: undefined,
    bio: undefined,
    fullName: undefined,
    phoneNumber: undefined
  };

  mymediaResponse: MymediaResponse = {
    errorCode: -1,
    errorMessage: ''
  }
  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
    this.apiService.getPersonalDataByToken(this.apiService.getCookie('token')).subscribe(
      res => {
        this.personalResponse = res;
        if (this.personalResponse.errorCode < 0) {
          alert('MSG: ' + this.personalResponse.errorMessage + ' CODE: ' + this.personalResponse.errorCode);
        }
      },
      err => {
        alert('An error occurred while get personal data');
        this.apiService.getLogout(this.apiService.getCookie('token'));
        this.apiService.deleteCookie('token');
        this.router.navigateByUrl('/');
      }
    );
  }

  updatePersonalData() {
    this.updateProfile.token = this.apiService.getCookie('token');
    this.apiService.postUpdatePersonalData(this.updateProfile).subscribe(
      res => {
        this.mymediaResponse = res;
        if (this.mymediaResponse.errorCode < 0) {
          alert('MSG: ' + this.mymediaResponse.errorMessage + ' CODE: ' + this.mymediaResponse.errorCode);
        }
        this.router.navigateByUrl('/profile');
      },
      err => {
        alert('An error occurred while update personal data');
        this.apiService.getLogout(this.apiService.getCookie('token'));
        this.apiService.deleteCookie('token');
        this.router.navigateByUrl('/');
      }
    );
  }

}
