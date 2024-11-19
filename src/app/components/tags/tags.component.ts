import { Component, OnInit } from '@angular/core';
import { TagService } from '../../services/tag.service';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit {
  searchTerm: string = '';
  sortBy: string = 'popular';

  // tags = [
  //   { name: 'javascript', description: 'For questions about programming in ECMAScript...', questions: 2536445, created: 1715004800 },
  //   { name: 'python', description: 'Python is a dynamically typed, multi-purpose programming language...', questions: 2214963, created: 1715004800 },
  //   { name: 'javascript', description: 'For questions about programming For questions about programming For questions about programming For questions about programming For questions about programming in ECMAScript...', questions: 2536445, created: 1715004800 },
  //   { name: 'python', description: 'Python is a dynamically typed, multi-purpose programming language...', questions: 2214963, created: 1715004800 },
  //   { name: 'javascript', description: 'For questions about programming in ECMAScript...', questions: 2536445, created: 1715004800 },
  //   { name: 'python', description: 'Python is a dynamically typed, multi-purpose programming language...', questions: 2214963, created: 1715004800 },
  //   // Add more tags as needed
  // ];

  tags: {name: string, description: string, questions: number, date: number}[] = [];

  constructor(private tagService: TagService) {}

  ngOnInit(): void {
    this.tagService.getTags().subscribe((tags) => {
      this.tags = tags;
    });
  }

  filteredTags() {
    let filtered = this.tags.filter(tag => 
      tag.name.toLowerCase().includes(this.searchTerm.toLowerCase())
    );

    switch (this.sortBy) {
      case 'name':
        return filtered.sort((a, b) => a.name.localeCompare(b.name));
      case 'new':
        // Sorting to ensure the latest new tag is on top, considering date format "2024-11-17T17:36:52.089561"
        return filtered.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
      default:
        return filtered.sort((a, b) => b.questions - a.questions);
    }
  }

  sortTags(criteria: string) {
    this.sortBy = criteria;
  }
}