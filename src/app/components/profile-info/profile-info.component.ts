import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import moment from 'moment';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrl: './profile-info.component.css'
})
export class ProfileInfoComponent implements OnInit {
  userId: string = '';
  paramsId: string = '';

  constructor(public router: Router, private authService: AuthService, private userService: UserService, private route: ActivatedRoute) {
    this.userId = this.userService.getUserId();
    this.paramsId = this.route.snapshot.paramMap.get('id') || '';
  }

  user: {
    name: string;
    image: string;
    daysSinceJoined: number;
  } = {
    name: '',
    image: '',
    daysSinceJoined: 0
  };

  ngOnInit() {
    if(this.paramsId === this.userId) {
      this.userService.getUser().subscribe((response) => {
        this.user = {
          name: response.name,
          image: response.image,
          daysSinceJoined: moment().diff(response.date, 'days')
        };
      });
    } else {
      this.userService.getUserById(this.paramsId).subscribe((response) => {
        this.user = {
          name: response.name,
          image: response.image,
          daysSinceJoined: moment().diff(response.date, 'days')
        };
      });
    }
  }

  navigateTo(path: string) {
    this.router.navigate([path]);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
