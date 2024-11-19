import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileSavesComponent } from './profile-saves.component';

describe('ProfileSavesComponent', () => {
  let component: ProfileSavesComponent;
  let fixture: ComponentFixture<ProfileSavesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileSavesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProfileSavesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
