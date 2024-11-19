import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  
  userData: any = {};

  isPopupVisible: boolean = false;
  success: boolean = true;
  popupMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.registerUser(this.userData).subscribe(
      (response) => {
        this.success = true;
        this.popupMessage = 'User registered successfully';
        this.isPopupVisible = true;
      },
      (error) => {
        this.popupMessage = error.error;
        this.success = false;
        this.isPopupVisible = true;
      }
    );
  }

  onClosePopup(isPopupVisible: boolean) {
    this.isPopupVisible = isPopupVisible;
    if (this.success) {
      this.router.navigate(['/login']);
    }
  }
}
