import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide: boolean = true;

  constructor(private userService: UserService,
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [],
      password: []
    })
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }

    this.userService.login(this.loginForm.get('username').value, this.loginForm.get('password').value)
      .subscribe(data => {
        alert('Logged in!');
        this.router.navigate(['/']);
      }, () => alert('error during the connection'));
  }

}
