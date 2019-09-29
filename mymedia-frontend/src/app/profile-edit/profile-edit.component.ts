import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {FollowersAndFollowingResponse, PersonalResponse} from '../profile/Model/PersonalData';
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

  followersAndFollowingResponse: FollowersAndFollowingResponse = {
    errorCode: -1,
    errorMessage: '',
    followers: 0,
    following: 0,
    posts: 0
  };

  mymediaResponse: MymediaResponse = {
    errorCode: -1,
    errorMessage: ''
  };

  selectedFile: File = null;

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

    this.apiService.getFollowersAndFollowingByToken(this.apiService.getCookie('token')).subscribe(
      res => {
        this.followersAndFollowingResponse = res;
        if (this.followersAndFollowingResponse.errorCode < 0) {
          alert('MSG: ' + this.followersAndFollowingResponse.errorMessage + ' CODE: ' + this.followersAndFollowingResponse.errorCode);
          this.followersAndFollowingResponse.following = 0;
          this.followersAndFollowingResponse.followers = 0;
          this.followersAndFollowingResponse.posts = 0;
        }
      },
      err => {
        alert('An error occurred while loading followers and following numbers');
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
