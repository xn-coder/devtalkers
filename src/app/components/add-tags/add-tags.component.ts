import { Component } from '@angular/core';
import { TagService } from '../../services/tag.service';

@Component({
  selector: 'app-add-tags',
  templateUrl: './add-tags.component.html',
  styleUrl: './add-tags.component.css'
})
export class AddTagsComponent {

  tag = {
    name: '',
    description: ''
  };

  validateForm() {
    return this.tag.name === '' || this.tag.description === '';
  }

  popupMessage = '';
  popupSuccess = false;
  isPopupVisible = false;

  constructor(private tagService: TagService) {}

  onSubmit() {
    this.tagService.addTag(this.tag).subscribe((response) => {
      if(response.status === "success") {
        this.popupMessage = response.message;
        this.popupSuccess = true;
        this.isPopupVisible = true;
      } else {
        this.popupMessage = response.message;
        this.popupSuccess = false;
        this.isPopupVisible = true;
      }
    });
  }

  onClosePopup(event: boolean) {
    this.isPopupVisible = event;
  }
}
