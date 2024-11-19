import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  userId: string = '';

  constructor(private route: ActivatedRoute) {
    this.route.params.subscribe((params: any) => {
      this.userId = params['id'];
    });
  }

}
