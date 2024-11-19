import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  sortBy: string = 'reputation';
  users: any[] = [];
  usersCopy: any[] = [];
  searchQuery: string = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((response) => {
      this.users = response;
      this.usersCopy = this.users;
    });
  }

  filterUsers() {
    if(this.searchQuery === '') {
      this.users = this.usersCopy;
    } else {
      this.users = this.usersCopy.filter(user => user.name.toLowerCase().includes(this.searchQuery.toLowerCase()));
    }
  }

  sortUsers(sort: string) {
    this.sortBy = sort;
  }
}