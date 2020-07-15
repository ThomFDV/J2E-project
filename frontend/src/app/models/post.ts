import {Comment} from "./comment";

export class Post {
  id: string;
  title: string;
  content: string;
  author: string;
  comments: Comment[];
}
