import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutomovilistasComponent } from './automovilistas.component';

describe('AutomovilistasComponent', () => {
  let component: AutomovilistasComponent;
  let fixture: ComponentFixture<AutomovilistasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AutomovilistasComponent]
    });
    fixture = TestBed.createComponent(AutomovilistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
