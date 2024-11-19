import { Component, OnInit } from '@angular/core';
import { TagService } from '../../services/tag.service';
import { QuestionsService } from '../../services/questions.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ask-questions',
  templateUrl: './ask-questions.component.html',
  styleUrls: ['./ask-questions.component.css'],
})
export class AskQuestionsComponent implements OnInit {
  question = {
    title: '',
    description: '',
    tags: [] as string[]
  };

  tagInput = '';
  allTags: string[] = [];
  filteredTags: string[] = [];
  activeIndex = -1;

  popupMessage = '';
  popupSuccess = false;
  isPopupVisible = false;

  constructor(private tagService: TagService, private questionsService: QuestionsService, private router: Router) { }

  ngOnInit(): void {
    this.tagService.getTagNames().subscribe((response: any) => {
      this.allTags = response;
    });
  }

  filterTags() {
    const filterValue = this.tagInput.toLowerCase();
    this.filteredTags = this.allTags.filter(tag => 
      tag.toLowerCase().startsWith(filterValue) && !this.question.tags.includes(tag)
    );
    this.activeIndex = -1;
  }

  handleKeyDown(event: KeyboardEvent) {
    if (event.key === 'ArrowDown') {
      this.activeIndex = (this.activeIndex + 1) % this.filteredTags.length;
    } else if (event.key === 'ArrowUp') {
      this.activeIndex = (this.activeIndex - 1 + this.filteredTags.length) % this.filteredTags.length;
    } else if (event.key === 'Enter' && this.activeIndex >= 0) {
      this.addTag(this.filteredTags[this.activeIndex]);
      event.preventDefault();
    }
  }

  addTag(tag?: string) {
    const newTag = tag || this.tagInput.trim();
    if (newTag && !this.question.tags.includes(newTag)) {
      this.question.tags.push(newTag);
    }
    this.tagInput = '';
    this.filteredTags = [];
    this.activeIndex = -1;
  }

  removeTag(index: number) {
    this.question.tags.splice(index, 1);
  }

  validateForm() {
    return this.question.title.trim() === '' || this.question.description.trim() === '' || this.question.tags.length === 0;
  }

  onClosePopup(event: boolean) {
    if(this.popupSuccess) {
      this.router.navigate(['/']);
    }
    this.isPopupVisible = event;
  }

  onSubmit() {
    this.questionsService.postQuestion(this.question).subscribe((response: any) => {
      this.popupMessage = 'Question posted successfully';
      this.popupSuccess = true;
      this.isPopupVisible = true;
    });
  }
}
