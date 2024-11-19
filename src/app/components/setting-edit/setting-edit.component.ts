import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-setting-edit',
  templateUrl: './setting-edit.component.html',
  styleUrl: './setting-edit.component.css'
})
export class SettingEditComponent {

  isPopupVisible: boolean = false;
  popupMessage: string = '';
  success: boolean = true;
  
  user: {
    image: any;
    name: string | null;
    location: string | null;
    title: string | null;
    about: string | null;
    web_link: string | null;
    twi_link: string | null;
    git_link: string | null;
    full_name: string | null;
  } = {
    name: null,
    location: null,
    title: null,
    about: null,
    web_link: null,
    twi_link: null,
    git_link: null,
    full_name: null,
    image: null
  }

  constructor(private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.userService.getUser().subscribe((response) => {
      this.user = response;
    });
  }

  onImageSelected(event: any) {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const base64Image = e.target.result.split(',')[1];
        this.user.image = 'data:image/jpeg;base64,' + base64Image;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  saveProfile() {
    if(this.user.name === "") {
      this.popupMessage = 'Please enter your name';
      this.success = false;
      this.isPopupVisible = true;
      return;
    }
    this.userService.updateUser(this.user).subscribe((response) => {
      this.popupMessage = 'Profile updated successfully';
      this.success = true;
      this.isPopupVisible = true;
    });
  }

  onClosePopup(isPopupVisible: boolean) {
    this.isPopupVisible = isPopupVisible;
    if (this.success) {
      this.router.navigate(['/']);
    }
  }

  cancel() {
    this.router.navigate(['/']);
  }

}
