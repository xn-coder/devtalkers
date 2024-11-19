import { Component } from '@angular/core';

@Component({
  selector: 'app-setting-delete',
  templateUrl: './setting-delete.component.html',
  styleUrls: ['./setting-delete.component.css']
})
export class SettingDeleteComponent {
  isConfirmed = false;

  deleteProfile() {
    if (this.isConfirmed) {
      console.log('Profile deleted');
    }
  }
}