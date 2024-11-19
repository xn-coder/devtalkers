import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent implements OnInit {

  isAuthenticated: boolean = false;
  userId: string = '';

  constructor(public router: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.userId = localStorage.getItem('userId') || '';
    this.authService.authState$.subscribe((isAuthenticated) => {
      this.isAuthenticated = isAuthenticated;
    });
  }
}
