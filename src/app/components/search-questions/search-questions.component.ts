import { Component, OnInit } from '@angular/core';
import { QuestionsService } from '../../services/questions.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-questions',
  templateUrl: './search-questions.component.html',
  styleUrl: './search-questions.component.css'
})
export class SearchQuestionsComponent implements OnInit {
  questions: any[] = [];

  query: string = '';

  constructor(private questionsService: QuestionsService, private route: ActivatedRoute) {
    this.route.params.subscribe((params: any) => {
      this.query = params['query'];
    });
  }

  ngOnInit(): void {
    this.questionsService.getQuestionsByQuery(this.query).subscribe((res: any) => {
      this.questions = res;
    });
  }

}
