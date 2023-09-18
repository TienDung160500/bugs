import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { QuanLyThongSoDeleteDialogComponent } from './../delete/quan-ly-thong-so-delete-dialog.component';
import { ThongSoMay } from './../../thong-so-may/thong-so-may.model';
import { IThietBi } from 'app/entities/thiet-bi/thiet-bi.model';
import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IQuanLyThongSo, QuanLyThongSo } from '../quan-ly-thong-so.model';
import { QuanLyThongSoService } from '../service/quan-ly-thong-so.service';

@Component({
  selector: 'jhi-quan-ly-thong-so-update',
  templateUrl: './quan-ly-thong-so-update.component.html',
  styleUrls: ['./quan-ly-thong-so-update.css']
})
export class QuanLyThongSoUpdateComponent implements OnInit {
  isSaving = false;
  predicate!: string;
  ascending!: boolean;


  thongSoThietBi?: IQuanLyThongSo[];

  selectedStatus: string | null = null;

  editForm = this.fb.group({
    id: [],
    maThongSo: [],
    tenThongSo: [],
    moTa: [],
    ngayTao: [],
    ngayUpdate: [],
    updateBy: [],
    status: [],
  });

  thietBiQlyThongSo: IThietBi[] = [];
  qLyThongSo: IQuanLyThongSo[] = [];

  constructor(
    protected quanLyThongSoService: QuanLyThongSoService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder,
    protected modalService: NgbModal)
  { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ quanLyThongSo }) => {
      if (quanLyThongSo.id === undefined) {
        const today = dayjs().startOf('day');
        quanLyThongSo.ngayTao = today;
        quanLyThongSo.ngayUpdate = today;
      }

      this.updateForm(quanLyThongSo);
    });
  }


  previousState(): void {
    window.history.back()
    // window.history.pushState({},"",window.location.href);
  }

  save(): void {
    this.isSaving = true;
    const quanLyThongSo = this.createFromForm();
    if (quanLyThongSo.id !== undefined) {
      alert("Cập nhật thông số thành công!")
      this.subscribeToSaveResponse(this.quanLyThongSoService.update(quanLyThongSo));
    } else {
      alert("Thêm mới thông số thành công!")
      this.subscribeToSaveResponse(this.quanLyThongSoService.create(quanLyThongSo));
    }
  }

  trackId(_index: number, item: IQuanLyThongSo): number {
    return item.id!;
  }

  trackThietBiById(_index: number, item: IQuanLyThongSo): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuanLyThongSo>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(quanLyThongSo: IQuanLyThongSo): void {
    this.editForm.patchValue({
      id: quanLyThongSo.id,
      maThongSo: quanLyThongSo.maThongSo,
      tenThongSo: quanLyThongSo.tenThongSo,
      moTa: quanLyThongSo.moTa,
      ngayTao: quanLyThongSo.ngayTao ? quanLyThongSo.ngayTao.format(DATE_TIME_FORMAT) : null,
      ngayUpdate: quanLyThongSo.ngayUpdate ? quanLyThongSo.ngayUpdate.format(DATE_TIME_FORMAT) : null,
      updateBy: quanLyThongSo.updateBy,
      status: quanLyThongSo.status,
    });
  }

  protected createFromForm(): IQuanLyThongSo {
    return {
      ...new QuanLyThongSo(),
      id: this.editForm.get(['id'])!.value,
      maThongSo: this.editForm.get(['maThongSo'])!.value,
      tenThongSo: this.editForm.get(['tenThongSo'])!.value,
      moTa: this.editForm.get(['moTa'])!.value,
      ngayTao: this.editForm.get(['ngayTao'])!.value ? dayjs(this.editForm.get(['ngayTao'])!.value, DATE_TIME_FORMAT) : undefined,
      ngayUpdate: this.editForm.get(['ngayUpdate'])!.value ? dayjs(this.editForm.get(['ngayUpdate'])!.value, DATE_TIME_FORMAT) : undefined,
      // updateBy: this.editForm.get(['updateBy'])!.value,
      // mặc định là admin
      updateBy: 'admin',
      status: this.editForm.get(['status'])!.value,
    };
  }
}
