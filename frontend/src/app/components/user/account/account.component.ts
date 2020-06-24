import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  connectedUser: User;
  opennedForm: Boolean[] = [false, false, false, false];
  updateForm: FormGroup;

  constructor(private userService: UserService, private fb: FormBuilder) { }

  ngOnInit() {
    this.getProfile();
    this.updateForm = this.fb.group({
      email: [],
      username: [],
      firstName: [],
      lastName: []
    })
  }

  getProfile() {
    this.userService.getProfile().subscribe((user: User) => {
      this.connectedUser = user;
    })
  }

  clearForm(index) {
    this.opennedForm[index] = !this.opennedForm[index];
    this.updateForm.reset();
  }

  updateProfile() {
    console.log(this.updateForm.value);
    console.log(this.connectedUser.id);
    this.updateForm.reset();
  }

}
