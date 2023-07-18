import { TestBed } from '@angular/core/testing';

import { AutomovilsitaService } from './automovilsita.service';

describe('AutomovilsitaService', () => {
  let service: AutomovilsitaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutomovilsitaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
