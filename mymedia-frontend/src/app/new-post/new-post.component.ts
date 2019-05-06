import { Component, OnInit } from '@angular/core';
import {ApiService} from '../shared/api.service';
import {Router} from '@angular/router';
import {CreatePostDto, CreatePostResponse} from './model/createPost';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  constructor(private apiService: ApiService, private router: Router) { }

  public createPostDto: CreatePostDto = {
    token: '',
    text: ''
};

  public createPostResponse: CreatePostResponse = {
    errorCode: -1,
    errorMessage: '',
    postId: ''
  };

  ngOnInit() {
  }


  public getCookie(name: string) {
    const value = '; ' + document.cookie;
    const parts = value.split('; ' + name + '=');

    if (parts.length === 2) {
      return parts.pop().split(';').shift();
    }
  }

  doCreatePost() {
    this.createPostDto.token = this.getCookie('token');
    if (this.createPostDto.token === null || this.createPostDto.token === undefined || this.createPostDto.token === '') {
      alert('Failed to create post!');

      this.apiService.getLogout(this.apiService.getCookie('token'));
      this.apiService.deleteCookie('token');
      this.router.navigateByUrl('/');
    }
    this.apiService.postCreatePost(this.createPostDto).subscribe(
        res => {
            this.createPostResponse = res;
            alert('CODE: ' + this.createPostResponse.errorCode
              + ' MSG:' + this.createPostResponse.errorMessage
              + ' POSTID:' + this.createPostResponse.postId);
            this.router.navigateByUrl('/newsfeed');
        },
        err => {
            alert('Failed to create post');

            this.apiService.getLogout(this.apiService.getCookie('token'));
            this.apiService.deleteCookie('token');
            this.router.navigateByUrl('/');
        }
    );
  }
}
