import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { QuestionsService } from '../../services/questions.service';
import moment from 'moment';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  userName: string = '';
  questions: any[] = [];
  isAuthenticated: boolean = false;
  popupMessage: string = '';
  popupSuccess: boolean = false;
  isPopupVisible: boolean = false;

  constructor(private userService: UserService, private questionsService: QuestionsService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe((user) => {
      this.userName = user.name;
    });

    this.isAuthenticated = this.authService.isAuthenticated();

    this.questionsService.getAllQuestions().subscribe((questions) => {
      for(let question of questions) {
        this.questions.push({
          id: question.id,
          text: question.text,
          tags: question.tags,
          timeAgo: this.getTimeAgo(question.date),
          votes: question.votes,
          answers: question.answers,
          views: question.views,
          isAnswered: question.answered,
        });
      }
    });
  }

  onClosePopup(event: boolean) {
    this.isPopupVisible = event;
    this.router.navigate(['/login']);
  }

  askQuestion() {
    if (!this.isAuthenticated) {
      this.popupMessage = 'You must be logged in to ask a question';
      this.popupSuccess = false;
      this.isPopupVisible = true;
      return;
    }
    this.router.navigate(['/ask-question']);
  }

  getTimeAgo(date: string) {
    return moment(date).fromNow();
  }

  navigateToQuestion(id: string) {
    const token = localStorage.getItem('token');
    if(token) {
      this.questionsService.viewQuestion(id).subscribe((data: any) => {
        if(data.data === "added") {
          this.questions.find(question => question.id === id)!.views++;
        }
      });
    }
    this.router.navigate(['/question/' + id]);
  }

}
