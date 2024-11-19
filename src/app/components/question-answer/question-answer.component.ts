import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionsService } from '../../services/questions.service';
import moment from 'moment';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-question-answer',
  templateUrl: './question-answer.component.html',
  styleUrl: './question-answer.component.css'
})
export class QuestionAnswerComponent {

  popupMessage: string = '';
  popupSuccess: boolean = false;
  isPopupVisible: boolean = false;
  acceptOption: boolean = false;

  isAuthenticated: boolean = false;

  questionId!: string;
  question: {
    id: string;
    title: string;
    body: string;
    votes: number;
    views: number;
    timeAgo: string;
    tags: string[];
  } = {
    id: "",
    title: "",
    body: "",
    votes: 0,
    views: 0,
    timeAgo: "",
    tags: [],
  };

  answers: {
    id: string;
    body: string;
    votes: number;
    timeAgo: string;
    profileImage: string;
    profileName: string;
    accepted: boolean;
  }[] = [
    {
      id: "1",
      body: "I have a table with a lot of columns and rows. I need to find the date in which column found the first data/value in a specific row. I have a table with a lot of columns and rows. I need to find the date in which column found the first data/value in a specific row. I have a table with a lot of columns and rows. I need to find the date in which column found the first data/value in a specific row.",
      votes: 0,
      timeAgo: "",
      profileImage: "",
      profileName: "",
      accepted: false,
    }
  ];
  answerBody: string = '';

  constructor(private route: ActivatedRoute, private questionsService: QuestionsService, private authService: AuthService, private router: Router) {
    this.route.params.subscribe((params: any) => {
      this.questionId = params['id'];
      this.acceptOption = this.questionId === localStorage.getItem('userId');
      
      this.questionsService.getQuestionById(this.questionId).subscribe((data: any) => {
        
        this.question = {
          id: data.id,
          title: data.title,
          body: data.description,
          votes: data.votes,
          views: data.views,
          timeAgo: moment(data.createdAt).fromNow(),
          tags: data.tags,
        };
      });

      this.questionsService.getAnswers(this.questionId).subscribe((data: any) => {
        console.log(data);
        
        this.answers = data.map((answer: any) => ({
          id: answer.id,
          body: answer.answer,
          votes: answer.votes,
          timeAgo: moment(answer.createdAt).fromNow(),
          profileImage: answer.image,
          profileName: answer.name,
          accepted: answer.accept,
        }));
      });

      this.isAuthenticated = this.authService.isAuthenticated();

    });
  }

  acceptAnswer(answerId: string, questionId: string) {
      this.questionsService.acceptAnswer(answerId, questionId).subscribe((data: any) => {
        this.answers.find(answer => answer.id === answerId)!.accepted = true;
      });
  }

  voteQuestion(direction: string) {

    const token = localStorage.getItem('token');
    if(!token) {
      this.popupMessage = 'You must be logged in to vote';
      this.popupSuccess = false;
      this.isPopupVisible = true;
      return;
    }

    this.questionsService.voteQuestion(this.questionId, direction).subscribe((data: any) => {
      if(data.data === "added") {
        this.question.votes++;
      } else if(data.data === "already") {
        this.popupMessage = 'You have already voted on this question';
        this.popupSuccess = false;
        this.isPopupVisible = true;
      } else if(data.data === "removed") {
        this.question.votes--;
      } else if(data.data === "not") {
        this.popupMessage = 'You have not voted on this question yet';
        this.popupSuccess = false;
        this.isPopupVisible = true;
      }
    });
  }

  voteAnswer(answerId: string, direction: string) {
    const token = localStorage.getItem('token');
    if(!token) {
      this.popupMessage = 'You must be logged in to vote';
      this.popupSuccess = false;
      this.isPopupVisible = true;
      return;
    }

    this.questionsService.voteAnswer(answerId, direction).subscribe((data: any) => {
      if(data.data === "added") {
        this.answers.find(answer => answer.id === answerId)!.votes++;
      } else if(data.data === "already") {
        this.popupMessage = 'You have already voted on this answer';
        this.popupSuccess = false;
        this.isPopupVisible = true;
      } else if(data.data === "removed") {
        this.answers.find(answer => answer.id === answerId)!.votes--;
      } else if(data.data === "not") {
        this.popupMessage = 'You have not voted on this answer yet';
        this.popupSuccess = false;
        this.isPopupVisible = true;
      }
    });
  }

  onClosePopup(isPopupVisible: boolean) {
    this.isPopupVisible = isPopupVisible;
  }

  addAnswer() {

    if (!this.isAuthenticated) {
      this.popupMessage = 'You must be logged in to answer a question';
      this.popupSuccess = false;
      this.isPopupVisible = true;
      return;
    }

    this.questionsService.postAnswer({
      qid: this.questionId,
      answer: this.answerBody,
    }).subscribe((data: any) => {
      this.popupMessage = 'Your answer has been posted successfully';
      this.popupSuccess = true;
      this.isPopupVisible = true;
      this.answerBody = '';
    });
  }

}
