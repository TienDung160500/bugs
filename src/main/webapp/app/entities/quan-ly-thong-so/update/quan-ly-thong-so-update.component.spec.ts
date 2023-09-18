import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { QuanLyThongSoService } from '../service/quan-ly-thong-so.service';
import { IQuanLyThongSo, QuanLyThongSo } from '../quan-ly-thong-so.model';

import { QuanLyThongSoUpdateComponent } from './quan-ly-thong-so-update.component';

describe('QuanLyThongSo Management Update Component', () => {
  let comp: QuanLyThongSoUpdateComponent;
  let fixture: ComponentFixture<QuanLyThongSoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let quanLyThongSoService: QuanLyThongSoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [QuanLyThongSoUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(QuanLyThongSoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(QuanLyThongSoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    quanLyThongSoService = TestBed.inject(QuanLyThongSoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const quanLyThongSo: IQuanLyThongSo = { id: 456 };

      activatedRoute.data = of({ quanLyThongSo });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(quanLyThongSo));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<QuanLyThongSo>>();
      const quanLyThongSo = { id: 123 };
      jest.spyOn(quanLyThongSoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ quanLyThongSo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: quanLyThongSo }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(quanLyThongSoService.update).toHaveBeenCalledWith(quanLyThongSo);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<QuanLyThongSo>>();
      const quanLyThongSo = new QuanLyThongSo();
      jest.spyOn(quanLyThongSoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ quanLyThongSo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: quanLyThongSo }));
      saveSubject.complete();

      // THEN
      expect(quanLyThongSoService.create).toHaveBeenCalledWith(quanLyThongSo);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<QuanLyThongSo>>();
      const quanLyThongSo = { id: 123 };
      jest.spyOn(quanLyThongSoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ quanLyThongSo });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(quanLyThongSoService.update).toHaveBeenCalledWith(quanLyThongSo);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
