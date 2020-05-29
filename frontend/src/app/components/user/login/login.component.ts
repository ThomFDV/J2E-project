import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide: boolean = true;

  constructor(private userService: UserService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [],
      password: []
    })
  }

  login() {
    console.log(`logged in ${this.loginForm.get('username').value}!`);
  }

}
