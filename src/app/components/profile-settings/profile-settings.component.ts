import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profile-settings',
  templateUrl: './profile-settings.component.html',
  styleUrl: './profile-settings.component.css'
})
export class ProfileSettingsComponent {

  userId: string = '';

  constructor(public router: Router, private userService: UserService) {
    this.userId = this.userService.getUserId();
  }

  navigateTo(path: string) {
    this.router.navigate([path]);
  }
}
