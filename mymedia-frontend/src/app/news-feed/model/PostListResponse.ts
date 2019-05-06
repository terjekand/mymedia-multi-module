import {MymediaResponse} from '../../shared/model/Shared';
import {Post} from './Posts';

export interface PostListResponse  extends MymediaResponse {
  postList: Post [];
}
