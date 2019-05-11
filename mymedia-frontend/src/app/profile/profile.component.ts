import { Component, OnInit } from '@angular/core';
import {FollowersAndFollowingResponse, PersonalResponse} from './Model/PersonalData';
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

  followersAndFollowingResponse: FollowersAndFollowingResponse = {
    errorCode: -1,
    errorMessage: '',
    followers: 0,
    following: 0,
    posts: 0
  };

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

}
