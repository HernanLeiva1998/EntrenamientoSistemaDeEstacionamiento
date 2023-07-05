import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearAutomovilistaComponent } from './crear-automovilista.component';

describe('CrearAutomovilistaComponent', () => {
  let component: CrearAutomovilistaComponent;
  let fixture: ComponentFixture<CrearAutomovilistaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CrearAutomovilistaComponent]
    });
    fixture = TestBed.createComponent(CrearAutomovilistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
