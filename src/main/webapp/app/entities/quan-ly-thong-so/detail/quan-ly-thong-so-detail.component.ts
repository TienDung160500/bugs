import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuanLyThongSo } from '../quan-ly-thong-so.model';

@Component({
  selector: 'jhi-quan-ly-thong-so-detail',
  templateUrl: './quan-ly-thong-so-detail.component.html',
})
export class QuanLyThongSoDetailComponent implements OnInit {
  predicate!: string;
  ascending!: boolean;
  quanLyThongSo: IQuanLyThongSo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ quanLyThongSo }) => {
      this.quanLyThongSo = quanLyThongSo;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
