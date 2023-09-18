import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KichBanDetailComponent } from './kich-ban-detail.component';

describe('KichBan Management Detail Component', () => {
  let comp: KichBanDetailComponent;
  let fixture: ComponentFixture<KichBanDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KichBanDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ kichBan: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(KichBanDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(KichBanDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load kichBan on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.kichBan).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
