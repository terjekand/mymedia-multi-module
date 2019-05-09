import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {MymediaResponse} from '../shared/model/Shared';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  token: string;

  response: MymediaResponse = {
    errorMessage: '',
    errorCode: -1
  };

  private req = '';

  ngOnInit() {
  }
  constructor(private apiService: ApiService, private router: Router) {
    this.token = this.getCookie('token');
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

  public logout(): any {
    this.apiService.getLogout(this.getCookie('token')).subscribe(
      res => {
        this.response = res;
        if (this.response.errorCode < 0) {
          alert('MSG: ' + this.response.errorMessage + ' CODE: ' + this.response.errorCode);
        }
        this.deleteCookie('token');
        this.router.navigateByUrl('/');

      },
      err => {
        alert('An error occurred while logout');
        location.reload();
      }
    );
  }

  search() {
    this.apiService.req = this.req;
    this.apiService.doSearch();
  }

}
