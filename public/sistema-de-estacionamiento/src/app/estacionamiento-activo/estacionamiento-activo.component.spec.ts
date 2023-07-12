import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstacionamientoActivoComponent } from './estacionamiento-activo.component';

describe('EstacionamientoActivoComponent', () => {
  let component: EstacionamientoActivoComponent;
  let fixture: ComponentFixture<EstacionamientoActivoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EstacionamientoActivoComponent]
    });
    fixture = TestBed.createComponent(EstacionamientoActivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
