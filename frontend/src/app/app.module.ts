import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { HomeComponent } from './components/home/home.component';
import { SafePipe } from './safe.pipe';
import { RandomGifComponent } from './components/home/random-gif/random-gif.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { ReactiveFormsModule } from '@angular/forms';
import { TrendingGifComponent } from './components/home/trending-gif/trending-gif.component';
import { SearchGifComponent } from './components/home/search-gif/search-gif.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { AccountComponent } from './components/user/account/account.component';
import { BlogComponent } from './components/blog/blog.component';
import { CreatePostComponent } from './components/blog/create-post/create-post.component';
import { PostViewComponent } from './components/blog/post-view/post-view.component';
import { AddCommentComponent } from './components/blog/add-comment/add-comment.component';
import { CommentListComponent } from './components/blog/comment-list/comment-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SafePipe,
    RandomGifComponent,
    TrendingGifComponent,
    SearchGifComponent,
    LoginComponent,
    RegisterComponent,
    AccountComponent,
    BlogComponent,
    CreatePostComponent,
    PostViewComponent,
    AddCommentComponent,
    CommentListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    InfiniteScrollModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [RandomGifComponent]
})
export class AppModule { }
