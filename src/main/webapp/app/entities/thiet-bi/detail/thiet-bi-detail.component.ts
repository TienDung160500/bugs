import { IThongSoMay } from 'app/entities/thong-so-may/thong-so-may.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThietBi } from '../thiet-bi.model';
import { HttpClient } from '@angular/common/http';
import { ApplicationConfigService } from 'app/core/config/application-config.service';

@Component({
  selector: 'jhi-thiet-bi-detail',
  templateUrl: './thiet-bi-detail.component.html',
})
export class ThietBiDetailComponent implements OnInit {
  resourceUrl = this.applicationConfigService.getEndpointFor('api/thiet-bi/chi-tiet-thiet-bi');

  predicate!: string;
  ascending!: boolean;
  thietBi: IThietBi | null = null;
  thietBi1: IThietBi | null = null;

  dataThongSoMay?: IThongSoMay | null | undefined = null;

  constructor(protected activatedRoute: ActivatedRoute,protected http: HttpClient,protected applicationConfigService:ApplicationConfigService) {}
  // lay thong tin thiet bi
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ thietBi }) => {
      this.thietBi = thietBi;
    });
    // lay thong tin thong so thiet bi
    if(this.thietBi?.id){
    this.http.get<any>(`${this.resourceUrl}/${this.thietBi.id}`).subscribe(res =>{
      this.thietBi1 = res ;
      console.log("id :", this.thietBi);
      console.log("chi tiet thiet bi :", this.thietBi1?.thongSoMays);
    });
  }
  }

  previousState(): void {
    window.history.back();
  }
}
