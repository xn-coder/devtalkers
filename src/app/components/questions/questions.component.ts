import { Component, OnInit } from '@angular/core';
import { QuestionsService } from '../../services/questions.service';
import moment from 'moment';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {
  tab: string = 'Newest';
  questions: any[] = [];
  questionsCopy: any[] = [];
  isAuthenticated: boolean = false;
  popupMessage: string = '';
  popupSuccess: boolean = false;
  isPopupVisible: boolean = false;

  constructor(private questionsService: QuestionsService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.questionsService.getAllQuestions().subscribe((questions) => {
      for(let question of questions) {
        this.questions.push({
            id: question.id,
            votes: question.votes,
            answers: question.answers,
            views: question.views,
            isAnswered: question.answered,
            text: question.text,
            description: question.description,
            tags: question.tags,
            timeAgo: question.date
        });
        
      }
      this.questions = this.questions.sort((a, b) => new Date(b.timeAgo).getTime() - new Date(a.timeAgo).getTime());
    });
    this.questionsCopy = this.questions;
    this.isAuthenticated = this.authService.isAuthenticated();
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

  getTimeAgo(date: string) {
    return moment(date).fromNow();
  }

  tabChanged(tab: string) {
    this.tab = tab;
    if(tab === "Unanswered") {
      this.questions = this.questionsCopy.filter(question => !question.isAnswered);
    } else if(tab === "Active") {
      this.questions = this.questionsCopy.filter(question => question.isAnswered);
    } else if(tab === "Newest") {
      this.questions = this.questionsCopy.sort((a, b) => new Date(b.timeAgo).getTime() - new Date(a.timeAgo).getTime());
    }
  }
}