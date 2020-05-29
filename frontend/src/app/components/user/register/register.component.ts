import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  hide: boolean = true;

  constructor(private userService: UserService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.registerForm = this.fb.group({
      email: [],
      username: [],
      password: [],
      firstname: [],
      lastname: [],
      // role: []
    })
  }

  register() {
    if (this.registerForm.invalid) {
      return;
    }
    this.userService.register(this.registerForm.value)
      .subscribe(data => {
        return alert("logged in!");
      })
    console.log(`Registerred ${this.registerForm.get('username').value}!`);
  }

}
