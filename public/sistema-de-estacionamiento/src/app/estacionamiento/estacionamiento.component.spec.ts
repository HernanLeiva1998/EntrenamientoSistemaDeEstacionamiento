import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstacionamientoComponent } from './estacionamiento.component';

describe('IniciarEstacionamientoComponent', () => {
  let component: EstacionamientoComponent;
  let fixture: ComponentFixture<EstacionamientoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EstacionamientoComponent]
    });
    fixture = TestBed.createComponent(EstacionamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
