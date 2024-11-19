import { Component } from '@angular/core';
import { QuestionsService } from '../../services/questions.service';

@Component({
  selector: 'app-temp',
  templateUrl: './temp.component.html',
  styleUrl: './temp.component.css'
})
export class TempComponent {

  questions: {
    id: string;
    title: string;
    tags: string[];
    asked: string;
    votes: number;
    answers: number;
    answered: boolean;
  }[] = [
    {
      id: "1",
      title: "lorem10dvfn fdvjdfnvdf",
      tags: ["lorem10", "lorem10", "lorem10"],
      asked: "lorem10",
      votes: 0,
      answers: 0,
      answered: false,
    }
  ];
  currentPage: number = 1;
  totalPages: number = 1;

  // constructor(private questionsService: QuestionsService) {
  //   this.questionsService.getAllQuestions().subscribe((data: any) => {
  //     this.questions = data;
  //   });
  // }

  prevPage() {
    this.currentPage--;
  }

  nextPage() {
    this.currentPage++;
  }
}
