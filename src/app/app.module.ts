import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { FormsModule } from '@angular/forms';
import { CollapseModule } from 'ngx-bootstrap/collapse';
import { HttpClientModule } from '@angular/common/http';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { HomeComponent } from './components/home/home.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { TagsComponent } from './components/tags/tags.component';
import { SavesComponent } from './components/saves/saves.component';
import { UsersComponent } from './components/users/users.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileInfoComponent } from './components/profile-info/profile-info.component';
import { ProfileSettingsComponent } from './components/profile-settings/profile-settings.component';
import { SettingDeleteComponent } from './components/setting-delete/setting-delete.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { PopupComponent } from './components/popup/popup.component';
import { SettingEditComponent } from './components/setting-edit/setting-edit.component';
import { AskQuestionsComponent } from './components/ask-questions/ask-questions.component';
import { QuestionAnswerComponent } from './components/question-answer/question-answer.component';
import { TempComponent } from './components/temp/temp.component';
import { AichatComponent } from './components/aichat/aichat.component';
import { ProfileBodyComponent } from './components/profile-body/profile-body.component';
import { ProfileActivityComponent } from './components/profile-activity/profile-activity.component';
import { SearchQuestionsComponent } from './components/search-questions/search-questions.component';
import { AddTagsComponent } from './components/add-tags/add-tags.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    SideBarComponent,
    HomeComponent,
    QuestionsComponent,
    ProfileComponent,
    ProfileInfoComponent,
    TagsComponent,
    SavesComponent,
    UsersComponent,
    ProfileSettingsComponent,
    SettingDeleteComponent,
    SignupComponent,
    LoginComponent,
    PopupComponent,
    SettingEditComponent,
    AskQuestionsComponent,
    QuestionAnswerComponent,
    TempComponent,
    AichatComponent,
    ProfileBodyComponent,
    ProfileActivityComponent,
    SearchQuestionsComponent,
    AddTagsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    CollapseModule,
    HttpClientModule,
  ],
  providers: [provideClientHydration(), provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }
