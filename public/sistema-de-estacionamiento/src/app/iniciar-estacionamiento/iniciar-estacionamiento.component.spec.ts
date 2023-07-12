import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IniciarEstacionamientoComponent } from './iniciar-estacionamiento.component';

describe('IniciarEstacionamientoComponent', () => {
  let component: IniciarEstacionamientoComponent;
  let fixture: ComponentFixture<IniciarEstacionamientoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IniciarEstacionamientoComponent]
    });
    fixture = TestBed.createComponent(IniciarEstacionamientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
