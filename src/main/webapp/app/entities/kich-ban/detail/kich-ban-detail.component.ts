import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKichBan } from '../kich-ban.model';
import { HttpClient } from '@angular/common/http';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { IChiTietKichBan } from 'app/entities/chi-tiet-kich-ban/chi-tiet-kich-ban.model';

@Component({
  selector: 'jhi-kich-ban-detail',
  templateUrl: './kich-ban-detail.component.html',
})
export class KichBanDetailComponent implements OnInit {
  resourceUrl = this.applicationConfigService.getEndpointFor('api/kich-ban/thong-so-kich-ban');
  predicate!: string;
  ascending!: boolean;
  kichBan: IKichBan | null = null;
  chiTietKichBans: IChiTietKichBan[] | null =[];

  constructor(protected activatedRoute: ActivatedRoute,protected http: HttpClient,protected applicationConfigService:ApplicationConfigService) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kichBan }) => {
      this.kichBan = kichBan;
    });
       // lay thong tin thong so thiet bi
    if(this.kichBan?.id){
      this.http.get<any>(`${this.resourceUrl}/${this.kichBan.id}`).subscribe(res =>{
        this.chiTietKichBans = res ;
        console.log("res :", res);
        console.log("chi tiet kich ban :", this.chiTietKichBans);
      });
    }
  };

  previousState(): void {
    window.history.back();
  }
}

