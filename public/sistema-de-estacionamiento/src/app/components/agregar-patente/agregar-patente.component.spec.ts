import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarPatenteComponent } from './agregar-patente.component';

describe('AgregarPatenteComponent', () => {
  let component: AgregarPatenteComponent;
  let fixture: ComponentFixture<AgregarPatenteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AgregarPatenteComponent]
    });
    fixture = TestBed.createComponent(AgregarPatenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
