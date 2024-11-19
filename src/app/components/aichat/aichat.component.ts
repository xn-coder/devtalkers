import { Component } from '@angular/core';
import { AichatService } from '../../services/aichat.service';
@Component({
  selector: 'app-aichat',
  templateUrl: './aichat.component.html',
  styleUrls: ['./aichat.component.css']
})
export class AichatComponent {
  messages: { text: string, sender: string }[] = [];

  constructor(private aichatService: AichatService) { }

  addMessage(text: string, sender: string) {
    this.messages.push({ text, sender });
    setTimeout(() => {
      const messagesContainer = document.getElementById('messages');
      if (messagesContainer) {
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
      }
    }, 200);
  }

  async sendMessage(userMessage: string) {
    if (!userMessage.trim()) return;

    this.addMessage(userMessage, 'user');

    this.aichatService.sendMessage(userMessage).subscribe(response => {
      this.addMessage(this.responseFormat(response.response), 'bot');
    });

  }

  responseFormat(response: string): string {
    response = response.replace(/\n/g, '<br>');
    response = response.replace(/(\*\*.*?\*\*)/g, '<strong>$1</strong>');
    response = response.replace(/\*\*/g, '');
    response = response.replace(/^\* (.*)$/gm, '<li>$1</li>');
    response = response.replace(/(<li>.*<\/li>)/g, '<ul>$1</ul>');
    response = response.replace(/```([^`]+)```/g, '<pre><code>$1</code></pre>');
    return response;
  }

  
}
