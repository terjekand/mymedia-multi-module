import { Component, OnInit } from '@angular/core';
import {Post} from './model/Posts';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {PostListResponse} from './model/PostListResponse';
import {LikeWithUserId} from './model/LikeWithUserId';

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

  likeWithUserId: LikeWithUserId = {
    token: this.apiService.getCookie('token'),
    postId: ''
  };
  posts: Post[] = [];

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
    this.apiService.getNewsfeedByToken(this.apiService.getCookie('token')).subscribe(
      res => {
        this.postListResponse = res;
        if (this.postListResponse.errorCode < 0) {
          alert('MSG: ' + this.postListResponse.errorMessage + ' CODE: ' + this.postListResponse.errorCode);

          this.apiService.getLogout(this.apiService.getCookie('token'));
          this.apiService.deleteCookie('token');
          this.router.navigateByUrl('/');
        } else {
          this.posts = this.postListResponse.postList;
        }
      },
      err => {
        alert('An error occurred while load newsfeed');

        this.apiService.getLogout(this.apiService.getCookie('token'));
        this.apiService.deleteCookie('token');
        this.router.navigateByUrl('/');
      }
    );
  }

  doDeletePost(postId: string) {

    this.apiService.deletePostByPostId(postId).subscribe(
      res => {
          this.router.navigateByUrl('/newsfeed');
      } ,
      err => {
        alert('An error occurred while Delete Post');
      }
    );
  }

  likeUserId(postId: string) {
    this.likeWithUserId.postId = postId;
    this.apiService.likePostWithToken(this.likeWithUserId).subscribe(
      res => {
        location.reload();
      },
      err => {
        alert('An error occurred while LikePost');
      }
    );
  }

}
