import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-profile-body',
  templateUrl: './profile-body.component.html',
  styleUrl: './profile-body.component.css'
})
export class ProfileBodyComponent {
  paramsId: string = '';
  userId: string = '';
  about: string = '';

  user: any;

  constructor(public router: Router, private route: ActivatedRoute, private userService: UserService) {
    this.userId = this.userService.getUserId();
    this.route.params.subscribe((params: any) => {
      this.paramsId = params['id'];
    });
  }

  navigateTo(path: string) {
    this.router.navigate([path]);
  }

}
