import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {FollowWithTokenDto, IsFollowResponse, SearchData, SearchDto, SearchResponse} from './model/SearchResponse';
import {MymediaResponse} from '../shared/model/Shared';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private apiService: ApiService, private router: Router) { }


  private followDto: FollowWithTokenDto = {
    token: '',
    destUserId: ''
  };

  private response: MymediaResponse = {
    errorCode: -1,
    errorMessage: ''
  }

  ngOnInit() {
  }


  doFollowAction(destUserId: string) {
    this.followDto.token = this.apiService.getCookie('token');
    this.followDto.destUserId = destUserId;
    this.apiService.postFollowUserByToken(this.followDto).subscribe(
      res => {
        this.response = res;
        if (this.response.errorCode < 0) {
          alert('MSG: ' + this.response.errorMessage + ' CODE: ' + this.response.errorCode);
        }
        this.router.navigateByUrl('/newsfeed');
      },
      err => {
        alert('An error occurred while follow' + err);
      }
    );
  }
}
