import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentesComponent } from './patentes.component';

describe('PatentesComponent', () => {
  let component: PatentesComponent;
  let fixture: ComponentFixture<PatentesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatentesComponent]
    });
    fixture = TestBed.createComponent(PatentesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
