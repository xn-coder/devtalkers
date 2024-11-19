import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit {
  isAuthenticated: boolean = false;
  profileImage: string = '/assets/profile.png';
  userId: string = '';

  constructor(private router: Router, private authService: AuthService, private userService: UserService) {}

  ngOnInit(): void {

    this.userService.getUser().subscribe((response) => {
      this.profileImage = response.image;
      this.userId = response.id;
    }, (error) => {
      console.log(error);
    });

    this.authService.authState$.subscribe((isAuthenticated) => {
      this.isAuthenticated = isAuthenticated;
    });
    this.authService.checkAuthState();
    this.userService.profileUpdate$.subscribe((newProfileImage) => {
      this.profileImage = newProfileImage;
    });
  }

  searchQuestions(event: any) {
    setTimeout(() => {
      if(event.target.value.length === 0) {
        this.router.navigate(['/']);
      } else {
        this.router.navigate(['/search', event.target.value]);
      }
    }, 1000);
  }

  profileInfo() {
    this.router.navigate(['/profile', this.userId]);
  }
}
