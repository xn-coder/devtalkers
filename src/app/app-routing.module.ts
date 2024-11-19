import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { TagsComponent } from './components/tags/tags.component';
import { SavesComponent } from './components/saves/saves.component';
import { UsersComponent } from './components/users/users.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileBodyComponent } from './components/profile-body/profile-body.component';
import { ProfileActivityComponent } from './components/profile-activity/profile-activity.component';
import { ProfileSettingsComponent } from './components/profile-settings/profile-settings.component';
import { ProfileSavesComponent } from './components/profile-saves/profile-saves.component';
import { SettingPreferenceComponent } from './components/setting-preference/setting-preference.component';
import { SettingEditComponent } from './components/setting-edit/setting-edit.component';
import { SettingDeleteComponent } from './components/setting-delete/setting-delete.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { AskQuestionsComponent } from './components/ask-questions/ask-questions.component';
import { QuestionAnswerComponent } from './components/question-answer/question-answer.component';
import { TempComponent } from './components/temp/temp.component';
import { AichatComponent } from './components/aichat/aichat.component';
import { SearchQuestionsComponent } from './components/search-questions/search-questions.component';
import { AddTagsComponent } from './components/add-tags/add-tags.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'questions', component: QuestionsComponent },
  { path: 'tags', component: TagsComponent },
  { path: 'saves', component: SavesComponent },
  { path: 'profile/:id', component: ProfileComponent, children: [
    { path: '', component: ProfileBodyComponent },
    { path: 'activity', component: ProfileActivityComponent },
    { path: 'saves', component: ProfileSavesComponent },
    { path: 'settings', component: ProfileSettingsComponent, children: [
      { path: '', component: SettingPreferenceComponent },
      { path: 'edit', component: SettingEditComponent },
      { path: 'delete', component: SettingDeleteComponent }
    ]}
  ]},
  { path: 'search/:query', component: SearchQuestionsComponent },
  { path: 'users', component: UsersComponent },
  { path: 'login', component: LoginComponent },
  { path: 'temp', component: TempComponent },
  { path: 'ask-question', component: AskQuestionsComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'question/:id', component: QuestionAnswerComponent },
  { path: 'aichat/:id', component: AichatComponent },
  { path: 'aichat', component: AichatComponent },
  { path: 'add-tag', component: AddTagsComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
