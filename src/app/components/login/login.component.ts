import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
  
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userData: { email: string, password: string } = { email: '', password: '' };

  isPopupVisible: boolean = false;
  success: boolean = true;
  popupMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private userService: UserService) {}

  onClosePopup(isPopupVisible: boolean) {
    this.isPopupVisible = isPopupVisible;
    if (this.success) {
      this.router.navigate(['/']);
    }
  }

  onSubmit() {
    if(this.userData.email === '' || this.userData.password === '') {
      this.popupMessage = 'Please enter your email and password';
      this.success = false;
      this.isPopupVisible = true;
      return;
    }
    this.authService.loginUser(this.userData).subscribe(
      (response) => {
        this.popupMessage = 'Your login credentials are correct. Welcome back!';
        this.success = true;
        this.isPopupVisible = true;
        this.userService.updateProfileImage();
      },
      (error) => {
        this.popupMessage = error.error;
        this.success = false;
        this.isPopupVisible = true;
      }
    );
  }
}

