import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  token: string;

  ngOnInit() {
  }
  constructor() {
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
    this.deleteCookie('token');
    location.reload();
  }



}
