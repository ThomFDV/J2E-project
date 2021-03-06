import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { AccountComponent } from './components/user/account/account.component';
import { ProfileGuard } from './guards/profile.guard';
import {BlogComponent} from "./components/blog/blog.component";
import {CreatePostComponent} from "./components/blog/create-post/create-post.component";
import {PostViewComponent} from "./components/blog/post-view/post-view.component";


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'profile',
    component: AccountComponent,
    canActivate: [ProfileGuard]
  },
  {
    path: 'blog',
    component: BlogComponent
  },
  {
    path: 'blog/create',
    component: CreatePostComponent,
    canActivate: [ProfileGuard]
  },
  {
    path: 'blog/:postId',
    component: PostViewComponent,
    canActivate: [ProfileGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
