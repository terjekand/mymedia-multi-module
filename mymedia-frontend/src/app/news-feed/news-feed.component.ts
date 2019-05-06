import { Component, OnInit } from '@angular/core';
import {Post} from './model/Posts';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {PostListResponse} from './model/PostListResponse';

@Component({
  selector: 'app-news-feed',
  templateUrl: './news-feed.component.html',
  styleUrls: ['./news-feed.component.css']
})
export class NewsFeedComponent implements OnInit {

  postListResponse: PostListResponse = {
    errorCode: -1,
    errorMessage: '',
    postList: []
  };

  posts: Post[] = [];

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
    this.apiService.getNewsfeedByToken(this.apiService.getCookie('token')).subscribe(
      res => {
        this.postListResponse = res;
        if (this.postListResponse.errorCode < 0) {
          alert('MSG: ' + this.postListResponse.errorMessage + ' CODE: ' + this.postListResponse.errorCode);
          this.router.navigateByUrl('/');
        } else {
          this.posts = this.postListResponse.postList;
          alert('size: ' + this.posts.length);
        }
      },
      err => {
        alert('An error occurred while load newsfeed');
        this.router.navigateByUrl('/');
      }
    );
  }

}
