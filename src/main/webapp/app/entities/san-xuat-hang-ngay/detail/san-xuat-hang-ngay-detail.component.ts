import { IChiTietSanXuat } from 'app/entities/chi-tiet-san-xuat/chi-tiet-san-xuat.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISanXuatHangNgay } from '../san-xuat-hang-ngay.model';
import { HttpClient } from '@angular/common/http';
import { ApplicationConfigService } from 'app/core/config/application-config.service';

@Component({
  selector: 'jhi-san-xuat-hang-ngay-detail',
  templateUrl: './san-xuat-hang-ngay-detail.component.html',
})
export class SanXuatHangNgayDetailComponent implements OnInit {
  //---------------------- url lay thong tin chi tiet kich ban --------------------
  resourceUrl = this.applicationConfigService.getEndpointFor('api/san-xuat-hang-ngay/chi-tiet-san-xuat');
  predicate!: string;
  ascending!: boolean;
  sanXuatHangNgay: ISanXuatHangNgay | null = null;
  chiTietSanXuats: IChiTietSanXuat[] | null = []
  constructor(protected activatedRoute: ActivatedRoute, protected http: HttpClient,protected applicationConfigService:ApplicationConfigService) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sanXuatHangNgay }) => {
      this.sanXuatHangNgay = sanXuatHangNgay;
      console.log('sxhn: ',this.sanXuatHangNgay)
    });
    // lay thong tin thong so thiet bi
    if(this.sanXuatHangNgay?.id){
      this.http.get<any>(`${this.resourceUrl}/${this.sanXuatHangNgay.id}`).subscribe(res =>{
        this.chiTietSanXuats = res ;
        console.log("res :", res);
        console.log("chi tiet san xuat :", this.chiTietSanXuats);
      });
    }
  }

  previousState(): void {
    window.history.back();
  }
}
