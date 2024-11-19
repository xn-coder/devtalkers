import { Component, Input, OnInit } from '@angular/core';
import { QuestionsService } from '../../services/questions.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile-activity',
  templateUrl: './profile-activity.component.html',
  styleUrl: './profile-activity.component.css'
})
export class ProfileActivityComponent implements OnInit{

  user: any;
  paramsId: string = '';

  questions: {
    question: string,
    id: string
  }[] = [];

  answers: {
    answer: string,
    id: string
  }[] = [];

  constructor(private questionsService: QuestionsService, private route: ActivatedRoute) {}

  ngOnInit(): void {

    this.paramsId = this.route.parent?.snapshot.paramMap.get('id') || '';

    this.questionsService.getQuestionByUserId(this.paramsId).subscribe((res: any) => {
      this.questions = res;
    });

    this.questionsService.getAnswersByUserId(this.paramsId).subscribe((res: any) => {
      this.answers = res;
    });

    this.user = {
      answers: [
        {
          question: 'What is the capital of France?',
          answer: 'Paris'
        }
      ],
      questions: [
        {
          question: 'What is the capital of France?'
        }
      ],
      tags: [
        {
          tag: 'JavaScript'
        }
      ],
      reputation: [
        {
          reputation: '100'
        }
      ]
    }
  }

}