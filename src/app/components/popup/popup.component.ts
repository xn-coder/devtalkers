import { Component, EventEmitter, Output, Input } from '@angular/core';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrl: './popup.component.css'
})
export class PopupComponent {

  @Input() message: string = '';
  @Input() success: boolean = false;
  @Input() isPopupVisible: boolean = false;
  @Output() onClose = new EventEmitter<boolean>();

  onClosePopup() {
    this.isPopupVisible = false;
    this.onClose.emit(false);
  }

}
