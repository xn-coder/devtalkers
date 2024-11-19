import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-settings',
  templateUrl: './profile-settings.component.html',
  styleUrl: './profile-settings.component.css'
})
export class ProfileSettingsComponent {

  constructor(public router: Router) {}

  navigateTo(path: string) {
    this.router.navigate([path]);
  }
}
