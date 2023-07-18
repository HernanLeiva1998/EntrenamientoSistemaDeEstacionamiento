import { TestBed } from '@angular/core/testing';

import { PatenteService } from './patente.service';

describe('PatenteService', () => {
  let service: PatenteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatenteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
